import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class ControleurChoixLibrairie implements ChangeListener<String>{

    private Text librairie;
    
    public ControleurChoixLibrairie(Text librairie){
        this.librairie = librairie;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        this.librairie.setText(arg2);
    }
}
