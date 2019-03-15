public class Player {

	private String username;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

	private int totalGuesses;



	private int correctGuesses;

	private int accuracy;

	/**
	 * Constructor for a new player (i.e. there is no data saved on them yet)
	 */
	public Player(String uName) {

		username = uName;
		cryptogramsPlayed = 0;
		cryptogramsCompleted = 0;

		totalGuesses = 1;
		correctGuesses=0;
		accuracy = 0;

	}

	/**
	 * Constructor for a returning player who will already have statistics 
	 */
	public Player(String uName, int totPlayed, int totCompleted, int totG, int correct) {

		username = uName;

		cryptogramsPlayed = totPlayed;
		cryptogramsCompleted = totCompleted;

		totalGuesses = totG;
		correctGuesses = correct;


		accuracy = correctGuesses/totalGuesses;

	}

	/**
	 * Setter Method for accuracy
	 */
	public void updateAccuracy() {

		accuracy = correctGuesses/totalGuesses;

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

	public void incrementCorrectGuesses(){

		correctGuesses++;
	}

    public void incrementPlayed() {

        cryptogramsPlayed++;
    }

	/**
	 * Getter Method for accuracy
	 */
	public int getAccuracy() {

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
	public int getTotalGuesses() {
		return totalGuesses;
	}

	public int getCorrectGuesses() {
		return correctGuesses;
	}

	public void setGuesses(int x){
		totalGuesses = x;
	}
}