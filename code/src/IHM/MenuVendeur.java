import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuVendeur extends BorderPane {

    private Button btnTransferer;
    private Button btnMettreAJour;
    private Button btnDeconnexion;
    private Button btnAjouter;
    private Button btnCommander;
    private Button btnVerif;
    private AppliLib appli;
    private VendeurBD modele;
    private TextField isbn;
    private TextField titre;
    private TextField publication;
    private TextField page;
    private TextField qte;
    private TextField prix;
    private Text textLibrairie;
    private Vendeur vendeur;

    public MenuVendeur(Vendeur vend,AppliLib app) {
        

        this.vendeur = vend ;
        this.appli = app;
        this.btnTransferer = new Button("Transférer \n    Livre");
        this.btnMettreAJour = new Button("Mettre à jour \n   Quantité");
        this.btnDeconnexion = new Button("Déconnexion");
        this.btnAjouter = new Button("Ajouter");
        this.btnCommander = new Button("Commander");
        this.btnVerif = new Button("Vérifier Disponibilité");

        this.btnTransferer.setStyle(AppliLib.styleBouton);
        this.btnMettreAJour.setStyle(AppliLib.styleBouton);
        this.btnDeconnexion.setStyle(AppliLib.styleBouton);
        this.btnAjouter.setStyle(AppliLib.styleBouton);
        this.btnCommander.setStyle(AppliLib.styleBouton);
        this.btnVerif.setStyle(AppliLib.styleBouton);

        this.btnTransferer.setSkin(new MyButtonSkin(btnTransferer));
        this.btnMettreAJour.setSkin(new MyButtonSkin(btnMettreAJour));
        this.btnDeconnexion.setSkin(new MyButtonSkin(btnDeconnexion));
        this.btnAjouter.setSkin(new MyButtonSkin(btnAjouter));
        this.btnCommander.setSkin(new MyButtonSkin(btnCommander));
        this.btnVerif.setSkin(new MyButtonSkin(btnVerif));

        this.btnTransferer.setOnAction(new ControleurTransfererLivre(appli));
        this.btnMettreAJour.setOnAction(new ControleurMajQte(appli));
        this.btnDeconnexion.setOnAction(new ControleurDeconnexion(appli));
        this.btnAjouter.setOnAction(new ControleurAjouterLivre(modele, this));
        this.btnCommander.setOnAction(new ControleurCommanderV(appli));
        this.btnVerif.setOnAction(new ControleurVerifDispo(appli));


        this.setTop(top());
        this.setLeft(left());
        this.setCenter(center());
    }

    public BorderPane top() {
        BorderPane bp = new BorderPane();
        // bp.setFillHeight(false);

        VBox vb = new VBox();
        Text txt = new Text("Livre Express");
        Text txt2 = new Text();
        txt.setFont(Font.font("Arial", 30));
        txt2.setFont(Font.font("Arial", 30));
        vb.getChildren().addAll(txt, txt2);
        ImageView logo = new ImageView("../img/logo_placeholder.png");
        logo.setFitHeight(48);
        logo.setFitWidth(48);

        HBox hbleft = new HBox(10);
        HBox hbright = new HBox(10);
        hbleft.getChildren().addAll(vb, logo);
        hbright.getChildren().addAll(
                this.btnTransferer,
                this.btnMettreAJour,
                this.btnDeconnexion);
        bp.setLeft(hbleft);
        bp.setRight(hbright);

        bp.setStyle(AppliLib.styleBanniere);
        bp.setPadding(new Insets(20));

        return bp;
    }

    public VBox center() {

        VBox vb0 = new VBox(15);

        VBox vb1 = new VBox(15);

        HBox hb0 = new HBox(10);
        Text txt1 = new Text("Librairie :");
        txt1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        textLibrairie = new Text(this.vendeur.getMag().getNom());
        textLibrairie.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        hb0.getChildren().addAll(txt1, textLibrairie);

        vb1.getChildren().add(hb0);
        vb1.setPadding(new Insets(20));

        VBox vb = new VBox(15);

        HBox hb1 = new HBox(10);
        Text txtId = new Text("Identifiant :");
        txtId.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField identifiant = new TextField();
        identifiant.setFont(Font.font("Arial", 15));
        identifiant.setStyle(AppliLib.styleTextField);

        Text txtISBN = new Text("Publication :");
        txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        isbn = new TextField();
        isbn.setFont(Font.font("Arial", 15));
        isbn.setStyle(AppliLib.styleTextField);

        hb1.getChildren().addAll(txtId, identifiant, txtISBN, isbn);

        HBox hb2 = new HBox(10);
        Text txtMdp = new Text("Mot de passe :");
        txtMdp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        PasswordField mdp = new PasswordField();
        mdp.setFont(Font.font("Arial", 15));
        mdp.setStyle(AppliLib.styleTextField);

        Text txtQte = new Text("Quantité :");
        txtQte.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField qte = new TextField();
        qte.setFont(Font.font("Arial", 15));
        qte.setStyle(AppliLib.styleTextField);

        hb2.getChildren().addAll(txtMdp, mdp, txtQte, qte);

        HBox hb3 = new HBox(10);
        Text txtPrixTot = new Text("Prix totale :");
        txtPrixTot.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text prixTot = new Text();
        prixTot.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        hb3.getChildren().addAll(txtPrixTot, prixTot);

        HBox hb4 = new HBox(10);
        hb4.getChildren().addAll(this.btnCommander, this.btnVerif);

        vb.getChildren().addAll(hb1, hb2, hb3, hb4);
        vb.setStyle(AppliLib.styleDefaultContainer);
        vb.setPadding(new Insets(20));

        vb0.getChildren().addAll(vb1, vb);
        vb0.setPadding(new Insets(20));

        return vb0;
    }

    public VBox left() {

        VBox vb = new VBox();
        vb.setPadding(new Insets(10));
        vb.setSpacing(10);
        vb.setAlignment(Pos.TOP_CENTER);

        HBox hbIsbn = new HBox(10);
        Text txtIsbn = new Text("ISBN :");
        txtIsbn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        isbn = new TextField();
        isbn.setFont(Font.font("Arial", 15));
        isbn.setStyle(AppliLib.styleTextField);
        hbIsbn.getChildren().addAll(txtIsbn, isbn);

        HBox hbTitre = new HBox(10);
        Text txtTitre = new Text("Titre :");
        txtTitre.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titre = new TextField();
        titre.setFont(Font.font("Arial", 15));
        titre.setStyle(AppliLib.styleTextField);
        hbTitre.getChildren().addAll(txtTitre, titre);

        HBox hbPub = new HBox(10);
        Text txtPublication = new Text("Publication :");
        txtPublication.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        publication = new TextField();
        publication.setFont(Font.font("Arial", 15));
        publication.setStyle(AppliLib.styleTextField);
        hbPub.getChildren().addAll(txtPublication, publication);

        HBox hbPage = new HBox(10);
        Text txtPage = new Text("Pages :");
        txtPage.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        page = new TextField();
        page.setFont(Font.font("Arial", 15));
        page.setStyle(AppliLib.styleTextField);
        hbPage.getChildren().addAll(txtPage, page);

        HBox hbQte = new HBox(10);
        Text txtQuantite = new Text("Quantité :");
        txtQuantite.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        qte = new TextField();
        qte.setFont(Font.font("Arial", 15));
        qte.setStyle(AppliLib.styleTextField);
        hbQte.getChildren().addAll(txtQuantite, qte);

        HBox hbPrix = new HBox(10);
        Text txtPrix = new Text("Prix :");
        txtPrix.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        prix = new TextField();
        prix.setFont(Font.font("Arial", 15));
        prix.setStyle(AppliLib.styleTextField);
        hbPrix.getChildren().addAll(txtPrix, prix);

        vb.getChildren().addAll(hbIsbn, hbTitre, hbPub, hbPage, hbQte, this.btnAjouter);
        vb.setStyle(AppliLib.styleDefaultContainer);
        vb.setPadding(new Insets(25));

        return vb;
    }

    public String getIsbn() {
        return isbn.getText();
    }

    public String getTitre() {
        return titre.getText();
    }

    public String getNbPages() {
        return isbn.getText();
    }

    public String getDatePubli() {
        return publication.getText();
    }

    public String getPrix() {
        return prix.getText();
    }

    public String getQte() {
        return qte.getText();
    }

    public String getLibrairie() {
        return textLibrairie.getText();
    }

}