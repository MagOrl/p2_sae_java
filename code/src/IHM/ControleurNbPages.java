import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurNbPages implements ChangeListener<String>{
    private TextField tf;

    public ControleurNbPages(TextField tf) {
        this.tf = tf;
    }

    public boolean contientAutreQueChiffre(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

        if (!arg2.isBlank() && contientAutreQueChiffre(arg2)) {
            tf.setText(arg1);
            tf.setStyle(AppliLib.styleTextField + " -fx-border-color: red;");
        } else {
            tf.setStyle(AppliLib.styleTextField);
        }
        
    }
}

    

 
    

