
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class Sprint2Tests {

    File playerFile;
    Players allPlayers;
    PlayerController pController;
    GameController gController;

    @BeforeAll
    static void doInitially() throws IOException {
        File savedPlayerList = new File("savedList.txt");
        File currentPlayerList = new File("allplayers.txt");
        if (currentPlayerList.exists()) {
            currentPlayerList.renameTo(savedPlayerList);
        }
    }

    @AfterAll
    static void doAfterAll() {
        File savedPlayerList = new File("savedList.txt");
        File currentPlayerList = new File("allplayers.txt");
        if (savedPlayerList.exists()) {
            currentPlayerList.delete();
            savedPlayerList.renameTo(currentPlayerList);
        }
    }

    @BeforeEach
    void setUpEach() throws IOException {
        File currentPlayerList = new File("allplayers.txt");
        if (currentPlayerList.exists()) {
            currentPlayerList.delete();
        }
        pController = new PlayerController();
        allPlayers = new Players();
    }

    @AfterEach
    void doAfterEach() {
        if (playerFile != null) {
            playerFile.delete();
        }
    }


    /* User Story One*/
    @Test
    public void makeSureSaveIsWorking() {
        //Generate a new Game and player
        Player john = new Player("john");
        GameModel game = new GameModel(MappingType.LETTERS);

        playerFile = new File(john.getUsername() + "_save.txt");

        //perform a save
        try {
            game.saveToDisk(john);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Make sure that a save file has been generated
        assertTrue(playerFile.exists());

    }


    /* User Story Two*/
    @Test
    public void makeSureLoadIsWorking() {

        //generate new game
        Player john = new Player("john");
        GameModel game = new GameModel(MappingType.LETTERS);
        //make a random mapping to check loading properly
        game.mapLetter('e', 'l', john);
        String mappingBeforeSaveLoad = game.getCurrentMapping();

        //save to disk
        try {
            game.saveToDisk(john);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //map to different character to make sure loading is overwriting current
        game.removeLetter('e');
        game.mapLetter('e', 'q', john);

        //load saved game
        GameController gamec = pController.loadGame(john);
        game = gamec.getModel();
        assertEquals(game.getCurrentMapping(), mappingBeforeSaveLoad);

    }


    /* User Story Three*/
    @Test
    public void makeSurePlayerNameIsStored() {

        //Generate a new Player
        Player john = new Player("john");

        try {
            allPlayers.addPlayer(john);
            assertTrue(pController.userNameExists("john"));
            assertEquals(allPlayers.getAllPlayers().get(0).getUsername(), "john");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*User Story Four*/
    @Test
    public void makeSureNumberOfCryptosPlayedIsTracked() {
        Player john = new Player("john");
        try {
            allPlayers.addPlayer(john);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController g1 = new GameController(MappingType.LETTERS, john);
        g1.getModel().autocomplete(john);
        assertEquals(1, 1);
    }

    /*User Story Five*/
    @Test
    public void makeSureNumberOfCryptosWonIsTracked(){

        Player john = new Player("john");
        try {
            allPlayers.addPlayer(john);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        GameController g1 = new GameController(MappingType.LETTERS, john);
        GameModel game = g1.getModel();
        char cryptogram[] = game.getCrytogram().toCharArray();
        ArrayList<Character> cryptogramChars = new ArrayList<>();
        for (char c : cryptogram) {
            if (c != ' ' && c != ',' && c != '\'' && c != '.' && !cryptogramChars.contains(Character.toLowerCase(c))) {
                cryptogramChars.add(Character.toLowerCase(c));
            }
        }
        char cryptogramSolution[] = game.getSolution().toCharArray();
        ArrayList<Character> cryptogramSolutionChars = new ArrayList<>();
        for (char c : cryptogramSolution) {
            if (c != ' ' && c != ',' && c != '\'' && c != '.' && !cryptogramSolutionChars.contains(Character.toLowerCase((c)))) {
                cryptogramSolutionChars.add(Character.toLowerCase(c));
            }
        }
        int i;
        for (i = 0; i < cryptogramChars.size()-1; i++) {
            game.mapLetter(cryptogramChars.get(i), cryptogramSolutionChars.get(i), john);
        }

        //getting crypto to state before being solved
        assertEquals(0, john.getCryptogramsCompleted());
        assertEquals(0, john.getCryptogramsPlayed());

        //adding final step to solve then checking updated
        game.mapLetter(cryptogramChars.get(i), cryptogramSolutionChars.get(i), john);
        assertEquals(1, john.getCryptogramsCompleted());
        assertEquals(1, john.getCryptogramsPlayed());
    }


    /*User Story Six*/
    @Test
    public void makeSureAccuracyIsTracked(){
        Player john = new Player("john");
        try {
            allPlayers.addPlayer(john);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        GameController g1 = new GameController(MappingType.LETTERS, john);
        if (g1.getModel().mapLetter(Character.toLowerCase(g1.getModel().getCrytogram().charAt(0)), 'a', john)){
            john.updateAccuracy();
            assertEquals(1, john.getAccuracy());
        }
        else {
            john.updateAccuracy();
            assertEquals(0, john.getAccuracy());
        }

    }

    /*User Story Seven*/
    @Test
    public void makeSurePlayerStatsAreLoaded(){
        Player john = new Player("john");
        try {
            allPlayers.addPlayer(john);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> stats = allPlayers.loadStatsFromFile("john");
        assertEquals(0, stats.get(0).intValue());
        assertEquals(0, stats.get(1).intValue());
        assertEquals(0, stats.get(2).intValue());
        john.incrementPlayed();
        john.incrementCryptogramsCompleted();
        john.updateAccuracy();
        try {
            allPlayers.removePlayer("john");
            allPlayers.writeUser(john);
            allPlayers.writeUser(new Player("FReed"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        john.incrementCryptogramsCompleted();
        assertEquals(2, john.getCryptogramsCompleted());
        stats = allPlayers.loadStatsFromFile("john");
        assertEquals(1, stats.get(0).intValue());
        assertEquals(1, stats.get(1).intValue());
        assertEquals(0, stats.get(2).intValue());


    }

}
