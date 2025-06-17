
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

    //---------------------
    private MenuGererStocksGlobaux menuGSG;
    //---------------------
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
    public static String styleTextField = "-fx-background-color: #dbb1b1, #fff0f0;\n" +
            "    -fx-background-radius: 15;\n" +
            "    -fx-border-radius: 15;\n" +
            "    -fx-border-color: black;";


    @Override
    public void init() {
        this.btnQuitte = new Button("Quitter");
        this.connexion = new Button("Connexion");
        this.creeCompte = new Button("Créer compte");
        this.menuAcc = new MenuAcceuil(this.btnQuitte, this.connexion, this.creeCompte);
        //------------------------------------------
        this.menuGSG = new MenuGererStocksGlobaux(this.btnQuitte);
        //-------------------------------------------
        this.btnQuitte.setStyle(styleBouton);
        this.connexion.setStyle(styleBouton);
        this.creeCompte.setStyle(styleBouton);
        this.connexion.setMinHeight(40);        
        this.connexion.setMinWidth(90);

        this.btnQuitte.setSkin(new MyButtonSkin(this.btnQuitte));

        this.btnQuitte.setMinHeight(40);
        this.btnQuitte.setMinWidth(90);
    }

    @Override
    public void start(Stage stg) {
        this.scene = new Scene(this.menuGSG);
        stg.setScene(this.scene);
        stg.setTitle("Menu principal");
        stg.show();
    }

    public void afficheMenuAcceuil() {
        this.scene.setRoot(new MenuAcceuil(this.btnQuitte, this.connexion, this.creeCompte));
    }

    public void afficheMenuGererStocksGlobaux(){
        this.scene.setRoot(this.menuGSG);
    }

}
