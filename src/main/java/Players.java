import java.io.*;
import java.util.ArrayList;


public class Players {

	private ArrayList < Player > allPlayers;
	private final String FILE_ALLPLAYERS = "allplayers.txt";

	/**
	 * Add a new player to the file
	 */
	public void addPlayer(Player p) throws IOException {

		if (validateUserName(p)) {
			writeUser(p);

		} else {
			System.out.println("Username all ready exists!");
		}

	}

	/**
	 * Remove a player from the system
	 */
	public void removePlayer(String username) throws IOException{
		try {
			File current = new File(FILE_ALLPLAYERS);
			File temp = new File("temp.txt");

			FileReader fr = new FileReader(current);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(temp);
			BufferedWriter bw = new BufferedWriter(fw);

			String line;
			while ((line = br.readLine()) != null) {
				if (!(line.split(" ")[0].equals(username))) {
					bw.write(line + System.getProperty("line.separator"));
				}
			}
			temp.renameTo(current);
			bw.close();
			br.close();
			temp.delete();
		}
		catch (IOException ex) {
			System.out.println("Error writing to file");
		}
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
	public ArrayList < Player > getAllPlayersAccuracies() {
		return allPlayers;
	}

	/**
	 * Method which will  write a players data to the file
	 */
	public void writeUser(Player p) throws IOException {

		try {

			FileWriter fr = new FileWriter(FILE_ALLPLAYERS, true);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pr = new PrintWriter(br);
			pr.println(p.getUsername() + " " + p.getCryptogramsPlayed() + " " + p.getCryptogramsCompleted() + " " + p.getAccuracy());
			pr.close();
			br.close();
			fr.close();


		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_ALLPLAYERS + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILE_ALLPLAYERS + "'");
		}
	}
	

	/**
	 * Helper method which will ensure that no duplicate usernames can exist within the file
	 */
	public boolean validateUserName(Player p) {

		String line = " ";
		boolean valid = true;

		try {

			FileReader fileReader = new FileReader(FILE_ALLPLAYERS);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				//Getting the user name from the file
				String username = line.split(" ")[0];

				if (username.equals(p.getUsername())) {
					valid = false;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_ALLPLAYERS + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILE_ALLPLAYERS + "'");


		}
		return valid;
	}
	
	/**
	 * Get the details of a player which will be used to generate an object
	 */
	public ArrayList<Integer> loadStatsFromFile(String p){

		String line = null;
		ArrayList<Integer> stats = new ArrayList<>();


		try {

			FileReader fileReader = new FileReader(FILE_ALLPLAYERS);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				String username = line.split(" ")[0];

				if (username.equals(p)) {

					String numGame = line.split(" ")[1];
					String numComp = line.split(" ")[2];
					String acc = line.split(" ")[3];

					stats.add(Integer.parseInt(numGame));
					stats.add(Integer.parseInt(numComp));
					stats.add(Integer.parseInt(acc));

				}

			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_ALLPLAYERS + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILE_ALLPLAYERS + "'");


		}
		return stats;

	}

}