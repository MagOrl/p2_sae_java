import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class ControleurChoixLibrairie implements ChangeListener<String>{

    private Text librairie;
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;
    
    public ControleurChoixLibrairie(Text librairie, MenuGererStocksGlobaux vue, AdministrateurBD modele){
        this.librairie = librairie;
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        this.librairie.setText(arg2);
        try{
            this.vue.setGpStocks(new GridPaneStocks(this.modele.afficheStock(this.modele.trouveLibrairie(this.vue.getLibrairieActuelle())), 0, vue, modele));
            this.vue.majVBoxCenter(this.vue.getGpStocks());
        }catch(SQLException e){
                   
        }
        
    }
}
