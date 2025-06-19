import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

import javax.swing.text.AbstractDocument;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

public class MenuAdmin extends BorderPane {
    private Button bDeconnexion;
    private Button bCreerVendeur;
    private Button bAjouterVendeur;
    private Button bAjouter;
    private Button bAjouterLibrairie;
    private Button bGererStocks;
    private ComboBox<String> cb;
    private AdministrateurBD adminBD;
    private static ConnexionMySQL connexion;
    private Administrateur adm;
    private AppliLib app;

    public MenuAdmin(Button quitter, Administrateur adm, AppliLib app) {
        this.adm = adm;
        this.app = app;
        this.bDeconnexion = new Button("Déconnexion");
        this.bCreerVendeur = new Button("Créer compte vendeur");
        this.bAjouter = new Button("Ajouter");
        this.bGererStocks = new Button("Gérer les stocks");
        this.bAjouterLibrairie = new Button("Ajouter une librairie");
        this.bAjouterVendeur = new Button("Ajouter");

        this.bAjouterLibrairie.setOnAction(new ControleurAfficherAjouterLibrairie(this));
        this.bCreerVendeur.setOnAction(new ControleurCreerCompteVendeur(this));
        this.bDeconnexion.setOnAction(new ControleurDeconnexion(this.app));
        // this.bAjouterVendeur.setOnAction(new ControleurAjouterVendeur());
    //     (String nom, String prenom, String identifiant, String adresse, String tel,
    //   String email, String mdp, String codePostal, String ville, String nommag)

        try {
            this.connexion = new ConnexionMySQL();
            this.adminBD = new AdministrateurBD(connexion);
        } catch (ClassNotFoundException e) {
            System.out.println("");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.bDeconnexion.setStyle(AppliLib.styleBouton);
        this.bCreerVendeur.setStyle(AppliLib.styleBouton);
        this.bAjouter.setStyle(AppliLib.styleBouton);
        this.bGererStocks.setStyle(AppliLib.styleBouton);
        this.bAjouterLibrairie.setStyle(AppliLib.styleBouton);

        this.bDeconnexion.setSkin(new MyButtonSkin(bDeconnexion));
        this.bCreerVendeur.setSkin(new MyButtonSkin(bCreerVendeur));
        this.bAjouter.setSkin(new MyButtonSkin(bAjouter));
        this.bGererStocks.setSkin(new MyButtonSkin(bGererStocks));
        this.bAjouterLibrairie.setSkin(new MyButtonSkin(bAjouterLibrairie));

        this.setTop(top());
        this.setLeft(left());
        this.setCenter(center());

        BorderPane.setMargin(this.getTop(), new Insets(0, 0, 10, 0));
        BorderPane.setMargin(this.getLeft(), new Insets(0, 10, 0, 0));
    }

    public BorderPane top() {
        BorderPane bp = new BorderPane();
        // bp.setFillHeight(false);

        Text txt = new Text("Livre Express");
        txt.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        ImageView logo = new ImageView("../img/logo_placeholder.png");
        logo.setFitHeight(48);
        logo.setFitWidth(48);

        HBox hbleft = new HBox(10);
        HBox hbright = new HBox(10);
        hbleft.getChildren().addAll(txt, logo);
        hbright.getChildren().add(this.bDeconnexion);
        // bDeconnexion.setOnAction(new ControleurDeco());
        bp.setLeft(hbleft);
        bp.setRight(hbright);

        bp.setStyle(AppliLib.styleBanniere);
        bp.setPadding(new Insets(20));

        return bp;
    }

    public Pane left() {
        VBox vbMain = new VBox(50);

        vbMain.setPrefWidth(250);
        vbMain.setPrefHeight(350);
        vbMain.setPadding(new Insets(20));
        vbMain.setStyle(AppliLib.styleDefaultContainer);
        vbMain.setAlignment(Pos.BASELINE_CENTER);

        this.cb = new ComboBox<>();
        this.cb.getItems().addAll("Nombre de livres vendus par magasin par année",
                "CA par thème pour une année",
                "Évolution des CA des magasins par mois pour une année",
                "Comparaison vente en ligne et en magasin",
                "Les dix éditeurs les plus importants en nombre d'auteur",
                "Origine des clients ayant acheté des livres d'un auteur",
                "Valeur du stock par magasin",
                "Évolution du chiffre d'affaire total par client");
        this.cb.valueProperty().addListener(new ControleurGraph(this, adminBD));
        cb.setMaxWidth(200);

        vbMain.getChildren().addAll(this.bCreerVendeur, this.bGererStocks, this.bAjouterLibrairie, this.cb);

        return vbMain;
    }

    public Pane center() {
        VBox vbMain = new VBox(10);
        vbMain.setPadding(new Insets(100, 50, 100, 50));

        Text txt1 = new Text("Menu Administrateur");
        txt1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        Text txt2 = new Text("Bienvenue !");
        txt2.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        vbMain.getChildren().addAll(txt1, txt2);
        vbMain.setAlignment(Pos.BASELINE_CENTER);

        return vbMain;
    }

    public VBox afficherAjouterLibrairie() {
        VBox vbMain = new VBox(25);
        vbMain.setPrefHeight(350);
        vbMain.setPrefWidth(400);
        vbMain.setStyle(AppliLib.styleDefaultContainer);
        vbMain.setAlignment(Pos.BASELINE_CENTER);
        vbMain.setPadding(new Insets(10, 0, 0, 0));

        HBox hb1 = new HBox(10);
        hb1.setPadding(new Insets(5, 0, 0, 1));
        hb1.setAlignment(Pos.BASELINE_CENTER);

        HBox hb2 = new HBox(10);
        hb2.setPadding(new Insets(5, 0, 0, 1));
        hb2.setAlignment(Pos.BASELINE_CENTER);

        HBox hb3 = new HBox(10);
        hb3.setPadding(new Insets(5, 0, 0, 1));
        hb3.setAlignment(Pos.BASELINE_CENTER);

        Text t1 = new Text("Ajouter une librairie");
        t1.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Text nom = new Text("Nom :");
        nom.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text ville = new Text("Ville :");
        ville.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text idmag = new Text("Id :");
        idmag.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TextField tfNom = new TextField();
        TextField tfVille = new TextField();
        TextField tfIdmag = new TextField();

        tfNom.setStyle(AppliLib.styleTextField);
        tfVille.setStyle(AppliLib.styleTextField);
        tfIdmag.setStyle(AppliLib.styleTextField);

        hb1.getChildren().addAll(nom, tfNom);
        hb2.getChildren().addAll(ville, tfVille);
        hb3.getChildren().addAll(idmag, tfIdmag);

        vbMain.getChildren().addAll(t1, hb1, hb2, hb3, this.bAjouter);
        this.bAjouter.disableProperty().bind(tfNom.textProperty().isEmpty().or(tfVille.textProperty().isEmpty())
                .or(tfIdmag.textProperty().isEmpty()));
        this.bAjouter.setOnAction(new ControleurAjouterLibrairie(this, this.adminBD, tfNom, tfVille, tfIdmag));

        return vbMain;
    }

    public HBox afficherCreerVendeur() {
        HBox hbMain = new HBox(150);
        hbMain.setPrefHeight(350);
        hbMain.setPrefWidth(400);
        hbMain.setStyle(AppliLib.styleDefaultContainer);
        hbMain.setAlignment(Pos.BASELINE_CENTER);
        hbMain.setPadding(new Insets(20));
        

        Text txt1 = new Text("Identifiant");
        Text txt2 = new Text("Mot de passe");
        Text txt3 = new Text("Confirmation\nmot de passe");
        Text txt4 = new Text("Email");
        Text txt5 = new Text("Nom");
        Text txt6 = new Text("Prénom");
        Text txt7 = new Text("Adresse");
        Text txt8 = new Text("Ville");
        Text txt9 = new Text("Code Postal");
        Text txt10 = new Text("Téléphone");

        txt1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt5.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt7.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt8.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt9.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt10.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        TextField tfId = new TextField();
        PasswordField pf = new PasswordField();
        PasswordField pfConfirm = new PasswordField();
        TextField tfMail = new TextField();
        TextField tfNom = new TextField();
        TextField tfPrenom = new TextField();
        TextField tfAddress = new TextField();
        TextField tfVille = new TextField();
        TextField tfCodePostal = new TextField();
        TextField tfTel = new TextField();

        tfId.setStyle(AppliLib.styleTextField);
        pf.setStyle(AppliLib.styleTextField);
        pfConfirm.setStyle(AppliLib.styleTextField);
        tfMail.setStyle(AppliLib.styleTextField);
        tfNom.setStyle(AppliLib.styleTextField);
        tfPrenom.setStyle(AppliLib.styleTextField);
        tfAddress.setStyle(AppliLib.styleTextField);
        tfVille.setStyle(AppliLib.styleTextField);
        tfCodePostal.setStyle(AppliLib.styleTextField);
        tfTel.setStyle(AppliLib.styleTextField);

        VBox vbId = new VBox(10);
        VBox vbPwd = new VBox(10);
        VBox vbPwdConf = new VBox(10);
        VBox vbMail = new VBox(10);
        VBox vbNom = new VBox(10);
        VBox vbPrenom = new VBox(10);
        VBox vbAddress = new VBox(10);
        VBox vbVille = new VBox(10);
        VBox vbCodePostal = new VBox(10);
        VBox vbTel = new VBox(10);

        vbId.getChildren().addAll(txt1, tfId);
        vbPwd.getChildren().addAll(txt2, pf);
        vbPwdConf.getChildren().addAll(txt3, pfConfirm);
        vbMail.getChildren().addAll(txt4, tfMail);
        vbNom.getChildren().addAll(txt5, tfNom);
        vbPrenom.getChildren().addAll(txt6, tfPrenom);
        vbAddress.getChildren().addAll(txt7, tfAddress);
        vbVille.getChildren().addAll(txt8, tfVille);
        vbCodePostal.getChildren().addAll(txt9, tfCodePostal);
        vbTel.getChildren().addAll(txt10, tfTel);

        tfTel.textProperty().addListener(new ControleurTextFieldTel(tfTel));
        tfCodePostal.textProperty().addListener(new ControleurCodePostal(tfCodePostal));

        VBox vb1 = new VBox(15);
        VBox vb2 = new VBox(15);

        vb1.getChildren().addAll(vbId, vbPwd, vbPwdConf, vbMail, vbNom, vbPrenom);
        vb2.getChildren().addAll(vbAddress, vbVille, vbCodePostal, vbTel, this.bAjouterVendeur);

        hbMain.getChildren().addAll(vb1, vb2);

        return hbMain;
    }

    public Alert popUpConfirmerLibrairie(String nom, String ville) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Voulez-vous vraiment ajouter la librairie " + nom + " qui se situe à " + ville + " ?", ButtonType.OK,
                ButtonType.CANCEL);
        alert.setTitle("Confirmation");
        return alert;
    }

}