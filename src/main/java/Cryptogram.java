import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cryptogram {
    private final String cipherText;
    private final OneToOneMap<Character, Character> solutionMapping;

    public Cryptogram(String cipherText, OneToOneMap<Character, Character> solutionMapping) {
        this.cipherText = cipherText;
        this.solutionMapping = solutionMapping;
    }

    public boolean isSolution(OneToOneMap<Character, Character> mapping) {
        return applyMapping(mapping).equals(getClearText());
    }

    public String getClearText() {
        return applyMapping(solutionMapping);
    }

    public OneToOneMap<Character, Character> getSolutionMapping() {
        return solutionMapping;
    }

    private String applyMapping(OneToOneMap<Character, Character> mapping) {
        StringBuilder s = new StringBuilder();
        for(char c : cipherText.toCharArray()) {
            s.append(mapping.get(c));
        }
        return s.toString();
    }

    public static OneToOneMap<Character, Character> generateRandomMapping() {
        OneToOneMap<Character, Character> mapping = new OneToOneMap<>();

        ArrayList<Character> alphabet = new ArrayList<>(Reference.getAlphaSet());
        ArrayList<Character> shuffled = new ArrayList<>(alphabet);
        Collections.shuffle(shuffled);

        for(int i = 0; i < alphabet.size(); i++) {
            mapping.put(shuffled.get(i), alphabet.get(i));
        }

        mapping.put(' ', ' ');
        mapping.put('.', '.');
        mapping.put(',', ',');

        return mapping;
    }

    public static String getQuoteFromFile(String path) {
        String line = null;
        ArrayList<String> quotes = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                quotes.add(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");


        }

        int random = new Random().nextInt(quotes.size());
        String randomQuote = quotes.get(random).toUpperCase();

        return randomQuote;
    }
}
