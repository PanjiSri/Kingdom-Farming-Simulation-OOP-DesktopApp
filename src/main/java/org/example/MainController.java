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
import org.example.card.CardGUI.*;


public class MainController {

    @FXML
    private GridPane ladang, shuffle_panel, deck_aktif;

    @FXML
    private Pane pane_ladang, ambil_kartu, jumlah_turn, player_saat_ini;

    @FXML
    private Button reset_data, next_turn, shuffle_card, close_button, ladang_lawan;

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
            Player a = main.getPlayernow();
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && db.getString().equals("pane")) {
                Pane draggedPane = (Pane) event.getGestureSource();
                draggedPane.setStyle("-fx-background-color: white");

                // Hapus dari deck_aktif hanya jika Pane berasal dari deck_aktif
                if (deck_aktif.getChildren().contains(draggedPane)) {
                    deck_aktif.getChildren().remove(draggedPane);
                }

                if (ladang.getChildren().contains(draggedPane)) {
                    a.drop_ladang(draggedPane.getId());
                    ladang.getChildren().remove(draggedPane);
                }

                // Hitung baris dan kolom baru berdasarkan posisi drop
                int col = (int) (event.getX() / (ladang.getWidth() / ladang.getColumnCount()));
                int row = (int) (event.getY() / (ladang.getHeight() / ladang.getRowCount()));

                // Pastikan tidak ada elemen pada posisi tersebut
                System.out.println(draggedPane.getId());
                a.add_in_lahan(row, col, draggedPane.getId());
                a.drop_deck_aktif(draggedPane.getId());
                System.out.println("Ini kartu: " + draggedPane.getId());
                System.out.println();

                    // Tambahkan ke ladang
                ladang.add(draggedPane, col, row);
                a.print_lahan();
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });


        // Initialize the action buttons
        initialize_click();
    }

    public void ladang_lawan() {
        ladang.getChildren().clear();
        init();
        Player a = main.getEnemyNow();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!(a.get_card_ladang(i, j).equals(" x "))) {
                    System.out.println(a.get_card_ladang(i, j));
                    Pane pane = new Pane();
                    pane.setStyle("-fx-pref-height: 90; -fx-pref-width: 70; -fx-background-color: white");
                    pane.setId(a.get_card_ladang(i, j));
                    pane.getChildren().add(new Label(a.get_card_ladang(i, j)));
                    ladang.add(pane, j, i);
                }
            }
        }
    }

    public void initialize_click() {
        reset_data.setOnAction(e -> clear_pane());
        next_turn.setOnAction(e -> change_to_shuffle());
        shuffle_card.setOnAction(e -> shuffle_kartu());
        close_button.setOnAction(e -> change_to_main());
        ladang_lawan.setOnAction(e -> ladang_lawan());
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
        Player a = main.getPlayernow();
        for (int i = 0; i < 6; i++) {
            if (!(a.get_card_aktif(i).equals("   "))) {
                System.out.println(a.get_card_aktif(i));
                Pane pane = new Pane();
                pane.setStyle("-fx-pref-height: 90; -fx-pref-width: 70; -fx-background-color: white");
                pane.setId(a.get_card_aktif(i));
                pane.getChildren().add(new Label(a.get_card_aktif(i)));
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
    }

    public void add_to_ladang() {
        Player a = main.getPlayernow();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                String card = a.get_card_ladang(i, j);
                // Pastikan kartu tidak null atau tidak valid
                if (card != null && !(card.equals(" x "))) {
                    System.out.println(card);
                    Pane pane = new Pane();
                    pane.setStyle("-fx-pref-height: 90; -fx-pref-width: 70; -fx-background-color: white");
                    pane.setId(a.get_card_ladang(i, j)); // Menggunakan ID unik berdasarkan posisi
                    pane.getChildren().add(new Label(card));
                    pane.setOnDragDetected(event -> {
                        Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString("pane");
                        db.setContent(content);
                        event.consume();
                    });
                        ladang.add(pane, j, i);
                }
            }
        }
    }


    public void change_to_shuffle() {
        main.add_turn();
        Player a = main.getPlayernow();
        a.reset_ciot();
        board.getChildren().clear();
        add_kartu_ke_shuffle_field();
        board.getChildren().setAll(pane_ladang, ambil_kartu);
        System.out.println(board.getChildren().size());
    }

    public void change_to_main() {
        Player a = main.getPlayernow();
        jumlah_turn.getChildren().clear();
        player_saat_ini.getChildren().clear();
        set_turn();
        set_player();
        init();
        add_to_deck_aktif();
        add_to_ladang();
        a.print_lahan();
        board.getChildren().clear();
        board.getChildren().setAll(ambil_kartu, pane_ladang);

    }

    public void add_kartu_ke_shuffle_field() {
        shuffle_panel.getChildren().clear();
        Player a = main.getPlayernow();
        System.out.println(a.getName());
        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

                String kata = a.get_kartu(idx);
                Pane card_shuffle = new Pane();
                card_shuffle.getChildren().add(new Label("Kartu " + kata));
                card_shuffle.setStyle("-fx-background-color: white; -fx-pref-width: 60; -fx-pref-height: 60;");
                card_shuffle.setId(a.get_deck(idx));
                card_shuffle.setOnMouseClicked(event -> remove_pane(a, card_shuffle));
                idx += 1;
                shuffle_panel.add(card_shuffle, i, j);
            }
        }
    }

    public void remove_pane(Player player, Pane pane) {
        if (player.getCard_in_one_turn() < 4) {
            player.add_ciot();
            player.shuffle_to_deck_aktif(pane.getId());
            player.remove_deck(pane.getId());
            shuffle_panel.getChildren().remove(pane);
            player.print_deck_aktif();
            player.print_deck();
        }
    }

    public void set_turn() {
        jumlah_turn.getChildren().addAll(new Label("Turn: " + main.getTotalturn()));
    }

    public void set_player() {
        Player a = main.getPlayernow();
        player_saat_ini.getChildren().addAll(new Label("Player saat ini: " + a.getName()));
    }

    public void setBoard(Board board) {
        main = board;
    }
}
