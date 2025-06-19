import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurChangeInfoBD implements EventHandler<ActionEvent> {
    private AppliLib app;

    public ControleurChangeInfoBD(AppliLib app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.app.afficheInfoBD();
    }

}
