
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class AppliLib extends Application {
    /**
     * La scène de la page, mit en attribut pour pouvoir changer de fenetre
     * dynamiquement
     */
    private Scene scene;
    /**
     * Boutton pour quitter l'application
     */
    private Button btnQuitte;
    /**
     * Bouton pour passer à la page de création de compte
     */
    private Button creeCompte;
    /**
     * Bouton se connecter
     */
    private Button connexion;
    /**
     * Modèle pour les requètes administrateur
     */
    private AdministrateurBD adminBD;
    /**
     * Modèle pour les requètes vendeur
     */

    private VendeurBD vendeurBD;
    /**
     * Modèle pour les requètes client
     */
    private ClientBD clientBD;
    /**
     * L'utilisateur actuel
     */
    private Personne utilisateur;
    /**
     * Connexion avec mysqld
     */
    private ConnexionMySQL connexionSQL;
    /**
     * le magasin que le vendeur choisi pour se connecter
     */
    private ComboBox<String> nomMag;
    /**
     * Le menu de création de compte directement charger dans les attribut pour
     * pouvoir
     * changer de page rapidement
     */
    private MenuCreaCompte menuCrea;
    private Button confirmCrea;
    private Button quitteCrea;
    private Button changeInfoBD;
    private Button quitteInfo;
    private MenuAcceuil menuAcc;
    /**
     * style CSS à utilise pour les boutons
     */
    public static String styleBouton = "-fx-background-color:rgb(120, 120, 120);" +
            "-fx-border-radius: 50; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    /**
     * Style CSS à utiliser pour les bannière
     */
    public static String styleBanniere = "-fx-border-radius: 15;" +
            "-fx-border-color:rgb(0, 0, 0);" +
            "-fx-border-width: 3;" + "-fx-background-color: #0b7f94;" + "-fx-border-insets: -2.5px;" +
            "-fx-border-width: 3;" + "-fx-background-color: #0b7f94;" + "-fx-border-insets: -2.5px;";
    /**
     * Style CSS à utiliser pour les boîte d'interaction habituel
     */
    public static String styleDefaultContainer = "-fx-background-color: #5d9b7d;" +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    /** Style CSS à utiliser pour les textfield/Combobox */
    public static String styleTextField = "    -fx-background-color: #dbb1b1, #fff0f0;\n" +
            "    -fx-background-radius: 15;\n" +
            "    -fx-border-radius: 15;\n" +
            "    -fx-border-color: black;";
    /** Style CSS utiliser pour certaines images */
    public static String styleBoutonImg = "-fx-background-color: transparent;";

    /**
     * La méthode d'initialisation qui charge tout les attributs ainsi que les menus
     */
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
        this.btnQuitte.setStyle(styleBouton);

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

    /**
     * Méthode lors du lancement, met en scène les éléments du menu client
     */
    @Override
    public void start(Stage stg) {
        this.scene = new Scene(this.menuAcc);
        stg.setScene(this.scene);
        stg.setTitle("Menu principal");
        stg.show();
    }

    /**
     * Affiche l'acceuil
     */
    public void afficheMenuAcceuil() {
        this.scene.setRoot(this.menuAcc);
    }

    /**
     * Affiche le menu client
     */
    public void afficheMenuClient() {
        this.scene.setRoot(new MenuClient(this));
    }

    /**
     * Fait un retour sur le menu client
     * 
     * @param menuCli le menu client en mémoire
     */
    public void retourMenuClient(MenuClient menuCli) {
        this.scene.setRoot(menuCli);
    }

    /**
     * Affiche le menu des information personel du client
     * 
     * @param menuCli
     */
    public void afficheMenuInfosPersos(MenuClient menuCli) {

        this.scene.setRoot(new MenuInfosPersos(this, this.utilisateur, menuCli));
    }

    /**
     * Affiche le menu historique des commandes du client
     * 
     * @param menuCli
     */
    public void afficheMenuHistorique(MenuClient menuCli) {
        this.scene.setRoot(new MenuHistorique(this, menuCli, (Client) this.utilisateur));
    }

    /**
     * affiche le menu du panier du client
     * 
     * @param menuCli
     */
    public void afficheMenuPanier(MenuClient menuCli) {
        this.scene.setRoot(new MenuPanier(this, (Client) this.utilisateur, menuCli));
    }

    /**
     * affiche le menu administrateur
     * 
     * @param adm
     */
    public void afficheMenuAdmin(Administrateur adm) {
        this.scene.setRoot(new MenuAdmin(this.btnQuitte, adm, this));
    }

    /**
     * affiche le menu de création de compte
     */
    public void afficheMenuCreaCompte() {
        this.scene.setRoot(this.menuCrea);
    }

    /**
     * affiche le menu pour pouvoir changer ses éléments
     */
    public void afficheInfoBD() {
        this.scene.setRoot(new MenuChangeInfoBD(this.quitteInfo));
    }

    /**
     * affiche le menu pour gerer les stocks globaux
     * 
     * @param adm
     */
    public void afficheMenuGererStocksGlobaux(Administrateur adm) {
        this.scene.setRoot(new MenuGererStocksGlobaux(this, adm));
    }

    /**
     * affiche le menu du vendeur
     */
    public void afficheMenuVendeur() {
        this.scene.setRoot(new MenuVendeur((Vendeur) this.utilisateur, this, this.vendeurBD));
    }

    /**
     * affiche le menu du vendeur
     * 
     * @param vend
     */
    public void afficheMenuVendeur(Vendeur vend) {
        this.scene.setRoot(new MenuVendeur(vend, this, this.vendeurBD));
    }

    /**
     * affiche le menu pour transferer les livres
     * 
     * @param menuVend
     */
    public void afficheFenetreTransfererLivre(MenuVendeur menuVend) {
        this.scene.setRoot(new FenetreTransfererLivre(this, menuVend, this.vendeurBD));

    }

    /**
     * affiche la fenetre de la mise à jour de quantitié d'un livre
     * 
     * @param menuV
     */
    public void afficheFenetreMajQte(MenuVendeur menuV) {
        this.scene.setRoot(new FenetreMajQte(this, this.vendeurBD, menuV));
    }

    /**
     * Méthode qui permet de faire quitter l'application entièrement
     */
    public void quitte() {
        Platform.exit();
    }

    /**
     * pop-up si utilisateur quitte
     * 
     * @return
     */
    public Alert popUpQuitte() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "êtes vous sûr de vouloir quitter ?", ButtonType.YES);
        alert.setTitle("Panier plein");
        return alert;
    }

    /**
     * pop-up si utilisateur n'a pas mit d'utilisateur
     * 
     * @return
     */

    public Alert popUpPasMitDeUser() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Vous avez pas mit d'utilisateur", ButtonType.YES);
        alert.setTitle("Pas d'utilisateur");
        return alert;
    }

    /**
     * pop-up si utilisateur à mit une mauvaise saisie dans menu connexion
     * 
     * @return
     */

    public Alert popUpMauvaiseSaisie() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Vous avez mal saisie votre identifiant ou votre mot de passe ", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    /**
     * pop-up si il n'y à pas de livre recommander
     * 
     * @return
     */

    public Alert popUpPasDeRecommandations() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de recommandations pour vous.", ButtonType.YES);
        alert.setTitle("Aucune recommandation");
        return alert;
    }

    /**
     * pop-up si le thême ,n'existe pas
     * 
     * @return
     */

    public Alert popUpPasDeThemes() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de thêmes existants.", ButtonType.YES);
        alert.setTitle("Aucun thême");
        return alert;
    }

    /**
     * pop up si pas de magasin existant
     * 
     * @return
     */
    public Alert popUpPasDeMagasins() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de magasins existants.", ButtonType.YES);
        alert.setTitle("Aucun magasin");
        return alert;
    }

    /**
     * pop-up si pas de commande actuellement
     * 
     * @return
     */

    public Alert popUpPasDeCommandes() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Pas de commandes enregistrées.", ButtonType.YES);
        alert.setTitle("Aucune commande");
        return alert;
    }

    /**
     * pop up si le panier est plein
     * 
     * @return
     */
    public Alert popUpPanierPlein() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Votre panier est plein.", ButtonType.YES);
        alert.setTitle("Panier plein");
        return alert;
    }

    /**
     * pop-up pour savoir si client est sur de commander
     * 
     * @return
     */

    public Alert popUpSurDeCommender() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Voulez vous finaliser votre transaction ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Panier plein");
        return alert;
    }

    /**
     * pop-up si le mot de passe de confirmation n'est pas le même
     * 
     * @return
     */

    public Alert popUpPasMemeMotDePasse() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Le mot de passe de confirmation ne correspond pas", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    /**
     * pop-up si tout les champs n'ont pas été completer
     * 
     * @return
     */

    public Alert popUpMettreToutesLesVal() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Un ou plusieurs champs n'ont pas été complété.", ButtonType.YES);
        alert.setTitle("Erreur");
        return alert;
    }

    /**
     * pop-up si compte est crée
     * 
     * @return
     */

    public Alert popUpCompteCree(String prenom) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Compte à bien été créé, bienvenue " + prenom, ButtonType.YES);
        alert.setTitle("Tout est bon " + prenom);
        return alert;
    }

    /**
     * pop-up quand livre ajouté
     * 
     * @return
     */

    public Alert popUpAjouteLivre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Le livre a été ajouté avec succès :)");
        alert.setTitle("Tout est bon !!");
        return alert;
    }

    /**
     * pop-up si quantité de livre à bien été changé
     * 
     * @return
     */

    public Alert popUpModifQte() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "La quantité du livre a été changé avec succès :)");
        alert.setTitle("Tout est bon !!");
        return alert;
    }

    /**
     * si transfert de livre à été effectuer
     * 
     * @return
     */
    public Alert popUpTransferer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Le transfert a été effectué avec succès :)");
        alert.setTitle("Tout est bon !!");
        return alert;
    }

    /**
     * si mauvaise quantité
     * 
     * @return
     */
    public Alert popUpQteInfAZero() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de quantité");
        alert.setHeaderText(null);
        alert.setContentText("La quantité doit être supérieure ou égale à zéro.");
        return alert;
    }

    /**
     * 
     * @return
     */
    public Alert popUpDeconnexion() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de déconnexion");
        alert.setHeaderText("Voulez-vous vraiment vous déconnecter ?");
        alert.setContentText("Cliquez sur OK pour vous déconnecter, ou Annuler pour rester.");
        return alert;
    }

    /**
     * donne le modèle pour les requètes de vendeur
     * 
     * @return
     */
    public VendeurBD getVendeurBD() {
        return this.vendeurBD;
    }

    /**
     * donne le modèle pour les requètes admin
     * 
     * @return
     */
    public AdministrateurBD getAdminBD() {
        return this.adminBD;
    }

    /**
     * donne le modèle pour les requètes client
     * 
     * @return
     */
    public ClientBD getClientBD() {
        return this.clientBD;
    }

    /**
     * donne le menu acceuil
     * 
     * @return
     */
    public MenuAcceuil getMenuAcc() {
        return this.menuAcc;
    }

    /**
     * donne l'utilisateur
     * 
     * @return
     */
    public Personne getUtilisateur() {
        return this.utilisateur;
    }

    /**
     * met un utilisateur
     * 
     * @param pers
     */
    public void setUtilisateur(Personne pers) {
        this.utilisateur = pers;
    }

    /**
     * donne la valeur actuel saisie par l'utilisateur de la combobox de la
     * connexion du compte en vendeur
     * 
     * @return
     */
    public String getValMag() {
        return this.nomMag.getValue();
    }

    /**
     * donne le menu de création de compte
     * 
     * @return
     */
    public MenuCreaCompte getMenuCreaCompte() {
        return this.menuCrea;
    }

}
