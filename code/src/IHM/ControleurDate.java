import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurDate implements ChangeListener<Boolean>{
    private TextField datepubli;
    private MenuGererStocksGlobaux vue;

    public ControleurDate(TextField tf, MenuGererStocksGlobaux vue){
        this.datepubli = tf;
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
        if(!newValue){
            try{
                Integer.parseInt(this.datepubli.getText());
            }catch(NumberFormatException e){
                this.datepubli.setText("");
            }
        }
    }


    
}
