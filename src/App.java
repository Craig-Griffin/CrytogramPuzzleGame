import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class App {

    private final static String FILENAME = "allplayers.txt";

    
    /*MAIN GAME LOOP*/
    
    public static void main(String[] args) throws IOException {

        login();

    }



    
    /* Some methods to help with the testing of player and players class. Probs can do these better */
    
    public static String promptUser(String message) {

        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String store = sc.nextLine();
        return store;
    }

    public static void login() throws IOException {

        Players allPlayers = new Players();

        System.out.println("Welome to the game!!\n");

        String choice = promptUser("-S to sign up \n-L to login\n\n=> ");

        if (choice.equals("s") || choice.equals("S")) {


            String username = promptUser("**Sign Up** \n enter a username\n\n=> ");
            boolean valid = false;


            while (!valid) {
            	
                Player newPlayer = new Player(username);

                if (allPlayers.validateUserName(newPlayer)) {

                    allPlayers.addPlayer(newPlayer);
                    valid = true;
                    System.out.println("Sign up Succesful\n");

                }
                else{

                  username = promptUser("\n That user name already exists enter another username\n\n=> ");

              }
            }

        } else if (choice.equals("L") || choice.equals("l")) {
            String username = promptUser("**Login** \n Please enter your username\n\n=>");

            if (userNameExists(username)) {

                ArrayList<Integer> playerStats = allPlayers.loadStatsFromFile(username);
                
                
                Player current = new Player(username, playerStats.get(0), playerStats.get(1), playerStats.get(2));
                System.out.println(playerStats.get(2));
                System.out.println(current.getCryptogramsPlayed());
                



                System.out.println("welcome back..." + current.getUsername() 
                + " your current stats are...\n Cryptograms Played - " + current.getCryptogramsPlayed() + 
                "\n Cryptograms Completed - " + current.getCryptogramsCompleted() +
                "\n Cryptograms Accuracy - " + current.getAccuracy());
                		
                

            } else {
                System.out.println("counld not find your username");
            }
        }
    }

    public static boolean userNameExists(String userName) {

        String line = null;
        boolean valid = false;

        try {

            FileReader fileReader = new FileReader(FILENAME);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String username = line.split(" ")[0];

                if (username.equals(userName)) {

                    valid = true;

                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FILENAME + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + FILENAME + "'");


        }
        return valid;

    }

}
