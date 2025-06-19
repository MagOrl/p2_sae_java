import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class ControleurMaj implements EventHandler<ActionEvent> {

    private VendeurBD modele;
    private MenuVendeur menu;
    private AppliLib appli;

    public ControleurMaj(VendeurBD modele, MenuVendeur menu, AppliLib appli) {

        this.modele = modele;
        this.menu = menu;
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String isbn = ""+this.menu.getIsbn2()+"";
            int qte = this.menu.getQte2();
            Magasin librairie = this.modele.trouveLibrairie(this.menu.getLibrairie(), -1);
            if (isbn.isEmpty() || qte <= 0) { 
                this.appli.popUpMettreToutesLesVal().showAndWait();
                return;
            }
            this.modele.majQteLivre(isbn, librairie, qte);
            this.menu.resetTFAjouterLivre();
            this.appli.popUpAjouteLivre().show();

        } catch (QteInfAZeroException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de quantité");
            alert.setHeaderText(null);
            alert.setContentText("La quantité ne peut pas être inférieure ou égale à zéro.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}