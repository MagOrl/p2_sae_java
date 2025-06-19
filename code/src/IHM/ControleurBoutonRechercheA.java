import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurBoutonRechercheA implements EventHandler<ActionEvent>{

    private TextField tf;
    private MenuGererStocksGlobaux vue;
    private AdministrateurBD modele;

    public ControleurBoutonRechercheA(MenuGererStocksGlobaux vue, AdministrateurBD modele, TextField tf){
        this.vue = vue;
        this.modele = modele;
        this.tf = tf;
    }

    @Override
    public void handle(ActionEvent event){
        System.out.println("check5");
        System.out.println(this.tf.getText());
        System.out.println(this.vue.getCritereActuel());
        try{
            switch (this.vue.getCritereActuel()) {
                case "Titre":
                    this.vue.setGpStocks(new GridPaneStocks(this.modele.rechercheCritere("titre", "", this.tf.getText(), "",this.modele.trouveLibrairie(this.vue.getLibrairieActuelle())), 0, vue, modele));
                    this.vue.majVBoxCenter(this.vue.getGpStocks());
                    break;
                
                case "Auteur":
                    this.vue.setGpStocks(new GridPaneStocks(this.modele.rechercheCritere("auteur", "", "", this.tf.getText(),this.modele.trouveLibrairie(this.vue.getLibrairieActuelle())), 0, vue, modele));
                    this.vue.majVBoxCenter(this.vue.getGpStocks());
                    break;
                
                case "ISBN":
                    this.vue.setGpStocks(new GridPaneStocks(this.modele.rechercheCritere("isbn", this.tf.getText() , "", "", this.modele.trouveLibrairie(this.vue.getLibrairieActuelle())), 0, vue, modele));
                    this.vue.majVBoxCenter(this.vue.getGpStocks());
                    break;

                default:
                    return;
            }
            
        }catch(NullPointerException e){
            this.vue.popPasDeLibrairie().show();
        }
        catch(SQLException e){
            this.vue.popUpAjouterLivreSQLException().show();       
        }
    }
}
