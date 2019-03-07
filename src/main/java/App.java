import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class App {


  


    /*MAIN GAME LOOP*/

    public static void main(String[] args) throws IOException {


        PlayerController playerController = new PlayerController();
    	
    	Player player = playerController.login();

    	GameController game = new GameController(MappingType.LETTERS, player);

    	game.playGame();
    	
    }

    

}
