import java.util.List;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class GridPaneCommande extends GridPane{
    
    private VendeurBD modele;
    private MenuVendeur vue;
    private Map<Livre, Integer> commande;
    private int cptColonne;

    public GridPaneCommande(MenuVendeur vue, VendeurBD modele){
        super();
        this.vue = vue;
        this.modele = modele;
        this.commande = new HashMap<>();
        this.setHgap(5.0);
        this.setVgap(7.0);
        this.cptColonne = 0;

    }

    public void ajouteCommande(Livre livre, int qte){
            System.out.println(commande);
        if(commande.containsKey(livre)){
            System.out.println(commande);
            this.commande.replace(livre, commande.get(livre)+1);
        }else{
             this.commande.put(livre, qte);
            Text qtetext = new Text(qte + "");
            qtetext.setFont(Font.font("Arial", 19));
            Text livreText = new Text(livre.toString());
            qtetext.setFont(Font.font("Arial", 19));
            this.add(livreText, 0, cptColonne);
            this.add(qtetext, 1, cptColonne);
            cptColonne++;
           
        }

        this.getChildren().clear();
        this.cptColonne = 0;
        for(Livre livre2 : commande.keySet()){
            Text qtetext = new Text(" quantité :" + commande.get(livre2));
            qtetext.setFont(Font.font("Arial", 20));
            Text livreText = new Text(livre2.toString());
            qtetext.setFont(Font.font("Arial", 20));
            this.add(livreText, 0, cptColonne);
            this.add(qtetext, 1, cptColonne);
            cptColonne++;
        }
        this.vue.majVbCommande(this);
        
    }

    public Map<Livre, Integer> getCommande(){
        return this.commande;
    }




}