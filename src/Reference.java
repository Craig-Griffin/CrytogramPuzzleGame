import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Reference {
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
