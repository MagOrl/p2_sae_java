import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurInfosPersos implements EventHandler<ActionEvent> {

    private AppliLib appli;

    public ControleurInfosPersos(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheInfosPersos();
    }

}