// MainController.java
package org.example;

import org.example.Board.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.Player.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class MainController {

    @FXML
    private GridPane ladang, shuffle_panel;

    @FXML
    private Pane pane_ladang, ambil_kartu;

    @FXML
    private Button reset_data, next_turn;

    @FXML
    private StackPane board;

    private Board main;

    public void init() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: black");
                ladang.add(pane, i, j);
            }
        }
    }

    public void change_label() {
        // Menampilkan jumlah anak dari GridPane
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                Pane pane = new Pane();
                VBox vbox = new VBox();
                vbox.getChildren().add(new Label("Kartu"));
                vbox.getChildren().add(new Label(Integer.toString(i)));
                pane.getChildren().add(vbox);
                pane.setStyle("-fx-background-color: #fff; -fx-pref-width: 20; -fx-pref-height: 20; -fx-border-color: red");
                ladang.add(pane, j, i);
            }
        }
    }

    public  void clear_pane() {
        System.out.println("Child pane: " + ladang.getChildren().size());
        ladang.getChildren().clear();
        System.out.println("Child pane: " + ladang.getChildren().size());
    }

    public  void initizialize_click() {
        reset_data.setOnAction(e -> clear_pane());
        next_turn.setOnAction(e -> change_to_shuffle());
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
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                String kata = a.get_kartu(idx);
                Pane card_shuffle = new Pane();
                card_shuffle.getChildren().add(new Label("Kartu" + kata));
                card_shuffle.setStyle("-fx-background-color: white");
                idx += 1;
                shuffle_panel.add(card_shuffle, i, j);
            }
        }
    }

    public void setBoard(Board board) {
        main = board;
    }


}
