import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FenetreMajQte extends VBox {

    private Button btnMaj;
    private Button btnRetour;
    private TextField isbn;
    private TextField qte;
    private VendeurBD modele;
    private AppliLib appli;

    public FenetreMajQte() {

        this.btnMaj = new Button("Mettre à jour");
        this.btnRetour = new Button("Retour");

        this.btnMaj.setStyle(AppliLib.styleBouton);
        this.btnRetour.setStyle(AppliLib.styleBouton);

        this.btnMaj.setSkin(new MyButtonSkin(btnMaj));
        this.btnRetour.setSkin(new MyButtonSkin(btnRetour));

        this.btnMaj.setOnAction(new ControleurMajQte(this,modele));
        this.btnRetour.setOnAction(new ControleurRetour(this, appli));

        this.getChildren().add(maj());
    }

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

    public String getIsbn() {
        return isbn.getText();
    }

    public String getQte() {
        return qte.getText();
    }

}