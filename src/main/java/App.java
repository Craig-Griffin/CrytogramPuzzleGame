import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class App {


  


    /*MAIN GAME LOOP*/

    public static void main(String[] args) throws IOException {


        PlayerController login = new PlayerController();
    	
    	Player player = login.login();


    	GameController game = new GameController(MappingType.LETTERS, player);

    	game.playGame();
    	
    }

    

}
