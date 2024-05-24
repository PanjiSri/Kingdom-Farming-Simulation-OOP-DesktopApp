package org.example;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plugin.TXTLoader;
import plugin.TXTSaver;

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
        TXTSaver saver = new TXTSaver();

        this.gameState = loader.tokenizeLines(loader.readFromFile("game_state.txt"));
        this.player1 = loader.tokenizeLines(loader.readFromFile("player1.txt"));
        this.player2 = loader.tokenizeLines(loader.readFromFile("player2.txt"));

        // System.out.println(player2);

        // System.out.println(formatPlayerData(player2));
        
        List<String> asli = loader.readFromFile("player2.txt");

        System.out.println(asli);

        // List<String> formatted = loader.repairFormat(gameState);

        // System.out.println(formatted);

        // saver.saveFormattedData("data_game", "ngopi.txt", formatted);

        saver.saveFormattedData("ngopi", "ngopi.txt", asli);

        scanner.close();
    }

    public static void main(String[] args) {
        // launch(args);
        MainApp m = new MainApp();
        m.loadTXT();
    }

    public List<String> formatPlayerData(List<String> dataList) {
        List<String> formattedData = new ArrayList<>();

        if (dataList.size() > 2) {
            formattedData.add(dataList.get(0)); 
            formattedData.add(dataList.get(1)); 
            formattedData.add(dataList.get(2)); 
        }

        StringBuilder currentEntry = new StringBuilder();
        for (int i = 3; i < dataList.size(); i++) {
            String item = dataList.get(i).trim();
            boolean isNumeric = item.matches("^[0-9]+$");

            // Start a new line if it's a card location or numeric (indicating a new group) and currentEntry is not empty
            if ((item.matches("^[AB][0-9]{2}$") || isNumeric) && currentEntry.length() > 0) {
                formattedData.add(currentEntry.toString().trim());
                currentEntry.setLength(0); // Reset the StringBuilder
            }

            if (currentEntry.length() > 0) {
                currentEntry.append(" ");
            }
            currentEntry.append(item);
        }

        // Add the last entry if it's not empty
        if (currentEntry.length() > 0) {
            formattedData.add(currentEntry.toString());
        }

        return formattedData;
    
    }
    
}
