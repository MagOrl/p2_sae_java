
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    public MenuAcceuil(Button btnQuitte, Button creeCompte, Button connexion) {
        this.tfID = new TextField();
        this.pf = new PasswordField();
        this.cb = new ComboBox<>();
        this.btnQuitte = btnQuitte;
        this.connexion = connexion;
        this.creeCompte = creeCompte;
        ImageView logo = new ImageView("../img/PileOfBooks.png");
        logo.setFitHeight(400);
        logo.setFitWidth(200);
        this.setTop(top());
        this.setLeft(left());
        this.setRight(logo);

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
        hbright.getChildren().addAll(this.creeCompte, this.btnQuitte);
        bp.setLeft(hbleft);
        bp.setRight(hbright);
        bp.setStyle(AppliLib.styleBanniere);
        bp.setPadding(new Insets(20));
        return bp;
    }

    public GridPane left() {
        GridPane gp = new GridPane();
        Text txt = new Text("Se connecter"); // c r c r
        gp.add(new Label("Identifiant : "), 0, 1);
        gp.add(new Label("Mot de passe : "), 0, 2);
        gp.add(new Label("Compte : "), 0, 3);
        gp.add(txt, 1, 0, 6, 1);
        gp.add(this.tfID, 1, 1);
        gp.add(this.pf, 1, 2);
        gp.add(this.cb, 1, 3);
        gp.add(this.connexion, 1, 4, 6, 1);
        gp.setStyle(AppliLib.styleDefaultContainer);
        gp.setPadding(new Insets(20));
        gp.setVgap(20.0);
        gp.setHgap(50.0);

        return gp;

    }

}
