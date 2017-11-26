package Domain.StartServer;

import Domain.Controller.ProductController;
import Domain.Stock;
import Requests.BuyRequest;
import Requests.GetStockRequest;
import Responses.BuyResponse;
import Responses.GetStockResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private ProductController productController;

    public void start(int port, ProductController productController) throws IOException {
        serverSocket = new ServerSocket(port);
        this.productController = productController;
        executorService = Executors.newFixedThreadPool(10);

        while (true)
            executorService.submit(new EchoClientHandler(serverSocket.accept(), productController));
    }

    public void stop() throws IOException {
        executorService.shutdownNow();
        this.productController.Stop();
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private ProductController productController;

        public EchoClientHandler(Socket socket, ProductController productController) {
            this.clientSocket = socket;
            this.productController = productController;
        }

        public void run() {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                while(true){
                    Object request = in.readObject();
                    if (request instanceof GetStockRequest){
                        out.writeObject(HandleGetStock());
                    }
                    else if (request instanceof BuyRequest){
                        out.writeObject(HandleBuyRequest((BuyRequest)request));
                    }
                    else break;

                    out.reset();
                }

                in.close();
                out.close();
                this.clientSocket.close();

            } catch (Exception e) {
                //Todo
            }
        }

        public GetStockResponse HandleGetStock() throws ExecutionException, InterruptedException {
            Future<List<Stock>> stock = productController.GetStock();
            return new GetStockResponse(stock.get());
        }

        public BuyResponse HandleBuyRequest(BuyRequest request) throws ExecutionException, InterruptedException {
            Future<Boolean> buy = productController.Buy(request.getClientName(), request.getProductId(), request.getQuantity());
            return new BuyResponse(buy.get());
        }

    }
}
