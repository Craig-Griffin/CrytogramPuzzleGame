
import java.util.*;
import java.io.*;

public class Game {

	private String crytogram;
	private String userInput;
	private OneToOneMap<Character, Character> currentMapping;

	private String solution;
	private OneToOneMap<Character, Character> solutionMapping;


	private final String FILE_QUOTES = "quotes.txt";


	/**
	 * Main Constructor for the Game. 
	 * It will generate a new crytogram puzzle for a player to play.
	 * @param player
	 * @param type
	 */
	public Game(Player player, MappingType type) {

		currentMapping = new OneToOneMap<>();
		solutionMapping = new OneToOneMap<>();
		crytogram = "";
		userInput="";

		generateCrypto();
		encrpytQuote();
		userInputQuote();

		
		System.out.println(getSolution());

		while(!isComplete()) {

			displaycryptOrSolution(getCrytogram());
			displayUserInput(getUserInput());

			Character one = promptForChar("\nEnter the letter you would like map\n=> ");
			Character two = promptForChar("Enter the letter you would like to map\n=> ");

			mapLetter(one,two);

		}
		System.out.println("WINNER!!");

	}

	/**
	 * Test Constructor for the game.
	 * Similar to main constructor except does not need a player parameter
	 */
	public Game(){

		currentMapping = new OneToOneMap<>();
		solutionMapping = new OneToOneMap<>();
		crytogram = "";
		userInput="";

		generateCrypto();
		encrpytQuote();
		userInputQuote();


		System.out.println(getSolution());

		while(!isComplete()) {

			displaycryptOrSolution(getCrytogram());
			displayUserInput(getUserInput());

			Character one = promptForChar("\nEnter the letter you would like map\n=> ");
			Character two = promptForChar("Enter the letter you would like to map\n=> ");

			mapLetter(one,two);

		}
		System.out.println("WINNER!!");

	}

	/**
	 * Method which will check to make sure a user input is valad and then map it to the current Mapping Hash Map.
	 * If an input is not valid an appropriate error message will be displayed
	 * @param cipher
	 * @param mapping
	 */
	public void mapLetter(char cipher, char mapping) {

		if (!Character.isAlphabetic(cipher)) {
			System.out.println("Error! " + cipher + " is not an alphabetic character!");
			return;
		}

		if (!Character.isAlphabetic(mapping)) {
			System.out.println("Error! " + mapping + " is not an alphabetic character!");
			return;
		}

		if (currentMapping.containsKey(cipher)) {
			System.out.println(cipher + " already has a mapping! You mapped " + currentMapping.get(cipher) + " to it!");
			return;
		}

		if (currentMapping.containsValue(mapping)) {
			System.out.println("You already mapped " + currentMapping.getReverse(mapping) + " to " + mapping + "!");
			return;
		}

		currentMapping.put(Character.toUpperCase(cipher), Character.toUpperCase(mapping));
		userInputQuote();
	}

	/**
	 * Method which will open quotes file read in each line to an array list
	 * A random quote will be selected from this array list and returned.
	 * @return randomQuote
	 */
	public String loadRandomQuote(){

		String line = null;
		ArrayList<String> quotes = new ArrayList<>();

		try {

			FileReader fileReader = new FileReader(FILE_QUOTES);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				quotes.add(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_QUOTES + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILE_QUOTES + "'");


		}

		int random = new Random().nextInt(quotes.size());
		String randomQuote = quotes.get(random).toUpperCase();

		//Set the solution to the random selected Quote
		solution = quotes.get(random).toUpperCase();

		return randomQuote;
	}

	/**
	 * Method which will build the Solution Map and the Current Map
	 * Solution Map will be assigned a random letter from the alphabet
	 * Current Map will be assigned # as a place holder
	 * Spaces and punctuation will be ingonred
	 */
	private void generateCrypto() {

		loadRandomQuote();

		ArrayList<Character> alphabet = new ArrayList<>(Reference.getAlphaSet());
		ArrayList<Character> shuffled = new ArrayList<>(alphabet);
		Collections.shuffle(shuffled);

		for(int i = 0; i < alphabet.size(); i++) {
			solutionMapping.put(shuffled.get(i), alphabet.get(i));

		}

		for(int i = 0; i < alphabet.size(); i++) {
			currentMapping.put(alphabet.get(i),'#');

		}

		//Map punctuation to avoid null pointer exceptions
		solutionMapping.put(' ', ' ');
		solutionMapping.put('.', '.');
		solutionMapping.put(',', ',');
		currentMapping.put(' ', ' ');
		currentMapping.put('.', '.');
		currentMapping.put(',', ',');

	}

	/**
	 * Use the solution Mapping to get the letters that are relevant to the solution and 
	 * store the random sequence of letters in the crytogram variable
	 */
	public void encrpytQuote() {

		char[] quote = getSolution().toCharArray();

		for(Character c: quote) {
			crytogram= crytogram + solutionMapping.get(c);

		}
	}

	/**
	 * Method used to map out the correct number of characters for the quote
	 * This method will be used to update the userInput String
	 */
	public void userInputQuote() {

		char[] quote = getSolution().toCharArray();
		userInput= "";

		for(Character c: quote) {
			userInput= userInput+ currentMapping.get(solutionMapping.get(c));
		}
	}

	/**
	 * Getter Method for crytogram
	 * @return crytogram
	 */
	public String getCrytogram() {

		return crytogram;
	}

	/**
	 * Getter Method for userInput
	 * @return userInput
	 */
	public String getUserInput() {

		return userInput;
	}

	/**
	 * Getter Method for solutionMapping.toString()
	 * @return solutionMapping.toString()
	 */
	public String getSolutionMapping() {

		return solutionMapping.toString();
	}

	/**
	 * Getter Method for solution
	 * @return solution
	 */
	public String getSolution() {

		return solution;
	}

	/**
	 * Getter Method for currentMapping.toString()
	 * @return currentMapping.toString()
	 */
	public String getCurrentMapping() {

		return currentMapping.toString();
	}


	/**
	 * Method used to display either the crytogram or Solution
	 * @param either a string containing a crytogram or the solution to the crytogram
	 */
	public void displaycryptOrSolution(String cryptOrSolution) {

		StringBuilder displaycryptOrSolution = new StringBuilder();

		for(int i=0; i<cryptOrSolution.length(); i++) {


			if(cryptOrSolution.charAt(i) == '.' || cryptOrSolution.charAt(i) == ',') {
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

	/**
	 * Helper Method to determine whether a game is finished or not
	 */
	public boolean isComplete() {
		return solution.equals(userInput);
	}


	enum MappingType {
		LETTERS,
		NUMBERS;
	}

	public void removeLetter() {

	}

	public void saveToDisk() {

	}

	public void autocomplete() {

	}

	public void getFrequencies() {

	}


}
