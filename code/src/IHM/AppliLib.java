
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class AppliLib extends Application {

    private Scene scene;
    private Button btnQuitte;
    private Button creeCompte;
    private Button connexion;
    private MenuAcceuil menuAcc;

    @Override
    public void init() {
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.connexion, this.creeCompte);
        this.btnQuitte.setStyle("-fx-background-color: #4CAF50;" +
                "-fx-border-radius: 50; " +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #388E3C;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: white;");
    }

    @Override
    public void start(Stage stg) {
        this.scene = new Scene(this.menuAcc);
        stg.setScene(this.scene);
        stg.setTitle("Menu principale");
        stg.show();
    }

    public void afficheMenuAcceuil() {
        this.scene.setRoot(new MenuAcceuil(this.btnQuitte, this.connexion, this.creeCompte));
    }

}
