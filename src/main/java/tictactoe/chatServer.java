package tictactoe;

import javafx.event.ActionEvent;

import java.io.Serializable;
import java.util.function.Consumer;

public class chatServer extends chat {

    private int port;

    public chatServer(int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return true;
    }

    @Override
    protected String getIP() {
        return null;
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
