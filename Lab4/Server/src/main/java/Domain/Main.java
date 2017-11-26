package Domain;

import Domain.Controller.ProductController;
import Domain.Services.CheckerClass;
import Domain.Services.ProductService;
import Domain.StartServer.Server;


public class Main {
    public static void main(String[] args) throws Exception{

        ProductService p = new ProductService();
        ProductController c = new ProductController(p);
        Server s = new Server();

        // Start checker
        CheckerClass checker = new CheckerClass(c);
        checker.start();

        s.start(4444, c);


        checker.Stop();
        checker.join();
    }
}
