import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurPagePrecedenteClie implements EventHandler<ActionEvent> {
    private MenuClient vue;

    public ControleurPagePrecedenteClie(MenuClient vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            GridPaneResultatRech gpStocks = this.vue.getGpLivRech();
            if (gpStocks != null && gpStocks.getIndex() - 1 > 0) {
                gpStocks.setIndex(gpStocks.getIndex() - 1);
                gpStocks.majGPStocks();
                this.vue.majVBoxCenter(gpStocks);
            }
        } catch (NullPointerException e) {
            System.out.println("bemol");
        }
    }

}
