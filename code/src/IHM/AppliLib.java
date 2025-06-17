
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Circle;

public class AppliLib extends Application {

    private Scene scene;
    private Button btnQuitte;
    private Button creeCompte;
    private Button connexion;
    private MenuAcceuil menuAcc;
    public static String styleBouton = "-fx-background-color:rgb(120, 120, 120);" +
            "-fx-border-radius: 50; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    public static String styleBanniere = "-fx-border-radius: 15;" +
            "-fx-border-color:rgb(0, 0, 0);" +
            "-fx-border-width: 3;" + "-fx-background-color: #0b7f94;" + "-fx-border-insets: -2.5px;";
    public static String styleDefaultContainer = "-fx-background-color: #5d9b7d;" +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20;" +
            "-fx-border-color:rgb(32, 33, 32);" +
            "-fx-border-width: 2;" +
            "-fx-text-fill: white;";
    public static String styleTextField = "    -fx-background-color: #dbb1b1, #fff0f0;\n" +
            "    -fx-background-radius: 15;\n" +
            "    -fx-border-radius: 15;\n" +
            "    -fx-border-color: black;";

    @Override
    public void init() {
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.btnQuitte.setStyle(styleBouton);
        this.connexion.setStyle(styleBouton);
        this.creeCompte.setStyle(styleBouton);
        this.connexion.setMinHeight(40);
        this.connexion.setMinWidth(90);
        this.btnQuitte.setMinHeight(40);
        this.btnQuitte.setMinWidth(90);
        this.creeCompte.setMinHeight(40);
        this.creeCompte.setMinWidth(90);
        this.btnQuitte.setOnAction(new ControlleurQuitter(this));
        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.creeCompte, this.connexion);

    }

    @Override
    public void start(Stage stg) {
        this.scene = new Scene(this.menuAcc);
        stg.setScene(this.scene);
        stg.setTitle("Menu principale");
        stg.show();
    }

    public void afficheMenuAcceuil() {
        this.scene.setRoot(this.menuAcc);
    }

    public void quitte() {
        Platform.exit();
    }

    public Alert popUpQuitte() {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Êtes vous sûr de quitter ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        return alert;
    }
}
