import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurCreerCompteVendeur implements EventHandler<ActionEvent> {

    private MenuAdmin vue;

    public ControleurCreerCompteVendeur(MenuAdmin vue){
        this.vue = vue;
    }

    public void handle(ActionEvent event){
        vue.setCenter(vue.afficherCreerVendeur());
    }
}