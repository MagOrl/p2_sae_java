import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurMajQte implements EventHandler<ActionEvent> {
    
    private AppliLib appli;

    public ControleurMajQte(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreMajQte();
    }
}