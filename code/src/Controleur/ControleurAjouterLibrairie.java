import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControleurAjouterLibrairie implements EventHandler<ActionEvent> {
    /**
     * Le menu administrateur
     */
    private MenuAdmin vue;
    /**
     * Le modeèle, pour accèder au requetes SQL
     */
    private AdministrateurBD adminBD;
    /**
     * Le textefield ou l'ID du magasin est rentrée
     */
    private TextField tfIdmag;
    /**
     * Le textefield ou le nom du magasin est rentrée
     */
    private TextField tfNom;
    /**
     * Le textefield ou la ville du magasin est rentrée
     */
    private TextField tfVille;

    /**
     * Constructeur prennant en paramètre la vue, la connexion adminstrateur de la
     * base de donnéen, le textfield de l'idmag
     * et le textfield de l'id nom
     * 
     * @param vue
     * @param adminBD
     * @param tfIdmag
     * @param tfNom
     * @param tfVille
     */
    public ControleurAjouterLibrairie(MenuAdmin vue, AdministrateurBD adminBD, TextField tfIdmag, TextField tfNom,
            TextField tfVille) {
        this.vue = vue;
        this.adminBD = adminBD;
        this.tfNom = tfNom;
        this.tfVille = tfVille;
        this.tfIdmag = tfIdmag;
    }

    /**
     * À chaque action utilisateur, on récupère les données rentrer par
     * l'utilisateur
     * et on lui demande si il est sûr d'ajouter une nouvelle livbrairie dans sont
     * réseau
     */
    public void handle(ActionEvent event) {
        String nom = tfNom.getText();
        String ville = tfVille.getText();
        String idmag = tfIdmag.getText();

        try {
            Alert alert = this.vue.popUpConfirmerLibrairie(nom, ville);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.out.println(idmag);
                this.adminBD.ajouteNouvelleLibrairie(nom, ville, idmag);
            }
        } catch (NumberFormatException e) {
            this.vue.popUpNumberFormatExceptionIdLibrairie().show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}