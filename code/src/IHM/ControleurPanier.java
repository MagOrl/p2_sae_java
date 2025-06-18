import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurPanier implements EventHandler<ActionEvent> {

    private AppliLib appli;

    public ControleurPanier(AppliLib appli){
        this.appli = appli;
    }

    public void handle(ActionEvent event){
        this.appli.afficheMenuPanier();
    }
}