
import java.util.List;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RecomDynamique extends VBox {
    /**
     * timeline qui va gérer le temps
     */
    private Timeline timeline;
    /**
     * la fenêtre de temps
     */
    private KeyFrame keyFrame;
    /**
     * le contrôleur associé au chronomètre
     */
    private ControleurTextDynamique actionTemps;

    private Livre livre;

    /**
     * Constructeur permettant de créer le chronomètre
     * avec un label initialisé à "0:0:0"
     * Ce constructeur créer la Timeline, la KeyFrame et le contrôleur
     */
    public RecomDynamique(List<Livre> lesRecomm) {
        super();
        this.actionTemps = new ControleurTextDynamique(this,lesRecomm);
        this.keyFrame = new KeyFrame(Duration.seconds(10), this.actionTemps);
        this.timeline = new Timeline(this.keyFrame);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.start();
    }


    public void setRecom(Livre liv) {
        this.livre = liv;
        BorderPane dateEtPrix = new BorderPane();
        Text date = new Text(liv.getDatePubli());
        date.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        Text prix = new Text(liv.getPrix()+" €");
        prix.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        dateEtPrix.setLeft(date);
        dateEtPrix.setRight(prix);
        Text titre = new Text(liv.getTitre());
        titre.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        this.getChildren().clear();
        this.getChildren().addAll(titre, dateEtPrix);
    }

    public Livre getLivre(){
        return this.livre;
    }


    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

}
