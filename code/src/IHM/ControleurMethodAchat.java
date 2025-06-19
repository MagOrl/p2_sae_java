import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurMethodAchat implements ChangeListener<Boolean> {
    private MenuPanier menu;
    private String laMethod;

    public ControleurMethodAchat(MenuPanier menu, String laMethod) {
        this.menu = menu;
        this.laMethod = laMethod;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
        this.menu.setMethodComm(this.laMethod);
    }

}
