package org.example.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class CardGUI {
    private ImageView imageView;
    private VBox end;
    private Card card;

    public CardGUI(Card card) {
        // Load the image
        this.card = card;
        Image image = new Image(getClass().getResource(this.card.getImgPath()).toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        // Add the ImageView to the Pane
        VBox vBox = new VBox();
        vBox.getChildren().addAll(new Label("Beruang"), imageView);
        vBox.setStyle("-fx-pref-width: 70.0; -fx-pref-height: 300.0; -fx-border-color: black; -fx-max-height: 120; -fx-max-width: 120.0");
        this.end = new VBox();
        end.getChildren().addAll(vBox);
    }

    public VBox getVBox() {
        return end;
    }
}
