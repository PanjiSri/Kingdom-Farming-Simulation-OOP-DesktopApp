package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Shuffle {

    @FXML
    private GridPane shuffle_panel;

    public void add_card() {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                Pane pane = new Pane();
                pane.getChildren().add(new Label("Kartu"));
                pane.setStyle("-fx-background-color: green; -fx-border-color: black");
                shuffle_panel.add(pane, i, j);
            }
        }
    }
}
