import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurNbPages implements ChangeListener<Boolean>{
    private TextField nbPages;
    private MenuGererStocksGlobaux vue;

    public ControleurNbPages(TextField tf, MenuGererStocksGlobaux vue){
        this.nbPages = tf;
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
        if(!newValue){
            try{
                Integer.parseInt(this.nbPages.getText());
            }catch(NumberFormatException e){
                this.nbPages.setText("");
            }
        }
    }
}

    

 
    

