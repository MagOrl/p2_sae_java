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
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.Random;
import java.lang.Math;
import javafx.scene.layout.GridPane;
import javax.swing.border.Border;

public class MenuClient extends BorderPane {

    private AppliLib appli;

    private Client client;

    private ClientBD clientBD;

    private Map<Integer, List<List<Livre>>> recommandations;

    private List<Livre> listeRecommandes;

    public MenuClient(AppliLib appli) {
        super();

        this.appli = appli;
        this.clientBD = this.appli.getClientBD();
        this.client = (Client) this.appli.getUtilisateur();
        this.recommandations = lesRecommandations();
        this.listeRecommandes = livresRecommandes();

        this.setTop(this.ajouteTop());
        this.setLeft(this.ajouteLeft());
        // this.setCenter(this.ajouteRight());
    }

    public BorderPane ajouteTop() {
        BorderPane top = new BorderPane();

        Text titre = new Text("Livre Express");
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        Text nomCli = new Text(this.client.getNom() + " " + this.client.getPrenom());
        nomCli.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox left = new VBox(10);
        left.getChildren().addAll(titre, nomCli);

        ImageView logo = new ImageView(new Image("../img/logo.png"));
        logo.setFitHeight(96);
        logo.setFitWidth(96);
        ImageView loupe = new ImageView(new Image("../img/loupe.png"));
        loupe.setFitHeight(65);
        loupe.setFitWidth(65);
        Button recherche = new Button("", loupe);
        recherche.setStyle(AppliLib.styleBoutonImg);
        recherche.setMinHeight(40);
        recherche.setMinWidth(90);
        recherche.setSkin(new MyButtonSkin(recherche));

        VBox lesThemes = new VBox(5);
        ToggleGroup groupTheme = new ToggleGroup();
        try {
            Map<Integer, String> themesBD = this.clientBD.afficheThemes();
            for (String theme : themesBD.values()) {
                RadioButton t = new RadioButton(theme);
                t.setToggleGroup(groupTheme);
                // t.setOnAction(new ControleurTheme());
                lesThemes.getChildren().add(t);
            }
        } catch (Exception e) {
            this.appli.popUpPasDeThemes();
        }
        TitledPane themes = new TitledPane("Thêmes", lesThemes);
        BorderPane center = new BorderPane();
        center.setLeft(logo);
        HBox barreRecherche = new HBox(10);
        barreRecherche.getChildren().addAll(recherche,themes);
        center.setCenter(barreRecherche);
        BorderPane.setAlignment(center, Pos.CENTER_RIGHT);

        ImageView pan = new ImageView(new Image("../img/panier.png"));
        pan.setFitHeight(35);
        pan.setFitWidth(35);
        Button accesPanier = new Button("", pan);
        accesPanier.setStyle(AppliLib.styleBoutonImg);
        accesPanier.setMinHeight(40);
        accesPanier.setMinWidth(90);
        accesPanier.setSkin(new MyButtonSkin(accesPanier));
        ImageView histo = new ImageView(new Image("../img/historique.png"));
        histo.setFitHeight(35);
        histo.setFitWidth(35);
        Button histori = new Button("", histo);
        histori.setStyle(AppliLib.styleBoutonImg);
        histori.setMinHeight(40);
        histori.setMinWidth(90);
        histori.setSkin(new MyButtonSkin(histori));
        // accesPanier.setOnAction(new ControleurPanier());
        // histori.setOnAction(new ControleurHistorique());
        VBox blocA = new VBox(10);
        blocA.getChildren().addAll(accesPanier, histori);

        Button deco = new Button("Déconnexion");
        deco.setStyle(AppliLib.styleBouton);
        deco.setMinHeight(40);
        deco.setMinWidth(90);
        deco.setSkin(new MyButtonSkin(deco));
        Button infosPerso = new Button("Informations personelles");
        infosPerso.setStyle(AppliLib.styleBouton);
        infosPerso.setMinHeight(40);
        infosPerso.setMinWidth(90);
        infosPerso.setSkin(new MyButtonSkin(infosPerso));
        deco.setOnAction(new ControleurDeconnexion(this.appli));
        // infosPerso.setOnAction(new ControleurInfosPersos());
        VBox blocB = new VBox(10);
        blocB.getChildren().addAll(deco, infosPerso);
        HBox right = new HBox(10);
        right.getChildren().addAll(blocA, blocB);
        right.setFillHeight(true);
        right.setAlignment(Pos.CENTER);

        top.setLeft(left);
        top.setCenter(center);
        top.setRight(right);

        top.setStyle(AppliLib.styleBanniere);
        top.setPadding(new Insets(20));
        return top;
    }

    public VBox ajouteLeft() {
        VBox left = new VBox();

        VBox blocObservable = new VBox();
        ImageView liv = new ImageView(new Image("../img/logo.png"));
        liv.setFitHeight(200);
        liv.setFitWidth(200);
        int i = (int) Math.round(Math.random()*this.livresRecommandes().size());
        Text titre = new Text(this.listeRecommandes.get(i).getTitre());
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        BorderPane blocA = new BorderPane();
        Text date = new Text(this.listeRecommandes.get(i).getDatePubli());
        date.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        Text prix = new Text(""+this.listeRecommandes.get(i).getPrix());
        prix.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        blocA.setLeft(date);
        blocA.setRight(prix);
        blocA.setPadding(new Insets(20));
        blocObservable.getChildren().addAll(liv,titre,blocA);
        blocObservable.setAlignment(Pos.CENTER);
        Button consulter = new Button("Consulter");
        //consulter.setOnAction(new ControleurConsulter(this.appli, this));
        consulter.setStyle(AppliLib.styleBouton);
        consulter.setMinHeight(40);
        consulter.setMinWidth(90);
        consulter.setSkin(new MyButtonSkin(consulter));
        left.getChildren().addAll(blocObservable,consulter);
        left.setStyle(AppliLib.styleDefaultContainer);
        left.setAlignment(Pos.CENTER);
        return left;
    }

    public HBox ajouteCenter(){
        HBox center = new HBox();
        return center;
    }

    public Map<Integer, List<List<Livre>>> lesRecommandations() {
        Map<Integer, List<List<Livre>>> recommendations = new HashMap<>();
        Integer i = 0;
        try {
            for (Magasin mag : this.clientBD.afficheMagasin().values()) {
                try {
                    recommendations.put(i, this.clientBD.onVousRecommande(this.client, mag));
                } catch (SQLException e) {
                    this.appli.popUpPasDeRecommandations();
                }
                i++;
            }
        } catch (Exception e) {
            this.appli.popUpPasDeMagasins();
        }
        return recommendations;
    }

    public List<Livre> livresRecommandes(){
        List<Livre> livres = new ArrayList<>();
        for (List<List<Livre>> listes : this.recommandations.values()){
            for (List<Livre> petiteListe : listes){
                livres.addAll(petiteListe);
            }
        }
        return livres;
    }

}