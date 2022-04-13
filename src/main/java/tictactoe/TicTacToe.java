package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class TicTacToe extends Application {
    public static Stage stage;

    /**
     * Starts the application
     * @param stage the stage of the application
     * @throws IOException throws an IOException if the TicTacToeController.fxml file cannot be found
     */
    public void start(Stage stage) throws IOException, NoSuchMethodException {

        // Testing
//        if (Testing.TestController.class.getMethod("checkSignup").equals(true)){
//            System.out.println("Signup test passed");
//        } else {
//            System.out.println("Signup test failed");
//        }
//
//        if (Testing.TestController.class.getMethod("checkLogin").equals(true)) {
//            System.out.println("Login test passed");
//        } else {
//            System.out.println("Login test failed");
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/TicTacToeController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
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