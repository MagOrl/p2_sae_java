import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControleurAjouterLibrairie implements EventHandler<ActionEvent> {

    private AppliLib app;
    private AdministrateurBD adminBD;
    private TextField tf1;
    private TextField tf2;

    public ControleurAjouterLibrairie(AppliLib app,AdministrateurBD adminBD, TextField tf1, TextField tf2){
        this.app = app;
        this.adminBD = adminBD;
        this.tf1 = tf1;
        this.tf2 = tf2;
    }

    public void handle(ActionEvent event){
        String nom = tf1.getText();
        String ville = tf2.getText();
        if (nom.equals("") || ville.equals("")) {
            this.app.popUpMettreToutesLesVal().showAndWait();
        } else {
            try {
                Alert alert = this.app.popUpConfirmerLibrairie(nom, ville);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    this.adminBD.ajouteNouvelleLibrairie(nom, ville);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            
            }
        }
    }
}