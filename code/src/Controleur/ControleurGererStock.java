import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurGererStock implements EventHandler<ActionEvent> {
    private AppliLib appli;
    private Administrateur adm;

    public ControleurGererStock(AppliLib appli, Administrateur adm) {
        this.appli = appli;
        this.adm = adm;
    }

    public void handle(ActionEvent event){
        this.appli.afficheMenuGererStocksGlobaux(this.adm);   
    }
}
