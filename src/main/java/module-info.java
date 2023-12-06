module es.ieslosmontecillos.componentes_galvezdiego {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.componentes_galvezdiego to javafx.fxml;
    exports es.ieslosmontecillos.componentes_galvezdiego;
}