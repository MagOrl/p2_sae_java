import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FenetreMajQte extends VBox {
    /**
     * Le bouton pour mettre à jour les données
     */
    private Button btnMaj;
    /**
     * Le bouton de retour
     */
    private Button btnRetour;
    /**
     * Le textfield ou l'ISBN est rentrer
     */
    private TextField isbn;
    /**
     * Le textfield ou la quantité est rentrer
     */
    private TextField qte;
    /**
     * le modèle pour les requètes vendeur
     */
    private VendeurBD modele;
    /**
     * la vue de l'application
     */
    private AppliLib appli;
    /**
     * le menu vendeur
     */
    private MenuVendeur menu;

    /**
     * Constructeur qui initialise les attributs
     * 
     * @param appli
     * @param modele
     * @param menu
     */
    public FenetreMajQte(AppliLib appli, VendeurBD modele, MenuVendeur menu) {

        this.appli = appli;
        this.modele = modele;
        this.menu = menu;

        this.btnMaj = new Button("Mettre à jour");
        this.btnRetour = new Button("Retour");

        this.btnMaj.setStyle(AppliLib.styleBouton);
        this.btnRetour.setStyle(AppliLib.styleBouton);

        this.btnMaj.setSkin(new MyButtonSkin(this.btnMaj));
        this.btnRetour.setSkin(new MyButtonSkin(this.btnRetour));

        this.btnMaj.setOnAction(new ControleurMaj(this.modele, this, this.appli, this.menu));
        this.btnRetour.setOnAction(new ControleurRetourVendeur(this.appli));

        this.getChildren().add(maj());
    }

    /**
     * la fenetre de mise à jour
     * 
     * @return
     */
    public VBox maj() {

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
        hb3.getChildren().addAll(this.btnMaj, this.btnRetour);

        vb.getChildren().addAll(hb1, hb2, hb3);
        vb.setStyle(AppliLib.styleDefaultContainer);
        vb.setPadding(new Insets(20));

        return vb;
    }

    /**
     * donne l'isbn du textfield
     * 
     * @return
     */
    public int getIsbn() {
        return Integer.parseInt(isbn.getText());
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
     * remet les textfield à vide
     */
    public void resetTFMaj() {
        this.isbn.setText("");
        this.qte.setText("");
    }

}