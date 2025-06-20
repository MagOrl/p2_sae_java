import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class ControleurTransfererLivre implements EventHandler<ActionEvent> {
    
    private AppliLib appli;
    private MenuVendeur menuVend;
    public ControleurTransfererLivre(AppliLib appli,MenuVendeur menuVend) {
        this.appli = appli;
        this.menuVend = menuVend;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreTransfererLivre(this.menuVend);
    }
}