import javafx.event.EventHandler;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurAjouteCommande implements EventHandler<ActionEvent> {
    /**
     * Le menu vendeur
     */
    private MenuVendeur vue;
    /**
     * Le modèle qui va permetre de faire les requètes SQL
     */
    private VendeurBD modele;
    
    /**
     * Constructeur prennant en paramètre la vue et le modèle
     * @param vue
     * @param modele
     */
    public ControleurAjouteCommande(MenuVendeur vue, VendeurBD modele) {
        this.vue = vue;
        this.modele = modele;
    }
    /**
     * À chaque actions, une nouvelle commande est ajouté  
     */
    @Override
    public void handle(ActionEvent event) {
        String isbn = this.vue.getIsbn2();
        int qte = this.vue.getQte2();
        try{
            this.vue.getGpCommande().ajouteCommande(modele.trouveLivreIsbn(isbn), qte);
            this.vue.majVbCommande(this.vue.getGpCommande());
        }catch(NullPointerException e){
            this.vue.popUpLivreInexistant().show();
        }
        catch(SQLException e){
            this.vue.popUpErreurSQL().show();
        }
    }
}