import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.sql.SQLException;

public class ControleurAjouterPanier implements EventHandler<ActionEvent> {

    private MenuClient menu;
    private Livre liv;

    public ControleurAjouterPanier(MenuClient menu,Livre liv){
        this.menu = menu;
        this.liv = liv;
    }

    public void handle(ActionEvent event) {
        try {
            this.menu.getClient().addPanier(menu.getClientBD().trouveLibrairie(this.menu.getLibActuelle()).getId(), liv, liv.getQte());

        } catch (TopDeLivreException | MauvaiseQuantiteException | SQLException e) {
            e.printStackTrace();
        }
    }
}