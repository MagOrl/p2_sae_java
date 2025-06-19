import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurPageSuivante implements EventHandler<ActionEvent>{
    private MenuGererStocksGlobaux vue;

    public ControleurPageSuivante(MenuGererStocksGlobaux vue){
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event){
        try{
            GridPaneStocks gpStocks = this.vue.getGpStocks();
            if (gpStocks != null && gpStocks.getIndex()+1 < gpStocks.getStock().size()){
                gpStocks.setIndex(gpStocks.getIndex()+1);
                gpStocks.majGPStocks();
                this.vue.majVBoxCenter(gpStocks);
            }
        }catch(NullPointerException e){
            this.vue.popPasDeLibrairie().show();
        }
    }


}
