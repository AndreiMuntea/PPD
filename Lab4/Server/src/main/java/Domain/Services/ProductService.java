package Domain.Services;


import Domain.Bill;
import Domain.Product;
import Domain.Stock;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProductService {
    private ReentrantReadWriteLock lock;
    private HashMap<Integer, Stock> stocks;
    private ConcurrentLinkedQueue<Bill> bills;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private Double lastCheckSold;
    private Double currentSold;

    public ProductService(){
        lock = new ReentrantReadWriteLock();
        stocks = new HashMap<>();
        bills = new ConcurrentLinkedQueue<>();
        lastCheckSold = 0.0;
        currentSold = 0.0;

        LoadProducts();
    }

    public void Buy(
            String clientName,
            Integer productId,
            Integer quantity
    ) {
        lock.readLock().lock();

        // Product not found
        if (!stocks.containsKey(productId)){
            lock.readLock().unlock();
            throw new RuntimeException("Product: " + productId + "doesn't exist!");
        }

        synchronized (stocks.get(productId)) {
            Stock s = stocks.get(productId);
            if (s.getQuantity() < quantity){
                lock.readLock().unlock();
                throw new RuntimeException("Quantity too small: " + productId);
            }
            // Update quantity
            s.setQuantity(s.getQuantity() - quantity);

            // Register new bill
            Product p = stocks.get(productId).getProduct();
            Bill bill = new Bill(p.getId(), quantity, quantity * p.getPrice(), clientName);
            bills.add(bill);

            // Update current sold
            currentSold += (quantity * p.getPrice());
        }

        lock.readLock().unlock();
    }

    public void Check(){
        lock.writeLock().lock();

        try(FileWriter fw = new FileWriter("e:\\Projects\\PPD\\PPD\\Lab4\\src\\main\\resources\\log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.write("[ STARTED CHECKING -  " + dateFormat.format(new Date()) + " ]\n\n");

            Double total = CalculateTotalBills();

            out.write("[INFO] Last Sold - " + lastCheckSold + "\n");
            out.write("[INFO] Current Sold - " + currentSold + "\n");
            out.write("[INFO] Bills Total - " + total + "\n");

            Double rt = total + lastCheckSold;

            if( Math.abs(rt - currentSold)  > 0.00001) {
                out.write("[ERROR] Corrupted sold!");
            }
            else {
                out.write("[INFO] BILLS:\n\n");
                // Dump bills
                for (Bill b : bills){
                    out.write(b.toString() + "\n");
                }
            }
            out.write("\n[ FINISHED CHECKING -  " + dateFormat.format(new Date()) + " ]\n\n");

            // Free bills
            lastCheckSold = currentSold;
            bills.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

        lock.writeLock().unlock();
    }

    public List<Stock> GetStock(){
        lock.readLock().lock();

        ArrayList<Stock> availableProducts = new ArrayList<>();
        for(Integer id : stocks.keySet()){
            synchronized (stocks.get(id)) {
                availableProducts.add(stocks.get(id));
            }
        }

        lock.readLock().unlock();
        return availableProducts;
    }

    private Double CalculateTotalBills(){
        Double total = 0.0;
        for(Bill b : bills) {
            total += b.getTotal();
        }

        return total;
    }

    private void LoadProducts(){
        try(BufferedReader br = new BufferedReader(new FileReader("e:\\Projects\\PPD\\PPD\\Lab4\\src\\main\\resources\\products.txt"))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] parts = line.split(",");

                Product p = new Product(parts[1],parts[2],Double.parseDouble(parts[3]), Integer.parseInt(parts[0]));
                Stock s = new Stock(p, Integer.parseInt(parts[4]));
                stocks.put(p.getId(), s);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
