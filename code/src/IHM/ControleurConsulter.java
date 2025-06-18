import javafx.event.EventHandler;
import javafx.event.ActionEvent ;


public class ControleurConsulter implements EventHandler<ActionEvent>{
    
    private AppliLib appli;
    private MenuClient menu;

    public ControleurConsulter(AppliLib appli, MenuClient menu) {
        this.appli = appli;
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent event) { 
        
        this.appli.afficheMenuClient();
    }
}