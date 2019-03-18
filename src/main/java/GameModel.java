
import java.util.*;
import java.io.*;

public class GameModel {
    private String crytogram;
    private String userInput;
    private String solution;
    private int hintCount;

    //For Letters Mapping
    public OneToOneMap<Character, Character> currentMapping;
    public OneToOneMap<Character, Character> solutionMapping;

    private MappingType lettersOrNums;


    /**
     * Main Constructor for the Game.
     * It will generate a new crytogram puzzle for a player to play.
     *
     * @param type
     */
    public GameModel(MappingType type) {

        GenerateCrypto buildCrypto = new GenerateCrypto();

        solution = buildCrypto.getSolution();
        lettersOrNums = type;

        currentMapping = buildCrypto.generateBlank();
        solutionMapping = buildCrypto.generateCrypto();
        crytogram = "";
        userInput = "";

        hintCount = 0;

        encrpytQuote();
        userInputQuote();
    }


    /**
     * Load from file constructor
     */
    public GameModel(MappingType type, String solution, String userInput, String crytogram, OneToOneMap<Character, Character> solutionMapping, OneToOneMap<Character, Character> currentMapping) {
        this.solution = solution;
        this.currentMapping = currentMapping;
        this.solutionMapping = solutionMapping;

        this.crytogram = crytogram;
        this.userInput = userInput;

        hintCount = 0;

        userInputQuote();


    }


    public void encrpytQuote() {
        char[] quote = solution.toCharArray();

        for (Character c : quote) {
            crytogram = crytogram + solutionMapping.get(c);

        }
    }

    /**
     * Method used to map out the correct number of characters for the quote
     * This method will be used to update the userInput String
     */
    public void userInputQuote() {
        char[] quote = getSolution().toCharArray();
        userInput = "";

        for (Character c : quote) {
            userInput = userInput + currentMapping.get(solutionMapping.get(c));
        }
    }

    /**
     * Method which will check to make sure a user input is valad and then map it to the current Mapping Hash Map.
     * If an input is not valid an appropriate error message will be displayed
     *
     * @param cipher
     * @param mapping
     */
    public boolean mapLetter(char cipher, char mapping, Player p) {
        boolean correctGuess = false;
        if (!Character.isAlphabetic(cipher)) {
            System.out.println("Error! " + cipher + " is not an alphabetic character!");
            return correctGuess;
        }

        if (!Character.isAlphabetic(mapping)) {
            System.out.println("Error! " + mapping + " is not an alphabetic character!");
            return correctGuess;
        }

        if (currentMapping.containsKey(cipher)) {
            System.out.println(cipher + " already has a mapping! You mapped " + currentMapping.get(cipher) + " to it!");
            return correctGuess;
        }

        if (currentMapping.containsValue(mapping)) {
            System.out.println("You already mapped " + currentMapping.getReverse(mapping) + " to " + mapping + "!");
            return correctGuess;
        }

        p.incrementGuesses();
        if (checkIfRight(cipher, mapping)) {
            p.incrementCorrectGuesses();
            correctGuess = true;
        }
        currentMapping.put(Character.toUpperCase(cipher), Character.toUpperCase(mapping));
        userInputQuote();
        if (userInput.equals(solution)) {
            p.incrementPlayed();
            p.incrementCryptogramsCompleted();

        }
        return correctGuess;
    }

    public boolean checkIfRight(char mapping, char cipherChar) {
        if (Character.toUpperCase(mapping) == solutionMapping.get(Character.toUpperCase(cipherChar))) {
            return true;
        }
        return false;
    }
    /**
     * Method which will remove a letter from the current Mapping
     */
    public void removeLetter(char cipher) {
        if (!Character.isAlphabetic(cipher)) {
            System.out.println("Error! " + cipher + " is not an alphabetic character!");
            return;
        }
        currentMapping.remove(Character.toUpperCase(cipher));
        currentMapping.put(Character.toUpperCase(cipher), '#');
        userInputQuote();

    }

    public void saveToDisk(Player p) throws IOException {
        File f = new File(p.getUsername() + "_save.txt");

        if (f.exists())
            f.delete();


        f.createNewFile();
        FileWriter fr = new FileWriter(f.getName(), true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(solution + "\n" + userInput + "\n" + crytogram + "\n" + solutionMapping + "\n" + currentMapping);
        pr.close();
        br.close();
        fr.close();

    }

    public void autocomplete(Player p) {
        p.incrementPlayed();
        userInput = solution;
    }


    public void giveHint(Player p) {
        char[] quote = getSolution().toCharArray();

        for (int i = hintCount; i < quote.length; i++) {
            Character c = quote[i];
            hintCount++;
            if (!c.equals(currentMapping.get(c))) {
                currentMapping.put(solutionMapping.get(c), c);
                userInputQuote();
                break;
            }
        }

        System.out.println("\nHint Number: " + hintCount + "\n");

        if (userInput.equals(solution)) {
            p.incrementPlayed();
            return;
        }

    }


    public void getFrequencies() {
        String cryto = getCrytogram();
        HashMap<Character, Integer> temp = new HashMap<>();

        for (Character c : cryto.toCharArray()) {
            if (c != ' ' && c != '.' && c != ',') {
                int currentCount = countChar(cryto, c);
                temp.put(c, currentCount);
            }
        }

        System.out.println("\nLetter Frequencies: " + temp.toString() + "\n");
    }

    public void addGuess(Player p) {
        p.incrementGuesses();
    }

    public void updatePlayerAccuracy(Player p) {
        p.updateAccuracy();
    }

    public void quitWithoutSave(Player p) {
        p.incrementPlayed();
    }

    public int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }

        return count;
    }


    void loadFromFile(){ //workaround being used in PlayerController, hard to refactor
    }


    public void sprintOnePrint() {
        System.out.println("***Sprint One Console Print Out***");
        System.out.println(getSolution());
        System.out.println(getCrytogram());
        System.out.println(getSolutionMapping() + "\n\n");
    }

    public String getCrytogram() {
        return crytogram;
    }

    public String getUserInput() {
        return userInput;
    }

    public String getSolutionMapping() {
        return solutionMapping.toString();
    }

    public String getSolution() {
        return solution;
    }

    public String getCurrentMapping() {
        return currentMapping.toString();
    }


}
