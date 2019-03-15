
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerController {

    private final static String FILE_ALLPLAYERS = "allplayers.txt";

    private GameView display = new GameView();
    private Players allPlayers;
    private Player currentPlayer;


    public PlayerController() throws IOException{
        allPlayers = new Players();


        File file = new File(FILE_ALLPLAYERS);
        if (!file.exists()) {
            file.createNewFile();
        }
    }



    public String promptUser(String message) {

        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String store = sc.nextLine();
        return store;
    }



    public Player login() throws IOException {


        display.printLogo();
        System.out.println("**Welome To The Game!**\n");
        boolean validInput = false;

        while(!validInput) {


            String choice = promptUser("Sign Up     <s> \n" +
                                       "Login       <l>\n" +
                                       "Help        <h>\n" +
                                       "Exit        <q>\n" +
                                       "\n=> ");

            if (choice.equals("s") || choice.equals("S")) {


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

            } else if (choice.equals("L") || choice.equals("l")) {
                String username = promptUser("\n\n**Login** \n Please enter your username\n=> ");

                if (userNameExists(username)) {

                    ArrayList<Integer> playerStats = allPlayers.loadStatsFromFile(username);


                    Player current = new Player(username, playerStats.get(0), playerStats.get(1), playerStats.get(2),playerStats.get(3));

                    currentPlayer = current;
                    return current;


                } else {
                    System.out.println("\nError!! Could not find your username!\n");
                }


            } else if(choice.equals("h") || choice.equals("H")){
                System.out.println("\n**Help**\nuse the keyboard to select <s> to sign if you are a new player and use <l> if you are a returning player\n");
            }
            else if(choice.equals("q") || choice.equals("Q")){
                System.exit(0);

            }
            else {


                System.out.println("\n**Error!! Please enter <s>, <l>, <h> or <q> ** \n\n");
            }
        }

        return null;
    }

    public boolean userNameExists(String userName) {

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


    public void menu() throws IOException {


        System.out.println("\n**Hi " + currentPlayer.getUsername() + "**\n");

        boolean done = false;

        while (!done) {


            String choice = promptUser("New Game                  <n>\n" +
                    "Load Game                 <l>\n" +
                    "View Stats                <s>\n" +
                    "View Leader Board         <b>\n" +
                    "remove Account            <r>\n" +
                    "Go Back                   <q>\n"+
                    "\n=> ");


            //New Game
            if(choice.equals("n")|| choice.equals("N")){

                GameController game = new GameController(MappingType.LETTERS, currentPlayer);
                game.playGame();



            }


            //Load Game
            if(choice.equals("l")|| choice.equals("L")){

                File f = new File(currentPlayer.getUsername()+"_save.txt");

                if(!f.exists()){
                    System.out.println("\nYou do not have a saved game!! Starting a new game\n");

                    GameController game = new GameController(MappingType.LETTERS, currentPlayer);
                    game.playGame();

                }
                else{
                    ArrayList<String> components = new ArrayList<>();
                    String line = null;

                    try {

                        FileReader fileReader = new FileReader(currentPlayer.getUsername()+"_save.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        while ((line = bufferedReader.readLine()) != null) {
                           components.add(line);

                        }

                        bufferedReader.close();

                    } catch (FileNotFoundException ex) {
                        System.out.println("Unable to open file '" + FILE_ALLPLAYERS + "'");
                    } catch (IOException ex) {
                        System.out.println("Error reading file '" + FILE_ALLPLAYERS + "'");

                    }

                    String solution = components.get(0);
                    String userInput = components.get(1);
                    String cryptogram=components.get(2);
                    OneToOneMap<Character,Character> solutionMapping =  convertToOneToOneMap(components.get(3));
                    OneToOneMap<Character,Character> currentMapping =  convertToOneToOneMap(components.get(4));

                    GameController game = new GameController(MappingType.LETTERS, currentPlayer, solution, userInput, cryptogram, solutionMapping, currentMapping);
                    game.playGame();




                }


            }


            //View Stats
            if(choice.equals("s")|| choice.equals("S")){

                System.out.println("\n"+currentPlayer.getUsername()
                        + " your current stats are...\n\nCryptograms Played - " + currentPlayer.getCryptogramsPlayed() +
                        "\nCryptograms Completed - " + currentPlayer.getCryptogramsCompleted() +
                        "\nCryptograms Accuracy - " + currentPlayer.getAccuracy()+"\n" +
                        "tests"+ currentPlayer.getCorrectGuesses()+ " "+ currentPlayer.getTotalGuesses()+"\n");
            }


            //Leader Board
            if(choice.equals("b")|| choice.equals("B")){

                System.out.println("View Leader Board");
            }


            //Remove a Player
            if(choice.equals("r")|| choice.equals("R")){

                String usernameToRemove = promptUser("**Removing** \n Enter player username to remove\n\n=> ");
                if (userNameExists(usernameToRemove)) {
                    allPlayers.removePlayer(usernameToRemove);
                }
                else {
                    System.out.println("User does not exist");
                }

                if(usernameToRemove.equals(currentPlayer.getUsername())){
                    login();
                }
            }


            //Quit Game
            if(choice.equals("q")|| choice.equals("Q")){

               login();
            }
        }



    }

    public OneToOneMap<Character,Character> convertToOneToOneMap (String input){

        OneToOneMap<Character,Character>  map = new  OneToOneMap<>();

        String value = input;
        value = value.substring(1, value.length()-1);
        String[] keyValuePairs = value.split(", ");



        for(String pair: keyValuePairs){

            String[] entry = pair.split("=");
            char[] key = entry[0].toCharArray();
            char[] val = entry[1].toCharArray();

            map.put(key[0],val[0]);
        }

        return map;

    }





}
