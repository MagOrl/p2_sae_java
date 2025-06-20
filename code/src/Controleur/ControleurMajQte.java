import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurMajQte implements EventHandler<ActionEvent> {
    
    private AppliLib appli;
    private MenuVendeur mVendeur;

    public ControleurMajQte(AppliLib appli,MenuVendeur mVendeur) {
        this.appli = appli;
        this.mVendeur = mVendeur;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreMajQte(mVendeur);
    }
}