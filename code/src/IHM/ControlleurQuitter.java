import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public class ControlleurQuitter implements EventHandler<ActionEvent> {
    private AppliLib app;

    public ControlleurQuitter(AppliLib app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        Optional<ButtonType> reponse = this.app.popUpQuitte().showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            this.app.quitte();
        }
    }

}