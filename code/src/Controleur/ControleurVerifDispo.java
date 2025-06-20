import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;


public class ControleurVerifDispo implements EventHandler<ActionEvent> {
    
    private MenuVendeur vue;
    private VendeurBD modele;



    public ControleurVerifDispo(MenuVendeur vue, VendeurBD modele) {
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            String isbn = this.vue.getIsbn2();
            int qte = this.vue.getQte2();
            this.vue.majVbDisponibilite(new GridPaneDisponibilite(this.vue, this.modele, this.modele.trouveLivreIsbn(isbn), qte));
        }catch(NumberFormatException e){
            this.vue.popUpNumberFormatException();
        }catch(NullPointerException e){
            this.vue.popUpLivreInexistant();
        }
        catch(SQLException e){
            this.vue.popUpErreurSQL();
        }
    }
}