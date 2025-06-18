import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class ControleurTransfererLivre implements EventHandler<ActionEvent> {
    
    private AppliLib appli;

    public ControleurTransfererLivre(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreTransfererLivre();
    }
}