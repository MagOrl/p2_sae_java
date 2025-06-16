
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
        Circle shapOfButt = new Circle(5);
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.connexion, this.creeCompte);
        this.btnQuitte.setShape(shapOfButt);
        bt.setMinSize(35 * 1.5, 20 * 1.5);
        bt.setMaxSize(35 * 1.5, 20 * 1.5);

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
