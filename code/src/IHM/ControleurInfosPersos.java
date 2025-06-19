import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurInfosPersos implements EventHandler<ActionEvent> {

    private AppliLib appli;
    private MenuClient menuClient;

    public ControleurInfosPersos(AppliLib appli,MenuClient menuClient) {
        this.appli = appli;
        this.menuClient = menuClient;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheMenuInfosPersos(this.menuClient);
    }

}