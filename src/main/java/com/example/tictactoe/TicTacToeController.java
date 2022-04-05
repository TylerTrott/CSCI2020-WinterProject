package com.example.tictactoe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    Button firstLoginBtn;

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
    Button signUpBtn;

    @FXML
    Button returnToMain;


    /**
     * TicTacToe SCREEN COMPONENTS
     */

    @FXML
    Label playerLabel;

    @FXML
    Button startGameBtn;

    /**
     * GAME LOGIC COMPONENTS
     */

    boolean player1Turn = false;
    String PLAYER_ONE_SYMBOL = "X";
    String PLAYER_TWO_SYMBOL = "O";

    @FXML
    protected void onLoginButtonClick() throws IOException {
        /**
         * Display textField for username
         * Display TextField for password
         */
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("login.fxml"));
        LoginStage.setTitle("Login for TicTacToe");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) firstLoginBtn.getScene().getWindow();
        stage.hide();
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
                Scene scene = new Scene(fxmlLoader.load(), 400, 400);
                TTTStage.setScene(scene);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.hide();
                TTTStage.show();
                break;
            }
        }
        if (!success) {
            System.out.println("Something went wrong");
        }
    }



    @FXML
    protected void onLoginReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("TicTacToeController.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TTTStage.setScene(scene);
        TTTStage.show();

    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("signup.fxml"));
        SignUpStage.setTitle("Sign Up");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) signUpBtn.getScene().getWindow();
        stage.hide();

        SignUpStage.setScene(scene);
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
        } catch (Error e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Something went wrong. \nPlease try again");
            a.show();
        }
        if (success) {
            System.out.println("Login successful");
            FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("tictactoe.fxml"));
            TTTStage.setTitle("TicTacToe");
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            TTTStage.setScene(scene);
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            stage.hide();
            TTTStage.show();
        }
    }
    @FXML
    protected void onSignupReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("TicTacToeController.fxml"));
        Stage stage = (Stage) returnToMain.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TTTStage.setScene(scene);
        TTTStage.show();

    }



    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) MainMenuExitBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * GAME LOGIC
     */

    @FXML
    Button Btn11;
    @FXML
    Button Btn12;
    @FXML
    Button Btn13;
    @FXML
    Button Btn21;
    @FXML
    Button Btn22;
    @FXML
    Button Btn23;
    @FXML
    Button Btn31;
    @FXML
    Button Btn32;
    @FXML
    Button Btn33;
    boolean gameOver = false;

    @FXML
    protected void onBtn11Click(){
        if (player1Turn) {
            Btn11.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn11.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn12Click(){
        if (player1Turn) {
            Btn12.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn12.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn13Click(){
        if (player1Turn) {
            Btn13.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn13.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    @FXML
    protected void onBtn21Click(){
        if (player1Turn) {
            Btn21.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn21.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn22Click(){
        if (player1Turn) {
            Btn22.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn22.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn23Click(){
        if (player1Turn) {
            Btn23.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn23.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    @FXML
    protected void onBtn31Click(){
        if (player1Turn) {
            Btn31.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn31.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn32Click(){
        if (player1Turn) {
            Btn32.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn32.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }
    @FXML
    protected void onBtn33Click(){
        if (player1Turn) {
            Btn33.setText(PLAYER_ONE_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            Btn33.setText(PLAYER_TWO_SYMBOL);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }

        }
    }

    @FXML
    protected void onStartGameClick() {
        startGameBtn.setVisible(false);
        playerLabel.setText("Player 1 Turn");
        System.out.println("Start Game");
        /**
         * Enable buttons
         * Will be utilized better when
         * after having established
         * connection to server
         */
        gameOver = false;
        enableBtns();
        startNewGame();
    }

    public void enableBtns(){
        Btn11.setDisable(false);
        Btn12.setDisable(false);
        Btn13.setDisable(false);
        Btn21.setDisable(false);
        Btn22.setDisable(false);
        Btn23.setDisable(false);
        Btn31.setDisable(false);
        Btn32.setDisable(false);
        Btn33.setDisable(false);
    }

    public void disableBtns(){
        Btn11.setDisable(true);
        Btn12.setDisable(true);
        Btn13.setDisable(true);
        Btn21.setDisable(true);
        Btn22.setDisable(true);
        Btn23.setDisable(true);
        Btn31.setDisable(true);
        Btn32.setDisable(true);
        Btn33.setDisable(true);
        startGameBtn.setVisible(true);
    }


    public void changePlayerTurn() {
        if (player1Turn) {
            // set for player 2 turn
            player1Turn = false;
            playerLabel.setText("PLAYER 2 TURN");
        } else {
            // set back to player 1 turn
            player1Turn = true;
            playerLabel.setText("PLAYER 1 TURN");
        }
    }

    @FXML
    protected void startNewGame() {
        player1Turn = true;
        clearBoard();

        if (player1Turn) {
            playerLabel.setText("Player 1's Turn");
            playerLabel.setVisible(true);
        } else {
            playerLabel.setText("Player 2's Turn");
            playerLabel.setVisible(true);
        }
    }

    private void clearBoard()
    {
        Btn11.setText("");
        Btn12.setText("");
        Btn13.setText("");
        Btn21.setText("");
        Btn22.setText("");
        Btn23.setText("");
        Btn31.setText("");
        Btn32.setText("");
        Btn33.setText("");
    }

    public void checkIfGameIsOver() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> Btn11.getText() + Btn12.getText() + Btn13.getText();
                case 1 -> Btn21.getText() + Btn22.getText() + Btn23.getText();
                case 2 -> Btn31.getText() + Btn32.getText() + Btn33.getText();
                case 3 -> Btn11.getText() + Btn22.getText() + Btn33.getText();
                case 4 -> Btn13.getText() + Btn22.getText() + Btn31.getText();
                case 5 -> Btn11.getText() + Btn21.getText() + Btn31.getText();
                case 6 -> Btn12.getText() + Btn22.getText() + Btn32.getText();
                case 7 -> Btn13.getText() + Btn23.getText() + Btn33.getText();
                default -> null;
            };

            //X winner
            if (line.equals(PLAYER_ONE_SYMBOL+PLAYER_ONE_SYMBOL+PLAYER_ONE_SYMBOL)) {
                playerLabel.setText("Player 1 won!");
                gameOver = true;
                disableBtns();
            }

            //O winner
            else if (line.equals(PLAYER_TWO_SYMBOL+PLAYER_TWO_SYMBOL+PLAYER_TWO_SYMBOL)) {
                playerLabel.setText("Player 2 won!");
                gameOver = true;
                disableBtns();
            }
            // tie game
            else if (!Btn11.getText().equals("") &&
                    !Btn12.getText().equals("") &&
                    !Btn13.getText().equals("") &&
                    !Btn21.getText().equals("") &&
                    !Btn22.getText().equals("") &&
                    !Btn23.getText().equals("") &&
                    !Btn31.getText().equals("") &&
                    !Btn32.getText().equals("") &&
                    !Btn33.getText().equals(""))
            {
                playerLabel.setText("TIE GAME!");
                gameOver = true;
                disableBtns();

            }


        }
    }
}