import misc.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Players {
    private List<Player> allPlayers = new ArrayList<>();
    private FileHandler fileHandler;

    public Players(FileHandler fileHandler) {
        allPlayers = new ArrayList<>();
        this.fileHandler = fileHandler;
    }

    /**
     * Add a new player to the file
     */
    public void addPlayer(Player p) throws IOException {
        if (validateUserName(p)) {
            writeUser(p);
            allPlayers.add(p);

        } else {
            System.out.println("Username all ready exists!");
        }

    }

    /**
     * Remove a player from the system
     */
    public void removePlayer(String p) {
        try {
            File current = fileHandler.getPlayersFile();
            File temp = new File("temp.txt");

            FileReader fr = new FileReader(current);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(temp);
            BufferedWriter bw = new BufferedWriter(fw);

            String line;
            while ((line = br.readLine()) != null) {
                if (!(line.split(" ")[0].equals(p))) {
                    bw.write(line + System.getProperty("line.separator"));
                }
            }
            bw.close();
            br.close();
            current.delete();
            temp.renameTo(fileHandler.getPlayersFile());
            allPlayers.remove(p);

        } catch (IOException ex) {
            System.out.println("Error writing to file");
        }
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
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
     * Method which will  write a players data to the file
     */
    public void writeUser(Player p) {
        try {
            FileWriter fr = new FileWriter(fileHandler.getPlayersFile(), true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(p.getUsername() + " " + p.getCryptogramsPlayed() + " " + p.getCryptogramsCompleted() + " " + p.getTotalGuesses()+ " " + p.getCorrectGuesses());
            pr.close();
            br.close();
            fr.close();


        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        }
    }


    /**
     * Helper method which will ensure that no duplicate usernames can exist within the file
     */
    public boolean validateUserName(Player p) {
        String line = " ";
        boolean valid = true;

        try {

            FileReader fileReader = new FileReader(fileHandler.getPlayersFile());
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
            System.out.println("Unable to open file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");


        }
        return valid;
    }

    /**
     * Get the details of a player which will be used to generate an object
     */
    public ArrayList<Integer> loadStatsFromFile(String p) {
        String line = null;
        ArrayList<Integer> stats = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(fileHandler.getPlayersFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {

                String[] parsedLine = line.split(" ");

                if(parsedLine.length != 5) {
                    System.err.println("Error parsing line " + lineCount + " of the players file:");
                    System.out.println(line);
                    lineCount++;
                    continue;
                }

                String username = parsedLine[0];

                if (username.equals(p)) {

                    String numGame = line.split(" ")[1];
                    String numComp = line.split(" ")[2];
                    String numGuess = line.split(" ")[3];
                    String numCorrect = line.split(" ")[4];

                    stats.add(Integer.parseInt(numGame));
                    stats.add(Integer.parseInt(numComp));
                    stats.add(Integer.parseInt(numGuess));
                    stats.add(Integer.parseInt(numCorrect));

                }

                lineCount++;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileHandler.getPlayersFile().getAbsolutePath() + "'");

        }
        return stats;

    }

    public List<Player> getTopTen() {

        List<Player> Leaderboard = LoadAllPlayers();


        Collections.sort(Leaderboard);



        if (Leaderboard.size() > 9)
            return Leaderboard.subList(0, 9);
        else
            return Leaderboard.subList(0, Leaderboard.size());
    }

    private ArrayList<Player> LoadAllPlayers() {

        ArrayList<Player> temp = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(fileHandler.getPlayersFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;


            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {

                String[] parsedLine = line.split(" ");

                if (parsedLine.length != 5) {
                    System.err.println("Error parsing line " + lineCount + " of the players file:");
                    System.out.println(line);
                    lineCount++;
                    continue;
                }

                temp.add(new Player(line.split(" ")[0],Integer.parseInt(line.split(" ")[1]),Integer.parseInt(line.split(" ")[2]),Integer.parseInt(line.split(" ")[3]),Integer.parseInt(line.split(" ")[4])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }
    }
