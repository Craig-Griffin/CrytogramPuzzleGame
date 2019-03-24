/**
 * Class representing a player of all the game, and all their assotiated stats.
 *
 * NOTE: To allow proper sorting of the leaderboard, Player objects use the accurracy field for natural ordering. At
 * this time, the class does not provide an implementation of equals(), however if it did in the future, it would be
 * highly likely that a.compareTo(b) would be DIFFERENT from a.equals(b), since different players could be tied on
 * the leaderboard.
 */
public class Player implements Comparable<Player>{

    private String username;
    private double accuracy;

    private int cryptogramsPlayed;
    private int cryptogramsCompleted;


    private int totalGuesses;
    private int correctGuesses;

    /**
     * Constructor for a new player (i.e. there is no data saved on them yet)
     */
    public Player(String uName) {
        username = uName;
        totalGuesses = 0;
        cryptogramsPlayed = 0;
        cryptogramsCompleted = 0;

    }

    /**
     * Constructor for a returning player who will already have statistics
     */
    public Player(String uName, int totPlayed, int totCompleted, int totGuess, int totCorrect) {
        username = uName;

        cryptogramsPlayed = totPlayed;
        cryptogramsCompleted = totCompleted;

        totalGuesses = totGuess;
        correctGuesses = totCorrect;

        accuracy = getAccuracy();
    }

    /**
     * Setter Method for accuracy
     */
    public void updateAccuracy() {
        if (totalGuesses != 0) {
            accuracy = ((double) correctGuesses / (double) totalGuesses)*100;
        }
    }

    /**
     * Increment the number of games played by one
     */
    public void incrementCryptogramsCompleted() {
        cryptogramsCompleted++;
    }

    public void incrementGuesses() {
        totalGuesses++;
    }

    public void incrementPlayed() {
        cryptogramsPlayed++;
    }

    /**
     * Getter Method for accuracy
     */
    public double getAccuracy() {
        updateAccuracy();
        accuracy = Math.round(accuracy * 100.0) / 100.0;
        return accuracy;
    }

    /**
     * Getter method for username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method for getCryptogramsPlayed
     */
    public int getCryptogramsPlayed() {
        return cryptogramsPlayed;
    }

    /**
     * Getter method for cryptogramsCompleted
     */
    public int getCryptogramsCompleted() {
        return cryptogramsCompleted;
    }

    /**
     * Getter method for totalGuesses
     */
    public int totalGuesses() {
        return totalGuesses;
    }

    public void incrementCorrectGuesses() {
        correctGuesses++;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    /**
     * Implementation of compareTo to enable sorting of the leaderboard. Note that this method returning 0 does NOT
     * imply that the two players are the same, only that they have the same accuracy.
     *
     * @param p Other player to compare to
     * @return  +ve if this player has a greater accurracy than p
     *          =0 if this player has an equal accurracy to p
     *          -ve if this player has a lower accurracy than p
     */
    @Override
    public int compareTo(Player p) {
        double accuP = p.getAccuracy();

        return (int) Math.round(accuP - accuracy);
    }
}