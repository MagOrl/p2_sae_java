import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurSuppDuPan implements EventHandler<ActionEvent> {
    private Client cli;
    private int idmag;
    private Livre liv;

    public ControleurSuppDuPan(Client cli, int idmag, Livre liv) {
        this.cli = cli;
        this.idmag = idmag;
        this.liv = liv;
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.cli.suppPanier(idmag, liv);
        
    }

}
