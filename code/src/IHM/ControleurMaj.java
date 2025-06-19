import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class ControleurMaj implements EventHandler<ActionEvent> {

    private VendeurBD modele;
    private FenetreMajQte fenetreMajQte;
    private AppliLib appli;
    private MenuVendeur menu;

    public ControleurMaj(VendeurBD modele, FenetreMajQte fenetreMajQte, AppliLib appli, MenuVendeur menu) {

        this.modele = modele;
        this.fenetreMajQte = fenetreMajQte;
        this.appli = appli;
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            
            String isbn = "" + this.fenetreMajQte.getIsbn() + "";
            int qte = this.fenetreMajQte.getQte();
            Magasin librairie = this.modele.trouveLibrairie(this.menu.getLibrairie(),-1);
            if (isbn.isEmpty() || (""+qte+"").isEmpty()) {
                this.appli.popUpMettreToutesLesVal().showAndWait();
                return;
            }
            this.modele.majQteLivre(isbn, librairie, qte);
            this.fenetreMajQte.resetTFMaj();
            this.appli.popUpModifQte().show();

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