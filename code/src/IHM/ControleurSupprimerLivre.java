import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurSupprimerLivre implements EventHandler<ActionEvent>{

    private AdministrateurBD modele;
    private MenuGererStocksGlobaux vue;
    private Button btSuppr;

    public ControleurSupprimerLivre(MenuGererStocksGlobaux vue, AdministrateurBD modele, Button btSuppr){
        this.vue = vue;
        this.btSuppr = btSuppr;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event){
        try{
            GridPaneStocks gpStocks = this.vue.getGpStocks();
            Livre livreASuppr = gpStocks.getLivreSuppr(btSuppr);
            this.modele.SupprimerLivre(livreASuppr.getIsbn(), this.modele.trouveLibrairie(this.vue.getLibrairieActuelle()));
            GridPaneStocks nouvGPStocks = new GridPaneStocks(this.modele.afficheStock(this.modele.trouveLibrairie(this.vue.getLibrairieActuelle())), gpStocks.getIndex(), vue, modele);
            this.vue.majVueGPStocks(nouvGPStocks);
            this.vue.majVBoxCenter(nouvGPStocks);
        }catch(NullPointerException e){
            this.vue.popPasDeLibrairie().show();
        }catch(SQLException e){
            this.vue.popUpAjouterLivreSQLException().show();
        }
    }
}