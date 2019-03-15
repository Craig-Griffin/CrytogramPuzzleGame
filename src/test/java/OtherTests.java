import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class OtherTests {

    private PlayerController controller;
    private Players allPlayers;

    public OtherTests() throws IOException {
        controller = new PlayerController();
        allPlayers = new Players();
    }


    /* User Story One*/
    @Test
    public void makeSureSaveIsWorking() {
        //Generate a new Game and player
        GameController gc = new GameController(MappingType.LETTERS,null);


        String one = "qwer 1 1 1 1";
        String two = "dsfhg 0 1 1 1";


        gc.updateStats(null,one,two);


    }







}
