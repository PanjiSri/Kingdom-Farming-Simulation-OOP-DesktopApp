package org.example.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

//import java.lang.classfile.Label;
import java.nio.file.Paths;

public class CardPane extends Pane {
    private ImageView imageView;
    private Card card;

    public CardPane(Card card) {
        // Load the image
        this.card = card;

        Image image = new Image(getClass().getResource(this.card.getImgPath()).toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(100);  // Set image width
        imageView.setFitHeight(100); // Set image height

        // Add the ImageView to the Pane
        getChildren().add(imageView);
        
        // Enable dragging for the pane
        setOnDragDetected(event -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("pane");
            db.setContent(content);
            event.consume();
        });
    }
}