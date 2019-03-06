public class GameView {



    GameView(){

    }


    public void displaycryptOrSolution(String cryptOrSolution) {

        StringBuilder displaycryptOrSolution = new StringBuilder();

        for(int i=0; i<cryptOrSolution.length(); i++) {


            if(cryptOrSolution.charAt(i) == '.' || cryptOrSolution.charAt(i) == ','|| cryptOrSolution.charAt(i) == '\'') {
                displaycryptOrSolution.append(cryptOrSolution.charAt(i));
            }
            else if(cryptOrSolution.charAt(i) == ' '){
                displaycryptOrSolution.append("     ");
            }
            else {
                displaycryptOrSolution.append("["+ cryptOrSolution.charAt(i) + "]");

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

            if(userInput.charAt(i) == '.' || userInput.charAt(i) == ',' || userInput.charAt(i) == '\'') {
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

}