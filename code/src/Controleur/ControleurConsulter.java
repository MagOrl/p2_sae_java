import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleurConsulter implements EventHandler<ActionEvent>{
    
    private MenuClient menu;

    public ControleurConsulter(MenuClient menu) {
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent event) { 
        BorderPane center = new BorderPane();
        Livre livre = this.menu.getLivreDyna();

        VBox recommande = new VBox(10);
        
        Button leRecommande = new Button(livre.toString());
        leRecommande.setOnAction(new ControleurSelectLivre(this.menu,livre));
        leRecommande.setStyle(AppliLib.styleBouton);
        leRecommande.setMinHeight(40);
        leRecommande.setMinWidth(90);
        leRecommande.setSkin(new MyButtonSkin(leRecommande));
        recommande.getChildren().add(leRecommande);
        recommande.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(10);
        Button ajouter = new Button("Ajouter au panier");
        //ajouter.setOnAction(new ControleurAjouterPanier(this.clientBD));
        ajouter.setStyle(AppliLib.styleBouton);
        ajouter.setMinHeight(40);
        ajouter.setMinWidth(90);
        ajouter.setSkin(new MyButtonSkin(ajouter));
        bottom.getChildren().add(ajouter);
        bottom.setAlignment(Pos.CENTER);

        center.setCenter(recommande);
        center.setBottom(bottom);
        center.setPadding(new Insets(20));
        BorderPane.setAlignment(center, Pos.CENTER);
        this.menu.setCenter(center);
    }
}