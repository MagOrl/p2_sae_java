
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurCreationCompte implements EventHandler<ActionEvent> {
    private AppliLib app;

    public ControleurCreationCompte(AppliLib app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        app.afficheMenuCreaCompte();
    }

}
