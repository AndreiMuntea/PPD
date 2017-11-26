package Domain.Controller;

import Domain.Services.ProductService;
import Domain.Stock;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProductController {
    private ProductService productService;
    private ExecutorService executorService;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void Stop(){
        executorService.shutdownNow();
    }

    public Future<Void> Check(){
        Callable<Void> worker = new CheckCallback();
        return executorService.submit(worker);
    }

    public Future<Boolean> Buy(
            String clientName,
            Integer productId,
            Integer quantity
    ){
        Callable<Boolean> worker = new BuyCallback(clientName, productId, quantity);
        return executorService.submit(worker);
    }

    public Future<List<Stock>> GetStock(){
        Callable<List<Stock>> worker = new GetStockCallback();
        return executorService.submit(worker);
    }

    private class CheckCallback implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            productService.Check();
            return null;
        }
    }

    private class BuyCallback implements Callable<Boolean> {
        private String clientName;
        private Integer productId;
        private Integer quantity;

        public BuyCallback(String clientName, Integer productId, Integer quantity) {
            this.clientName = clientName;
            this.productId = productId;
            this.quantity = quantity;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                productService.Buy(clientName, productId, quantity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private class GetStockCallback implements Callable<List<Stock>>{
        @Override
        public List<Stock> call() throws Exception {
            return productService.GetStock();
        }
    }

}
