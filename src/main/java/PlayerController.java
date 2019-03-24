import misc.MappingType;
import misc.OneToOneMap;
import misc.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerController {
    private GameView display = new GameView();
    private Players allPlayers;
    private Player currentPlayer;
    private FileHandler fileHandler;


    public PlayerController() throws IOException {
        fileHandler = new FileHandler();
        allPlayers = new Players(fileHandler);
    }

    public String promptUser(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    public Player login() throws IOException {
        display.printLogo();
        System.out.println("**Welome To The Game!**\n");

        boolean validInput = false;

        while (!validInput) {
            String choice = promptUser("Sign Up     <s> \n" +
                    "Login       <l>\n" +
                    "Help        <h>\n" +
                    "Exit        <q>\n" +
                    "\n=> ");

            if (choice.toUpperCase().equals("S")) {
                String username = promptUser("**Sign Up** \n enter a username\n\n=> ");
                boolean valid = false;

                while (!valid) {
                    Player newPlayer = new Player(username);

                    if (allPlayers.validateUserName(newPlayer)) {
                        allPlayers.addPlayer(newPlayer);
                        System.out.println("Sign up Succesful\n");
                        display.clearScreen();
                        currentPlayer = newPlayer;
                        return newPlayer;

                    } else {
                        username = promptUser("\n That user name already exists enter another username\n\n=> ");

                    }
                }

            } else if (choice.toUpperCase().equals("L")) {
                String username = promptUser("\n\n**Login** \n Please enter your username\n=> ");

                if (userNameExists(username)) {
                    ArrayList<Integer> playerStats = allPlayers.loadStatsFromFile(username);

                    Player current = new Player(username, playerStats.get(0), playerStats.get(1), playerStats.get(2), playerStats.get(3));

                    currentPlayer = current;
                    return current;

                } else {
                    System.out.println("\nError!! Could not find your username!\n");
                }

            } else if (choice.toUpperCase().equals("H")) {
                System.out.println("\n**Help**\nuse the keyboard to select <s> to sign if you are a new player and use <l> if you are a returning player\n");

            } else if (choice.toUpperCase().equals("Q")) {
                System.exit(0);

            } else {
                System.out.println("\n**Error!! Please enter <s>, <l>, <h> or <q> ** \n\n");
            }
        }

        return null;
    }

    public boolean userNameExists(String userName) {
        String line;
        boolean valid = false;

        try {
            FileReader fileReader = new FileReader(fileHandler.getPlayersFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String username = line.split(" ")[0];

                if (username.equals(userName)) {
                    valid = true;
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        }

        return valid;

    }


    public void menu() throws IOException {
        System.out.println("\n**Hi " + currentPlayer.getUsername() + "**\n");

        boolean done = false;

        while (!done) {
            String choice = promptUser("New Game                  <n>\n" +
                    "Load Game                 <l>\n" +
                    "View Stats                <s>\n" +
                    "View Leader Board         <b>\n" +
                    "Remove Account            <r>\n" +
                    "Go Back                   <q>\n" +
                    "\n=> ");

            //New Game
            if (choice.equals("n") || choice.equals("N")) {
                GameController game = new GameController(MappingType.LETTERS, currentPlayer);
                game.playGame();
            }


            //Load Game
            if (choice.equals("l") || choice.equals("L")) {
                File f = new File(currentPlayer.getUsername() + "_save.txt");

                if (!f.exists()) {
                    System.out.println("\nYou do not have a saved game!! Starting a new game\n");

                    GameController game = new GameController(MappingType.LETTERS, currentPlayer);
                    game.playGame();

                } else {
                    GameController game = loadGame(currentPlayer);
                    game.playGame();
                }
            }

            //View Stats
            if (choice.equals("s") || choice.equals("S")) {
                System.out.println("\n" + currentPlayer.getUsername()
                        + " your current stats are...\n\nCryptograms Played - " + currentPlayer.getCryptogramsPlayed() +
                        "\nCryptograms Completed - " + currentPlayer.getCryptogramsCompleted() +
                        "\nCryptograms Accuracy - " + currentPlayer.getAccuracy() + "%\n");
            }

            //Leader Board
            if (choice.equals("b") || choice.equals("B")) {
                List<Player> leaderboard = allPlayers.getTopTen();

                int pos = 1;


                System.out.println("\n**LeaderBoard**\npos\tUsername\tAccuracy");
                for (int i = leaderboard.size() - 1; i > 0; i--) {

                    System.out.println(pos + ": " + leaderboard.get(i).getUsername() + "\t" + leaderboard.get(i).getAccuracy()+"%");
                    pos++;
                }

                System.out.println();
            }


            //Remove a Player
            if (choice.equals("r") || choice.equals("R")) {
                String usernameToRemove = promptUser("**Removing** \n Enter player username to remove\n\n=> ");
                if (userNameExists(usernameToRemove)) {
                    allPlayers.removePlayer(usernameToRemove);
                    System.out.println(usernameToRemove + " removed\n");
                } else {
                    System.out.println("User does not exist\n");
                }

                if (usernameToRemove.equals(currentPlayer.getUsername())) {
                    login();
                }
            }

            //Quit Game
            if (choice.equals("q") || choice.equals("Q")) {
                login();
            }
        }


    }

    public OneToOneMap<Character, Character> convertToOneToOneMap(String input) {

        OneToOneMap<Character, Character> map = new OneToOneMap<>();

        String value = input;
        value = value.substring(1, value.length() - 1);
        String[] keyValuePairs = value.split(", ");


        for (String pair : keyValuePairs) {

            String[] entry = pair.split("=");
            char[] key = entry[0].toCharArray();
            char[] val = entry[1].toCharArray();

            map.put(key[0], val[0]);
        }

        return map;

    }

    GameController loadGame(Player currentP) {
        ArrayList<String> components = new ArrayList<>();
        String line = null;

        try {
            FileReader fileReader = new FileReader(currentP.getUsername() + "_save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                components.add(line);
            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        }

        String solution = components.get(0);
        String userInput = components.get(1);
        String cryptogram = components.get(2);
        OneToOneMap<Character, Character> solutionMapping = convertToOneToOneMap(components.get(3));
        OneToOneMap<Character, Character> currentMapping = convertToOneToOneMap(components.get(4));

        return new GameController(MappingType.LETTERS, currentPlayer, solution, userInput, cryptogram, solutionMapping, currentMapping);
    }


}
