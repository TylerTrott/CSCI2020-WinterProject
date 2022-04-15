package tictactoe;

import javafx.event.ActionEvent;

import java.io.Serializable;
import java.util.function.Consumer;

public class chatClient extends TicTacToeController{
private String ip;
private int port;

    public chatClient(String ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public void onSendMsgClick(ActionEvent actionEvent) {

    }

    @Override
    public void onChatReturnClick(ActionEvent actionEvent) {

    }
}
