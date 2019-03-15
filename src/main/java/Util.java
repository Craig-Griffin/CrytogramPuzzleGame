import java.io.File;
import java.util.HashSet;

public class Util {
    private static final HashSet<Character> alphabet = populateAlphabet();


    public static HashSet<Character> getAlphaSet() {
        return alphabet;
    }

    private static HashSet<Character> populateAlphabet() {
        HashSet<Character> alphabet = new HashSet<>();
        for(char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        return alphabet;
    }
    
}
