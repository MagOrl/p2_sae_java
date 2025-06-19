import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControleurSuppDuPan implements EventHandler<ActionEvent> {
    private Client cli;
    private int idmag;
    private Livre liv;
    private VBox panier;
    private VBox vb;
    private MenuPanier menuPanier;

    public ControleurSuppDuPan(Client cli, int idmag, Livre liv, VBox panier, VBox vb, MenuPanier menuPanier) {
        this.cli = cli;
        this.idmag = idmag;
        this.liv = liv;
        this.panier = panier;
        this.vb = vb;
        this.menuPanier = menuPanier;
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.cli.suppPanier(idmag, liv);
        this.panier.getChildren().remove(this.vb);
        this.menuPanier.initCenter();
    }

}
