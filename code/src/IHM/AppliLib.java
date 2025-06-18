
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    //private FenetreMajQte fenetreMajQte;
    private FenetreTransfererLivre fenetreTransfererLivre;
    private FenetreVerifDispo fenetreVerifDispo;

    private AdministrateurBD adminBD;
    private VendeurBD vendeurBD;
    private ClientBD clientBD;
    private Personne utilisateur;
    private ConnexionMySQL connexionSQL;
    private ComboBox<String> nomMag;
    private MenuCreaCompte menuCrea;
    private Button confirmCrea;
    private Button quitteCrea;
    private Button changeInfoBD;
    private Button quitteInfo;

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

    @Override
    public void init() {
        this.nomMag = new ComboBox<>();

        try {
            this.connexionSQL = new ConnexionMySQL();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.clientBD = new ClientBD(this.connexionSQL);
            this.adminBD = new AdministrateurBD(this.connexionSQL);
            this.vendeurBD = new VendeurBD(this.connexionSQL);
            this.nomMag.getItems().addAll(this.vendeurBD.choixLibrairie());

        } catch (SQLException e) {
            System.err.println("pas bonne base de données");
        }
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.confirmCrea = new Button("Confirmer");
        this.quitteCrea = new Button("Quitter");
        this.changeInfoBD = new Button("Paramètre");
        this.quitteInfo = new Button("Quitter");

        this.btnQuitte.setStyle(styleBouton + "-fx-background-color:rgb(194, 60, 60);");
        this.connexion.setStyle(styleBouton);
        this.creeCompte.setStyle(styleBouton);
        this.confirmCrea.setStyle(styleBouton);
        this.quitteCrea.setStyle(styleBouton);
        this.confirmCrea.setStyle(styleBouton);
        this.changeInfoBD.setStyle(styleBouton);
        this.quitteInfo.setStyle(styleBouton);

        this.connexion.setMinHeight(40);
        this.connexion.setMinWidth(90);
        this.btnQuitte.setMinHeight(40);
        this.btnQuitte.setMinWidth(90);

        //this.fenetreMajQte = new FenetreMajQte();
        this.fenetreTransfererLivre = new FenetreTransfererLivre(this);
        this.fenetreVerifDispo = new FenetreVerifDispo();

        this.creeCompte.setMinHeight(40);
        this.creeCompte.setMinWidth(90);
        this.quitteCrea.setMinHeight(40);
        this.quitteCrea.setMinWidth(90);
        this.confirmCrea.setMinHeight(40);
        this.confirmCrea.setMinWidth(90);
        this.quitteCrea.setMinHeight(40);
        this.quitteCrea.setMinWidth(90);
        this.changeInfoBD.setMinHeight(40);
        this.changeInfoBD.setMinWidth(90);
        this.quitteInfo.setMinHeight(40);
        this.quitteInfo.setMinWidth(90);

        this.btnQuitte.setOnAction(new ControlleurQuitter(this));
        this.connexion.setOnAction(new ControlleurConnexion(this));
        this.creeCompte.setOnAction(new ControleurCreationCompte(this));
        this.quitteCrea.setOnAction(new ControleurQuitteCreaCompte(this));
        this.confirmCrea.setOnAction(new ControleurCreeCompte(this));
        this.changeInfoBD.setOnAction(new ControleurChangeInfoBD(this));
        this.quitteInfo.setOnAction(new ControleurQuitteCreaCompte(this));

        this.btnQuitte.setSkin(new MyButtonSkin(this.btnQuitte));
        this.connexion.setSkin(new MyButtonSkin(this.connexion));
        this.creeCompte.setSkin(new MyButtonSkin(this.creeCompte));

        this.quitteCrea.setSkin(new MyButtonSkin(this.quitteCrea));
        this.confirmCrea.setSkin(new MyButtonSkin(this.confirmCrea));
        this.changeInfoBD.setSkin(new MyButtonSkin(this.changeInfoBD));
        this.quitteInfo.setSkin(new MyButtonSkin(this.quitteInfo));

        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.creeCompte, this.connexion, this.nomMag, this.changeInfoBD);
        this.menuCrea = new MenuCreaCompte(this.quitteCrea, this.confirmCrea);

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

    public void afficheMenuVendeur(Vendeur vend) {
        this.scene.setRoot(new MenuVendeur(vend, this));
    }

    public void afficheMenuClient() {
        // A implementer
    }

    public void afficheInfosPersos() {
        // A implementer
    }

    public void afficheHistorique() {

    }

    public void afficheFenetreMajQte() {
        this.scene.setRoot(new FenetreMajQte(this));
    }

    public void afficheFenetreTransfererLivre() {
        this.scene.setRoot(this.fenetreTransfererLivre);
    }
    

    public void afficheFenetreVerifDispo() {
        this.scene.setRoot(this.fenetreVerifDispo);
    }

    public void afficheMenuCreaCompte() {
        this.scene.setRoot(this.menuCrea);
    }

    public void afficheInfoBD() {
        this.scene.setRoot(new MenuChangeInfoBD(this.quitteInfo));
    }

    public void quitte() {
        Platform.exit();
    }

    public Alert popUpQuitte() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Êtes vous sûr de quitter ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
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

    public static Alert popUpDeconnexion() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de déconnexion");
        alert.setHeaderText("Voulez-vous vraiment vous déconnecter ?");
        alert.setContentText("Cliquez sur OK pour vous déconnecter, ou Annuler pour rester.");
        return alert;
    }

    public Alert popUpPasMemeMotDePasse() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Le mot de passe de confirmation ne correspond pas", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    public Alert popUpMettreToutesLesVal() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Un ou plusieurs champs n'ont pas été complété.", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    public Alert popUpCompteCree(String prenom) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Compte à bien été créé, bienvenue " + prenom, ButtonType.YES);
        alert.setTitle("Tout est bon " + prenom);
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

    public MenuCreaCompte getMenuCreaCompte() {
        return this.menuCrea;
    }

}
