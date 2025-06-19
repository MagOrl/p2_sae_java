import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurInfoBDTxtField implements ChangeListener<String> {
    private List<String> data;
    private String qui;

    public ControleurInfoBDTxtField(List<String> data, String qui) {
        System.out.println("truc");
        this.data = data;
        this.qui = qui;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        System.out.println(arg1);
        System.out.println(arg2);
        this.data.set(Integer.parseInt(qui)-1, arg2);
        System.out.println(data);
    }
}
