package Client;

import Domain.Stock;
import Requests.BuyRequest;
import Requests.GetStockRequest;
import Responses.GetStockResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class DummyClient {

    static Random rn = new Random();
    static List<Stock> allStocks;

    public static BuyRequest CreateRequest(){
        Integer id = rn.nextInt(allStocks.size());
        Integer quantity = rn.nextInt(6) + 1;

        return new BuyRequest("DummyClient",id, quantity);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 4444);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject(new GetStockRequest());

        allStocks = ((GetStockResponse)in.readObject()).getResponse();
        while(true){
            try {
                out.writeObject(CreateRequest());
                in.readObject();
                Thread.sleep(1500);
            }catch (Exception ignored){
                break;
            }
        }
        in.close();
        out.close();
        socket.close();
    }
}
