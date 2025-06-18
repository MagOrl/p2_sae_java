import java.util.List;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurTextDynamique implements EventHandler<ActionEvent> {
    /**
     * temps enregistré lors du dernier événement
     */
    private long tempsCourant;
    /**
     * temps écoulé depuis le début de la mesure
     */
    private long duree;
    /**
     * Vue du chronomètre
     */
    private TextRecomDynamique txt;

    private List<Livre> lesRecomm;

    private final int premierInd;

    private int indCour;

    /**
     * Constructeur du contrôleur du chronomètre
     * noter que le modèle du chronomètre est tellement simple
     * qu'il est inclus dans le contrôleur
     * 
     * @param chrono Vue du chronomètre
     */
    public ControleurTextDynamique(TextRecomDynamique txt, List<Livre> lesRecomm) {
        this.lesRecomm = lesRecomm;
        this.premierInd = (int) Math.round(Math.random() * this.lesRecomm.size());
        this.indCour = premierInd;
        this.txt = txt;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.txt.setRecom(lesRecomm.get(indCour));
        this.indCour += 1;
        this.indCour = this.indCour % lesRecomm.size();
    }
}