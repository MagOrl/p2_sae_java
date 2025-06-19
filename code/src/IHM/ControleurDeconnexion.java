import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import javafx.event.ActionEvent;


public class ControleurDeconnexion implements EventHandler<ActionEvent> {
    
    private AppliLib appli;

    public ControleurDeconnexion(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        
        Alert alert = this.appli.popUpDeconnexion();
        Optional<ButtonType> resultat = alert.showAndWait();

        if (resultat.isPresent() && resultat.get() == ButtonType.OK) {
            this.appli.afficheMenuAcceuil(); 
        }

    }
}