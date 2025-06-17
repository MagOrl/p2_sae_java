import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurDeconnexion implements EventHandler<ActionEvent> {

    private AppliLib appli;

    public ControleurDeconnexion(AppliLib appli){
        this.appli = appli;
    }


    public void handle(ActionEvent event){
        this.appli.afficheMenuAcceuil();
    }
}