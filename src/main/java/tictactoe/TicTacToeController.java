package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class TicTacToeController {
    /**
     * The stages used for the various menus of the application.
     */
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
    public TextField username;

    @FXML
    public TextField password;

    @FXML
    Button firstLoginBtn;

    boolean loggedIn = false; // used to keep track of the login status

    // getter for loggedIn
    public boolean getLoggedIn() {return loggedIn;}

    /**
     * SIGN UP SCREEN COMPONENTS
     */
    @FXML
    Button submitBtn;

    @FXML
    public TextField signUpusername;

    @FXML
    public TextField signUppassword;

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

    @FXML
    Button backToMenu;


    /**
     * GAME LOGIC COMPONENTS
     */
    boolean player1Turn = false;
    String PLAYER_ONE_SYMBOL = "X";
    String PLAYER_TWO_SYMBOL = "O";

    /**
     * Displays the login screen. This screen is used to log in to the game, not sign up.
     * @throws IOException an IOException is thrown if the login.fxml file cannot be found.
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
//      Display textField for username
//      Display TextField for password
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));

        LoginStage.setTitle("Login for TicTacToe");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) firstLoginBtn.getScene().getWindow();
        stage.hide();
        LoginStage.setScene(scene);
        LoginStage.show();
    }

    /**
     * References the accounts.csv file and checks if the username and password are valid.
     * @throws IOException an IOException is thrown if the accounts.csv file cannot be found and/or the tictactoe.fxml file cannot be found.
     */
    @FXML
    protected void onLoginClick() throws IOException {
        String line;
        boolean success = false;
        BufferedReader accountReader;
        String[] accountsArr;
        accountReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/accounts.csv")));
        for (line = accountReader.readLine(); line != null; line = accountReader.readLine()) {
            accountsArr = line.split(",");
            if (accountsArr.length > 0 && accountsArr[0].equals(username.getText()) && accountsArr[1].equals(password.getText())) {
                System.out.println("Login successful"); // Prints to console that login was successful

                success = true; // If login is successful, set success to true
                loggedIn = true; // Set loggedIn to true

                FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/tictactoe.fxml"));
                TTTStage.setTitle("TicTacToe");
                Scene scene = new Scene(fxmlLoader.load(), 400, 400);
                TTTStage.setScene(scene);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.hide();
                TTTStage.show();
                break;
            }
        }
        if (!success) { // Creates an error dialogue if login fails
            System.out.println("Login failed."); // Prints to console

            // outputs an error dialogue
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Login. Check your username and password and try again");
            a.show();
        }
    }

    /**
     * Returns to the main menu from the login screen when the "Return to Menu" button is clicked.
     * @throws IOException an IOException is thrown if the TicTacToeController.fxml file cannot be found.
     */
    @FXML
    protected void onLoginReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/TicTacToeController.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TTTStage.setScene(scene);
        TTTStage.show();
    }

    /**
     * Displays the sign-up screen of the application. It takes the username and password and stores it into a csv file.
     * @throws IOException an IOException is thrown if the signup.fxml file cannot be found.
     */
    @FXML
    protected void onSignUpButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/signup.fxml"));
        SignUpStage.setTitle("Sign Up for TicTacToe");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) signUpBtn.getScene().getWindow();
        stage.hide();

        SignUpStage.setScene(scene);
        SignUpStage.show();
    }

    /**
     * Writes the username and password to the accounts.csv file. If the accounts.csv file does not exist, it creates it.
     * If the username and password are valid, the game screen is displayed.
     * @throws IOException an IOException is thrown if the accounts.csv cannot be found.
     */
    @FXML
    protected void onSubmitClick() throws IOException {
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
            System.out.println("Could not write to file."); // Prints to console

            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Could not write to file.");
            a.show();
        }
        if (success) {
            System.out.println("Login successful"); // Prints to console
            loggedIn = true; // Set loggedIn to true

            // outputs the game screen
            FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/tictactoe.fxml"));
            TTTStage.setTitle("TicTacToe");
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            TTTStage.setScene(scene);
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            stage.hide();
            TTTStage.show();
        }
    }

    /**
     * Returns to the main menu from the sign-up screen when the "Return to Menu" button is clicked.
     * @throws IOException an IOException is thrown if the TicTacToeController.fxml file cannot be found
     */
    @FXML
    protected void onSignupReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/TicTacToeController.fxml"));
        Stage stage = (Stage) returnToMain.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TTTStage.setScene(scene);
        TTTStage.show();
    }

    /**
     * Closes the application when the "Exit" button is clicked.
     */
    @FXML
    protected void onExitButtonClick() {
        loggedIn = false; // game has ended; not logged in

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

    // used to clean background of buttons
    @FXML
    Button defaultBttn;

    boolean gameOver = false;

    /**
     * Button 1,1 (row 1, column 1)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn11Click(){
        if (player1Turn) {
            setCatImage(Btn11);
            Btn11.setText(PLAYER_ONE_SYMBOL);
            Btn11.setTextFill(Color.TRANSPARENT);

            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn11);
            Btn11.setText(PLAYER_TWO_SYMBOL);
            Btn11.setTextFill(Color.TRANSPARENT);

            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 1,2 (row 1, column 2)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn12Click(){
        if (player1Turn) {

            setCatImage(Btn12);
            Btn12.setText(PLAYER_ONE_SYMBOL);
            Btn12.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn12);
            Btn12.setText(PLAYER_TWO_SYMBOL);
            Btn12.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 1,3 (row 1, column 3)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn13Click(){
        if (player1Turn) {

            setCatImage(Btn13);
            Btn13.setText(PLAYER_ONE_SYMBOL);
            Btn13.setTextFill(Color.TRANSPARENT);

            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        } else {

            setDogImage(Btn13);
            Btn13.setText(PLAYER_TWO_SYMBOL);
            Btn13.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 2,1 (row 2, column 1)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn21Click(){
        if (player1Turn) {
            setCatImage(Btn21);
            Btn21.setText(PLAYER_ONE_SYMBOL);
            Btn21.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn21);
            Btn21.setText(PLAYER_TWO_SYMBOL);
            Btn21.setTextFill(Color.TRANSPARENT);

            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 2,2 (row 2, column 2)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn22Click(){
        if (player1Turn) {

            setCatImage(Btn22);
            Btn22.setText(PLAYER_ONE_SYMBOL);
            Btn22.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {

            setDogImage(Btn22);
            Btn22.setText(PLAYER_TWO_SYMBOL);
            Btn22.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 2,3 (row 2, column 3)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn23Click(){
        if (player1Turn) {

            setCatImage(Btn23);
            Btn23.setText(PLAYER_ONE_SYMBOL);
            Btn23.setTextFill(Color.TRANSPARENT);

            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn23);
            Btn23.setText(PLAYER_TWO_SYMBOL);
            Btn23.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 3,1 (row 3, column 1)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn31Click(){
        if (player1Turn) {
            setCatImage(Btn31);
            Btn31.setText(PLAYER_ONE_SYMBOL);

        } else {
            setDogImage(Btn31);
            Btn31.setText(PLAYER_TWO_SYMBOL);

        }
        Btn31.setTextFill(Color.TRANSPARENT);
        checkIfGameIsOver();
        if (!gameOver) {
            changePlayerTurn();
        }
    }

    /**
     * Button 3,2 (row 3, column 2)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn32Click(){
        if (player1Turn) {
            setCatImage(Btn32);
            Btn32.setText(PLAYER_ONE_SYMBOL);
            Btn32.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn32);
            Btn32.setText(PLAYER_TWO_SYMBOL);
            Btn32.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * Button 3,3 (row 3, column 3)
     * This method is called when the user clicks on a tile on the tic-tac-toe board.
     * On click, the tile is changed to an image of a cat or dog (depending on whose turn it is).
     * It also checks to see if the game is over. If not, it switches the turn.
     */
    @FXML
    protected void onBtn33Click(){
        if (player1Turn) {
            setCatImage(Btn33);
            Btn33.setText(PLAYER_ONE_SYMBOL);
            Btn33.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();

            if (!gameOver) {
                changePlayerTurn();
            }
        } else {
            setDogImage(Btn33);
            Btn33.setText(PLAYER_TWO_SYMBOL);
            Btn33.setTextFill(Color.TRANSPARENT);
            checkIfGameIsOver();
            if (!gameOver) {
                changePlayerTurn();
            }
        }
    }

    /**
     * This method is called when the user begins a new game (clicks the "Start Game" button).
     * It makes the board visible, and hides the back to menu, and start game buttons.
     * It also enables the game buttons, so the user can click on them.
     */
    @FXML
    protected void onStartGameClick() {
        startGameBtn.setVisible(false);
        backToMenu.setVisible(false);
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

    /**
     * This method is called when the user clicks the "Return to Menu" button.
     * @param actionEvent The action that triggers the method.
     * @throws IOException Throws an IOException if the FXML file cannot be found.
     */
    @FXML
    protected void onBackToMenuClick(ActionEvent actionEvent) throws IOException {
        loggedIn = false; // back to menu; not logged in

        backToMenu.setVisible(false);
        System.out.println("Back to Menu");
        gameOver = false;

        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/TicTacToeController.fxml"));
        Stage stage = (Stage) backToMenu.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TTTStage.setScene(scene);
        TTTStage.show();
    }

    /**
     * This method enables the game buttons.
     */
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

    /**
     * This method disables the game buttons.
     */
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
        backToMenu.setVisible(true);
    }

    /**
     * This method changes the player turn depending on whose turn it is currently.
     */
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

    /**
     * This method initializes the game. It clears the board, and sets the turn to player 1.
     */
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

    /**
     * This method clears the board. It clears the background of the buttons, and clear the text on the buttons.
     */
    private void clearBoard()
    {

        Btn11.setText("");
        setButtonBackground(Btn11);
        Btn12.setText("");
        setButtonBackground(Btn12);
        Btn13.setText("");
        setButtonBackground(Btn13);
        Btn21.setText("");
        setButtonBackground(Btn21);
        Btn22.setText("");
        setButtonBackground(Btn22);
        Btn23.setText("");
        setButtonBackground(Btn23);
        Btn31.setText("");
        setButtonBackground(Btn31);
        Btn32.setText("");
        setButtonBackground(Btn32);
        Btn33.setText("");
        setButtonBackground(Btn33);
    }

    /**
     * This method contains most of the logic for the game. It contains the logic for the winning conditions for both players,
     * and the logic for a tie. If a player wins, the winning player is displayed, the game is set to over, and the buttons are disabled.
     * If there is a tie, the game is set to over, and the buttons are disabled.
     */
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

    /**
     * This method changes the background to an image of a cat.
     * @param bttn the button whose background is to be changed
     */
    private void setCatImage(Button bttn)
    {
        Image image = new Image("/images/cat.png",bttn.getWidth(),
                bttn.getHeight(), false, true, true);

        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(bttn.getWidth(), bttn.getHeight(), true,
                        true, true, false));

        Background backGround = new Background(bImage);
        bttn.setBackground(backGround);

    }

    /**
     * This method changes the background to an image of a dog.
     * @param bttn the button whose background is to be changed
     */
    private void setDogImage(Button bttn)
    {
        Image image = new Image("/images/dog.png",bttn.getWidth(),
                bttn.getHeight(), false, true, true);

        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(bttn.getWidth(), bttn.getHeight(), true,
                        true, true, false));

        Background backGround = new Background(bImage);
        bttn.setBackground(backGround);
    }

    /**
     * This method clears the background of a button and replaces it with a blank space.
     * @param bttn the button whose background is to be changed
     */
    private void setButtonBackground(Button bttn)
    {
        Image image = new Image("/images/background.png",bttn.getWidth(),
                bttn.getHeight(), false, true, true);

        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(bttn.getWidth(), bttn.getHeight(), true,
                        true, true, false));

        Background backGround = new Background(bImage);
        bttn.setBackground(backGround);
    }
}