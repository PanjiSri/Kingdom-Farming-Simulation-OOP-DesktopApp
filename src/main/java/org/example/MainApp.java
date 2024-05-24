package org.example;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

import plugin.TXTLoader;
import java.util.Scanner;
import java.util.List;

public class MainApp{

    List<String> gameState;
    List<String> player1;
    List<String> player2;

//     @Override
//     public void start(Stage primaryStage) throws Exception {
//         // Add ke Board
//         Player p1 = new Player("player1", 0);
//         Player p2 = new Player("player2", 1);
//         Board board = new Board(p1, p2);
//         System.out.println(p1.getName());
//         // Load the main FXML file
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
//         Parent root = loader.load();
//         MainController controller = loader.getController();

//         // Set Board
//         controller.setBoard(board);
//         controller.add_kartu_ke_shuffle_field();
//         controller.initialize_click();
//         controller.init();
// //        controller.add_to_deck_aktif();

//         Scene scene = new Scene(root, 1000, 800);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("My JavaFX Application");
//         primaryStage.show();
//     }

    public void loadTXT(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the folder containing the text files:");
        String folderName = scanner.nextLine();

        TXTLoader loader = new TXTLoader(folderName);

        this.gameState = loader.tokenizeLines(loader.readFromFile("game_state.txt"));
        this.player1 = loader.tokenizeLines(loader.readFromFile("player1.txt"));
        this.player2 = loader.tokenizeLines(loader.readFromFile("player2.txt"));

        System.out.println("Game State:");
        gameState.forEach(System.out::println);

        System.out.println("\nPlayer 1 Data:");
        player1.forEach(System.out::println);

        System.out.println("\nPlayer 2 Data:");
        player2.forEach(System.out::println);

        scanner.close();
    }

    public static void main(String[] args) {
        // launch(args);
        MainApp m = new MainApp();
        m.loadTXT();
    }
    
}
