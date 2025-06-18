import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class MenuClient extends BorderPane {

    private AppliLib appli;

    private Client client;

    private ClientBD clientBD;

    private Map<Integer, List<List<Livre>>> recommandations;

    private List<Livre> listeRecommandes;

    private RecomDynamique livreDyna;

    public MenuClient(AppliLib appli) {
        super();

        this.appli = appli;
        this.clientBD = this.appli.getClientBD();
        this.client = (Client) this.appli.getUtilisateur();
        this.recommandations = lesRecommandations();
        this.listeRecommandes = livresRecommandes();
        this.livreDyna = new RecomDynamique(this.listeRecommandes);
        this.setTop(this.ajouteTop());
        this.setLeft(this.ajouteLeft());
        this.setCenter(this.ajouteCenter());
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
        histori.setOnAction(new ControleurHistorique(this.appli));
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
        infosPerso.setOnAction(new ControleurInfosPersos(this.appli));
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

    public BorderPane ajouteLeft() {
        BorderPane left = new BorderPane();

        VBox blocObservable = new VBox();
        ImageView liv = new ImageView(new Image("../img/logo.png"));
        liv.setFitHeight(200);
        liv.setFitWidth(200);

        blocObservable.getChildren().addAll(liv,this.livreDyna);
        blocObservable.setAlignment(Pos.CENTER);
        

        HBox bottom = new HBox(10);
        Button consulter = new Button("Consulter");
        //consulter.setOnAction(new ControleurConsulter(this.appli, this));
        consulter.setStyle(AppliLib.styleBouton);
        consulter.setMinHeight(40);
        consulter.setMinWidth(90);
        consulter.setSkin(new MyButtonSkin(consulter));
        bottom.getChildren().addAll(consulter);
        bottom.setAlignment(Pos.CENTER);

        left.setCenter(blocObservable);
        left.setBottom(bottom);
        left.setStyle(AppliLib.styleDefaultContainer);
        BorderPane.setAlignment(left, Pos.CENTER);
        return left;
    }

    public BorderPane ajouteCenter(){
        BorderPane center = new BorderPane();

        VBox boite = new VBox(10);
        Text message = new Text("Aucune recherche en cours");
        Text conseil = new Text("Vous pouvez chercher des livres selon leur thême via la barre de recherche.");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        conseil.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        boite.getChildren().addAll(message, conseil);
        Button ajouter = new Button("Ajouter au panier");
        //ajouter.setOnAction(new ControleurAjouterPanier(this.clientBD));
        ajouter.setStyle(AppliLib.styleBouton);
        ajouter.setMinHeight(40);
        ajouter.setMinWidth(90);
        ajouter.setSkin(new MyButtonSkin(ajouter));
        ajouter.setDisable(true);

        
        center.setCenter(boite);
        center.setBottom(ajouter);
        center.setPadding(new Insets(20));
        BorderPane.setAlignment(center, Pos.CENTER);
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