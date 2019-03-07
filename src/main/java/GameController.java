import java.util.Scanner;

public class GameController {

    private GameModel model;
    private GameView display;
    private GenerateCrypto crypto;
    private Player currentPlayer;


    public GameController(MappingType type, Player p){

        display = new GameView();
        model = new GameModel(type);
        currentPlayer = p;

    }

    public boolean isComplete() {
        return model.getSolution().equals(model.getUserInput());
    }

    public void playGame() {

        boolean giveup =  false;

        while(!isComplete()) {

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
                    + "=>");

            while(!validUserPlay) {

                //Enter a Letter
                if (userPlay.equals('e') || userPlay.equals('E')) {
                    Character one = promptForChar("\nEnter the letter you would like map\n=> ");
                    Character two = promptForChar("Enter the letter you would like to map\n=> ");

                    model.mapLetter(one, two);
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                }

                //Remove a Letter
                else if (userPlay.equals('r') || userPlay.equals('R')) {
                    Character one = promptForChar("\nEnter the letter you would like to remove from the puzzle\n=> ");
                    model.removeLetter(one);
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                }

                //Get a Hint
                else if (userPlay.equals('h') || userPlay.equals('H')) {

                    model.giveHint();
                    validUserPlay = true;
                    currentPlayer.incrementGuesses();
                }

                //Get frequency for each letter
                else if (userPlay.equals('f') || userPlay.equals('F')) {
                    model.getFrequencies();
                    validUserPlay = true;
                }

                //Save Game
                else if (userPlay.equals('s') || userPlay.equals('S')) {
                    System.out.println("\nsave\n");
                    model.autocomplete();
                    validUserPlay = true;
                }

                //Give up
                else if (userPlay.equals('g') || userPlay.equals('G')) {
                    System.out.println("\nsave\n");
                    model.autocomplete();
                    validUserPlay = true;
                    giveup = true;
                }

                //Letter that doesnt do anything
                else {
                    userPlay = promptForChar("\nInvalid Choice!! To enter a letter into the Puzzle enter <e>, To remove a letter from the puzzle enter <r>\n=>");
                }
            }
        }

        if(giveup){
            System.out.println("FAIL!! " + currentPlayer.getUsername() + currentPlayer.totalGuesses() +"\n");
        }
        else {
            System.out.println("WINNER!!\n");
        }
        display.displaycryptOrSolution(model.getCrytogram());
        display.displaycryptOrSolution(model.getSolution());
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

}
