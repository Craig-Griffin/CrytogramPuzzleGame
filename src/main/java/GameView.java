import java.util.Scanner;

public class GameView {
    public GameView() {

    }

    /**
     * Method used to display either the crytogram or Solution
     * @param cryptoSolution either a string containing a crytogram or the solution to the crytogram
     */
    public void displaycryptOrSolution(String cryptoSolution) {

        StringBuilder displaycryptOrSolution = new StringBuilder();

        for(int i=0; i<cryptoSolution.length(); i++) {


            if(cryptoSolution.charAt(i) == '.' || cryptoSolution.charAt(i) == ',') {
                displaycryptOrSolution.append(cryptoSolution.charAt(i));
            }
            else if(cryptoSolution.charAt(i) == ' '){
                displaycryptOrSolution.append("     ");
            }
            else {
                displaycryptOrSolution.append("["+ cryptoSolution.charAt(i) + "]");

            }
        }

        System.out.println(displaycryptOrSolution.toString());
    }

    /**
     * Method used to display the users current mapping
     * Similar to the displaycryptOrSolution(String cryptOrSolution) except will handle characters with no mapping
     * @param userInput
     */
    public void displayUserInput(String userInput) {

        StringBuilder displayUserInput = new StringBuilder();


        for(int i=0; i<userInput.length(); i++) {

            if(userInput.charAt(i) == '.' || userInput.charAt(i) == ',') {
                displayUserInput.append(userInput.charAt(i));

            }

            else if(userInput.charAt(i) == ' '){
                displayUserInput.append("     ");
            }
            else {

                if(userInput.charAt(i) == '#') {
                    displayUserInput.append("[ ]");
                }
                else {
                    displayUserInput.append("["+ userInput.charAt(i) + "]");
                }
            }
        }
        System.out.println(displayUserInput.toString());
    }

    /**
     * Helper Method used to get a character from the user.
     * @param message - A message to be passed into the method
     * @return c - the users input
     */
    public Character promptForChar(String message) {

        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        Character c = sc.next(".").charAt(0);

        return c;
    }


}
