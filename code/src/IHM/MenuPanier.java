import java.lang.management.ManagementPermission;
import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;

public class MenuPanier extends BorderPane {

    private AppliLib appli;

    private Client client;

    public MenuPanier(AppliLib appli, Personne client) {
        super();

        this.appli = appli;
        this.client = (Client) client;

        this.setStyle(AppliLib.styleBanniere);
        this.setPadding(new Insets(20));

        Text titre = new Text("Panier du compte n°" + this.client.getNumCompte());
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        VBox panier = new VBox(10);
        for (int idmag : this.client.getPanier().keySet()) {
            String magasin = "";
            try {
                magasin = this.appli.getClientBD().trouveLibrairie(idmag).getNom();
            } catch (SQLException e) {
                this.appli.popUpPasDeMagasins();
            }
            VBox livres = new VBox(10);
            for (Livre livre : this.client.getPanier(idmag)) {
                Text liv = new Text(livre.toString());
                liv.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
                Button suppLiv = new Button("-");
                suppLiv.setStyle(AppliLib.styleBouton);
                suppLiv.setSkin(new MyButtonSkin(suppLiv));
                HBox hb = new HBox(20);
                livres.getChildren().add(hb);
                suppLiv.setOnAction(new ControleurSuppDuPan(this.client, idmag, livre));
                hb.getChildren().addAll(liv, suppLiv);

            }
            TitledPane livreMag = new TitledPane(magasin, livres);
            panier.getChildren().add(livreMag);
        }

        HBox boutons = new HBox(10);
        Button commander = new Button("Commmander le panier");
        // commander.setOnAction(new ControleurCommanderC(this.appli));
        commander.setStyle(AppliLib.styleBouton);
        commander.setMinHeight(40);
        commander.setMinWidth(90);
        commander.setSkin(new MyButtonSkin(commander));
        Button retour = new Button("Retour");
        retour.setOnAction(new ControleurRetour(this.appli));
        retour.setStyle(AppliLib.styleBouton);
        retour.setMinHeight(40);
        retour.setMinWidth(90);
        retour.setSkin(new MyButtonSkin(retour));
        boutons.getChildren().addAll(commander, retour);

        this.setTop(titre);
        this.setCenter(panier);
        this.setBottom(boutons);
    }

}