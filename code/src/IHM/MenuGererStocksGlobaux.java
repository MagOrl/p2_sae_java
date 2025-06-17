

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
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

        TextField isbn = new TextField();
        TextField titre = new TextField();
        TextField datePubli = new TextField();
        TextField nbPages = new TextField();
        TextField qte = new TextField();
        TextField prix = new TextField();

        Text isbnText = new Text();
        Text titreText = new Text();
        Text datePubliText = new Text();
        Text qteText = new Text();
        Text prixText = new Text();

        GridPane gpTop = new GridPane();
        gpTop.add(isbnText, 0,0);
        gpTop.add(titreText, 1,0);
        gpTop.add(datePubliText, 2,0);
        gpTop.add(qteText, 3,0);
        gpTop.add(prixText, 4,0);

        gpTop.add(isbn, 1,1);
        gpTop.add(titre, 2,1);
        gpTop.add(datePubli, 3,1);
        gpTop.add(nbPages, 4,1);
        gpTop.add(qte, 5,1);
        gpTop.add(prix, 6,1);

        vbLeft.getChildren().addAll(gpTop);
        vbLeft.setStyle(AppliLib.styleDefaultContainer);

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

        bandeau.setLeft(vbLeft);
        bandeau.setRight(btDeconexion);
        
        bandeau.setStyle(AppliLib.styleBanniere);
        bandeau.setPadding(new Insets(20));

        return bandeau;
    }

    public VBox center(){

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