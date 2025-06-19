import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurPageSuivanteClie implements EventHandler<ActionEvent> {
    private MenuClient vue;

    public ControleurPageSuivanteClie(MenuClient vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            GridPaneResultatRech gpStocks = this.vue.getGpLivRech();
            if (gpStocks != null && gpStocks.getIndex() + 1 < gpStocks.getStock().size()) {
                gpStocks.setIndex(gpStocks.getIndex() + 1);
                gpStocks.majGPStocks();
                this.vue.majVBoxCenter(gpStocks);
            }
        } catch (NullPointerException e) {
            System.out.println("bemol");
        }
    }

}
