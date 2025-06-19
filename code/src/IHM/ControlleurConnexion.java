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
        String tf = this.app.getMenuAcc().getValTfID();
        String pf = this.app.getMenuAcc().getValPf();
        switch (this.app.getMenuAcc().getValComboBox()) {
            case "Client":
                try {
                    if (this.app.getClientBD().connectClient(tf, pf)) {
                        Client cli = this.app.getClientBD().trouveClient(tf, pf);
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
                    if (this.app.getVendeurBD().connectVendeur(tf, pf)) {
                        Vendeur vend = this.app.getVendeurBD().trouveVendeur(tf, pf, this.app.getValMag());
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
                    if (this.app.getAdminBD().connectAdmin(tf, pf)) {
                        this.app.afficheMenuAdmin(this.app.getAdminBD().trouveAdmin(tf, pf));

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
