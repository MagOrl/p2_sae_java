import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.sql.SQLException;

public class ControleurAjouterPanier implements EventHandler<ActionEvent> {

    private MenuClient menu;

    public ControleurAjouterPanier(MenuClient menu){
        this.menu = menu;
    }


    public void handle(ActionEvent event){
        Livre livre = this.menu.getLivreSelect();
        try {
            this.menu.getClient().addPanier(this.menu.getClientBD().trouveMagasin(livre), livre, 1);
        } catch (SQLException e) {
            this.menu.getAppli().popUpPasDeMagasins();
        } catch (TopDeLivreException e){
            this.menu.getAppli().popUpPanierPlein();
        } catch (MauvaiseQuantiteException e){
            e.debug();
        }
    }
}