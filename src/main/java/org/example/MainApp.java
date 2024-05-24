package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add ke Board
        Player p1 = new Player("player1", 0);
        Player p2 = new Player("player2", 1);
        Toko toko = new Toko();
        Board board = new Board(p1, p2, toko);
        System.out.println(p1.getName());
        // Load the main FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();

        // Set Board
        controller.setBoard(board);
        controller.add_kartu_ke_shuffle_field();
        controller.initialize_click();
        controller.init();
//        controller.add_to_deck_aktif();

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My JavaFX Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
