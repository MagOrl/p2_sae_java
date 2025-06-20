import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;


public class ControleurTransferer implements EventHandler<ActionEvent> {

    private VendeurBD modele;
    private MenuVendeur menu;
    private AppliLib appli;
    private FenetreTransfererLivre fenetreTransfererLivre;

    public ControleurTransferer(VendeurBD modele, MenuVendeur menu, AppliLib appli, FenetreTransfererLivre fenetreTransfererLivre) {
        this.modele = modele;
        this.menu = menu;
        this.appli = appli;
        this.fenetreTransfererLivre = fenetreTransfererLivre;

    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Livre isbn = this.modele.trouveLivreIsbn(this.fenetreTransfererLivre.getIsbn());
            int qte = this.fenetreTransfererLivre.getQte();
            Magasin librairie = this.modele.trouveLibrairie(this.menu.getLibrairie(),-1);
            if (isbn==null || qte < 0 ) {
                this.appli.popUpMettreToutesLesVal().showAndWait();
                return;
            }
            this.modele.transfererLivreCommande(isbn, qte, librairie);
            this.fenetreTransfererLivre.resetTFTransferer();
            this.appli.popUpTransferer().show();

        } catch (QteInfAZeroException e) {
            e.printStackTrace();
            this.appli.popUpQteInfAZero().showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}