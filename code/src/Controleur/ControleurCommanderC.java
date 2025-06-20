
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;

public class ControleurCommanderC implements EventHandler<ActionEvent> {
    private AppliLib appli;
    private MenuPanier menuPanier;
    private Client client;

    public ControleurCommanderC(AppliLib appli, MenuPanier menuPanier, Client client) {
        this.appli = appli;
        this.menuPanier = menuPanier;
        this.client = client;
    }

    @Override
    public void handle(ActionEvent event) {
        Optional<ButtonType> reponse = this.appli.popUpSurDeCommender().showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            try {
                this.appli.getClientBD().commandeLivre(client.getPanier(), client, menuPanier.getMethodComm());
                this.menuPanier.clearPan();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}

