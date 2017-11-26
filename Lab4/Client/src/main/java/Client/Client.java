package Client;

import Domain.Stock;
import Requests.BuyRequest;
import Requests.GetStockRequest;
import Responses.BuyResponse;
import Responses.GetStockResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void printMenu(){
        System.out.println("1 - Print available products");
        System.out.println("2 - Buy Product");
        System.out.println("3 - Bye");
    }

    public static void handlePrintResponse(GetStockResponse response){
        for(Stock s : response.getResponse()){
            System.out.println(s);
        }
    }

    public static void handleBuyResponse(BuyResponse response){
        if(response.getOkResponse()){
            System.out.println("Success!");
        }
        else{
            System.out.println("Internal error. Please retry");
        }
    }

    public static BuyRequest Buy(){
        Scanner in = new Scanner(System.in);

        System.out.println("name: ");
        String name = in.next();

        System.out.println("product id: ");
        Integer id = in.nextInt();

        System.out.println("product quantity: ");
        Integer quantity = in.nextInt();

        return new BuyRequest(name, id, quantity);
    }

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 4444);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        Scanner ins = new Scanner(System.in);

        String s = "";
        while(!s.equals("3")){
            printMenu();
            s = ins.next();

            if(s.equals("1")){
                out.writeObject(new GetStockRequest());
                handlePrintResponse((GetStockResponse)in.readObject());
            }
            else if (s.equals("2")){
                out.writeObject(Buy());
                handleBuyResponse((BuyResponse)in.readObject());
            }
            else if (!s.equals("3")){
                System.out.println("Try again...");
            }


            out.reset();
        }

        in.close();
        out.close();
        socket.close();

    }
}
