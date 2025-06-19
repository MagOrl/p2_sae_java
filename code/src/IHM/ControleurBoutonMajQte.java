import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurBoutonMajQte implements EventHandler<ActionEvent>{
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;

    public ControleurBoutonMajQte(MenuGererStocksGlobaux vue, AdministrateurBD modele){
        this.vue = vue;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event){
        try{
            if(modele.majQteLivre(this.vue.getISBNQte(), modele.trouveLibrairie(this.vue.getLibrairieActuelle()), Integer.parseInt(this.vue.getNouvQte()))){
                vue.resetTFMajQte();
                vue.popUpQteMaj().show();
            }else{
                vue.resetTFMajQte();
                vue.popUpLivreInexistant().show();
            }
        }catch(NumberFormatException e){
            vue.popUpNumberFormatException().show();
        }catch(QteInfAZeroException e){
            vue.popUpQteInfAZero().show();
        }
        catch(SQLException e){
            vue.popUpAjouterLivreSQLException().show();
        }
    }
}
