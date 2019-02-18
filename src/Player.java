public class Player {

    private String username;
    private int accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;


    /**
     * Player Constructor
     * @param uName
     */
    public Player(String uName){

        username = uName;
        totalGuesses = 0;
        cryptogramsPlayed=0;
        cryptogramsCompleted=0;

    }


    /**
     * Method which will update the players accuracy
     * @param newAccuracy
     */
    public void updateAccuracy(int newAccuracy){

        accuracy = newAccuracy;

    }


    /**
     * Method to increment the number of games played by one
     */
    public void incrementCryptogramsCompleted(){

        cryptogramsCompleted++;
    }


    /**
     * Getter method for accuracy
     * @return accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }


    /**
     * Getter method for for cryptogramsPlayed
     * @return
     */
    public int getCryptogramsPlayed() {
        return cryptogramsPlayed;
    }


    /**
     * Getter Method for cryptogramsCompleted
     * @return cryptogramsCompleted
     */
    public int getCryptogramsCompleted() {
        return cryptogramsCompleted;
    }
}
