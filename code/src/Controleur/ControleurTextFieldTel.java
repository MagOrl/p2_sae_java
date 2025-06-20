import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ControleurTextFieldTel implements ChangeListener<String> {
    TextField tf;

    public ControleurTextFieldTel(TextField tf) {
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
            tf.setStyle(AppliLib.styleTextField+" -fx-border-color: red;");
        } else {
            tf.setStyle(AppliLib.styleTextField);
        }
        if (arg2.length() >= 11)
            tf.setText(arg1);
    }

}
