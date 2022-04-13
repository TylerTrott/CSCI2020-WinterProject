package tictactoe;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;


public class Client {

    // A client has a socket to connect to the server and a reader and writer to receive and send messages respectively.
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    public static String lastMessage;
    public Stack messages;

    public Client(Socket socket) {
        try {
            this.lastMessage = "";
            this.messages = new Stack();
            this.messages.push("Constructor init");
            this.socket = socket;
            this.username = "preset name";
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void setLastMessage(String newMessage) {
        lastMessage = newMessage;
    }

    public void clearMessageStack(){
        this.messages.clear();
    }

    public void sendMessage() {
        new Thread(new Runnable() {

            @Override
            public void run(){

                try {
                    //Send username to stream for clienthandler to identify
                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    boolean testCondition = false;
                    while (socket.isConnected()) {

                        //Check if there are any new messages
                        if(!(Client.lastMessage).equals((String)messages.peek())){
                            System.out.println("New message on stack: " + messages.peek());

                            testCondition = (Client.lastMessage).equals((String)messages.peek());
                            //write new message to outputstream
                            //all other clients will then receive message
                            bufferedWriter.write((String)messages.peek());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            setLastMessage((String)messages.peek());
                            System.out.println("Last message: " + Client.lastMessage);

                            //DO the instructions on the message
                        }
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    // Gracefully close everything.
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();

    }

    // Listening for a message is blocking so need a separate thread for that.
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromApp;
                // While there is still a connection with the server, continue to listen for messages on a separate thread.
                while (socket.isConnected()) {
                    try {

                        while ((msgFromApp = bufferedReader.readLine()) != null) {

                            if(msgFromApp.length() == 0){
                                Thread.sleep(100);
                            }
                            else {
                                // Get the messages sent from other users and
                                System.out.println("Received message: " + msgFromApp);
                                messages.push(msgFromApp);
                                Client.lastMessage = msgFromApp;

                                //DO the instructions on the message
                            }
                        }



                    } catch(Exception e){
                        // Close everything gracefully.
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }

                }
            }
        }).start();
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) throws IOException {
//
//        // Get a username for the user and a socket connection.
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your username for the group chat: ");
//        String username = scanner.nextLine();
//        // Create a socket to connect to the server.
//        Socket socket = new Socket("localhost", 6666);
//
//
//        Client client = new Client(socket);
//        client.messages.push(username);
//
//        // Infinite loop to read and send messages.
//        client.listenForMessage();
//        System.out.println("listening");
//
//        client.sendMessage();
//        System.out.println("sending");
//    }
}
