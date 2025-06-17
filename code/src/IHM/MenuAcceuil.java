
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuAcceuil extends BorderPane {

    private Button btnQuitte;
    private Button creeCompte;
    private Button connexion;

    public MenuAcceuil(Button btnQuitte, Button creeCompte, Button connexion) {
        this.btnQuitte = btnQuitte;
        this.connexion = connexion;
        this.creeCompte = creeCompte;
        this.setTop(top());
        this.setLeft(left());
        this.setRight(right());
    }

    public BorderPane top() {
        BorderPane bp = new BorderPane();
        // bp.setFillHeight(false);

        Text txt = new Text("Livre Expresss");
        txt.setFont(Font.font("Arial", 30));
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

    public Pane center() {
        return new Pane();
    }

    public Pane left() {
        return new Pane();
    }

    public Pane right() {
        return new Pane();
    }

}
