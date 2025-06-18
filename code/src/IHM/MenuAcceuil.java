
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuAcceuil extends BorderPane {

    private Button btnQuitte;
    private Button creeCompte;
    private Button connexion;
    private TextField tfID;
    private PasswordField pf;
    private ComboBox<String> cb;
    private ComboBox<String> nomMag;
    private Button changeInfoBD;
    public MenuAcceuil(Button btnQuitte, Button creeCompte, Button connexion, ComboBox<String> nomMag,Button changeInfoBD) {
        this.tfID = new TextField();
        this.pf = new PasswordField();
        this.cb = new ComboBox<>();
        this.nomMag = nomMag;
        this.changeInfoBD = changeInfoBD;
        this.cb.getItems().addAll("Client", "Vendeur", "Administrateur");
        this.tfID.setStyle(AppliLib.styleTextField);
        this.pf.setStyle(AppliLib.styleTextField);
        this.cb.setStyle(AppliLib.styleTextField);
        this.nomMag.setStyle(AppliLib.styleTextField);
        this.cb.setValue("");
        this.btnQuitte = btnQuitte;
        this.connexion = connexion;
        this.creeCompte = creeCompte;
        this.setTop(top());
        this.setCenter(left());
        
    }

    public BorderPane top() {
        BorderPane bp = new BorderPane();
        Text txt = new Text("Livre Express");
        txt.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        ImageView logo = new ImageView("../img/logo_placeholder.png");
        logo.setFitHeight(48);
        logo.setFitWidth(48);
        HBox hbleft = new HBox(10);
        HBox hbright = new HBox(10);
        hbleft.getChildren().addAll(txt, logo);
        hbright.getChildren().addAll(this.changeInfoBD,this.creeCompte, this.btnQuitte);
        bp.setLeft(hbleft);
        bp.setRight(hbright);
        bp.setStyle(AppliLib.styleBanniere);
        bp.setPadding(new Insets(20));
        return bp;
    }

    public HBox left() {
        HBox hb = new HBox(100);
        GridPane gp = new GridPane();
        Text txt = new Text("Se connecter"); // c r c r
        txt.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Text txtLab1 = new Text("Identifiant : ");
        Text txtLab2 = new Text("Mot de passe : ");
        Text txtLab3 = new Text("Compte : ");

        txtLab1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txtLab2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txtLab3.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        gp.add(txtLab1, 0, 1);
        gp.add(txtLab2, 0, 2);
        gp.add(txtLab3, 0, 3);
        gp.add(txt, 1, 0, 6, 1);
        gp.add(this.tfID, 1, 1);
        gp.add(this.pf, 1, 2);
        gp.add(this.cb, 1, 3);
        gp.add(this.connexion, 1, 5, 6, 1);
        gp.setStyle(AppliLib.styleDefaultContainer);
        gp.setPadding(new Insets(20));
        gp.setVgap(20.0);
        gp.setHgap(50.0);

        this.cb.valueProperty().addListener(new ControleurUtilisateur(gp, nomMag));

        ImageView logo = new ImageView("../img/PileOfBooks.png");
        logo.setFitHeight(400);
        logo.setFitWidth(200);
        hb.getChildren().addAll(gp, logo);
        hb.setPadding(new Insets(10));
        hb.setAlignment(Pos.CENTER_LEFT);
        return hb;
    }

    public String getValComboBox() {
        return this.cb.getValue();
    }

    public String getValTfID() {
        return this.tfID.getText();
    }

    public String getValPf() {
        return this.pf.getText();
    }

    public void resetFields() {
        this.tfID.setText("");
        this.pf.setText("");
    }

    public void setError() {
        this.tfID.setStyle("-fx-border-color: red;");
        this.pf.setStyle("-fx-border-color: red;");
    }

}
