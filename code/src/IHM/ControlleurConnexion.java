import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControlleurConnexion implements EventHandler<ActionEvent> {
    private AppliLib app;

    public ControlleurConnexion(AppliLib app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent arg0) {
        switch (this.app.getMenuAcc().getValComboBox()) {
            case "Client":
                try {
                    if (this.app.getClientBD().connectClient(this.app.getMenuAcc().getValTfID(),
                            this.app.getMenuAcc().getValPf())) {

                    } else {
                        this.app.getMenuAcc().resetFields();
                        this.app.popUpMauvaiseSaisie().showAndWait();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Vendeur":
                try {
                    if (this.app.getVendeurBD().connectVendeur(this.app.getMenuAcc().getValTfID(),
                            this.app.getMenuAcc().getValPf())) {

                    } else {
                        this.app.getMenuAcc().resetFields();
                        this.app.popUpMauvaiseSaisie().showAndWait();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("vend");
                break;
            case "Administrateur":
                try {
                    if (this.app.getAdminBD().connectAdmin(this.app.getMenuAcc().getValTfID(),
                            this.app.getMenuAcc().getValPf())) {

                    } else {
                        this.app.getMenuAcc().resetFields();
                        this.app.popUpMauvaiseSaisie().showAndWait();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("vend");
                break;
            default:
                this.app.popUpPasMitDeUser().showAndWait();
                break;
        }

    }

}
