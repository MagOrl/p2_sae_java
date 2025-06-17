

import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
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


public class MenuGererStocksGlobaux extends BorderPane{
    private Button btDeconexion;
    private Text prenom;
    private Text nom;
    private Text librairieActuelle;
    private ComboBox<String> choixLibrairie;
    private ObservableList<String> lesLibrairies; 
    private Button btAjouterLivre;
    private TextField isbn;
    private TextField titre;
    private TextField datePubli;
    private TextField nbPages;  
    private TextField qte;
    private TextField prix;
    private AdministrateurBD modele;
    private static ConnexionMySQL connexion;

    
    public MenuGererStocksGlobaux(Button bouton){
        try{
            connexion = new ConnexionMySQL();
            modele = new AdministrateurBD(connexion);
        }    
        catch(ClassNotFoundException e){
            System.out.println("Nous n'avons pas pu connecter l'application à la base de données");
        }
        this.librairieActuelle = new Text();
        this.lesLibrairies = FXCollections.observableArrayList("Cap au Sud", "Loire et livres");
        this.choixLibrairie = new ComboBox<>(lesLibrairies);
        this.btAjouterLivre = new Button("");
        this.isbn = new TextField();
        this.titre = new TextField();
        this.datePubli = new TextField();
        this.nbPages = new TextField();
        this.qte = new TextField();
        this.prix = new TextField();
        this.btDeconexion = bouton;
        this.prenom = new Text();
        this.nom = new Text();
        this.setTop(top());
        this.setLeft(left());
        this.setCenter(center());
        
    }

    public VBox left(){
        VBox vbLeft = new VBox();
        VBox vbAjouteLivre = new VBox();

        this.isbn.setStyle(AppliLib.styleTextField);
        this.isbn.focusedProperty().addListener(new ControleurISBN(isbn, this));

        this.titre.setStyle(AppliLib.styleTextField);

        this.datePubli.setStyle(AppliLib.styleTextField);
        this.datePubli.focusedProperty().addListener(new ControleurDate(datePubli, this));

        this.nbPages.setStyle(AppliLib.styleTextField);
        this.nbPages.focusedProperty().addListener(new ControleurNbPages(nbPages, this));

        this.qte.setStyle(AppliLib.styleTextField);
        this.qte.focusedProperty().addListener(new ControleurQuantite(qte, this));

        this.prix.setStyle(AppliLib.styleTextField);
        this.prix.focusedProperty().addListener(new ControleurPrix(prix, this));


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
        this.btAjouterLivre.setStyle(AppliLib.styleBouton);
        this.btAjouterLivre.setSkin(new MyButtonSkin(btAjouterLivre));
        this.btAjouterLivre.disableProperty().bind(isbn.textProperty().isEmpty().or(datePubli.textProperty().isEmpty().
                                                    or(nbPages.textProperty().isEmpty().
                                                    or(qte.textProperty().isEmpty().   
                                                    or(prix.textProperty().isEmpty().
                                                    or(titre.textProperty().isEmpty()))))));

        
        //desactiverBtAjouter();
        vbBouton.getChildren().addAll(this.btAjouterLivre);
        vbBouton.setAlignment(Pos.BOTTOM_CENTER);
        vbBouton.setPadding(new Insets(10));

        vbAjouteLivre.getChildren().addAll(gpTop, vbBouton);
        vbAjouteLivre.setStyle(AppliLib.styleDefaultContainer);

        VBox vbMajQte = new VBox();

        GridPane gpQte = new GridPane();
        TextField isbnQte = new TextField();
        isbnQte.setStyle(AppliLib.styleTextField);
        isbnQte.focusedProperty().addListener(new ControleurISBN(isbnQte, this));

        TextField nouvQte = new TextField();
        nouvQte.setStyle(AppliLib.styleTextField);
        nouvQte.focusedProperty().addListener(new ControleurQuantite(nouvQte, this));
        
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
        Button btAjouterQte = new Button("Mettre a jour");
        btAjouterQte.setStyle(AppliLib.styleBouton);
        btAjouterQte.setSkin(new MyButtonSkin(btAjouterQte));
        vBoxBtQte.getChildren().addAll(btAjouterQte);
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
        setPrenom("Matteo");
        setPrenom("Foucher");

        vbLeft.getChildren().addAll(titre, this.nom, this.prenom);
        //--------------------------------------------------------
        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setStyle(AppliLib.styleTextField);
        //--------------------------------------------------------

        bandeau.setRight(btDeconexion);
        bandeau.setLeft(vbLeft);
        
        bandeau.setStyle(AppliLib.styleBanniere);
        bandeau.setPadding(new Insets(20));

        return bandeau;
    }

