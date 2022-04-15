//package tictactoe;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.awt.*;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.function.Consumer;
//
//public abstract class chat {
//    private ConnectionThread connThread = new ConnectionThread();
//private Consumer<Serializable> onReceiveCallback;
//
//
//
//
//public chat(Consumer <Serializable> onReceiveCallback){
//    this.onReceiveCallback = onReceiveCallback;
//    connThread.setDaemon(true);
//}
//
//    private chatServer createServer(){
//        return new chatServer(55555, data -> {
//            Platform.runLater(() ->{
//                sendTxt.append(data.toString() + "\n");
//            });
//        });
//    }
//
//    private chatClient createClient(){
//        return new chatClient("localhost", 55555, data -> {
//
//        });
//    }
//
//    TextField input = new TextField();
//
//    private boolean isServer = true;
//    Stage chatStage = new Stage();
//
//
//
//    private chat connection = isServer ? createServer() : createClient();
//
//    @FXML
//    private Parent createContent() throws Exception {
//        input.addActionListener(event -> {
//            String message = isServer ? "Server: " : "Client: ";
//            message += input.getText();
//            sendTxt.append(message + "\n");
//            try {
//                connection.send(message);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//        return null;
//    }
//    @FXML
//    public void initializeChat() throws Exception{
//        connection.StartConnection();
//        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/multiTTT.fxml"));
//        chatStage.setTitle("Chat");
//        Group root = new Group(createContent(), fxmlLoader.load());
//        Scene scene = new Scene(root, 300, 425);
//        chatStage.setScene(scene);
//        chatStage.show();
//    }
//
//    public void stop() throws Exception{
//        connection.closeConnection();
//    }
//
//public void StartConnection() throws Exception {
//connThread.start();
//}
//
//public void send(Serializable data) throws Exception {
//connThread.out.writeObject(data);
//}
//
//public void closeConnection() throws Exception {
//connThread.socket.close();
//}
//
//protected abstract boolean isServer();
//protected abstract String getIP();
//protected abstract int getPort();
//
//    public abstract void onSendMsgClick(ActionEvent actionEvent);
//
//    public abstract void onChatReturnClick(ActionEvent actionEvent);
//
//    class ConnectionThread extends Thread {
//    private Socket socket;
//    private ObjectOutputStream out;
//    @Override
//    public void run(){
//        try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
//                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
//                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
//            this.socket = socket;
//            this.out = out;
//            socket.setTcpNoDelay(true);
//            while (true){
//                Serializable data = (Serializable) in.readObject();
//                onReceiveCallback.accept(data);
//            }
//        } catch (Exception e) {
//            onReceiveCallback.accept("Connection Closed");
//        }
//    }
//}
//}
