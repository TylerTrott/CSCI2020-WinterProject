package tictactoe;



import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;


public class Server {


    public static void main(String[] args){
        ServerSocket serve = null;
        try {
            serve = new ServerSocket(6666); //0 -> lets your OS select a port; port > 1024
            serve.setReuseAddress(true);
            System.out.println("Starting server...");
            System.out.println("Waiting for client connection...");

            while(true){
                Socket sock = serve.accept();
                System.out.println("Client is connected " + sock.getInetAddress().getHostAddress()); //this will display the host address of client
                ClientHandler client = new ClientHandler(sock);
                new Thread(client).start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try {
            serve.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}




