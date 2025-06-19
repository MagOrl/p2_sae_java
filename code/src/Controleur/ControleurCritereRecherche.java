import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class ControleurCritereRecherche implements ChangeListener<String>{

    private String critereActuel ;
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;
    
    public ControleurCritereRecherche(String critereActuel, MenuGererStocksGlobaux vue, AdministrateurBD modele){
        this.critereActuel = critereActuel;
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        this.vue.setCritereActuel(arg2);
    }
}
