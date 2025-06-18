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
            if(modele.majQteLivre(this.vue.getISBN(), modele.trouveLibrairie(this.vue.getLibrairieActuelle()), Integer.parseInt(this.vue.getQuantite()))){
                vue.resetTFMajQte();
                vue.popUpQteMaj();
            }else{
                vue.popUpLivreInexistant();
            }
        }catch(NumberFormatException e){
            vue.popUpNumberFormatException();
        }catch(QteInfAZeroException e){
            vue.popUpQteInfAZero();
        }
        catch(SQLException e){
            vue.popUpAjouterLivreSQLException();
        }
    }
}
