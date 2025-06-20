import javafx.event.EventHandler;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurAjouteCommande implements EventHandler<ActionEvent> {
    
    private MenuVendeur vue;
    private VendeurBD modele;
    

    public ControleurAjouteCommande(MenuVendeur vue, VendeurBD modele) {
        this.vue = vue;
        this.modele = modele;
    }

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