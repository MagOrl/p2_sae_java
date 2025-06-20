import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;

public class ControleurAjouterLivre implements EventHandler<ActionEvent> {
    /**
     * Modèle de la vue pour les requètes vendeur
     */
    private VendeurBD modele;
    /**
     * Le menu vendeur
     */
    private MenuVendeur menu;
    /**
     * l'application principale
     */
    private AppliLib appli;

    /**
     * Constructeur qui initialise les attributs de la classe
     * 
     * @param modele
     * @param menu
     * @param appli
     */
    public ControleurAjouterLivre(VendeurBD modele, MenuVendeur menu, AppliLib appli) {

        this.modele = modele;
        this.menu = menu;
        this.appli = appli;
    }

    /**
     * À chaque action utilisateur, un livre est ajouter à la base de données si ses
     * éléments ne sont pas érroner
     */
    @Override
    public void handle(ActionEvent event) {
        try {
            String isbn = "" + this.menu.getIsbn() + "";
            String titre = this.menu.getTitre();
            String nbPages = "" + this.menu.getNbPages() + "";
            String datePubli = "" + this.menu.getDatePubli() + "";
            String prix = "" + this.menu.getPrix() + "";
            String qte = "" + this.menu.getQte() + "";
            Magasin librairie = this.modele.trouveLibrairie(this.menu.getLibrairie(), -1);
            if (isbn.isEmpty() || titre.isEmpty() || nbPages.isEmpty() || datePubli.isEmpty() || prix.isEmpty()
                    || qte.isEmpty()) {
                this.appli.popUpMettreToutesLesVal().showAndWait();
                return;
            }
            this.modele.AjouterLivre(isbn, titre, nbPages, datePubli, prix, qte, librairie);
            this.menu.resetTFAjouterLivre();
            this.appli.popUpAjouteLivre().show();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}