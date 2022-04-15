package tictactoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class TicTacToe extends Application {
    public static Stage stage;

    /**
     * Starts the application
     * @param stage the stage of the application
     * @throws IOException throws an IOException if the TicTacToeController.fxml file cannot be found
     */

    public void start(Stage stage) throws IOException, NoSuchMethodException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/TicTacToeController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 340);
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Launches the application
     * @param args the arguments of the application
     */
    public static void main(String[] args) {
        launch();
    }
}