import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;

public class ControleurCreeCompte implements EventHandler<ActionEvent> {
    private AppliLib app;

    public ControleurCreeCompte(AppliLib app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        List<String> listFields = this.app.getMenuCreaCompte().getListDeFields();
        if (listFields.contains("")) {
            this.app.popUpMettreToutesLesVal().showAndWait();
        } else if (!this.app.getMenuCreaCompte().isPwdSame()) {
            this.app.getMenuCreaCompte().setPwdOnError(true);
            this.app.popUpPasMemeMotDePasse().showAndWait();

        } else {
            this.app.getMenuCreaCompte().setPwdOnError(false);
            try {
                this.app.getClientBD().creeClient(listFields.get(0), listFields.get(3), listFields.get(4),
                        listFields.get(5), listFields.get(7), listFields.get(6), listFields.get(2), listFields.get(8),
                        listFields.get(1));
                this.app.popUpCompteCree(listFields.get(4)).showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
