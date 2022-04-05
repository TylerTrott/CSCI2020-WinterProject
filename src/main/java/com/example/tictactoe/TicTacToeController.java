package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;


public class TicTacToeController {
    Stage LoginStage = new Stage();
    Stage TTTStage = new Stage();
    Stage SignUpStage = new Stage();

    /**
     * LOGIN SCREEN COMPONENTS
     */
    @FXML
    Button MainMenuExitBtn;

    @FXML
    Button loginBtn;

    @FXML
    TextField username;

    @FXML
    TextField password;

    /**
     * SIGN UP SCREEN COMPONENTS
     */
    @FXML
    Button submitBtn;

    @FXML
    TextField signUpusername;

    @FXML
    TextField signUppassword;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        /**
         * Display textField for username
         * Display TextField for password
         */
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("login.fxml"));
        LoginStage.setTitle("Login for TicTacToe");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        LoginStage.setScene(scene);
        LoginStage.show();
    }

    @FXML
    protected void onLoginClick() throws IOException {

        /** TODO:
         * Create CSV file that contains two columns
         * uName and pWord
         * Read through file and see if
         * Uname and pWord match on any line
         */
        String line;
        boolean success = false;
        BufferedReader accountReader = null;
        String[] accountsArr = null;
        accountReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/accounts.csv")));
        for (line = accountReader.readLine(); line != null; line = accountReader.readLine()) {
            accountsArr = line.split(",");
            if (accountsArr[0].equals(username.getText()) && accountsArr[1].equals(password.getText())) {
                System.out.println("Login successful");
                success = true;
                FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("tictactoe.fxml"));
                TTTStage.setTitle("TicTacToe");
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                TTTStage.setScene(scene);
                LoginStage.close();
                TTTStage.show();
                break;
            }
        }
        if (!success){
            System.out.println("Something went wrong");
        }
}

    @FXML
    protected void onLoginReturnClick() throws IOException {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("signup.fxml"));
        SignUpStage.setTitle("Sign Up");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        SignUpStage.setScene(scene);
        LoginStage.close();
        SignUpStage.show();
    }

    @FXML
    protected void onSubmitClick() throws IOException {
        /**
         * TODO:
         * Write username and password provided
         * to accounts.csv file
         */
        boolean success = false;
        try {
            FileWriter pw = new FileWriter("src/main/resources/accounts.csv", true);
            pw.append(signUpusername.getText());
            pw.append(",");
            pw.append(signUppassword.getText());
            pw.append("\n");
            success = true;
            pw.flush();
            pw.close();
        } catch(Error e) {
            Alert a  = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Something went wrong. \nPlease try again");
            a.show();
        }
        if (success){
            System.out.println("Login successful");
            FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("tictactoe.fxml"));
            TTTStage.setTitle("TicTacToe");
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            TTTStage.setScene(scene);
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            stage.close();
            TTTStage.show();
        }
    }

    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) MainMenuExitBtn.getScene().getWindow();
        stage.close();
    }


}