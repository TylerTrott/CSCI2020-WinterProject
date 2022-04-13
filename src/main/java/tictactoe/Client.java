package tictactoe;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

public class Client {

    // A client has a socket to connect to the server and a reader and writer to
    // receive and send messages respectively.
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String lastMessage;
    public Stack messages;

    public Client(Socket socket) {
        try {
            this.lastMessage = "";
            this.messages = new Stack();
            this.messages.push("test");
            this.socket = socket;
            this.username = "preset name";
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(String toSend) {

        try {

            //System.out.println("got a new message on stack: " + messages.peek());
            bufferedWriter.write(toSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            messages.push(toSend);
            lastMessage = (String) messages.peek();
            System.out.println("Pushed : " + toSend);


        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    // Listening for a message is blocking so need a separate thread for that.
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                System.out.println("Listener check 1");
                // While there is still a connection with the server, continue to listen for
                // messages on a separate thread.
                while (socket.isConnected()) {
                    try {
                        //System.out.println("Listener check 2");
                        while ((msgFromGroupChat = bufferedReader.readLine()) != null) {
                            System.out.println("Listener check 3");
                            if (msgFromGroupChat.length() == 0) {
                                Thread.sleep(100);
                            } else {
                                // Get the messages sent from other users
                                //System.out.println("Getting message");
                                System.out.println("Got message: " + msgFromGroupChat);
                                messages.push(msgFromGroupChat);
                                lastMessage = msgFromGroupChat;

                                // DO the instructions on the message
                            }
                        }

                    } catch (Exception e) {
                        // Close everything gracefully.
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }

                }
                System.out.println("out of listener");
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

    public static void main(String[] args) throws IOException {

        // Get a username for the user and a socket connection.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        // Create a socket to connect to the server.
        Socket socket = new Socket("localhost", 6666);

        Client client = new Client(socket);
        client.messages.push(username);

        // Infinite loop to read and send messages.
        client.listenForMessage();
        System.out.println("listening");

        String message;
        while(true){
            message = scanner.nextLine();
            client.sendMessage(message);
            System.out.println("Current message stack: " + client.messages.toString());
        }
    }
}

