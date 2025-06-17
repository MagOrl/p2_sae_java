import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.Map;
import java.util.HashMap;


public class MenuClient extends BorderPane {

    private AppliLib appli;

    private Map<int,List<Livre>> panier;

    private Personne client;


    public MenuClient(AppliLib appli){
        super();

        this.panier = new HashMap<>();
        this.client = this.appli.getUtilisateur();

        this.setTop(this.ajouteTop());
        this.setLeft(this.ajouteLeft());
        this.setCenter(this.ajouteRight());
    }

    public BorderPane ajouteTop(){
        BorderPane top = new BorderPane();

        VBox blocA = new VBox();
        Text titre = new Text("Livre Express");
        Text nomCli = new Text(this.client.getNom()+" "+this.client.getPrenom());
        blocA.getChildren().addAll(titre,nomCli);

        ImageView logo = new ImageView(new Image("../../img/logo.png"));
        Button recherche = new Button("", new ImageView(new Image("../../img/loupe.png")));

        Text themeRech = new Text("Thême");
        VBox lesThemes = new VBox();
        Map<Integer,String> themesBD = this.appli.afficheThemes();
        
        TitledPane themes = new TitledPane();
    }

}