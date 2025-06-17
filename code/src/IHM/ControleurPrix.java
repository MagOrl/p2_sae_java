import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurPrix implements ChangeListener<Boolean>{
    private TextField prix;
    private MenuGererStocksGlobaux vue;

    public ControleurPrix(TextField tf, MenuGererStocksGlobaux vue){
        this.prix = tf;
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
        if(!newValue){
            try{
                Double.parseDouble(this.prix.getText());
            }catch(NumberFormatException e){
                this.prix.setText("");
            }
        }
    }
}

    

 
    


