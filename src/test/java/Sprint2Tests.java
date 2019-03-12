import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Sprint2Tests {

    private PlayerController controller;
    private Players allPlayers;

    public Sprint2Tests() throws IOException {
        controller = new PlayerController();
        allPlayers = new Players();
    }


    /* User Story One*/
    @Test
    public void makeSureSaveIsWorking() {
        //Generate a new Game and player
        Player john = new Player("john");
        GameModel game = new GameModel(MappingType.LETTERS);

        //perform a save
        try {
            game.saveToDisk(john);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Make sure that a save file has been generated
        File file = new File(john.getUsername()+"_save.txt");
        Assert.assertTrue(file.exists());


    }


    /* User Story Two*/
    @Test
    public void makeSureLoadIsWorking(){

    }


    /* User Story Three*/
    @Test
    public void makeSurePlayerNameIsStored(){

        //Generate a new Player
        Player jess = new Player("jess");


        try {


            allPlayers.addPlayer(jess);
            Assert.assertTrue(controller.userNameExists("jess"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*User Story Four*/
    @Test
    public void makeSureNumberOfCryptosPlayedIsTracked(){

    }


    /*User Story Five*/
    @Test
    public void makeSureNumberOfCryptosWonIsTracked(){

    }


    /*User Story Six*/
    @Test
    public void makeSureAccuracyIsTracked(){

    }






    /*User Story Seven*/
    @Test
    public void makeSurePlayerStatsAreLoaded(){

    }







}
