

import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;


public class MenuGererStocksGlobaux extends BorderPane{

    private AppliLib app;
    private Administrateur admin;
    private Button btRetour;
    private Text prenom;
    private Text nom;
    private Text librairieActuelle;
    private ComboBox<String> choixLibrairie;
    private ObservableList<String> lesLibrairies;
    private ComboBox critereReherche;
    private String critereActuel;
    private ObservableList<String> lesCriteres;
    private Button bTRecherche;  
    private TextField barreDeRecherche;
    private Button btAjouterLivre;
    private Button btAjouterQte;
    private TextField isbn;
    private TextField titre;
    private TextField datePubli;
    private TextField nbPages;  
    private TextField qte;
    private TextField prix;
    private TextField isbnQte;
    private TextField nouvQte;
    private GridPaneStocks gpStocks;
    private VBox vBoxCenter;
    private Map<Button, Livre> supprLivre;
    private AdministrateurBD modele;
    private static ConnexionMySQL connexion;

    public static String styleBoutonPage = "-fx-background-color:rgb(255, 255, 255);" +
            "-fx-border-radius: 50; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";

    
    public MenuGererStocksGlobaux(AppliLib app, Administrateur admin){
        try{
            connexion = new ConnexionMySQL();
            modele = new AdministrateurBD(connexion);
            this.librairieActuelle = new Text();
            this.lesLibrairies = FXCollections.observableArrayList();
            for(String mag : this.modele.choixLibrairie()){
                this.lesLibrairies.add(mag);
            }
        }catch(SQLException e){
            popUpAjouterLivreSQLException().show();
        }catch(ClassNotFoundException e){
            System.out.println("Nous n'avons pas pu connecter l'application à la base de données");
        }

        ImageView imgwRecherche = new ImageView();
        imgwRecherche.setImage(new Image("../img/loupeRecherche.png"));
        imgwRecherche.setFitHeight(20);
        imgwRecherche.setFitWidth(20);

        this.app = app;
        this.admin = admin;
        this.barreDeRecherche = new TextField();
        this.bTRecherche = new Button("",imgwRecherche);
        this.vBoxCenter = new VBox();
        this.gpStocks = new GridPaneStocks();
        this.choixLibrairie = new ComboBox<>();
        this.choixLibrairie.setItems(lesLibrairies);
        this.choixLibrairie.setValue("Changer de librairie");
        this.choixLibrairie.valueProperty().addListener(new ControleurChoixLibrairie(librairieActuelle, this, this.modele));
        this.choixLibrairie.setStyle(AppliLib.styleTextField);
        this.critereReherche = new ComboBox<>();
        this.lesCriteres =  FXCollections.observableArrayList("Titre", "Auteur", "ISBN");
        this.critereReherche.setItems(lesCriteres);
        this.critereReherche.setValue("Choisir un critère");
        this.critereReherche.valueProperty().addListener(new ControleurCritereRecherche(this.critereActuel, this, this.modele));
        this.critereReherche.setStyle(AppliLib.styleTextField);
        this.critereActuel = "";
        this.btAjouterLivre = new Button("Ajouter");
        this.btAjouterLivre.setSkin(new MyButtonSkin(btAjouterLivre));
        this.btAjouterLivre.setStyle(AppliLib.styleBouton);
        this.btAjouterQte = new Button("Modifier quantité");
        this.btAjouterQte.setSkin(new MyButtonSkin(btAjouterQte));
        this.btAjouterQte.setStyle(AppliLib.styleBouton);
        this.isbn = new TextField();
        this.titre = new TextField();
        this.datePubli = new TextField();
        this.nbPages = new TextField();
        this.qte = new TextField();
        this.prix = new TextField();
        this.isbnQte = new TextField();
        this.nouvQte = new TextField();
        this.btRetour = new Button("Retour");
        this.btRetour.setOnAction(new ControleurRetourAdmin(this.app, admin));
        this.prenom = new Text();
        this.nom = new Text();
        this.supprLivre = new HashMap<>();
        this.setTop(top());
        this.setLeft(left());
        this.setCenter(center());
        
    }

