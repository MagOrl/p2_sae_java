import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurBoutonAjouter implements EventHandler<ActionEvent>{
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;

    public ControleurBoutonAjouter (MenuGererStocksGlobaux vue, AdministrateurBD modele){
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event){
        try{
            modele.AjouterLivre(this.vue.getISBN(), this.vue.getTitre(), this.vue.getNbPages(),  this.vue.getDatePubli(), this.vue.getPrix(), this.vue.getQuantite(), modele.trouveLibrairie(this.vue.getLibrairieActuelle()));
            vue.resetTFAjouterLivre();
            vue.popUpLivreAjoute().show();
        }catch(NullPointerException e){
            this.vue.popPasDeLibrairie().show();
        }catch(SQLException e){
            vue.popUpAjouterLivreSQLException();
        }
    }
}
