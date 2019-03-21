import misc.OneToOneMap;
import misc.FileHandler;
import misc.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerateCrypto {
    private GameModel game;
    private String solution;
    private FileHandler fileHandler;

    public GenerateCrypto(FileHandler fileHandler) {
        this.fileHandler = fileHandler;

        solution = loadRandomQuote();

    }

    /**
     * Method which will open quotes file read in each line to an array list
     * A random quote will be selected from this array list and returned.
     *
     * @return randomQuote
     */
    public String loadRandomQuote() {
        String line = null;
        ArrayList<String> quotes = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileHandler.getQuotesFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                quotes.add(line);
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int random = new Random().nextInt(quotes.size());
        String randomQuote = quotes.get(random).toUpperCase();
        return randomQuote;
    }


    public String getSolution() {
        return solution;
    }

    /**
     * Method which will build the Solution Map and the Current Map
     * Solution Map will be assigned a random letter from the alphabet
     * Current Map will be assigned # as a place holder
     * Spaces and punctuation will be ingonred
     */
    public OneToOneMap<Character, Character> generateCrypto() {

        OneToOneMap<Character, Character> solutionMapping = new OneToOneMap<>();

        ArrayList<Character> alphabet = new ArrayList<>(Util.getAlphaSet());
        ArrayList<Character> shuffled = new ArrayList<>(alphabet);
        Collections.shuffle(shuffled);

        for (int i = 0; i < alphabet.size(); i++) {
            solutionMapping.put(shuffled.get(i), alphabet.get(i));

        }

        //Map punctuation to avoid null pointer exceptions
        solutionMapping.put(' ', ' ');
        solutionMapping.put('.', '.');
        solutionMapping.put(',', ',');
        solutionMapping.put('\'', '\'');


        return solutionMapping;
    }

    /**
     * Use the solution Mapping to get the letters that are relevant to the solution and
     * store the random sequence of letters in the crytogram variable
     */
    public OneToOneMap<Character, Character> generateBlank() {

        getSolution();


        OneToOneMap<Character, Character> currentMapping = new OneToOneMap<>();

        ArrayList<Character> alphabet = new ArrayList<>(Util.getAlphaSet());
        ArrayList<Character> shuffled = new ArrayList<>(alphabet);
        Collections.shuffle(shuffled);


        for (int i = 0; i < alphabet.size(); i++) {
            currentMapping.put(alphabet.get(i), '#');
        }

        //Map punctuation to avoid null pointer exceptions
        currentMapping.put(' ', ' ');
        currentMapping.put('.', '.');
        currentMapping.put(',', ',');
        currentMapping.put('\'', '\'');


        return currentMapping;
    }


}
