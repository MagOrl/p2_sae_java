import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurPanier implements EventHandler<ActionEvent> {

    private AppliLib appli;
    private MenuClient menuCli;

    public ControleurPanier(AppliLib appli, MenuClient menuCli) {
        this.appli = appli;
        this.menuCli = menuCli;
    }

    public void handle(ActionEvent event) {
        this.appli.afficheMenuPanier(menuCli);
    }
}