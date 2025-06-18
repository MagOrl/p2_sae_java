import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicBorders;

public class ControleurRecherche implements EventHandler<ActionEvent> {

    private MenuClient menu;

    private String theme;

    public ControleurRecherche(MenuClient menu, String theme){
        this.menu = menu;
        this.theme = theme;
    }

    @Override
    public void handle(ActionEvent event){
        BorderPane center = new BorderPane();

        Text title = new Text("Recherche selon le thême : "+this.theme);

        Integer idTheme = this.menu.themeID();
        VBox livresParMag = new VBox(10);
        Map<Integer,Magasin> magasins = this.menu.getClientBD().afficheMagasin();
        for (Integer idmag : magasins.keySet()){
            String magasin = "Magasin "+magasins.get(idmag).getNom();
            VBox boite = new VBox(10);
            List<List<Livre>> livresTheme = this.menu.getClientBD().rechercheTheme(idTheme,magasins.get(idmag));
            for (Livre livre : livresTheme.get(idmag)){
                Button select = new Button(livre.toString());
                select.setOnAction(new ControleurSelectLivre(this.menu,livre));
                boite.getChildren().add(select);
            }
            TitledPane livresMag = new TitledPane(magasin,boite);
            livresParMag.getChildren().add(livresMag);
        }
        
        

        
        center.setCenter(boite);
        center.setBottom(ajouter);
        center.setPadding(new Insets(20));
        BorderPane.setAlignment(center, Pos.CENTER);
        this.menu.setCenter(center);
    }

}