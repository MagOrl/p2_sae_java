import java.util.List;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurTextDynamique implements EventHandler<ActionEvent> {
    
    private RecomDynamique txt;

    private List<Livre> lesRecomm;

    private final int premierInd;

    private int indCour;

    public ControleurTextDynamique(RecomDynamique txt, List<Livre> lesRecomm) {
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