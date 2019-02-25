import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    private String crytogram;
    private OneToOneMap<Character, Character> currentMapping;

    private String solution;
    private OneToOneMap<Character, Character> solutionMapping;


    private final String FILE_QUOTES = "quotes.txt";



    public Game(Player player, MappingType type) {
        currentMapping = new OneToOneMap<>();
        solutionMapping = new OneToOneMap<>();
        generateCrypto();
    }

    //For testing
    public Game(){}


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

    public void mapRandomLetter() {

    }


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
            solution = quotes.get(random);

            return quotes.get(random);

        }

    public String encryptQuote(){

        //Assign a random letter of the alphabet 
        int random = new Random().nextInt(25);


        return crytogram;
    }


    public void removeLetter() {

    }

    public void saveToDisk() {

    }

    public void autocomplete() {

    }

    public void getFrequencies() {

    }

    private void generateCrypto() {
        loadRandomQuote();

    }

    private String getSolution(){
        return solution;
    }

    enum MappingType {
        LETTERS,
        NUMBERS;
    }
}
