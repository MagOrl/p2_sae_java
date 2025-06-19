import java.lang.management.ManagementPermission;
import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;

public class MenuPanier extends BorderPane {

    private AppliLib appli;

    private Client client;

    private MenuClient menuCli;

    private VBox panier;

    private String methodCom;

    public MenuPanier(AppliLib appli, Client client, MenuClient menuCli) {
        this.menuCli = menuCli;
        this.appli = appli;
        this.client = client;
        this.setStyle(AppliLib.styleBanniere);
        this.setPadding(new Insets(20));
        this.panier = new VBox(10);
        this.methodCom = "";
        initCenter();
        this.setTop(top());
        this.setCenter(this.panier);
        this.setBottom(bottom());
    }

    public Text top() {
        Text titre = new Text("Panier du compte n°" + this.client.getNumCompte());
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        return titre;
    }

    public VBox bottom() {
        VBox boutons = new VBox(10);
        Button commander = new Button("Commmander le panier");
        ToggleGroup tg = new ToggleGroup();
        HBox rdBox = new HBox(10);
        RadioButton rbC = new RadioButton("Commander");
        RadioButton rbR = new RadioButton("Reserver");
        rbC.setToggleGroup(tg);
        rbR.setToggleGroup(tg);
        rbC.pressedProperty().addListener(new ControleurMethodAchat(this, "C"));
        rbR.pressedProperty().addListener(new ControleurMethodAchat(this, "M"));
        rdBox.getChildren().addAll(rbC, rbR);
        commander.setOnAction(new ControleurCommanderC(this.appli, this, this.client));
        commander.setStyle(AppliLib.styleBouton);
        commander.setMinHeight(40);
        commander.setMinWidth(90);
        commander.setSkin(new MyButtonSkin(commander));
        commander.visibleProperty().bind(rbC.selectedProperty().or(rbR.selectedProperty()));
        Button retour = new Button("Retour");
        retour.setOnAction(new ControleurRetour(this.appli, this.menuCli));
        retour.setStyle(AppliLib.styleBouton);
        retour.setMinHeight(40);
        retour.setMinWidth(90);
        retour.setSkin(new MyButtonSkin(retour));
        HBox hb = new HBox();
        hb.getChildren().addAll(commander, retour);
        boutons.getChildren().addAll(rdBox, hb);
        return boutons;

    }

    public void initCenter() {
        this.panier.getChildren().clear();
        for (int idmag : this.client.getPanier().keySet()) {
            String magasin = "";
            try {
                magasin = this.appli.getClientBD().trouveLibrairie(idmag).getNom();
            } catch (SQLException e) {
                this.appli.popUpPasDeMagasins();
            }
            VBox vb = new VBox(20);
            for (Livre livre : this.client.getPanier(idmag)) {
                Text liv = new Text(livre.toString());
                liv.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
                Button suppLiv = new Button("Supprimer ce livre");
                suppLiv.setStyle(AppliLib.styleBouton);
                suppLiv.setSkin(new MyButtonSkin(suppLiv));
                vb.getChildren().addAll(liv, suppLiv);
                suppLiv.setOnAction(new ControleurSuppDuPan(this.client, idmag, livre, this.panier, vb, this));
            }
            TitledPane livreMag = new TitledPane(magasin, vb);

            panier.getChildren().add(livreMag);
        }
    }

    public void clearPan() {
        this.panier.getChildren().clear();
    }

    public void setMethodComm(String method) {
        this.methodCom = method;
    }

    public String getMethodComm() {
        return this.methodCom;
    }
}