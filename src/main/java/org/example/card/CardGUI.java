package org.example.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class CardGUI extends VBox {
    private ImageView imageView;
    private VBox vBox;
    private Card card;

    public CardGUI(Card card) {
        // Load the image
        this.card = card;
        Image image = new Image(this.getClass().getResource(this.card.getImgPath()).toExternalForm());
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(100.0);
        this.imageView.setFitHeight(100.0);
        this.vBox = new VBox();
        Label label = new Label(this.card.getName());
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");
        this.vBox.getChildren().addAll(label, this.imageView);
        this.vBox.setStyle("-fx-pref-width: 110.0; -fx-pref-height: 130.0; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: beige; -fx-padding: 5; -fx-spacing: 5; -fx-alignment: center; -fx-border-radius: 10; -fx-background-radius: 10;");
        this.getChildren().add(this.vBox);
    }

    public VBox getVBox() {
        return this.vBox;
    }

    public Card getCard() {
        return this.card;
    }

    
}
