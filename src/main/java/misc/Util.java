package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
     * A helper method to get a File object refererring to a file in the project resources root.
     *
     * @param path Path to the file, where "src/main/resources" is the root. So "src/main/resources/foo/bar.txt" is "foo/bar.txt"
     * @return The File object
     */
    public static File loadResource(String path) throws FileNotFoundException {
        File file;
        URL url = Util.class.getClassLoader().getResource(path);

        if(url == null) {
            throw new FileNotFoundException("Resource path '" + path + "' produced a null resource.");
        } else {
            file = new File(url.getFile());
        }

        return file;
    }


    private static HashSet<Character> populateAlphabet() {
        HashSet<Character> alphabet = new HashSet<>();
        for(char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        return alphabet;
    }
    
}
