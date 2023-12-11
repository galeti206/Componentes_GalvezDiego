package es.ieslosmontecillos.componentes_galvezdiego;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class Rectangle extends javafx.scene.shape.Rectangle {
    private Timeline timeline;

    public Rectangle(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rectangle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        final KeyValue kv = new KeyValue(super.xProperty(), 300);
        final KeyFrame kf = new KeyFrame(Duration.seconds(3), kv);

        timeline.getKeyFrames().add(kf);

        timeline.play();
    }
}
