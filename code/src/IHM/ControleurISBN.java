import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurISBN implements ChangeListener<Boolean>{
    private TextField isbn;
    private MenuGererStocksGlobaux vue;

    public ControleurISBN(TextField tf, MenuGererStocksGlobaux vue){
        this.isbn = tf;
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
        if(!newValue){
            try{
                Double.parseDouble(this.isbn.getText());
            }catch(NumberFormatException e){
                this.isbn.setText("");
            }
        }
    }
}

    

 
    


