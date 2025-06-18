import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControleurAjouterLibrairie implements EventHandler<ActionEvent> {

    private MenuAdmin vue;
    private AdministrateurBD adminBD;
    private TextField tfIdmag;
    private TextField tfNom;
    private TextField tfVille;

    public ControleurAjouterLibrairie(MenuAdmin vue,AdministrateurBD adminBD, TextField tfIdmag, TextField tfNom, TextField tfVille){
        this.vue = vue;
        this.adminBD = adminBD;
        this.tfNom = tfNom;
        this.tfVille = tfVille;
    }

    public void handle(ActionEvent event){
        String nom = tfNom.getText();
        String ville = tfVille.getText();
        String idmag = tfIdmag.getText();

        try {
            Alert alert = this.vue.popUpConfirmerLibrairie(nom, ville);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                this.adminBD.ajouteNouvelleLibrairie(nom, ville, idmag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}