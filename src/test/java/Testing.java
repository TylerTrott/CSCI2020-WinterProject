import static org.junit.Assert.*;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeController;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Testing extends TicTacToeController {
    /**
     * Tests the signup functionality.
      * @throws NoSuchFieldException throws NoSuchFieldException if the field "username", or "password" does not exist
     * @throws IllegalAccessException throws IllegalAccessException if the field "username", or "password" is not accessible
     * @throws IOException throws IOException if the file "accounts.csv" cannot be read.
     * @throws NoSuchMethodException throws NoSuchMethodException if the method "OnSubmitClick" does not exist
     */
    @Test
    public void testSignup() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException {

        // reading the accounts.csv file to ensure that the username and password "unitTest" are not in the file
        checkAccountsFileAndRemove("unitTest", "unitTest");

        // setting the username and password fields within the signup class to "unitTest"
        // TODO: change the username and password fields within the signup class to "unitTest"
        TicTacToeController.class.getMethod("onSubmitClick");

        // if the onSubmitClick method is successful, then the username and password "unitTest" have been added to the accounts.csv file, and checkAccountsFile() returns true.
        // If this is the case, the test is successful.
        System.out.print("SIGNUP SUCCESS!"); // ! DEBUGGING !
        assertTrue(checkAccountsFileAndRemove("unitTest", "unitTest"));
    }

    /**
     * Tests the login functionality.
     * @throws IOException throws IOException if the file "accounts.csv" cannot be read.
     * @throws NoSuchMethodException throws NoSuchMethodException if the method "OnSubmitClick" does not exist
     */
    @Test
    public void testLogin() throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        TicTacToeController ticTacToeController = new TicTacToeController();
        // reading the accounts.csv file to see if the username and password "unitTest" are in the file
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/accounts.csv"));
        String line;
        boolean valueFound = false; // boolean value to determine if the username and password "unitTest" are in the file
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[0].equals("unitTest") && values[1].equals("unitTest")) {
                valueFound = true;
                break;
            }
        } if (!valueFound) { // if the username and password "unitTest" are not in the file, write them to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/accounts.csv", true));
            bw.newLine();
            bw.write("unitTest,unitTest");
            bw.close();
        }

        // TODO: change the username and password fields within the login class to "unitTest"
        TicTacToeController.class.getMethod("onLoginClick");

        // if the user is logged in, then the test is successful
        System.out.print("LOGIN SUCCESS!"); // ! DEBUGGING !
        assertEquals(true, TicTacToeController.class.getMethod("getLoggedIn").equals(true));
    }

    /**
     * Checks if the defined username and password are in the accounts.csv file. If they are, then this method removes them from the file.
     * Note that this method is a utility method used by the testSignup() method.
     * @throws IOException throws IOException if the file "accounts.csv" cannot be found.
     */
    private boolean checkAccountsFileAndRemove(String username, String password) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/accounts.csv"));
        String line;
        boolean occurs = false; // if the username and password "unitTest" are in the file, then occurs will be true
        ArrayList<String> accountData = new ArrayList<>(); // arraylist to store the username and password that are being read from the file
        int lineNumber = 0; // store the line number where the username and password "unitTest" are located
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            // if the username and password "unitTest" are in the file, then occurs will be true, and the line number will be stored
            if (values[0].equals(username) && values[1].equals(password)) {
                occurs = true;
                lineNumber = lineNumber + 1;
            } else if (!occurs) { // if the username and password "unitTest" have not been found, increment the line number
                accountData.add(line);
                lineNumber++; // incrementing the line number
            }
        }
        br.close(); // closing the file

        if (occurs) { // if the username and password "unitTest" have been found, then remove the username and password "unitTest" from the file
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/newaccounts.csv"));
            for (int i = 0; i < accountData.size(); i++) {
                if (i != lineNumber) {
                    bw.write(accountData.get(i));
                }
            }
            bw.close(); // closing the file
            File oldFile = new File("src/main/resources/accounts.csv");
            oldFile.delete(); // deleting the old file
            File newFile = new File("src/main/resources/newaccounts.csv");
            newFile.renameTo(oldFile); // renaming the new file to the old file

            return true; // if the username and password "unitTest" have been found, then return true
        } else {
            return false; // if the username and password "unitTest" have not been found, then return false
        }
    }

    // checks the game logic
//    @Test
//    public boolean checkLogic() {
//
//    }
}
