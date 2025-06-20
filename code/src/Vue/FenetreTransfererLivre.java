import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FenetreTransfererLivre extends VBox {
    /**
     * le bouton de transfert
     */
    private Button btnTransf;
    /**
     * bouton pour retourner au menu vendeur
     */
    private Button btnRetour;
    /**
     * le textfield de l'isbn
     */
    private TextField isbn;
    /**
     * le textefield de la quantité
     */
    private TextField qte;
    /**
     * l'application
     */
    private AppliLib appli;
    /**
     * le modèle pour les requètes vendeur
     */
    private VendeurBD vend;
    /**
     * Le menu vendeur
     */
    private MenuVendeur menu;

    /**
     * Constructeur qui initialise les attributs et initalise la fenetre
     * 
     * @param appli
     * @param menuV
     * @param vend
     */
    public FenetreTransfererLivre(AppliLib appli, MenuVendeur menuV, VendeurBD vend) {
        this.vend = vend;
        this.btnTransf = new Button("Transférer");
        this.btnRetour = new Button("Retour");
        this.menu = menuV;
        this.appli = appli;

        this.btnTransf.setStyle(AppliLib.styleBouton);
        this.btnRetour.setStyle(AppliLib.styleBouton);

        this.btnTransf.setSkin(new MyButtonSkin(this.btnTransf));
        this.btnRetour.setSkin(new MyButtonSkin(this.btnRetour));

        this.btnTransf.setOnAction(new ControleurTransferer(this.vend, this.menu, this.appli, this));
        this.btnRetour.setOnAction(new ControleurRetourVendeur(this.appli));

        this.getChildren().add(trans());
    }

    /**
     * la fenetre principale
     * 
     * @return
     */
    public VBox trans() {

        VBox vb = new VBox(15);

        HBox hb1 = new HBox(10);
        Text txt1 = new Text("ISBN: ");
        txt1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        isbn = new TextField();
        isbn.setFont(Font.font("Arial", 15));
        isbn.setStyle(AppliLib.styleTextField);

        hb1.getChildren().addAll(txt1, isbn);

        HBox hb2 = new HBox(10);
        Text txt2 = new Text("Quantité: ");
        txt2.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        qte = new TextField();
        qte.setFont(Font.font("Arial", 15));
        qte.setStyle(AppliLib.styleTextField);

        hb2.getChildren().addAll(txt2, qte);

        HBox hb3 = new HBox(10);
        hb3.getChildren().addAll(this.btnTransf, this.btnRetour);

        vb.getChildren().addAll(hb1, hb2, hb3);
        vb.setStyle(AppliLib.styleDefaultContainer);
        vb.setPadding(new Insets(20));

        return vb;
    }

    /**
     * donne l'isbn du textefield
     * 
     * @return
     */
    public String getIsbn() {
        return this.isbn.getText();
    }

    /**
     * donne la quantité du textfield
     * 
     * @return
     */
    public int getQte() {
        return Integer.parseInt(this.qte.getText());
    }

    /**
     * reset les valeur du textfield
     */
    public void resetTFTransferer() {
        this.isbn.setText("");
        this.qte.setText("");
    }
}