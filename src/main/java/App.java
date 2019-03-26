import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        PlayerController playerController = new PlayerController();
        Player player = playerController.login();

        playerController.menu();


    }


}
