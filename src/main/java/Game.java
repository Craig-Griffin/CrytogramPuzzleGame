
import java.util.*;
import java.io.*;

public class Game {

	private Cryptogram cryptogram;
	private OneToOneMap<Character, Character> currentMapping;

	private final String FILE_QUOTES = "quotes.txt";
	
	private MappingType lettersOrNums;


	/**
	 * Main Constructor for the Game. 
	 * It will generate a new crytogram puzzle for a player to play.
	 * @param player
	 * @param type
	 */
	public Game(Player player, MappingType type, Cryptogram cryptogram) {
		
		lettersOrNums = type;

		currentMapping = new OneToOneMap<>();
		this.cryptogram = cryptogram;

		System.out.println("***Sprint One Console Print Out***");
		
		//Phrase Being Used
		System.out.println(cryptogram.getClearText());
		
		//cryptographic alphabet
		System.out.println(cryptogram.getSolutionMapping());
		
		//how it is mapped to the plain alphabet (A-Z)
		System.out.println(cryptogram.getSolutionMapping()+"\n\n");
	}

	public Cryptogram getCryptogram() {
		return cryptogram;
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
	}

	/**
	 * Method which will remove a letter from the current Mapping
	 */
	public void removeLetter(char cipher) {
		if (!Character.isAlphabetic(cipher)) {
			System.out.println("Error! " + cipher + " is not an alphabetic character!");
			return;
		}

		currentMapping.put(Character.toUpperCase(cipher), '#');
	}

	/**
	 * Helper Method to determine whether a game is finished or not
	 */
	public boolean isComplete() {
		return cryptogram.isSolution(currentMapping);
	}

	public void saveToDisk() {

	}

	public void autocomplete() {

	}

	//Not Working
	public void getFrequencies() {
//		String cryto = cryptogram;
//		ArrayList<Character> alphabet = new ArrayList<>(Reference.getAlphaSet());
//		HashMap<Character,Integer> temp = new HashMap<>();
//		Integer oldvalue;
//		Integer newValue;
//
//		for(int i = 0; i < alphabet.size(); i++) {
//			temp.put(alphabet.get(i),0);
//
//		}
//
//		System.out.println(temp);
//
//		for(Character current: cryto.toCharArray()) {
//			for(Character c: cryto.toCharArray()) {
//				if(current.equals(c)) {
//					oldvalue =  temp.get(c);
//					newValue = oldvalue + 1;
//
//					temp.replace(c, newValue);
//				}
//			}
//		}
//
//
//	}
//
//	//Working but only on UNIX terminal so find something better
//	public void clearScreen() {
//		System.out.print("\033[H\033[2J");
//		System.out.flush();
	}


}
