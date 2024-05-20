package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.Board.*;
import org.example.Player.*;

public class MainController {

    @FXML
    private GridPane ladang, shuffle_panel, deck_aktif;

    @FXML
    private Pane pane_ladang, ambil_kartu;

    @FXML
    private Button reset_data, next_turn, shuffle_card;

    @FXML
    private StackPane board;

    private Board main;

    public void init() {
        // Initialize the grid cells
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-pref-width: 60; -fx-pref-height: 60;");
                ladang.add(pane, i, j);
            }
        }

        // Set drag over for the GridPane
        ladang.setOnDragOver(event -> {
            if (event.getGestureSource() != ladang && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Set drop for the GridPane
        ladang.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && db.getString().equals("pane")) {
                Pane draggedPane = (Pane) event.getGestureSource();
                System.out.println(draggedPane.getId());
                draggedPane.setStyle("-fx-background-color: white");
                deck_aktif.getChildren().remove(draggedPane);

                // Calculate the new row and column based on the drop position
                int col = (int) (event.getX() / (ladang.getWidth() / ladang.getColumnCount()));
                int row = (int) (event.getY() / (ladang.getHeight() / ladang.getRowCount()));

                ladang.add(draggedPane, col, row);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // Initialize the action buttons
        initialize_click();
    }

    public void initialize_click() {
        reset_data.setOnAction(e -> clear_pane());
        next_turn.setOnAction(e -> change_to_shuffle());
        shuffle_card.setOnAction(e -> shuffle_kartu());
    }

    public void clear_pane() {
        System.out.println("Child pane: " + ladang.getChildren().size());
        ladang.getChildren().clear();
        System.out.println("Child pane: " + ladang.getChildren().size());
    }

    public void shuffle_kartu() {
        Player a = main.getPlayernow();
        a.shuffle();
        add_kartu_ke_shuffle_field();
    }

    public void add_to_deck_aktif() {
        for (int i = 0; i < 6; i++) {
            Pane pane = new Pane();
            pane.getChildren().add(new Label(Integer.toString(i)));
            pane.setStyle("-fx-background-color: black; -fx-border-radius: 6; -fx-pref-width: 60; -fx-pref-height: 60;");
            pane.setId(Integer.toString(i));
            // Enable dragging for the pane
            pane.setOnDragDetected(event -> {
                Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString("pane");
                db.setContent(content);
                event.consume();
            });

            deck_aktif.add(pane, i, 0);
        }
    }

    public void change_to_shuffle() {
        board.getChildren().clear();
        add_kartu_ke_shuffle_field();
        board.getChildren().setAll(pane_ladang, ambil_kartu);
        System.out.println(board.getChildren().size());
    }

    public void add_kartu_ke_shuffle_field() {
        shuffle_panel.getChildren().clear();
        Player a = main.getPlayernow();
        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String kata = a.get_kartu(idx);
                Pane card_shuffle = new Pane();
                card_shuffle.getChildren().add(new Label("Kartu " + kata));
                card_shuffle.setStyle("-fx-background-color: white; -fx-pref-width: 60; -fx-pref-height: 60;");
                card_shuffle.setId(Integer.toString(idx));
                idx += 1;
                shuffle_panel.add(card_shuffle, i, j);
            }
        }
    }

    public void setBoard(Board board) {
        main = board;
    }
}
