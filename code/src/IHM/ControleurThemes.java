import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

/**
 * Controleur des radio boutons gérant le niveau
 */
public class ControleurThemes implements EventHandler<ActionEvent> {

    /**
     * modèle du jeu
     */
    private MenuClient menu;

    /**
     * @param modelePendu modèle du jeu
     */
    public ControleurThemes(MenuClient menu) {
        this.menu = menu;
    }

    /**
     * gère le changement de niveau
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        RadioButton radiobouton = (RadioButton) actionEvent.getTarget();
        String nomDuRadiobouton = radiobouton.getText();
        switch (nomDuRadiobouton) {
            case "Facile":
                this.menu.setThemeRech("");
                break;
            case "Médium":
                this.menu.setThemeRech("");
                break;
            case "Difficile":
                this.menu.setThemeRech("");
                break;
            default:
                this.menu.setThemeRech("");
                break;
        }
        
    }
}