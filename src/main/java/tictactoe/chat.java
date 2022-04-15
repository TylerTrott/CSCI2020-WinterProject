package tictactoe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class chat {
    private ConnectionThread connThread = new ConnectionThread();
private Consumer<Serializable> onReceiveCallback;

public chat(Consumer <Serializable> onReceiveCallback){
    this.onReceiveCallback = onReceiveCallback;
    connThread.setDaemon(true);
}

    private chatServer createServer(){
        return new chatServer(55555, data -> {
            Platform.runLater(() ->{
                sendText.append(data.toString() + "\n");
            });
        });
    }

    private chatClient createClient(){
        return new chatClient("127.0.0.1", 55555, data -> {

        });
    }

    @FXML
    TextArea sendText;

    TextField input = new TextField();

    private boolean isServer = true;
    Stage chatStage = new Stage();



    private chat connection = isServer ? createServer() : createClient();

    private Parent createContent() throws Exception {
        init();
        input.addActionListener(event -> {
            String message = isServer ? "Server: " : "Client: ";
            message += input.getText();
            sendText.append(message + "\n");
            try {
                connection.send(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }
    public void init() throws Exception{
        connection.StartConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/multiTTT.fxml"));
        chatStage.setTitle("Chat");
        //Scene scene = new Scene(fxmlLoader.load(), 300, 425);
        chatStage.setScene(new Scene(createContent()));
        chatStage.show();
    }

    public void stop() throws Exception{
        connection.closeConnection();
    }

public void StartConnection() throws Exception {
connThread.start();
}

public void send(Serializable data) throws Exception {
connThread.out.writeObject(data);
}

public void closeConnection() throws Exception {
connThread.socket.close();
}

protected abstract boolean isServer();
protected abstract String getIP();
protected abstract int getPort();

    public abstract void onSendMsgClick(ActionEvent actionEvent);

    public abstract void onChatReturnClick(ActionEvent actionEvent);

    private class ConnectionThread extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    @Override
    public void run(){
        try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            this.socket = socket;
            this.out = out;
            socket.setTcpNoDelay(true);
            while (true){
                Serializable data = (Serializable) in.readObject();
                onReceiveCallback.accept(data);
            }
        } catch (Exception e) {
            onReceiveCallback.accept("Connection Closed");
        }
    }
}
}
