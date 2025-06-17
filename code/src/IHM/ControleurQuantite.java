import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurQuantite implements ChangeListener<Boolean>{
    private TextField qte;
    private MenuGererStocksGlobaux vue;

    public ControleurQuantite(TextField tf, MenuGererStocksGlobaux vue){
        this.qte = tf;
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
        if(!newValue){
            try{
                Integer.parseInt(this.qte.getText());
            }catch(NumberFormatException e){
                this.qte.setText("");
            }
        }
    }
}

    

 
    

