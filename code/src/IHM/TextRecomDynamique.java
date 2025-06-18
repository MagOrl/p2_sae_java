
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

public class TextRecomDynamique extends Text {
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

    

    /**
     * Constructeur permettant de créer le chronomètre
     * avec un label initialisé à "0:0:0"
     * Ce constructeur créer la Timeline, la KeyFrame et le contrôleur
     */
    public TextRecomDynamique(List<Livre> lesRecomm) {
        this.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        this.actionTemps = new ControleurTextDynamique(this,lesRecomm);
        this.keyFrame = new KeyFrame(Duration.seconds(10), this.actionTemps);
        this.timeline = new Timeline(this.keyFrame);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.start();
    }


    public void setRecom(Livre liv) {
        this.setText("    "+liv.getTitre()+"\n"+ liv.getDatePubli()+"                     "+liv.getPrix());
    }


    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

}
