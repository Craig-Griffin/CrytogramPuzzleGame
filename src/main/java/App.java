import java.io.*;

public class App {

    /*Start the game here!*/
    public static void main(String[] args) throws IOException {
        PlayerController playerController = new PlayerController();

        //A Player logs in and then proceeds to the main menu
        playerController.login();
        playerController.menu();


    }


}