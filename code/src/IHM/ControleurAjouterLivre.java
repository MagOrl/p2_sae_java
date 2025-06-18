import javafx.event.EventHandler;
import java.sql.SQLException;
import javafx.event.ActionEvent;

public class ControleurAjouterLivre implements EventHandler<ActionEvent> {

    private VendeurBD modele;
    private MenuVendeur menu;

    public ControleurAjouterLivre(VendeurBD modele, MenuVendeur menu) {

        this.modele = modele;
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent event) {

        try{
            this.modele.AjouterLivre(this.menu.getIsbn(),this.menu.getTitre(),this.menu.getNbPages(),this.menu.getDatePubli(),this.menu.getPrix(),this.menu.getQte(),this.modele.trouveLibrairie(this.menu.getLibrairie(), -1));
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}