    public VBox left(){
        VBox vbLeft = new VBox();
        VBox vbAjouteLivre = new VBox();

        this.isbn.setStyle(AppliLib.styleTextField);
        this.isbn.textProperty().addListener(new ControleurISBN(isbn));

        this.titre.setStyle(AppliLib.styleTextField);

        this.datePubli.setStyle(AppliLib.styleTextField);
        this.datePubli.textProperty().addListener(new ControleurDate(datePubli));

        this.nbPages.setStyle(AppliLib.styleTextField);
        this.nbPages.textProperty().addListener(new ControleurNbPages(nbPages));

        this.qte.setStyle(AppliLib.styleTextField);
        this.qte.textProperty().addListener(new ControleurQuantite(qte));

        this.prix.setStyle(AppliLib.styleTextField);
        this.prix.textProperty().addListener(new ControleurPrix(prix));


        Text isbnText = new Text("ISBN :");
        isbnText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text titreText = new Text("Titre :");
        titreText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text datePubliText = new Text("date de publication :");
        datePubliText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text nbPagesText = new Text("nombre de pages :");
        nbPagesText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text qteText = new Text("quantité :");
        qteText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text prixText = new Text("prix :");
        prixText.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        GridPane gpTop = new GridPane();
        gpTop.add(isbnText, 0,1);
        gpTop.add(titreText, 0,2);
        gpTop.add(datePubliText, 0,3);
        gpTop.add(nbPagesText, 0,4);
        gpTop.add(qteText, 0,5);
        gpTop.add(prixText, 0,6);

        gpTop.add(isbn, 1,1);
        gpTop.add(titre, 1,2);
        gpTop.add(datePubli, 1,3);
        gpTop.add(nbPages, 1,4);
        gpTop.add(qte, 1,5);
        gpTop.add(prix, 1,6);

        gpTop.setMargin(isbnText, new Insets(10));
        gpTop.setMargin(titreText, new Insets(10));
        gpTop.setMargin(datePubliText, new Insets(10));
        gpTop.setMargin(nbPagesText, new Insets(10));
        gpTop.setMargin(qteText, new Insets(10));
        gpTop.setMargin(prixText, new Insets(10));
        gpTop.setPadding(new Insets(10));

        VBox vbBouton = new VBox();
        this.btAjouterLivre.setOnAction(new ControleurBoutonAjouter(this, modele));
        this.btAjouterLivre.disableProperty().bind(isbn.textProperty().isEmpty().or(datePubli.textProperty().isEmpty().
                                                    or(nbPages.textProperty().isEmpty().
                                                    or(qte.textProperty().isEmpty().   
                                                    or(prix.textProperty().isEmpty().
                                                    or(titre.textProperty().isEmpty()))))));

        
        vbBouton.getChildren().addAll(this.btAjouterLivre);
        vbBouton.setAlignment(Pos.BOTTOM_CENTER);
        vbBouton.setPadding(new Insets(10));

        vbAjouteLivre.getChildren().addAll(gpTop, vbBouton);
        vbAjouteLivre.setStyle(AppliLib.styleDefaultContainer);

        VBox vbMajQte = new VBox();

        GridPane gpQte = new GridPane();
        this.isbnQte.setStyle(AppliLib.styleTextField);
        this.isbnQte.textProperty().addListener(new ControleurISBN(isbnQte));

        this.nouvQte.setStyle(AppliLib.styleTextField);
        this.nouvQte.textProperty().addListener(new ControleurNouvQte(nouvQte));


        
        Text isbnQteText = new Text("ISBN :");
        isbnQteText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text majQteText = new Text("Quantité :");
        majQteText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        gpQte.add(isbnQte, 1, 0);
        gpQte.add(nouvQte, 1, 1);

        gpQte.add(isbnQteText, 0,0);
        gpQte.add(majQteText, 0, 1);

        gpQte.setMargin(isbnQteText, new Insets(10));
        gpQte.setMargin(majQteText, new Insets(10));
        gpQte.setPadding(new Insets(10));

        VBox vBoxBtQte = new VBox();
        this.btAjouterQte.disableProperty().bind(isbnQte.textProperty().isEmpty().or(nouvQte.textProperty().isEmpty()));
        this.btAjouterQte.setOnAction(new ControleurBoutonMajQte(this, this.modele));
        vBoxBtQte.getChildren().addAll(this.btAjouterQte);
        vBoxBtQte.setAlignment(Pos.BOTTOM_CENTER);
        vBoxBtQte.setPadding(new Insets(10));

        vbMajQte.getChildren().addAll(gpQte, vBoxBtQte);
        vbMajQte.setStyle(AppliLib.styleDefaultContainer);
        vbMajQte.setAlignment(Pos.TOP_CENTER);
        vbLeft.getChildren().addAll(vbAjouteLivre, vbMajQte);
        vbLeft.setPadding(new Insets(7));
        vbLeft.setMargin(vbAjouteLivre, new Insets(5));
        vbLeft.setMargin(vbMajQte, new Insets(5));
        return vbLeft;
    }

