import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleurHistorique implements EventHandler<ActionEvent>{
    
    private MenuClient menu;
    private AppliLib appli;

    public ControleurHistorique(MenuClient menu, Button btnHist, AppliLib appli) {
        this.menu = menu;
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) { 
        
        if (this.menu.instanceOf(MenuHistorique) || this.menu.instanceOf(MenuInfosPersos)) {
          
            this.appli.afficheMenuClient();
        }else{
            this.appli.afficheMenuVendeur();
        }
    }

}