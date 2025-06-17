import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurBoutonAjouter implements EventHandler<ActionEvent>{
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;

    public ControleurBoutonAjouter (MenuGererStocksGlobaux vue, AdministrateurBD modele){
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event){
        try{
            modele.AjouterLivre(this.vue, null, null, null, null, null, null, null, null, null);
        }
    }
}