    public BorderPane top(){
        BorderPane bandeau = new BorderPane();

        //--------------------------------------------------------
        VBox vbLeft = new VBox();

        Text titre = new Text("Livre Express");
        titre.setFont(Font.font("Arial", 30));
        this.setPrenom(this.admin.getPrenom());
        this.setNom(this.admin.getPrenom());

        HBox infoAdmin = new HBox();
        infoAdmin.getChildren().addAll(this.nom, this.prenom);
        vbLeft.getChildren().addAll(titre, infoAdmin);
        //--------------------------------------------------------
        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setStyle(AppliLib.styleTextField);
        //--------------------------------------------------------

        bandeau.setRight(btRetour);
        bandeau.setLeft(vbLeft);
        
        bandeau.setStyle(AppliLib.styleBanniere);
        bandeau.setPadding(new Insets(20));

        return bandeau;
    }

    public VBox center(){
        
        VBox vBoxAfficheStock = new VBox();

        HBox top = new HBox();
        Text placeHolder = new Text("librairie actuelle : ");

        placeHolder.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.librairieActuelle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        


        top.getChildren().addAll(placeHolder, librairieActuelle, this.choixLibrairie);
        top.setMargin(this.choixLibrairie, new Insets(4,0,0,110));

        

        HBox hbCenter = new HBox(10);

        
        this.bTRecherche.setStyle(AppliLib.styleBouton);
        this.bTRecherche.setSkin(new MyButtonSkin(bTRecherche));

        this.barreDeRecherche.setStyle(AppliLib.styleTextField);
        bTRecherche.setOnAction(new ControleurBoutonRechercheA(this, modele, this.barreDeRecherche));
        hbCenter.getChildren().addAll(bTRecherche, barreDeRecherche, this.critereReherche);
        


        ImageView imgwSuppr = new ImageView();
        imgwSuppr.setImage(new Image("../img/croix.png"));
        imgwSuppr.setFitHeight(20);
        imgwSuppr.setFitWidth(20);

        
        this.vBoxCenter.setStyle(AppliLib.styleDefaultContainer);
        this.vBoxCenter.setPadding(new Insets(7));
        this.vBoxCenter.getChildren().addAll(this.gpStocks);


        ImageView imgwSuivante = new ImageView();
        imgwSuivante.setImage(new Image("../img/boutonSuivant.png"));
        imgwSuivante.setFitHeight(20);
        imgwSuivante.setFitWidth(20);

        Button pageSuivante = new Button();
        pageSuivante.setStyle(styleBoutonPage);
        pageSuivante.setSkin(new MyButtonSkin(pageSuivante));
        pageSuivante.setOnAction(new ControleurPageSuivante(this));
        pageSuivante.setGraphic(imgwSuivante);

        ImageView imgwPrecedente = new ImageView();
        imgwPrecedente.setImage(new Image("../img/boutonPrec.png"));
        imgwPrecedente.setFitHeight(20);
        imgwPrecedente.setFitWidth(20);

        Button pagePrecedente = new Button();
        pagePrecedente.setStyle(styleBoutonPage);
        pagePrecedente.setSkin(new MyButtonSkin(pagePrecedente));
        pagePrecedente.setOnAction(new ControleurPagePrecedente(this));
        pagePrecedente.setGraphic(imgwPrecedente);

        HBox passerPages = new HBox();
        passerPages.getChildren().addAll(pagePrecedente, pageSuivante);
        passerPages.setSpacing(20.0);
        passerPages.setAlignment(Pos.BOTTOM_CENTER);


        VBox vBoxCenterPrincipal = new VBox();

        vBoxCenterPrincipal.getChildren().addAll(top, hbCenter, vBoxCenter, passerPages);
        vBoxCenterPrincipal.setMargin(vBoxCenter, new Insets(14));


        return vBoxCenterPrincipal;

    }


