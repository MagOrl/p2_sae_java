import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class ControleurChoixLibrairie implements EventHandler<ActionEvent>{

    private Text librairie;
    private ObservableList<String> lesLibrairies;
    
    public ControleurChoixLibrairie(Text librairie, ObservableList<String> lesLibrairies){
        this.librairie = librairie;
        this.lesLibrairies = lesLibrairies;
    }

    @Override
    public void handle(ActionEvent event){
        
    }
}
