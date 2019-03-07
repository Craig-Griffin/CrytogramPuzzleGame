public class Player {

	private String username;
	private int accuracy;
	private int totalGuesses;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

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
	public Player(String uName, int totPlayed, int totCompleted, int acc) {

		username = uName;
		totalGuesses = 0;
		cryptogramsPlayed = totPlayed;
		cryptogramsCompleted = totCompleted;
		accuracy = acc;

	}

	/**
	 * Setter Method for accuracy
	 */
	public void updateAccuracy(int newAccuracy) {

		accuracy = newAccuracy;

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
	public int totalGuesses() {

		return totalGuesses;
	}
}