    public VBox center(){
        
        VBox vBoxCenter = new VBox();
        VBox vBoxAfficheStock = new VBox();

        HBox top = new HBox();
        Text placeHolder = new Text("librairie actuelle : ");
        this.librairieActuelle = new Text("Cap au Sud");
        
        placeHolder.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.librairieActuelle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        
        this.choixLibrairie.setValue("test");

        top.getChildren().addAll(librairieActuelle, changeLibrairie);
        top.setMargin(changeLibrairie, new Insets(4,0,0,110));

        

        HBox hbCenter = new HBox(10);

        ImageView imgwRecherche = new ImageView();
        imgwRecherche.setImage(new Image("../img/loupeRecherche.png"));
        imgwRecherche.setFitHeight(20);
        imgwRecherche.setFitWidth(20);
        Button recherche = new Button("",imgwRecherche);
        recherche.setStyle(AppliLib.styleBouton);
        recherche.setSkin(new MyButtonSkin(recherche));

        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setStyle(AppliLib.styleTextField);
        hbCenter.getChildren().addAll(recherche, barreDeRecherche);

        GridPane gpStocks = new GridPane();
        
        Text livre0 = new Text("Isbn : , titre : , date publi : 2006, Nombre de Pages : 45, Prix :4, Quantité : 2");
        Text livre1 = new Text("Isbn : , titre : , date publi : 2006, Nombre de Pages : 45, Prix :4, Quantité : 2");
        Text livre2 = new Text("Isbn : , titre : , date publi : 2006, Nombre de Pages : 45, Prix :4, Quantité : 2");
        Text livre3 = new Text("Isbn : , titre : , date publi : 2006, Nombre de Pages : 45, Prix :4, Quantité : 2");
        Text livre4 = new Text("Isbn : , titre : , date publi : 2006, Nombre de Pages : 45, Prix :4, Quantité : 2");


        ImageView imgwSuppr = new ImageView();
        imgwSuppr.setImage(new Image("../img/croix.png"));
        imgwSuppr.setFitHeight(20);
        imgwSuppr.setFitWidth(20);
        Button btSuppr0 = new Button("",imgwSuppr);
        btSuppr0.setStyle(AppliLib.styleBouton);
        btSuppr0.setSkin(new MyButtonSkin(btSuppr0));

        Button btSuppr1 = new Button("",imgwSuppr);
        btSuppr1.setStyle(AppliLib.styleBouton);
        btSuppr1.setSkin(new MyButtonSkin(btSuppr1));

        Button btSuppr2 = new Button("",imgwSuppr);
        btSuppr2.setStyle(AppliLib.styleBouton);
        btSuppr2.setSkin(new MyButtonSkin(btSuppr2));


        Button btSuppr3 = new Button("",imgwSuppr);
        btSuppr3.setStyle(AppliLib.styleBouton);
        btSuppr3.setSkin(new MyButtonSkin(btSuppr3));

        Button btSuppr4 = new Button("",imgwSuppr);
        btSuppr4.setStyle(AppliLib.styleBouton);
        btSuppr4.setSkin(new MyButtonSkin(btSuppr4));


        gpStocks.add(livre0, 0, 0);
        gpStocks.add(livre1, 0, 1);
        gpStocks.add(livre2, 0, 2);
        gpStocks.add(livre3, 0, 3);
        gpStocks.add(livre4, 0, 4);

        gpStocks.add(btSuppr0, 1, 0);
        gpStocks.add(btSuppr1, 1, 1);
        gpStocks.add(btSuppr2, 1, 2);
        gpStocks.add(btSuppr3, 1, 3);
        gpStocks.add(btSuppr4, 1, 4);
        
        gpStocks.setVgap(2.0);
        gpStocks.setHgap(5.0);
        vBoxCenter.setStyle(AppliLib.styleDefaultContainer);
        vBoxCenter.setPadding(new Insets(7));
        vBoxCenter.getChildren().addAll(gpStocks);

        ImageView imgwSuiv = new ImageView();
        imgwSuiv.setImage(new Image("../img/croix.png"));
        imgwSuiv.setFitHeight(20);
        imgwSuiv.setFitWidth(20);
        Button pageSuivante = new Button("", imgwSuiv);

        ImageView imgwPrec = new ImageView();
        imgwPrec.setImage(new Image("../img/croix.png"));
        imgwPrec.setFitHeight(20);
        imgwPrec.setFitWidth(20);
        Button pagePrecedente = new Button("", imgwPrec);

        HBox passerPages = new HBox();
        passerPages.getChildren().addAll(pagePrecedente, pageSuivante);



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

    public void resetTFAjouterLivre(){
        this.isbn.setText("");
        this.titre.setText("");
        this.datePubli.setText("");
        this.nbPages.setText("");
        this.qte.setText("");
        this.prix.setText("");
    }

    public Alert popUpAjouterLivreSQLException(){
        // A implementer    
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue lors de l'ajout du livre");
        return alert;
    }
}