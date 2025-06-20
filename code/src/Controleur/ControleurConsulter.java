import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurConsulter implements EventHandler<ActionEvent> {

    private Client cli;
    private ClientBD cliBD;
    private RecomDynamique livDyna;

    public ControleurConsulter(Client cli, RecomDynamique liv, ClientBD cliBD) {
        this.cli = cli;
        this.livDyna = liv;
        this.cliBD = cliBD;
    }

    @Override
    public void handle(ActionEvent event) {
        if (livDyna.getLivre() != null) {
            try {
                this.cli.addPanier(this.cliBD.trouveMagasin(this.livDyna.getLivre()), this.livDyna.getLivre(),
                        this.livDyna.getLivre().getQte());
            } catch (TopDeLivreException e) {
                e.printStackTrace();
            } catch (MauvaiseQuantiteException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}