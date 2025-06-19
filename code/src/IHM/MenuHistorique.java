import java.util.List;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.sql.SQLException;
import javafx.geometry.Insets;

public class MenuHistorique extends BorderPane {

    private AppliLib appli;

    private Client client;

    private Map<String,List<String>> historique;

    public MenuHistorique(AppliLib appli){
        super();

        this.appli = appli;
        this.client = (Client) this.appli.getUtilisateur();
        this.setStyle(AppliLib.styleBanniere);
        this.setPadding(new Insets(20));
        try{
            this.historique = this.appli.getClientBD().historiqueCommande(this.client); 
        }catch (SQLException e){
            this.appli.popUpPasDeCommandes();
        }
        

        Text titre = new Text("Historique de vos commandes");
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        VBox center = new VBox(10);
        for (String key : this.historique.keySet()){
            VBox lesLivres = new VBox(5);
            for (String value : this.historique.get(key)){
                Text ligne = new Text(value);
                ligne.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
                lesLivres.getChildren().add(ligne);
            }
            TitledPane commande = new TitledPane(key, lesLivres);
            commande.setExpanded(false);
            commande.setStyle(AppliLib.styleTextField);
            center.getChildren().add(commande);
        }
        center.setPadding(new Insets(20));

        Button retour = new Button("Retour");
        retour.setOnAction(new ControleurRetour(this.appli));
        retour.setStyle(AppliLib.styleBouton);
        retour.setMinHeight(40);
        retour.setMinWidth(90);
        retour.setSkin(new MyButtonSkin(retour));

        this.setTop(titre);
        this.setCenter(center);
        this.setBottom(retour);
    }
}

//"Aucune commande effectuer pour le moment"