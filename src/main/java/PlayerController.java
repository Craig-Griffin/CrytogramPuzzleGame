import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerController {

    private final static String FILE_ALLPLAYERS = "allplayers.txt";

    private GameView display = new GameView();



    public String promptUser(String message) {

        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String store = sc.nextLine();
        return store;
    }



    public Player login() throws IOException {

        Players allPlayers = new Players();

        File file = new File(FILE_ALLPLAYERS);

        if (!file.exists()) {
            file.createNewFile();
        }

        display.printLogo();

        System.out.println("Welome to the game!!\n");

        String choice = promptUser("-S to sign up \n-L to login\n-R to remove player\n=> ");

        if (choice.equals("s") || choice.equals("S")) {


            String username = promptUser("**Sign Up** \n enter a username\n\n=> ");
            boolean valid = false;


            while (!valid) {

                Player newPlayer = new Player(username);

                if (allPlayers.validateUserName(newPlayer)) {

                    allPlayers.addPlayer(newPlayer);
                    valid = true;
                    System.out.println("Sign up Succesful\n");
                    return newPlayer;

                }
                else{

                  username = promptUser("\n That user name already exists enter another username\n\n=> ");

              }
            }

        } else if (choice.equals("L") || choice.equals("l")) {
            String username = promptUser("**Login** \n Please enter your username\n\n=> ");

            if (userNameExists(username)) {

                ArrayList<Integer> playerStats = allPlayers.loadStatsFromFile(username);


                Player current = new Player(username, playerStats.get(0), playerStats.get(1), playerStats.get(2));
                System.out.println(playerStats.get(2));
                System.out.println(current.getCryptogramsPlayed());


                System.out.println("welcome back..." + current.getUsername()
                        + " your current stats are...\n Cryptograms Played - " + current.getCryptogramsPlayed() +
                        "\n Cryptograms Completed - " + current.getCryptogramsCompleted() +
                        "\n Cryptograms Accuracy - " + current.getAccuracy());


                return current;


            } else {
                System.out.println("could not find your username");
            }
        } else if (choice.equals("R")) {
            String usernameToRemove = promptUser("**Removing** \n Enter player username to remove\n\n=> ");
            if (userNameExists(usernameToRemove)) {
                allPlayers.removePlayer(usernameToRemove);
            }
            else {
                System.out.println("User does not exist");
            }
        }

        return null;
    }

    public static boolean userNameExists(String userName) {

        String line = null;
        boolean valid = false;

        try {

            FileReader fileReader = new FileReader(FILE_ALLPLAYERS);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String username = line.split(" ")[0];

                if (username.equals(userName)) {

                    valid = true;

                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FILE_ALLPLAYERS + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + FILE_ALLPLAYERS + "'");


        }
        return valid;

    }





}
