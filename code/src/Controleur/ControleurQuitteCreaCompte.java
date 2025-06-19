import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurQuitteCreaCompte implements EventHandler<ActionEvent> {

    private AppliLib app;
    public ControleurQuitteCreaCompte(AppliLib app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.app.afficheMenuAcceuil();
    }

}
