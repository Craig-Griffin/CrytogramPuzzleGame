import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private GameModel model;
    private GameView display;
    private Player currentPlayer;


    private final String FILE_ALLPLAYERS = "allplayers.txt";


    public GameController(MappingType type, Player p){

        display = new GameView();
        model = new GameModel(type);
        currentPlayer = p;

    }

    public GameController(MappingType type, Player p, String sol, String userin, String crpt, OneToOneMap<Character,Character> solMap,OneToOneMap<Character,Character>  curMap ){

        display = new GameView();
        model = new GameModel(type, sol, userin, crpt, solMap, curMap);
        currentPlayer = p;

    }

    public boolean isComplete() {
        return model.getSolution().equals(model.getUserInput());
    }

    public void playGame() throws IOException {


        boolean giveup =  false;
        String oldStats = "";
        String newStats = "";
        int countGuesses=0;

        while(!isComplete()) {

            oldStats= currentPlayer.getUsername() + " " + currentPlayer.getCryptogramsPlayed() + " " + currentPlayer.getCryptogramsCompleted() + " " + currentPlayer.getTotalGuesses()+ " " + currentPlayer.getCorrectGuesses();



            display.displaycryptOrSolution(model.getCrytogram());
            display.displayUserInput(model.getUserInput());

            boolean validUserPlay = false;

            Character userPlay = promptForChar("\n**User Options**\n"
                    + "To enter a letter into the Puzzle enter      <e>\n"
                    + "To remove a letter from the puzzle enter     <r>\n"
                    + "To get a hint enter                          <h>\n"
                    + "To get the frequencys of each letter enter   <f>\n"
                    + "To save your progress in this puzzle enter   <s>\n"
                    + "To Give up enter                             <g>\n"
                    + "To Exit the game                             <q>\n"
                    + "=>");

            while(!validUserPlay) {

                //Enter a Letter
                if (userPlay.equals('e') || userPlay.equals('E')) {
                    Character one = promptForChar("\nEnter the letter you would like map\n=> ");
                    Character two = promptForChar("Enter the letter you would like to map\n=> ");

                    model.mapLetter(one, two);
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                    countGuesses++;

                    if(model.checkForCorrectMove(one,two)){
                        currentPlayer.incrementCorrectGuesses();
                        System.out.println("Correct!!");
                    }

                }


                //Remove a Letter
                else if (userPlay.equals('r') || userPlay.equals('R')) {
                    Character one = promptForChar("\nEnter the letter you would like to remove from the puzzle\n=> ");
                    model.removeLetter(one);
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                    countGuesses++;
                }


                //Get a Hint
                else if (userPlay.equals('h') || userPlay.equals('H')) {

                    model.giveHint();
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                    countGuesses++;
                }


                //Get frequency for each letter
                else if (userPlay.equals('f') || userPlay.equals('F')) {
                    model.getFrequencies();
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                    countGuesses++;
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
                    model.autocomplete();
                    validUserPlay = true;
                    giveup = true;
                }


                //Quit
                else if (userPlay.equals('q') || userPlay.equals('Q')){
                    Character save = promptForChar("Would you like to Save?\n" +
                                                   "Yes    <y>\n" +
                                                   "No     <n>\n" +
                                                   "=>");


                    if(save.equals('y') || save.equals('Y')){
                        model.saveToDisk(currentPlayer);
                        System.out.println("\n Your game has been save. See you next time!");
                        System.exit(0);
                    }
                    else if(save.equals('n') || save.equals('N')){
                        System.out.println("\n See you next time!\n");
                        System.exit(0);
                    }
                    else{
                        System.out.println("\nPlease enter a valid option\n");
                    }




                }


                //Letter that doesnt do anything
                else {
                    userPlay = promptForChar("\nInvalid Choice!! Try another option...\n=>");
                }
            }
        }

        if(giveup){
            System.out.println("FAIL!! " + currentPlayer.getUsername() + currentPlayer.getTotalGuesses() +"\n");
            currentPlayer.incrementPlayed();


        }
        else {
            System.out.println("WINNER!!\n");
            currentPlayer.incrementCryptogramsCompleted();
            currentPlayer.incrementPlayed();
        }

        //Update player stats
        currentPlayer.updateAccuracy();
        newStats= currentPlayer.getUsername() +" "+ currentPlayer.getCryptogramsPlayed()+ " "+currentPlayer.getCryptogramsCompleted() + " " + currentPlayer.getTotalGuesses()+ " " + currentPlayer.getCorrectGuesses();


        updateStats(currentPlayer,oldStats,newStats);



        display.displaycryptOrSolution(model.getCrytogram());
        display.displaycryptOrSolution(model.getSolution());
        System.out.println();
    }

    /**
     * Helper Method used to get a character from the user.
     * @param message - A message to be passed into the method
     * @return c - the users input
     */
    public Character promptForChar(String message) {

        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        Character c = sc.next().charAt(0);

        return c;
    }

    /**
     * Get the details of a player which will be used to generate an object
     */
        public void updateStats(Player p,String oldStats, String newStats) {
            try {
                // input the file content to the StringBuffer "input"
                BufferedReader file = new BufferedReader(new FileReader(FILE_ALLPLAYERS));
                String line;


                ArrayList<String> temp = new ArrayList<>();

                while ((line = file.readLine()) != null) {
                    temp.add(line);
                }


                file.close();

                for(int i=0; i<temp.size(); i++){

                   String[] hold = temp.get(i).split(" ", 2);
                   String username = hold[0];

                    if (username.equals(p.getUsername())){
                        temp.set(i,newStats);
                    }
                }


                String newText = temp.toString();
                newText = newText.substring(1, newText.length()-1);
                String[] data = newText.split(", ");


                String forFile="";

               for(int x=0; x<data.length; x++){
                   forFile = forFile + data[x] + "\n";
               }


                FileOutputStream fileOut = new FileOutputStream(FILE_ALLPLAYERS);
                fileOut.write(forFile.getBytes());
                fileOut.close();

            } catch (Exception e) {
                System.out.println("Problem reading file.");
            }
        }




}
