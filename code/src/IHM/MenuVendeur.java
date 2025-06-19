import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
    private TextField isbn2;
    private TextField titre;
    private TextField publication;
    private TextField page;
    private TextField qte;
    private TextField qte2;
    private TextField prix;
    private Text textLibrairie;
    private Vendeur vendeur;

    public MenuVendeur(Vendeur vend, AppliLib app, VendeurBD vendeurBD) {

        this.vendeur = vend;
        this.appli = app;
        this.modele = vendeurBD;

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

        this.btnTransferer.setOnAction(new ControleurTransfererLivre(appli,this));
        this.btnMettreAJour.setOnAction(new ControleurMajQte(appli,this));
        this.btnDeconnexion.setOnAction(new ControleurDeconnexion(appli));
        this.btnAjouter.setOnAction(new ControleurAjouterLivre(this.modele, this, this.appli));
        this.btnCommander.setOnAction(new ControleurCommanderV(appli));
        this.btnVerif.setOnAction(new ControleurVerifDispo(appli));

        this.btnTransferer.setSkin(new MyButtonSkin(btnTransferer));
        this.btnMettreAJour.setSkin(new MyButtonSkin(btnMettreAJour));
        this.btnDeconnexion.setSkin(new MyButtonSkin(btnDeconnexion));
        this.btnAjouter.setSkin(new MyButtonSkin(btnAjouter));
        this.btnCommander.setSkin(new MyButtonSkin(btnCommander));
        this.btnVerif.setSkin(new MyButtonSkin(btnVerif));

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
        Text txtId = new Text("Identifiant Client :");
        txtId.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField identifiant = new TextField();
        identifiant.setFont(Font.font("Arial", 15));
        identifiant.setStyle(AppliLib.styleTextField);

        Text txtISBN = new Text("ISBN :");
        txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.isbn2 = new TextField();
        this.isbn2.setFont(Font.font("Arial", 15));
        this.isbn2.setStyle(AppliLib.styleTextField);

        hb1.getChildren().addAll(txtId, identifiant, txtISBN, this.isbn2);

        HBox hb2 = new HBox(10);
        Text txtMdp = new Text("Mot de passe Client :");
        txtMdp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        PasswordField mdp = new PasswordField();
        mdp.setFont(Font.font("Arial", 15));
        mdp.setStyle(AppliLib.styleTextField);

        Text txtQte = new Text("Quantité :");
        txtQte.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.qte2 = new TextField();
        this.qte2.setFont(Font.font("Arial", 15));
        this.qte2.setStyle(AppliLib.styleTextField);

        hb2.getChildren().addAll(txtMdp, mdp, txtQte, this.qte2);

        HBox hb3 = new HBox(10);
        Text txtPrixTot = new Text("Prix totale :");
        txtPrixTot.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text prixTot = new Text();
        prixTot.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        hb3.getChildren().addAll(txtPrixTot, prixTot);

        HBox hb4 = new HBox(10);

        this.btnCommander.disableProperty()
                .bind(this.isbn2.textProperty().isEmpty().or(identifiant.textProperty().isEmpty())
                        .or(mdp.textProperty().isEmpty()).or(this.qte2.textProperty().isEmpty()));
        this.btnVerif.disableProperty()
                .bind(this.isbn2.textProperty().isEmpty().or(this.qte2.textProperty().isEmpty()));


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
        this.isbn = new TextField();
        this.isbn.setFont(Font.font("Arial", 15));
        this.isbn.setStyle(AppliLib.styleTextField);
        hbIsbn.getChildren().addAll(txtIsbn, this.isbn);

        HBox hbTitre = new HBox(10);
        Text txtTitre = new Text("Titre :");
        txtTitre.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.titre = new TextField();
        this.titre.setFont(Font.font("Arial", 15));
        this.titre.setStyle(AppliLib.styleTextField);
        hbTitre.getChildren().addAll(txtTitre, this.titre);

        HBox hbPub = new HBox(10);
        Text txtPublication = new Text("Publication :");
        txtPublication.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.publication = new TextField();
        this.publication.setFont(Font.font("Arial", 15));
        this.publication.setStyle(AppliLib.styleTextField);
        hbPub.getChildren().addAll(txtPublication, this.publication);

        HBox hbPage = new HBox(10);
        Text txtPage = new Text("Pages :");
        txtPage.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.page = new TextField();
        this.page.setFont(Font.font("Arial", 15));
        this.page.setStyle(AppliLib.styleTextField);
        hbPage.getChildren().addAll(txtPage, this.page);

        HBox hbQte = new HBox(10);
        Text txtQuantite = new Text("Quantité :");
        txtQuantite.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.qte = new TextField();
        this.qte.setFont(Font.font("Arial", 15));
        this.qte.setStyle(AppliLib.styleTextField);
        hbQte.getChildren().addAll(txtQuantite, this.qte);

        HBox hbPrix = new HBox(10);
        Text txtPrix = new Text("Prix :");
        txtPrix.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.prix = new TextField();
        this.prix.setFont(Font.font("Arial", 15));
        this.prix.setStyle(AppliLib.styleTextField);
        hbPrix.getChildren().addAll(txtPrix, this.prix);

        this.btnAjouter.disableProperty()
                .bind(this.isbn.textProperty().isEmpty().or(this.titre.textProperty().isEmpty())
                        .or(this.publication.textProperty().isEmpty()).or(this.page.textProperty().isEmpty())
                        .or(this.qte.textProperty().isEmpty()).or(this.prix.textProperty().isEmpty()));

        vb.getChildren().addAll(hbIsbn, hbTitre, hbPage, hbPub, hbPrix, hbQte, this.btnAjouter);
        
        vb.setStyle(AppliLib.styleDefaultContainer);
        vb.setPadding(new Insets(25));

        return vb;
    }

    public int getIsbn() {
        return Integer.parseInt(this.isbn.getText());
    }

    public String getTitre() {
        return this.titre.getText();
    }

    public int getNbPages() {
        return Integer.parseInt(this.page.getText());
    }

    public int getDatePubli() {
        return Integer.parseInt(this.publication.getText());
    }

    public double getPrix() {
        return Double.parseDouble(this.prix.getText());
    }

    public int getQte() {
        return Integer.parseInt(this.qte.getText());
    }

    public String getLibrairie() {
        return this.textLibrairie.getText();
    }

    public int getIsbn2() {
        return Integer.parseInt(this.isbn2.getText());
    }

    public int getQte2() {
        return Integer.parseInt(this.qte2.getText());
    }

    public void resetTFAjouterLivre() {
        this.isbn.setText("");
        this.titre.setText("");
        this.publication.setText("");
        this.page.setText("");
        this.qte.setText("");
        this.prix.setText("");
    }

    


    public Alert popUpNumberFormatException() {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "Veuillez entrer uniquement des chiffres pour la quantité, la date de publication, le prix et le nombre de pages");
        return alert;
    }

    public Alert popUpLivreAjoute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le livre a bien été ajouté");
        return alert;
    }

}