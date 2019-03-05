public class GameController {
    private final Game model;
    private final GameView view;

    public GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Game loop
     */
    public void playGame() {
        while(!model.isComplete()) {

            view.displaycryptOrSolution(model.getCryptogram().getClearText());
            //view.displayUserInput(getUserInput());

            boolean validUserPlay = false;

            Character userPlay = view.promptForChar("\n**User Options**\n"
                    + "To enter a letter into the Puzzle enter      <e>\n"
                    +"To remove a letter from the puzzle enter     <r>\n"
                    + "To get a hint enter                          <h>\n"
                    + "To get the frequencys of each letter enter   <f>\n"
                    + "To save your progress in this puzzle enter   <s>\n"
                    + "=>");

            while(!validUserPlay) {

                //Enter a Letter
                if(userPlay.equals('e') ||userPlay.equals('E')) {
                    Character one = view.promptForChar("\nEnter the letter you would like map\n=> ");
                    Character two = view.promptForChar("Enter the letter you would like to map\n=> ");

                    model.mapLetter(one,two);
                    validUserPlay = true;
                }

                //Remove a Letter
                else if(userPlay.equals('r') ||userPlay.equals('R')) {
                    Character one = view.promptForChar("\nEnter the letter you would like to remove from the puzzle\n=> ");
                    model.removeLetter(one);
                    validUserPlay = true;
                }

                //Get a Hint
                else if(userPlay.equals('h') ||userPlay.equals('H')) {
                    System.out.println("\nhint\n");
                    validUserPlay = true;
                }

                //Get frequency for each letter
                else if(userPlay.equals('f') ||userPlay.equals('F')) {
                    model.getFrequencies();
                    validUserPlay = true;
                }

                //Save Game
                else if(userPlay.equals('s') ||userPlay.equals('S')) {
                    System.out.println("\nsave\n");
                    validUserPlay = true;
                }

                //Letter that doesnt do anything
                else {
                    userPlay = view.promptForChar("\nInvalid Choice!! To enter a letter into the Puzzle enter <e>, To remove a letter from the puzzle enter <r>");
                }
            }

            //Clear the terminal to improve the visuals -- still needs some work
            //clearScreen();


        }
        System.out.println("WINNER!!");
    }
}
