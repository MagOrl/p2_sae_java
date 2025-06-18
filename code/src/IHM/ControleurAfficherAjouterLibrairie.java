import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControleurAfficherAjouterLibrairie implements EventHandler<ActionEvent> {

    private MenuAdmin vue;

    public ControleurAfficherAjouterLibrairie(MenuAdmin vue){
        this.vue = vue;
    }

    public void handle(ActionEvent event){
        vue.setCenter(vue.afficherAjouterVendeur());
    }
}