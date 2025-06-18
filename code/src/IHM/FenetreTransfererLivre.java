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

    private Button btnTransf;
    private Button btnRetour;
    private TextField isbn;
    private TextField qte;
    //private AppliLib appli;

    public FenetreTransfererLivre() {

        this.btnTransf = new Button("Transférer");
        this.btnRetour = new Button("Retour");

        

        this.btnTransf.setStyle(AppliLib.styleBouton);
        this.btnRetour.setStyle(AppliLib.styleBouton);

        this.btnTransf.setSkin(new MyButtonSkin(this.btnTransf));
        this.btnRetour.setSkin(new MyButtonSkin(this.btnRetour));

        //this.btnTransf.setOnAction(new ControleurTransferer());
        //this.btnRetour.setOnAction(new ControleurRetour());

        this.getChildren().add(trans());
    }

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

    public String getIsbn() {
        return isbn.getText();
    }

    public String getQte() {
        return qte.getText();
    }

}