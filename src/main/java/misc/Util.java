package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class Util {
    private static final HashSet<Character> alphabet = populateAlphabet();

    /**
     * @return The set containing every member of the alphabet, in uppercase.
     */
    public static HashSet<Character> getAlphaSet() {
        return alphabet;
    }

    /**
     * Values courtesy of http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
     */
    public static HashMap<Character, String> getEnglishLetterFreqs() {
        HashMap<Character, String> map = new HashMap<>();
        map.put('A', "8.12%");
        map.put('B', "1.49%");
        map.put('C', "2.71%");
        map.put('D', "4.32%");
        map.put('E', "12.02%");
        map.put('F', "2.30%");
        map.put('G', "2.03%");
        map.put('H', "5.92%");
        map.put('I', "7.31%");
        map.put('J', "0.10%");
        map.put('K', "0.69%");
        map.put('L', "3.98%");
        map.put('M', "2.61%");
        map.put('N', "6.95%");
        map.put('O', "7.68%");
        map.put('P', "1.82%");
        map.put('Q', "0.11%");
        map.put('R', "6.02%");
        map.put('S', "6.28%");
        map.put('T', "9.10%");
        map.put('U', "2.88%");
        map.put('V', "1.11%");
        map.put('W', "2.09%");
        map.put('X', "0.17%");
        map.put('Y', "2.11%");
        map.put('Z', "0.07%");

        return map;
    }
    /**
     * A helper method to get a File object refererring to a file in the project resources root.
     *
     * @param path Path to the file, where "src/main/resources" is the root. So "src/main/resources/foo/bar.txt" is "foo/bar.txt"
     * @return The File object
     */
    static File loadResource(String path) throws FileNotFoundException {
        File file;
        URL url = Util.class.getClassLoader().getResource(path);

        if (url == null) {
            throw new FileNotFoundException("Resource path '" + path + "' produced a null resource.");
        } else {
            file = new File(url.getFile());
        }

        return file;
    }


    private static HashSet<Character> populateAlphabet() {
        HashSet<Character> alphabet = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        return alphabet;
    }

}
