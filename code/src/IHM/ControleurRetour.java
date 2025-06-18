import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleurRetour implements EventHandler<ActionEvent>{
    
    private AppliLib appli;
    

    public ControleurRetour(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) { 
        if (this.appli.getUtilisateur() instanceof Client){
            this.appli.afficheMenuClient();
        }else{
            // this.appli.afficheMenuVendeur();
        }
    }

}