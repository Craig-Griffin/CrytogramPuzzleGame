package misc;

public class Paths {
    private Paths(){}

    //save files
    public static final String INSTALL_DIR = "run";

    public static final String PLAYERS_FILE = INSTALL_DIR + "/allplayers.txt";

    public static String getPlayerSaveFilePath(String username) {
        return INSTALL_DIR + "/" + username + "_save.txt";
    }

    //resources
    public static final String QUOTES_FILE = "quotes.txt";
}
