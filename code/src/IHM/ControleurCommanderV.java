import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurCommanderV implements EventHandler<ActionEvent> {
    
    private AppliLib appli;

    public ControleurCommanderV(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
    // a implémenté
    }
}