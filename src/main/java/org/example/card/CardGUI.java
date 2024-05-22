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
    private VBox vBox;
    private Card card;

    public CardGUI(Card card) {
        // Load the image
        this.card = card;

        Image image = new Image(getClass().getResource(this.card.getImgPath()).toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        // Add the ImageView to the Pane
        this.vBox = new VBox();

        Label label = new Label(this.card.getName());
        label.setStyle( "-fx-font-size: 14;" + 
                        "-fx-font-weight: bold;" + 
                        "-fx-font-family: 'Comic Sans MS';");

        vBox.getChildren().addAll(label, imageView);
        vBox.setStyle( "-fx-pref-width: 110.0;" +
                       "-fx-pref-height: 130.0;" +
                       "-fx-border-color: black;" + 
                       "-fx-border-width: 1;" + 
                       "-fx-background-color: beige;" + 
                       "-fx-padding: 5;" +
                       "-fx-spacing: 5;" +
                       "-fx-alignment: center;" + 
                       "-fx-border-radius: 10;" +
                       "-fx-background-radius: 10;");
    }

    public VBox getVBox() {
        return this.vBox;
    }

    
}
