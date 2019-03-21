package misc;

import java.io.File;
import java.io.IOException;

public class FileHandler {
    private static final String INSTALL_DIR = "run";

    public FileHandler() {
    }

    public File getPlayerSaveFile(String username) {
        makeRunDir();
        return getFile(INSTALL_DIR + "/" + username + "_save.txt");
    }

    public File getPlayersFile() {
        makeRunDir();
        return getFile(INSTALL_DIR + "/allplayers.txt");
    }

    public File getQuotesFile() {
        makeRunDir();
        File file = null;

        try {
            file = Util.loadResource("quotes.txt");
        } catch (IOException e) {
            System.err.println("Failed to load quotes file from project resources.");
            e.printStackTrace();
        }

        return file;
    }

    private File getFile(String path) {
        File file = null;

        try {
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Failed to create file at " + file.getAbsolutePath() + ". Does the program have necessary permission to execute here?");
            e.printStackTrace();
        }

        return file;
    }

    private void makeRunDir() {
        File dir = new File(INSTALL_DIR);
        boolean result = true;

        if (!dir.exists()) {
            result = dir.mkdir();
        }

        if (!result) {
            new RuntimeException("Failed to make run directory at " + dir.getAbsolutePath() + ". Does the program have necessary permission to execute here?").printStackTrace();
        }
    }
}
