import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleurHistorique implements EventHandler<ActionEvent>{
    
    private AppliLib appli;

    public ControleurHistorique(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) { 
        this.appli.afficheHistorique();
    }

}