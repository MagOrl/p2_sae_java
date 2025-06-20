import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRetourAdmin implements EventHandler<ActionEvent> {
    private AppliLib app;
    private Administrateur admin;

    public ControleurRetourAdmin(AppliLib app, Administrateur admin){
        this.app = app;
        this.admin = admin;
    }

    @Override
    public void handle(ActionEvent arg0) {
        app.afficheMenuAdmin(this.admin);
    }

}
