import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.SQLException;

public class ControleurRechercheC implements EventHandler<ActionEvent> {

    private MenuClient menu;

    private String theme;

    public ControleurRechercheC(MenuClient menu){
        this.menu = menu;
        this.theme = this.menu.getTheme();
    }

    @Override
    public void handle(ActionEvent event){
        BorderPane center = new BorderPane();

        Text title = new Text("Recherche selon le thême : "+this.theme);

        Integer idTheme = this.menu.themeID();
        VBox livresParMag = new VBox(10);
        try {
            Map<Integer,Magasin> magasins = this.menu.getClientBD().afficheMagasin();
            for (Integer idmag : magasins.keySet()){
                String magasin = "Magasin "+magasins.get(idmag).getNom();
                HBox grandeBoite = new HBox(10);
                VBox boite = new VBox(10);
                int cpt = 0;
                try {
                    List<List<Livre>> livresTheme = this.menu.getClientBD().rechercheTheme(idTheme,magasins.get(idmag)); 
                    
                    List<Livre> tousLesLivres = new ArrayList<>();
                    for (List<Livre> liste : livresTheme){
                        tousLesLivres.addAll(liste);
                    }

                    for (Livre livre : tousLesLivres){
                        Button select = new Button(livre.toString());
                        select.setOnAction(new ControleurSelectLivre(this.menu,livre));
                        if (cpt == 10){
                            grandeBoite.getChildren().add(boite);
                            cpt=0;
                            boite = new VBox(10);
                        }
                        boite.getChildren().add(select);
                        cpt++;
                    }
                } catch (SQLException e) {
                    this.menu.getAppli().popUpPasDeThemes();
                }
                TitledPane livresMag = new TitledPane(magasin,grandeBoite);
                livresMag.setExpanded(false);
                livresParMag.getChildren().add(livresMag);
            }
        } catch (SQLException e) {
            this.menu.getAppli().popUpPasDeMagasins();
        }
        
        VBox bottom = new VBox(10);
        Button ajouter = new Button("Ajouter au panier");
        //ajouter.setOnAction(new ControleurAjouterPanier(this.clientBD));
        ajouter.setStyle(AppliLib.styleBouton);
        ajouter.setMinHeight(40);
        ajouter.setMinWidth(90);
        ajouter.setSkin(new MyButtonSkin(ajouter));
        bottom.getChildren().add(ajouter);
        bottom.setAlignment(Pos.CENTER);

        center.setTop(title);
        center.setCenter(livresParMag);
        center.setBottom(bottom);
        center.setPadding(new Insets(20));
        BorderPane.setAlignment(center, Pos.CENTER);
        this.menu.setCenter(center);
    }

}