import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

public class ControleurThemes implements EventHandler<ActionEvent> {

    private MenuClient menu;

    public ControleurThemes(MenuClient menu) {
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        RadioButton radiobouton = (RadioButton) actionEvent.getTarget();
        String nomDuRadiobouton = radiobouton.getText();
        this.menu.setTheme(nomDuRadiobouton);
    }
}