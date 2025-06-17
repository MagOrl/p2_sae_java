
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ControleurUtilisateur implements ChangeListener<String> {
    private GridPane gp;
    private ComboBox<String> nomMag;
    public ControleurUtilisateur(GridPane gp,ComboBox<String> nomMag) {
        this.gp = gp;
        this.nomMag = nomMag;
    }



    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        if(arg2.equals("Vendeur")){
            this.gp.add(new Text("Nom du magasin"), 0, 4);
            this.gp.add(nomMag, 1, 4);
        }else if(arg1.equals("Vendeur")){
            this.gp.getChildren().remove(this.gp.getChildren().getLast());
            this.gp.getChildren().remove(this.gp.getChildren().getLast());
        }
    }

}
