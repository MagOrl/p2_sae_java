import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuChangeInfoBD extends GridPane {
    private List<String> infoBD;
    private TextField tf1;
    private TextField tf2;
    private TextField tf3;
    private TextField tf4;
    private Button btnQuitte;
    private Button confModif;

    public MenuChangeInfoBD(Button btnQuitte) {
        this.btnQuitte = btnQuitte;
        infoBD = new ArrayList<>();
        File cache = new File(".cachePourBaseDeDonne");
        try {
            Scanner reader = new Scanner(cache);
            while (reader.hasNextLine()) {
                infoBD.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.confModif = new Button("Confirmer");
        this.confModif.setStyle(AppliLib.styleBouton);
        this.confModif.setSkin(new MyButtonSkin(this.confModif));
        this.confModif.setMinHeight(40);
        this.confModif.setMinWidth(90);

        this.tf1 = new TextField();
        this.tf2 = new TextField();
        this.tf3 = new TextField();
        this.tf4 = new TextField();
        this.tf1.setPromptText("1");
        this.tf2.setPromptText("2");
        this.tf3.setPromptText("3");
        this.tf4.setPromptText("4");
        tf1.setText(infoBD.get(0));
        tf2.setText(infoBD.get(1));
        tf3.setText(infoBD.get(2));
        tf4.setText(infoBD.get(3));
        this.confModif.setOnAction(new ControleurConfModif(infoBD));
        this.root();

    }

    public void root() {
        Text txtLab1 = new Text("Serveur SQL : ");
        Text txtLab2 = new Text("Base de donnée : ");
        Text txtLab3 = new Text("Nom utilisateur : ");
        Text txtLab4 = new Text("Mot de passe : ");

        txtLab1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txtLab2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txtLab3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txtLab4.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        this.add(txtLab1, 0, 0);
        this.add(txtLab2, 0, 1);
        this.add(txtLab3, 0, 2);
        this.add(txtLab4, 0, 3);

        this.add(tf1, 1, 0);
        this.add(tf2, 1, 1);
        this.add(tf3, 1, 2);
        this.add(tf4, 1, 3);

        this.add(this.btnQuitte, 0, 4, 2, 1);
        this.add(this.confModif, 1, 4, 2, 1);

        this.setStyle(AppliLib.styleDefaultContainer);
        this.setPadding(new Insets(30));
        this.setVgap(20.0);
        this.setHgap(50.0);
        this.tf1.textProperty().addListener(new ControleurInfoBDTxtField(this.infoBD, this.tf1.getPromptText()));
        this.tf2.textProperty().addListener(new ControleurInfoBDTxtField(this.infoBD, this.tf2.getPromptText()));
        this.tf3.textProperty().addListener(new ControleurInfoBDTxtField(this.infoBD, this.tf3.getPromptText()));
        this.tf4.textProperty().addListener(new ControleurInfoBDTxtField(this.infoBD, this.tf4.getPromptText()));

    }

}