    public void desactiverBtAjouter(){
        Text texteBouton = new Text("Ajouter");
        texteBouton.setStrikethrough(true);
        this.btAjouterLivre.setGraphic(texteBouton);
        this.btAjouterLivre.setDisable(true);
    }

    public void activerBtajouter(){
        Text texteBouton = new Text("Ajouter");
        texteBouton.setStyle("-fx-text-fill: white;");
        this.btAjouterLivre.setGraphic(texteBouton);
        this.btAjouterLivre.setDisable(false);
    }

    

    public void setNom(String nom){
        this.nom.setText(nom);
    }

    public void setPrenom(String prenom){
        this.prenom.setText(prenom);
    }

    public String getPrenom(){
        return this.prenom.getText();
    }

    public String getNom(){
        return this.nom.getText();
    }

    public String getISBN(){
        return this.isbn.getText();
    }

    public String getTitre(){
        return this.titre.getText();
    }

    public String getDatePubli(){
        return this.datePubli.getText();
    }

    public String getNbPages(){
        return this.nbPages.getText();
    }

    public String getQuantite(){
        return this.qte.getText();
    }

    public String getPrix(){
        return this.prix.getText();
    }

    public String getLibrairieActuelle(){
        return this.librairieActuelle.getText();
    }

    public String getNouvQte(){
        return this.nouvQte.getText();
    }

    public String getISBNQte(){
        return this.isbnQte.getText();
    }

    public ComboBox<String> getChoixLibrairie(){
        return this.choixLibrairie;
    }

    public void setLibrairieActuelle(String librairie){
        this.librairieActuelle.setText(librairie);
    }

    public void setStocks(Map<Button, Livre> supprLivre){
        this.supprLivre = supprLivre;
    }

    public void majVueGPStocks(GridPaneStocks nouvGPStocks){
        this.gpStocks = nouvGPStocks;
    }

    public void majVBoxCenter(GridPaneStocks nouvGpStock){
        this.vBoxCenter.getChildren().clear();
        this.vBoxCenter.getChildren().add(nouvGpStock);
    }

    public GridPaneStocks getGpStocks(){
        return this.gpStocks;
    }

    public void setGpStocks(GridPaneStocks gp){
        this.gpStocks = gp;
    }

    public void setCritereActuel(String nouvCritere){
        this.critereActuel = nouvCritere;
    }

    public String getCritereActuel(){
        return this.critereActuel;
    }

    





    public void resetTFAjouterLivre(){
        this.isbn.setText("");
        this.titre.setText("");
        this.datePubli.setText("");
        this.nbPages.setText("");
        this.qte.setText("");
        this.prix.setText("");
    }

    public void resetTFMajQte(){
        this.isbnQte.setText("");
        this.nouvQte.setText("");
    }

    public Alert popUpAjouterLivreSQLException(){
        // A implementer    
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue lors de l'ajout du livre");
        return alert;
    }

    public Alert popUpQteInfAZero(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "La nouvelle quantité est inférieure à zéro, veuillez enlever moins de quantitié");
        return alert;   
    }

    public Alert popUpLivreInexistant(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Le livre dont vous essayez de mettre à jour la quantité n'xiste pas dans la base de donnée");
        return alert;
    }

    public Alert popUpNumberFormatException(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer uniquement des chiffres pour la quantité, la date de publication, le prix et le nombre de pages");
        return alert;
    }

    public Alert popUpLivreAjoute(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le livre a bien été ajouté");
        return alert;
    }

    public Alert popUpQteMaj(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "la quantitié à bien été mise à jour");
        return alert;
    }

    public Alert popPasDeLibrairie(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez choisir une librairie");
        return alert;
    }
}