import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurSelectLivre implements EventHandler<ActionEvent> {

    private MenuClient menu;

    private Livre livre;

    public ControleurSelectLivre(MenuClient menu, Livre livre){
        this.menu = menu;
        this.livre = livre;
    }


    public void handle(ActionEvent event){
        this.menu.setLivreSelect(this.livre);
    }
}