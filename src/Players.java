import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Players {

	private ArrayList<Player> allPlayers;
	private final String FILENAME = "allplayers.txt";


	/**
	 * Add a new player to the file
	 */
	public void addPlayer(Player p) throws IOException {

		if(validateUserName(p)) {

			writeUser(p);

		}
		else {
			System.out.println("Username all ready exists!");
		}

	}


	/**
	 * Remove a player from the system
	 */
	public void removePlayer(Player p) {

		//Remove player from file

	}


	/**
	 * ??
	 */
	public void savePlayer(Player p) {

		//Save player to file

	}


	/**
	 * Find a player on the system
	 */
	public Player findPlayer() {

		//Look through file for a file
		return null;
	}


	/**
	 * Getter method for allPlayers
	 */
	public ArrayList<Player> getAllPlayersAccuracies() {
		return allPlayers;
	}


	/**
	 *
	 */
	public void writeUser(Player p) throws IOException {

		try {

			File file = new File(FILENAME);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pr = new PrintWriter(br);
			pr.println(p.getUsername()+ " " + p.getCryptogramsPlayed()+ " "+ p.getCryptogramsCompleted()+ " "+ p.getAccuracy());
			pr.close();
			br.close();
			fr.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 *
	 */
	public boolean validateUserName(Player p) {

		String line = " ";
		boolean valid = true;

		try {

			FileReader fileReader = new FileReader(FILENAME);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {

				//Getting the user name from the file
				String username = line.split(" ")[0];

				if(username.equals(p.getUsername())) {
					valid = false;
				}
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILENAME + "'");
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + FILENAME + "'");


		}
		return valid;
	}

}


