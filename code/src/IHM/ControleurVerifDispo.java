import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class ControleurVerifDispo implements EventHandler<ActionEvent> {
    
    private AppliLib appli;

    public ControleurVerifDispo(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreVerifDispo();
    }
}