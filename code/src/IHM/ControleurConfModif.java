import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurConfModif implements EventHandler<ActionEvent> {
    private List<String> valAcc;

    public ControleurConfModif( List<String> valAcc) {
        this.valAcc = valAcc;
    }

    public void write() {
        try {
            FileWriter fw = new FileWriter(".cachePourBaseDeDonne", false);
            for (String d : this.valAcc) {
                System.out.println("ICI : :" +d);
                fw.write(d + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent arg0) {
        write();
    }
}