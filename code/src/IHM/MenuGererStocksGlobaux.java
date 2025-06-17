
import java.awt.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private String btRechercheStyle = "-fx-background-color : #0b7f94;";

    
    public MenuGererStocksGlobaux(Button bouton){
        this.btDeconexion = bouton;
        this.prenom = new Text();
        this.nom = new Text();
        this.setTop(top());
        this.setLeft(left());
        
    }

    public VBox left(){
        VBox vbLeft = new VBox();
        VBox vbAjouteLivre = new VBox();

        TextField isbn = new TextField();
        isbn.setStyle(AppliLib.styleTextField);

        TextField titre = new TextField();
        titre.setStyle(AppliLib.styleTextField);

        TextField datePubli = new TextField();
        datePubli.setStyle(AppliLib.styleTextField);

        TextField nbPages = new TextField();
        nbPages.setStyle(AppliLib.styleTextField);

        TextField qte = new TextField();
        qte.setStyle(AppliLib.styleTextField);

        TextField prix = new TextField();
        prix.setStyle(AppliLib.styleTextField);


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
        Button bAjouterLivre = new Button("Ajouter");
        bAjouterLivre.setStyle(AppliLib.styleBouton);
        bAjouterLivre.setSkin(new MyButtonSkin(bAjouterLivre));
        vbBouton.getChildren().addAll(bAjouterLivre);
        vbBouton.setAlignment(Pos.BOTTOM_CENTER);
        vbBouton.setPadding(new Insets(10));

        vbAjouteLivre.getChildren().addAll(gpTop, vbBouton);
        vbAjouteLivre.setStyle(AppliLib.styleDefaultContainer);

        VBox vbMajQte = new VBox();

        GridPane gpQte = new GridPane();
        TextField isbnQte = new TextField();
        isbnQte.setStyle(AppliLib.styleTextField);

        TextField nouvQte = new TextField();
        nouvQte.setStyle(AppliLib.styleTextField);
        
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


        HBox top = new HBox();
        Text librairieActuelle = new Text("librairie actuelle : Cap au Sud");
        Button changeLibrairie = new Button("Changer de librairie");
        top.getChildren().addAll(librairieActuelle, changeLibrairie);


        HBox hbCenter = new HBox(10);

        ImageView imgwRecherche = new ImageView();
        imgwRecherche.setImage(new Image("../img/loupeRecherche.png"));
        imgwRecherche.setFitHeight(20);
        imgwRecherche.setFitWidth(20);
        Button recherche = new Button("",imgwRecherche);
        recherche.setStyle(btRechercheStyle);
        recherche.setSkin(new MyButtonSkin(recherche));

        return new VBox();


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
}