package tictactoe;


import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    private static class ClientHandler implements Runnable{

        private final Socket clientSock;

        public ClientHandler(Socket socket){
            clientSock = socket;
        }

        public void run(){

            BufferedReader inStream = null;
            PrintWriter outStream = null;
            try {
                inStream = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                outStream = new PrintWriter(clientSock.getOutputStream(), true);
                String message;
                while ((message = inStream.readLine()) != null){
                    System.out.println( "Sent from client: " + message);
                    outStream.write(message);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    inStream.close();
                    clientSock.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args){
        ServerSocket serve = null;
        try {
            serve = new ServerSocket(6666); //0 -> lets your OS select a port; port > 1024
            serve.setReuseAddress(true);
            System.out.println("Starting server...");
            System.out.println("Waiting for client connection...");
//            serve.accept();
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

