
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    public HBox top() {
        HBox hb = new HBox(5);
        Text txt = new Text("Livre Expresss");
        txt.setFont(Font.font("Arial", 30));
        ImageView logo = new ImageView("../img/logo_placeholder.png");
        logo.setFitHeight(48);
        logo.setFitWidth(48);
        hb.getChildren().addAll(txt, logo, creeCompte, btnQuitte);
        return hb;
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
