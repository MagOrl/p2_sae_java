import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

public class MenuClient extends BorderPane {

    private AppliLib appli;

    private Client client;

    private ClientBD clientBD;

    private Map<Integer, List<List<Livre>>> recommandations;

    private List<Livre> listeRecommandes;

    private RecomDynamique livreDyna;

    private TextField recheField;

    private Livre livreSelectionne;

    private VBox vBoxCenter;

    private final ComboBox<String> leCriter = new ComboBox<>();

    private ComboBox<String> lesMag;

    private GridPaneResultatRech gp;

    public static String styleBoutonPage = "-fx-background-color:rgb(255, 255, 255);" +
            "-fx-border-radius: 50; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";

    public MenuClient(AppliLib appli) {
        this.vBoxCenter = new VBox(10);
        leCriter.getItems().addAll("Titre", "Auteur", "ISBN");
        this.recheField = new TextField();
        this.recheField.setStyle(AppliLib.styleTextField);
        this.gp = new GridPaneResultatRech();
        this.gp.setStyle(AppliLib.styleDefaultContainer);
        this.appli = appli;
        this.clientBD = this.appli.getClientBD();
        this.client = (Client) this.appli.getUtilisateur();
        this.recommandations = lesRecommandations();
        this.listeRecommandes = livresRecommandes();
        this.livreDyna = new RecomDynamique(this.listeRecommandes);
        lesMag = new ComboBox<>();
        try {
            for (Magasin mag : clientBD.afficheMagasin().values()) {
                lesMag.getItems().add(mag.getNom());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ajouteCenter();
        this.lesMag.setPromptText("Mettre le magasin");
        this.leCriter.setPromptText("critère de recherche");
        this.lesMag.setStyle(AppliLib.styleTextField);
        this.leCriter.setStyle(AppliLib.styleTextField);

        this.setTop(this.ajouteTop());
        this.setLeft(this.ajouteLeft());
        this.setCenter(this.vBoxCenter);
        this.setBottom(lesBouton());
    }

    private Node lesBouton() {
        ImageView imgP = new ImageView("../img/boutonPrec.png");
        imgP.setFitWidth(20);
        imgP.setFitHeight(20);
        Button pageprec = new Button();
        pageprec.setStyle(styleBoutonPage);
        pageprec.setSkin(new MyButtonSkin(pageprec));
        pageprec.setOnAction(new ControleurPagePrecedenteClie(this));
        pageprec.setGraphic(imgP);

        ImageView imgS = new ImageView("../img/boutonSuivant.png");
        imgS.setFitWidth(20);
        imgS.setFitHeight(20);
        Button pageSuivante = new Button();
        pageSuivante.setStyle(styleBoutonPage);
        pageSuivante.setSkin(new MyButtonSkin(pageSuivante));
        pageSuivante.setOnAction(new ControleurPageSuivanteClie(this));
        pageSuivante.setGraphic(imgS);
        HBox boutonTournerLaPage = new HBox(10);
        boutonTournerLaPage.getChildren().addAll(pageprec, pageSuivante);
        boutonTournerLaPage.setAlignment(Pos.TOP_CENTER);
        return boutonTournerLaPage;
    }

    public BorderPane ajouteTop() {
        BorderPane top = new BorderPane();

        Text titre = new Text("Livre Express");
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        Text nomCli = new Text(this.client.getNom() + " " + this.client.getPrenom());
        nomCli.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox left = new VBox(10);
        left.getChildren().addAll(titre, nomCli);

        ImageView loupe = new ImageView(new Image("../img/loupe.png"));
        loupe.setFitHeight(75);
        loupe.setFitWidth(75);
        Button recherche = new Button("", loupe);
        recherche.setStyle(AppliLib.styleBoutonImg);
        recherche.setMinHeight(40);
        recherche.setMinWidth(90);
        recherche.setSkin(new MyButtonSkin(recherche));
        recherche.setOnAction(new ControleurRechercheLivreCli(this, clientBD));
        HBox rech = new HBox(5);

        rech.getChildren().addAll(recherche, this.recheField, lesMag, leCriter);

        BorderPane center = new BorderPane();
        HBox barreRecherche = new HBox(10);

        barreRecherche.getChildren().addAll(rech, lesMag);

        center.setCenter(barreRecherche);

        BorderPane.setAlignment(center, Pos.CENTER_RIGHT);

        ImageView pan = new ImageView(new Image("../img/panier.png"));
        pan.setFitHeight(35);
        pan.setFitWidth(55);
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
        accesPanier.setOnAction(new ControleurPanier(this.appli, this));
        histori.setOnAction(new ControleurHistorique(this.appli, this));
        VBox blocA = new VBox(10);
        blocA.getChildren().addAll(accesPanier, histori);

        Button deco = new Button("Déconnexion");
        deco.setStyle(AppliLib.styleBouton + "-fx-background-color:rgb(194, 60, 60);");
        deco.setMinHeight(40);
        deco.setMinWidth(90);
        deco.setSkin(new MyButtonSkin(deco));
        Button infosPerso = new Button("Informations personelles");
        infosPerso.setStyle(AppliLib.styleBouton);
        infosPerso.setMinHeight(40);
        infosPerso.setMinWidth(90);
        infosPerso.setSkin(new MyButtonSkin(infosPerso));
        deco.setOnAction(new ControleurDeconnexion(this.appli));
        infosPerso.setOnAction(new ControleurInfosPersos(this.appli, this));
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

        blocObservable.getChildren().addAll(liv, this.livreDyna);
        blocObservable.setAlignment(Pos.CENTER);
        blocObservable.setPadding(new Insets(20));

        HBox bottom = new HBox(10);
        Button consulter = new Button("Ajouter panier");
        consulter.setStyle(AppliLib.styleBouton);
        consulter.setMinHeight(40);
        consulter.setMinWidth(90);
        consulter.setSkin(new MyButtonSkin(consulter));
        bottom.getChildren().addAll(consulter);
        bottom.setAlignment(Pos.CENTER);
        consulter.setOnAction(new ControleurConsulter(client, this.livreDyna, this.clientBD));
        left.setCenter(blocObservable);
        left.setBottom(bottom);
        left.setStyle(AppliLib.styleDefaultContainer);
        BorderPane.setAlignment(left, Pos.CENTER);

        return left;
    }

    public void ajouteCenter() {
        Text message = new Text("Aucune recherche en cours");
        Text conseil = new Text("Vous pouvez chercher des livres selon leur thême via la barre de recherche.");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        conseil.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        VBox vb = new VBox();
        vb.getChildren().addAll(message, conseil);

        this.vBoxCenter.getChildren().addAll(vb, this.gp);

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

    public List<Livre> livresRecommandes() {
        List<Livre> livres = new ArrayList<>();
        for (List<List<Livre>> listes : this.recommandations.values()) {
            for (List<Livre> petiteListe : listes) {
                livres.addAll(petiteListe);
            }
        }
        return livres;
    }

    public ClientBD getClientBD() {
        return this.clientBD;
    }

    public Client getClient() {
        return this.client;
    }

    public void setLivreSelect(Livre livre) {
        this.livreSelectionne = livre;
    }

    public Livre getLivreSelect() {
        return this.livreSelectionne;
    }

    public AppliLib getAppli() {
        return this.appli;
    }

    public Livre getLivreDyna() {
        return this.livreDyna.getLivre();
    }

    public String getValCrit() {
        return this.leCriter.getValue();
    }

    public void setGpLivRech(GridPaneResultatRech gp) {
        this.gp = gp;

    }

    public GridPaneResultatRech getGpLivRech() {
        return this.gp;
    }

    public String getLibActuelle() {
        return this.lesMag.getValue();
    }

    public void majVBoxCenter(GridPaneResultatRech nouvGpStock) {
        this.vBoxCenter.getChildren().clear();
        this.vBoxCenter.getChildren().add(nouvGpStock);
    }

    public String getValRech() {
        return recheField.getText();
    }

    public void setRed() {
        this.lesMag.setStyle(AppliLib.styleTextField + "-fx-border-color: red;");
        this.leCriter.setStyle(AppliLib.styleTextField + "-fx-border-color: red;");

    }

    public void setNormal() {
        this.lesMag.setStyle(AppliLib.styleTextField);
        this.leCriter.setStyle(AppliLib.styleTextField);
    }
}

