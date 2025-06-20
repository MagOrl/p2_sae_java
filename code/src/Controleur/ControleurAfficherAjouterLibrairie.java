import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurAfficherAjouterLibrairie implements EventHandler<ActionEvent> {
    /**
     * La fenetre du menu administrateur
     */
    private MenuAdmin vue;

    /**
     * Constructeur prennant en paramètre la vue
     * 
     * @param vue
     */
    public ControleurAfficherAjouterLibrairie(MenuAdmin vue) {
        this.vue = vue;
    }

    /**
     * quand une action est détéctée, le menu met au centre le contenu de la fentre
     * ajouterLibrarie au centre du borderpane
     */
    public void handle(ActionEvent event) {
        vue.setCenter(vue.afficherAjouterLibrairie());
    }
}