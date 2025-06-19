import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControleurGraph implements ChangeListener<String> {
    private BorderPane bp;
    private AdministrateurBD adminBD;

    public ControleurGraph(BorderPane bp, AdministrateurBD adminBD) {
        this.bp = bp;
        this.adminBD = adminBD;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        try {
            bp.setCenter(new LesGraphic(arg2, adminBD));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
