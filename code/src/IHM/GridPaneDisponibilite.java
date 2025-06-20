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

public class GridPaneDisponibilite extends GridPane{
    
    private List<String> lesLibrairies;
    private VendeurBD modele;
    private MenuVendeur vue;
    private Livre livre;
    private int qte;

    public GridPaneDisponibilite(){

    }

    public GridPaneDisponibilite(MenuVendeur vue, VendeurBD modele, Livre livre, int qte){
        super();
        this.vue = vue;
        this.modele = modele;
        this.setHgap(5.0);
        this.setVgap(7.0);
        this.vue.setDispo("Disponibilité du livre : " + livre.getTitre());
        try{
            this.lesLibrairies = this.modele.choixLibrairie();
            this.livre = livre;
            this.qte = qte;
            int cptColonne = 0;

        
            for(String librairie : lesLibrairies){
                String dispo = this.modele.verifDispoLivre(livre, qte, this.modele.trouveLibrairie(librairie, -1))
                        ? "disponible"
                        : "indisponible";

                Label livreDisponible = new Label(librairie + " -> " + dispo);
                if(dispo.equals("disponible")){
                    livreDisponible.setFont(Font.font("Arial", 20));
                    //livreDisponible.setStyle("-fx-text-fill: green;");
                    livreDisponible.setTextFill(Color.LIGHTGREEN);
                }else{
                    livreDisponible.setFont(Font.font("Arial", 20));
                    //livreDisponible.setStyle("-fx-text-fill: red;"); 
                    livreDisponible.setTextFill(Color.RED);

                }

                this.add(livreDisponible, 0, cptColonne);
                cptColonne++;
            }
        }catch(SQLException e){
            this.vue.popUpErreurSQL().show();
        }
        
    }




}