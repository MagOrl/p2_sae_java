import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;

public class ControleurCommanderV implements EventHandler<ActionEvent>{
    private MenuVendeur vue;
    private VendeurBD modele;

    public ControleurCommanderV(MenuVendeur vue, VendeurBD modele){
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            String isbn = this.vue.getIsbn2();
            int qte = this.vue.getQte2();
            String idCli = this.vue.getIdentifiant();
            String mdp = this.vue.getMdp();
            this.modele.passerCommandeClient(this.modele.trouveClient(idCli, mdp), this.vue.getGpCommande().getCommande(), this.modele.trouveLibrairie(this.vue.getVendeur().getMag().getNom(), -1));
            this.vue.majVbCommande(new GridPaneCommande(this.vue, this.modele));
            this.vue.popUpCommandePassee().show();
        }catch(NumberFormatException e){
            this.vue.popUpNumberFormatException().show();;
        }catch(NullPointerException e){
            this.vue.popUpCommandeVide().show();;
        }
        catch(SQLException e){
            this.vue.popUpErreurSQL().show();;
        }
    }
}
