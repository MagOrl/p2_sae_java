import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurAfficherAjouterLibrairie implements EventHandler<ActionEvent> {

    private MenuAdmin vue;

    public ControleurAfficherAjouterLibrairie(MenuAdmin vue){
        this.vue = vue;
    }

    public void handle(ActionEvent event){
        vue.setCenter(vue.afficherAjouterLibrairie());
    }
}