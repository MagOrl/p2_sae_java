import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuInfosPersos extends BorderPane {

    private AppliLib appli;

    private Client client;

    private MenuClient menuCli;

    public MenuInfosPersos(AppliLib appli, Personne client, MenuClient menucli) {
        this.menuCli = menucli;
        this.appli = appli;
        this.client = (Client) client;

        this.setStyle(AppliLib.styleBanniere);
        this.setPadding(new Insets(20));

        Text titre = new Text("Informations du compte n°" + this.client.getNumCompte());
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        VBox center = new VBox(10);
        Text identifiant = new Text("Identifiant : " + this.client.getIdentifiant());
        identifiant.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text nom = new Text("Nom : " + this.client.getNom());
        nom.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text prenom = new Text("Prénom : " + this.client.getPrenom());
        prenom.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text email = new Text("Email : " + this.client.getEmail());
        email.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text adresse = new Text("Adresse : " + this.client.getAdresse());
        adresse.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text ville = new Text("Ville : " + this.client.getVille());
        ville.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text codePostal = new Text("Code Postal : " + this.client.getCodePostal());
        codePostal.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Text tel = new Text("Téléphone : " + this.client.getTel());
        tel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        center.getChildren().addAll(identifiant, nom, prenom, email, adresse, ville, codePostal, tel);

        Button retour = new Button("Retour");
        retour.setOnAction(new ControleurRetour(this.appli, this.menuCli));
        retour.setStyle(AppliLib.styleBouton);
        retour.setMinHeight(40);
        retour.setMinWidth(90);
        retour.setSkin(new MyButtonSkin(retour));

        this.setTop(titre);
        this.setCenter(center);
        this.setBottom(retour);
    }

}