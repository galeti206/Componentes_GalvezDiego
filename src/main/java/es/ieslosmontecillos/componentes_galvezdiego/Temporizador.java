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

/**
 *  Temporizador con cuenta regresiva que ejecuta una función indicada al finalizar.
 *  <p>Este componente se trata de un temporizador gráfico que realiza una cuenta atrás con las siguientes características:</p>
 *  <ul>
 *      <li>El componente se basa es una etiqueta que dispone de una propiedad llamada tiempo, de tipo int,
 *      que representa los segundos que van a transcurrir desde su creación hasta que llega a cero.</li>
 *      <li>Cada segundo disminuye en uno el valor de tiempo, que visualizamos en el texto de la etiqueta.</li>
 *      <li>Para programarlo se utiliza un atributo de tipo javafx.animation.Timeline, que será el que marque
 *      cuando se cambia el valor de tiempo.</li>
 *      <li>Al finalizar la cuenta atrás se lanza un evento de finalización de cuenta que puede ser recogido
 *      por la aplicación en la que se incluya el componente.</li>
 *  </ul>
 *
 *
 * @author Diego Gálvez Andrade
 * @version 1.0
 * @since JavaFX 2.0
 */

public class Temporizador extends AnchorPane {
    @FXML
    private Label lbl1;
    @FXML
    private Label lblSegundos;
    @FXML
    private Label lbl2;
    /**
     * Segundos del temporizador. Posee un escuchador y avisa cuando varía su valor.
     */
    private IntegerProperty segundos;
    private Timeline temporizador;
    /**
     * Manejador de eventos para la función de finalización el temporizador. Maneja la función a ejecutar cuando finaliza el temporizador.
     */
    private EventHandler<ActionEvent> onFinished;
    private boolean autoReverse;

    /**
     * Crea un nuevo temporizador.
     */
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

    /**
     * Inicia el temporizador. Método que inicia el temporizador y dentro de el se define la funcionalizad para cuando finalice la cuenta.
     */
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

    /**
     * Establece la propiedad onFinished del temporizador
     * @param onFinished Evento onFinished a ejecutar
     */
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

    /**
     * Comprueba el reinicio automático del temporizador.
     * @return true si está activado, false si no lo está
     */
    public boolean isAutoReverse() {
        return autoReverse;
    }

    /**
     * Establece la propiedad autoReverse.
     * @param autoReverse true si está activado; false si no lo está
     */
    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
        temporizador.setAutoReverse(autoReverse);
    }
}
