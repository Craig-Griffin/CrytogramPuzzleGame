import misc.MappingType;
import misc.OneToOneMap;
import misc.FileHandler;
import misc.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameController {
    private GameModel model;
    private GameView display;
    private Player currentPlayer;
    private FileHandler fileHandler;


    /*Game Controller Constructor. If playing a new game*/
    public GameController(MappingType type, Player p) {
        fileHandler = new FileHandler();
        display = new GameView();
        model = new GameModel(type, fileHandler);
        currentPlayer = p;
    }


    /*Game Controller Constructor. If loading a saved game*/
    public GameController(MappingType type, Player p, String sol, String userin, String crpt, OneToOneMap < Character, Character > solMap, OneToOneMap < Character, Character > curMap) {
        display = new GameView();
        model = new GameModel(type, sol, userin, crpt, solMap, curMap);
        currentPlayer = p;

    }


    /*Getter Method for GameModel. Used in Testing*/
    public GameModel getModel() {
        return model;
    }


    /*Helper Method -- Check to make sure UserInput is equal to the Solution*/
    public boolean isComplete() {
        return model.getSolution().equals(model.getUserInput());
    }


    /*Main Game Loop. A user will play complete the cryptogram puzzle here*/
    public void playGame() throws IOException {
        boolean giveup = false;
        String newStats = "";

        while (!isComplete()) {

            display.displaycryptOrSolution(model.getCrytogram());
            display.displayUserInput(model.getUserInput());

            boolean validUserPlay = false;

            Character userPlay = promptForChar("\n**User Options**\n" +
                    "To enter a letter into the Puzzle enter      <e>\n" +
                    "To remove a letter from the puzzle enter     <r>\n" +
                    "To get a hint enter                          <h>\n" +
                    "To get the frequencys of each letter enter   <f>\n" +
                    "To save your progress in this puzzle enter   <s>\n" +
                    "To Give up enter                             <g>\n" +
                    "To Exit the game                             <q>\n" +
                    "=>");

            while (!validUserPlay) {

                //Enter a Letter
                if (userPlay.equals('e') || userPlay.equals('E')) {
                    Character one = promptForChar("\nEnter the letter you would like map\n=> ");
                    Character two = promptForChar("Enter the letter you would like to map\n=> ");

                    model.mapLetter(one, two, currentPlayer);

                    if (model.checkIfRight(one, two)) {
                        currentPlayer.incrementCorrectGuesses();
                        System.out.println("\nCorrect\n");
                    }
                    validUserPlay = true;

                }

                //Remove a Letter
                else if (userPlay.equals('r') || userPlay.equals('R')) {
                    Character one = promptForChar("\nEnter the letter you would like to remove from the puzzle\n=> ");
                    model.removeLetter(one);
                    validUserPlay = true;
                    model.addGuess(currentPlayer);
                }

                //Get a Hint
                else if (userPlay.equals('h') || userPlay.equals('H')) {

                    model.giveHint(currentPlayer);
                    validUserPlay = true;
                    model.addGuess(currentPlayer);
                }

                //Get frequency for each letter
                else if (userPlay.equals('f') || userPlay.equals('F')) {
                    HashMap < Character, String > cryptoFreq = model.getFrequencies(model.getCrytogram());
                    HashMap < Character, String > englishFreq = new HashMap < > ();

                    System.out.println("\nLetter Frequency in Cryptogram: " + cryptoFreq.toString());
                    System.out.println("Average Engligh Letter Frequencies: " + Util.getEnglishLetterFreqs().toString());
                    validUserPlay = true;
                    model.addGuess(currentPlayer);
                }

                //Save Game
                else if (userPlay.equals('s') || userPlay.equals('S')) {
                    System.out.println("\nsave\n");
                    model.saveToDisk(currentPlayer);
                    validUserPlay = true;
                }

                //Give up
                else if (userPlay.equals('g') || userPlay.equals('G')) {
                    System.out.println("\nsave\n");
                    model.autocomplete(currentPlayer);
                    validUserPlay = true;
                    giveup = true;
                }

                //Quit
                else if (userPlay.equals('q') || userPlay.equals('Q')) {
                    Character save = promptForChar("Would you like to Save?\n" +
                            "Yes    <y>\n" +
                            "No     <n>\n" +
                            "=>");

                    if (save.equals('y') || save.equals('Y')) {
                        model.saveToDisk(currentPlayer);
                        System.out.println("\n Your game has been save. See you next time!");
                        System.exit(0);

                    } else if (save.equals('n') || save.equals('N')) {
                        model.quitWithoutSave(currentPlayer);
                        System.out.println("\n See you next time!\n");
                        System.exit(0);

                    } else {
                        System.out.println("\nPlease enter a valid option\n");
                    }

                  //An invalid option has been given
                } else {
                    userPlay = promptForChar("\nInvalid Choice!! To enter a letter into the Puzzle enter <e>, To remove a letter from the puzzle enter <r>\n=>");
                }

                model.addGuess(currentPlayer);
            }
        }

        //Has the give up feature been used?
        if (giveup) {
            System.out.println("FAIL! Better luck next time " + currentPlayer.getUsername() + "\n");
        } else {
            System.out.println("WINNER!! Congratulations " + currentPlayer.getUsername() + "\n");
            currentPlayer.incrementCryptogramsCompleted();
        }

        //Update player stats
        model.updatePlayerAccuracy(currentPlayer);

        newStats = currentPlayer.getUsername() + " " + currentPlayer.getCryptogramsPlayed() + " " + currentPlayer.getCryptogramsCompleted() + " " + currentPlayer.getTotalGuesses() + " " + currentPlayer.getCorrectGuesses();
        updateStats(currentPlayer, newStats);

        display.displaycryptOrSolution(model.getCrytogram());
        display.displaycryptOrSolution(model.getSolution());
        System.out.println();
    }


    /* Helper Method used to get a character from the user.*/
    public Character promptForChar(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next().charAt(0);
    }


    /* Get the details of a player which will be used to generate an object*/
    public void updateStats(Player p, String newStats) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(fileHandler.getPlayersFile()));
            String line;
            ArrayList < String > temp = new ArrayList < > ();

            //Put each line from the file into the ArrayList
            while ((line = file.readLine()) != null) {
                temp.add(line);
            }
            file.close();

            //Remove ArrayList features and then find the right user to update.
            for (int i = 0; i < temp.size(); i++) {

                String[] hold = temp.get(i).split(" ", 2);
                String username = hold[0];

                if (username.equals(p.getUsername())) {
                    temp.set(i, newStats);
                }
            }

            //Convert to String and remove ArrayList features
            String newText = temp.toString();
            newText = newText.substring(1, newText.length() - 1);
            String[] data = newText.split(", ");


            //Rewrite data to the file
            String forFile = "";
            for (int x = 0; x < data.length; x++) {
                forFile = forFile + data[x] + "\n";
            }

            FileOutputStream fileOut = new FileOutputStream(fileHandler.getPlayersFile());
            fileOut.write(forFile.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }


}