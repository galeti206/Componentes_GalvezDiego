package es.ieslosmontecillos.componentes_galvezdiego;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends AnchorPane {
    @FXML
    private Label lbl1;
    @FXML
    private Label lblSegundos;
    @FXML
    private Label lbl2;
    private IntegerProperty segundos;
    private Timeline temporizador;
    private EventHandler<ActionEvent> onFinished;
    private boolean autoReverse;

    public Temporizador(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        segundos = new SimpleIntegerProperty();
        segundos.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number valorAnterior, Number nuevoValor) {
                System.out.println(nuevoValor.toString());
                lblSegundos.setText(nuevoValor.toString());
            }
        });
    }

    public void play(){
        temporizador = new Timeline();
        temporizador.setAutoReverse(false);

        final KeyValue kv = new KeyValue(segundos, 0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(segundos.doubleValue()), onFinished, kv);

        temporizador.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbl1.setText("El temporizador ha finalizado");
                lblSegundos.setText("");
                lbl2.setText("");
                System.out.println("El temporizador ha finalizado");
            }
        });

        temporizador.getKeyFrames().add(kf);
        temporizador.play();
    }

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {
        this.onFinished = onFinished;
    }
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {
        return (ObjectProperty<EventHandler<ActionEvent>>) onFinished;
    }
    public final EventHandler<ActionEvent> getOnFinished() {
        return onFinishedProperty().get();
    }

    public int getSegundos() {
        return segundos.get();
    }

    public IntegerProperty segundosProperty() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos.set(segundos);
    }

    public boolean isAutoReverse() {
        return autoReverse;
    }

    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
        temporizador.setAutoReverse(autoReverse);
    }
}
