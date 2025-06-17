import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.Map;
import java.util.HashMap;


public class MenuClient extends BorderPane {

    private AppliLib appli;

    private Client client;


    public MenuClient(AppliLib appli){
        super();

        this.clientBD = this.appli.getClientBD();
        this.client = this.appli.getUtilisateur();

        this.setTop(this.ajouteTop());
        this.setLeft(this.ajouteLeft());
        this.setCenter(this.ajouteRight());
    }

    public HBox ajouteTop(){
        HBox top = new HBox();

        VBox blocA = new VBox();
        Text titre = new Text("Livre Express");
        Text nomCli = new Text(this.client.getNom()+" "+this.client.getPrenom());
        blocA.getChildren().addAll(titre,nomCli);

        ImageView logo = new ImageView(new Image("../../img/logo.png"));
        Button recherche = new Button("", new ImageView(new Image("../../img/loupe.png")));

        Text themeRech = new Text("Thêmes");
        VBox lesThemes = new VBox();
        Map<Integer,String> themesBD = this.appli.afficheThemes();
        for (String theme : themesBD.values()){
            RadioButton t = new RadioButton(theme);
            t.setOnAction(new ControleurTheme());
            lesThemes.getChildren().add(t);
        }
        TitledPane themes = new TitledPane(themeRech, lesThemes);

        VBox blocB = new VBox();
        Button accesPanier = new Button("", new ImageView(new Image("../../img/panier.png")));
        Button histori = new Button("", new ImageView(new Image("../../img/historique.png")));
        accesPanier.setOnAction(new ControleurPanier());
        histori.setOnAction(new ControleurHistorique());
        blocB.getChildren().addAll(accesPanier,histori);

        VBox blocC = new VBox();
        Button deco = new Button("Déconnexion");
        Button infosPerso = new Button("Informations personelles");
        deco.setOnAction(new ControleurDeconnexion());
        infosPerso.setOnAction(new ControleurInfosPersos());
        blocB.getChildren().addAll(deco,infosPerso);

        top.getChildren().addAll(blocA,logo,recherche,themes,blocB,blocC);
        return top;
    }

    public VBox setLeft(){
        VBox left = new VBox();

        VBox blocObservable = new VBox();

    }

    public Map<Integer,List<List<Livre>>> lesRecommendations(){
        Map<Integer,List<List<Livre>>> recommendations = new HashMap<>();
        for 
    }

}