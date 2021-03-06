package Ipwl.Server;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by lenovo on 2017/7/19.
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request req;
    private Response rep;
    private int code = 200;

    @Override
    public void run() {
        Serverlet serv = new Serverlet();
        serv.service(req,rep);
        rep.pushTOCLient(code);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Dispatcher(Socket client) {
        this.client = client;
        try {
            req = new Request(client.getInputStream());
            rep = new Response(client.getOutputStream());
        } catch (IOException e) {
            code = 500;
            return;
        }
    }
}
