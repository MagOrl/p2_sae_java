import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;



public class ControleurHistorique implements EventHandler<ActionEvent>{
    
    private AppliLib appli;
    private MenuClient menuCli;

    public ControleurHistorique(AppliLib appli,MenuClient menuCli) {
        this.appli = appli;
        this.menuCli = menuCli;

    }

    @Override
    public void handle(ActionEvent event) { 
        this.appli.afficheMenuHistorique(this.menuCli);
    }

}