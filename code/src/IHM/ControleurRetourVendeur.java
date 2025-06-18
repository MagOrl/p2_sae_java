import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleurRetourVendeur implements EventHandler<ActionEvent>{
    
    private AppliLib appli;
    

    public ControleurRetourVendeur(AppliLib appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) { 
        if (this.appli.getUtilisateur() instanceof Vendeur){
            Vendeur vendeur = (Vendeur) this.appli.getUtilisateur();
            this.appli.afficheMenuVendeur(vendeur);
        }else{
            
        }
    }

}