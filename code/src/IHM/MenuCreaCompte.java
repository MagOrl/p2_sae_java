
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ParallelCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MenuCreaCompte extends BorderPane {
    private Button quitteCrea;
    private Button confirmCrea;

    private TextField tfId;
    private PasswordField pf;
    private PasswordField pfConfirm;
    private TextField tfMail;
    private TextField tfNom;
    private TextField tfPrenom;
    private TextField tfAddress;
    private TextField tfVille;
    private TextField tfCodePostal;
    private TextField tfTel;

    public MenuCreaCompte(Button quitteCrea, Button confirmCrea) {
        this.quitteCrea = quitteCrea;
        this.confirmCrea = confirmCrea;
        this.tfId = new TextField();
        this.pf = new PasswordField();
        this.pfConfirm = new PasswordField();
        this.tfMail = new TextField();
        this.tfNom = new TextField();
        this.tfPrenom = new TextField();
        this.tfAddress = new TextField();
        this.tfVille = new TextField();
        this.tfCodePostal = new TextField();
        this.tfTel = new TextField();

        this.tfId.setStyle(AppliLib.styleTextField);
        this.pf.setStyle(AppliLib.styleTextField);
        this.pfConfirm.setStyle(AppliLib.styleTextField);
        this.tfMail.setStyle(AppliLib.styleTextField);
        this.tfNom.setStyle(AppliLib.styleTextField);
        this.tfPrenom.setStyle(AppliLib.styleTextField);
        this.tfAddress.setStyle(AppliLib.styleTextField);
        this.tfVille.setStyle(AppliLib.styleTextField);
        this.tfCodePostal.setStyle(AppliLib.styleTextField);
        this.tfTel.setStyle(AppliLib.styleTextField);

        this.setTop(top());
        this.setBottom(bottom());
        this.setCenter(center());

    }

    public HBox top() {
        HBox hb = new HBox();
        Text txt = new Text("Création compte");
        txt.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        hb.getChildren().add(txt);
        hb.setAlignment(Pos.BASELINE_CENTER);
        hb.setStyle(AppliLib.styleBanniere);
        hb.setPadding(new Insets(30));
        return hb;
    }

    public GridPane center() {
        GridPane gp = new GridPane();
        Text txt1 = new Text("Identifiant");
        Text txt2 = new Text("Mot de passe");
        Text txt3 = new Text("Confirmation\nmot de passe");
        Text txt4 = new Text("Email");
        Text txt5 = new Text("Nom");
        Text txt6 = new Text("Prénom");
        Text txt7 = new Text("Adresse");
        Text txt8 = new Text("Ville");
        Text txt9 = new Text("Code Postal");
        Text txt10 = new Text("Téléphoe");

        txt1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt5.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt7.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt8.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt9.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        txt10.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        gp.add(txt1, 0, 0);
        gp.add(txt2, 0, 1);
        gp.add(txt3, 0, 2);
        gp.add(txt4, 0, 3);
        gp.add(txt5, 0, 4);
        gp.add(txt6, 0, 5);
        gp.add(txt7, 0, 6);
        gp.add(txt8, 0, 7);
        gp.add(txt9, 0, 8);
        gp.add(txt10, 0, 9);

        gp.add(this.tfId, 1, 0);
        gp.add(this.pf, 1, 1);
        gp.add(this.pfConfirm, 1, 2);
        gp.add(this.tfMail, 1, 3);
        gp.add(this.tfNom, 1, 4);
        gp.add(this.tfPrenom, 1, 5);
        gp.add(this.tfAddress, 1, 6);
        gp.add(this.tfVille, 1, 7);
        gp.add(this.tfCodePostal, 1, 8);
        gp.add(this.tfTel, 1, 9);

        gp.setStyle(AppliLib.styleDefaultContainer);
        gp.setPadding(new Insets(30));
        gp.setVgap(20.0);
        gp.setHgap(50.0);
        
        return gp;
    }

    public HBox bottom() {
        HBox hb = new HBox(40);
        hb.getChildren().addAll(this.quitteCrea, this.confirmCrea);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(30));
        return hb;
    }
}