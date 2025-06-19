
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;

public class AppliLib extends Application {

    private Scene scene;
    private Button btnQuitte;
    private Button creeCompte;
    private Button connexion;
    private MenuAcceuil menuAcc;
    private AdministrateurBD adminBD;
    private VendeurBD vendeurBD;
    private ClientBD clientBD;
    private Personne utilisateur;

    private ConnexionMySQL connexionSQL;
    private ComboBox<String> nomMag;

    public static String styleBouton = "-fx-background-color:rgb(120, 120, 120);" +
            "-fx-border-radius: 50; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    public static String styleBanniere = "-fx-border-radius: 15;" +
            "-fx-border-color:rgb(0, 0, 0);" +
            "-fx-border-width: 3;" + "-fx-background-color: #0b7f94;" + "-fx-border-insets: -2.5px;";
    public static String styleDefaultContainer = "-fx-background-color: #5d9b7d;" +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    public static String styleTextField = "    -fx-background-color: #dbb1b1, #fff0f0;\n" +
            "    -fx-background-radius: 15;\n" +
            "    -fx-border-radius: 15;\n" +
            "    -fx-border-color: black;";

    public static String styleBoutonImg = "-fx-background-color: transparent;";

    @Override
    public void init() {
        try {
            this.connexionSQL = new ConnexionMySQL();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.clientBD = new ClientBD(this.connexionSQL);
        this.adminBD = new AdministrateurBD(this.connexionSQL);
        this.vendeurBD = new VendeurBD(this.connexionSQL);
        this.nomMag = new ComboBox<>();
        try {
            this.nomMag.getItems().addAll(this.vendeurBD.choixLibrairie());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.btnQuitte.setStyle(styleBouton);
        this.connexion.setStyle(styleBouton);
        this.creeCompte.setStyle(styleBouton);
        this.connexion.setMinHeight(40);
        this.connexion.setMinWidth(90);
        this.btnQuitte.setMinHeight(40);
        this.btnQuitte.setMinWidth(90);
        this.creeCompte.setMinHeight(40);
        this.creeCompte.setMinWidth(90);
        this.btnQuitte.setOnAction(new ControlleurQuitter(this));
        this.connexion.setOnAction(new ControlleurConnexion(this));
        this.creeCompte.setOnAction(new ControleurCreationCompte(this));
        this.btnQuitte.setSkin(new MyButtonSkin(this.btnQuitte));
        this.connexion.setSkin(new MyButtonSkin(this.connexion));
        this.creeCompte.setSkin(new MyButtonSkin(this.creeCompte));
        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.creeCompte, this.connexion, this.nomMag);

    }

    @Override
    public void start(Stage stg) {
        this.scene = new Scene(this.menuAcc);
        stg.setScene(this.scene);
        stg.setTitle("Menu principale");
        stg.show();
    }

    public void afficheMenuAcceuil() {
        this.scene.setRoot(this.menuAcc);
    }

    public void afficheMenuClient() {
        this.scene.setRoot(new MenuClient(this));
    }

    public void retourMenuClient(MenuClient menuCli) {
        this.scene.setRoot(menuCli);
    }

    public void afficheMenuInfosPersos(MenuClient menuCli) {

        this.scene.setRoot(new MenuInfosPersos(this, this.utilisateur, menuCli));
    }

    public void afficheMenuHistorique(MenuClient menuCli) {
        this.scene.setRoot(new MenuHistorique(this, menuCli,(Client)this.utilisateur));
    }

    public void afficheMenuPanier(MenuClient menuCli) {
        this.scene.setRoot(new MenuPanier(this, (Client) this.utilisateur, menuCli));
    }

    public void afficheMenuCreaCompte() {
        this.scene.setRoot(new MenuCreaCompte(btnQuitte, this.btnQuitte));
    }

    public void quitte() {
        Platform.exit();
    }

    public Alert popUpQuitte() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Votre panier est plein.", ButtonType.YES);
        alert.setTitle("Panier plein");
        return alert;
    }

    public Alert popUpPasMitDeUser() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Vous avez pas mit d'utilisateur", ButtonType.YES);
        alert.setTitle("Pas d'utilisateur");
        return alert;
    }

    public Alert popUpMauvaiseSaisie() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Vous avez mal saisie votre identifiant ou votre mot de passe ", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    public Alert popUpPasDeRecommandations() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de recommandations pour vous.", ButtonType.YES);
        alert.setTitle("Aucune recommandation");
        return alert;
    }

    public Alert popUpPasDeThemes() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de thêmes existants.", ButtonType.YES);
        alert.setTitle("Aucun thême");
        return alert;
    }

    public Alert popUpPasDeMagasins() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de magasins existants.", ButtonType.YES);
        alert.setTitle("Aucun magasin");
        return alert;
    }

    public Alert popUpPasDeCommandes() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de commandes enregistrées.", ButtonType.YES);
        alert.setTitle("Aucune commande");
        return alert;
    }

    public Alert popUpPanierPlein() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Votre panier est plein.", ButtonType.YES);
        alert.setTitle("Panier plein");
        return alert;
    }

    public Alert popUpSurDeCommender() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Voulez vous finaliser votre transaction ?", ButtonType.YES,ButtonType.NO);
        alert.setTitle("Panier plein");
        return alert;
    }

    public VendeurBD getVendeurBD() {
        return this.vendeurBD;
    }

    public AdministrateurBD getAdminBD() {
        return this.adminBD;
    }

    public ClientBD getClientBD() {
        return this.clientBD;
    }

    public MenuAcceuil getMenuAcc() {
        return this.menuAcc;
    }

    public Personne getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Personne pers) {
        this.utilisateur = pers;
    }

    public String getValMag() {
        return this.nomMag.getValue();
    }
}
