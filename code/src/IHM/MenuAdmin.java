import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;


public class MenuAdmin extends BorderPane{
    private Button bDeconnexion;
    private Button bCreerVendeur;
    private Button bAjouter;
    private Button bGererStocks;
    private Button bAfficherStat;
    private ComboBox<String> cb;
    private AdministrateurBD adminBD;
    private static ConnexionMySQL connexion;

    public MenuAdmin(Button quitter) {
        this.bDeconnexion = new Button("Deconnexion");
        this.bCreerVendeur = new Button("Créer compte vendeur");
        this.bAjouter = new Button("Ajouter");
        this.bGererStocks = new Button("Gérer les stocks");
        this.bAfficherStat = new Button("Afficher");
        try {
            this.connexion = new ConnexionMySQL();
            this.adminBD = new AdministrateurBD(connexion);
        } catch (ClassNotFoundException e) {
            System.out.println("");
        }

        this.bDeconnexion.setStyle(AppliLib.styleBouton);
        this.bCreerVendeur.setStyle(AppliLib.styleBouton);
        this.bAjouter.setStyle(AppliLib.styleBouton);
        this.bGererStocks.setStyle(AppliLib.styleBouton);
        this.bAfficherStat.setStyle(AppliLib.styleBouton);

        this.bDeconnexion.setSkin(new MyButtonSkin(bDeconnexion));
        this.bCreerVendeur.setSkin(new MyButtonSkin(bCreerVendeur));
        this.bAjouter.setSkin(new MyButtonSkin(bAjouter));
        this.bGererStocks.setSkin(new MyButtonSkin(bGererStocks));
        this.bAfficherStat.setSkin(new MyButtonSkin(bAfficherStat));

        this.setTop(top());
        this.setLeft(left());        
        this.setCenter(center());
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
        VBox vbMain = new VBox(10);
        vbMain.setPrefWidth(250);
        vbMain.setPrefHeight(400);
        vbMain.setPadding(new Insets(10,0,0,0));

        VBox vb1 = new VBox(15);
        vb1.setPrefHeight(250);
        
        HBox hb1 = new HBox(10);
        hb1.setPadding(new Insets(5,0,0,1));

        HBox hb2 = new HBox(10);
        hb1.setPadding(new Insets(5,0,0,1));

        HBox hb3 = new HBox(10);
        hb1.setPadding(new Insets(5,0,0,1));

        Text t1 = new Text("Ajouter une librairie");
        t1.setFont(Font.font("Arial",FontWeight.BOLD, 20));

        Text nom = new Text("Nom :");
        nom.setFont(Font.font("Arial", 15));
        
        Text ville = new Text("Ville :");
        ville.setFont(Font.font("Arial", 15));
        
        Text idmag = new Text("Id :");
        idmag.setFont(Font.font("Arial", 15)); 

        TextField tfNom = new TextField();
        TextField tfVille = new TextField();
        TextField tfIdmag = new TextField();
        
        tfNom.setStyle(AppliLib.styleTextField);
        tfVille.setStyle(AppliLib.styleTextField);
        tfIdmag.setStyle(AppliLib.styleTextField);
        
        hb1.getChildren().addAll(nom, tfNom);
        hb2.getChildren().addAll(ville, tfVille);
        hb3.getChildren().addAll(idmag, tfIdmag);


        //Centrer bouton ajouter

        VBox vb2 = new VBox(15);
        Text t2 = new Text("Statistiques de ventes");
        t2.setFont(Font.font("Arial",FontWeight.BOLD, 20));


        this.cb = new ComboBox<>(FXCollections.observableArrayList(
            "Nombre de livres vendus par magasin par année",
            "CA par thème pour une année",
            "Évolution des CA des magasins par mois pour une année",
            "Comparaison vente en ligne et en magasin",
            "Les dix éditeurs les plus importants en nombre d'auteur",
            "Origine des clients ayant acheté des livres d'un auteur",
            "Valeur du stock par magasin",
            "Évolution du chiffre d'affaire total par client"
        ));
        cb.setMaxWidth(200);

        vb1.getChildren().addAll(t1, hb1, hb2, hb3, this.bAjouter);
        this.bAjouter.setOnAction(new ControleurAjouterLibrairie(this, this.adminBD, tfNom, tfVille, tfIdmag));
        this.bAjouter.disableProperty().bind(tfNom.textProperty().isEmpty().or(tfVille.textProperty().isEmpty()).or(tfIdmag.textProperty().isEmpty()));
        vb1.setStyle(AppliLib.styleDefaultContainer);
        vb1.setAlignment(Pos.BASELINE_CENTER);

        vb2.getChildren().addAll(t2, cb, this.bAfficherStat);
        // bAfficherStat.setOnAction(new ControleurAfficherStat());
        vb2.setStyle(AppliLib.styleDefaultContainer);
        vb2.setAlignment(Pos.BASELINE_CENTER);
        vb2.setPrefHeight(150);

        vbMain.getChildren().addAll(vb1, vb2);
        
        return vbMain;
    }

    public Pane center() {
        VBox vbMain = new VBox(10);
        HBox hb = new HBox(10);
        vbMain.setPrefHeight(350);
        // Margin = out
        // Padding = in
        // Insets(top, right, bottom, left);
        vbMain.setPadding(new Insets(10,0,0,10));
        hb.setPadding(new Insets(10));
        hb.getChildren().addAll(bCreerVendeur, bGererStocks);
        
        VBox vbStat = new VBox();
        Text tStat = new Text("Titre du grapique");
        ImageView img = new ImageView("../img/logo_placeholder.png");
        vbStat.setPadding(new Insets(10));

        hb.setStyle(AppliLib.styleDefaultContainer);
        vbStat.setStyle(AppliLib.styleDefaultContainer);
        vbStat.getChildren().addAll(img, tStat);
        vbStat.setPrefHeight(300);

        vbMain.getChildren().addAll(hb, vbStat);
        return vbMain;
    }

    public Alert popUpMettreToutesLesVal() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Un ou plusieurs champs n'ont pas été complété.", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    public Alert popUpConfirmerLibrairie(String nom, String ville) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Voulez-vous vraiment ajouter la librairie " + nom + " qui se situe à " + ville + " ?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Confirmation");
        return alert;
    }

}