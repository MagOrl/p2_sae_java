import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControleurAjouterVendeur implements EventHandler<ActionEvent> {

    private MenuAdmin vue;
    private AdministrateurBD adminBD;
    private TextField tfNomMag;
    private TextField tfNom;
    private TextField tfVille;
    private PasswordField pf;
    private PasswordField pfConf;
    private TextField tfMail;
    private TextField tfPrenom;
    private TextField tfAdresse;
    private TextField tfCodePostal;
    private TextField tfTel;
    private TextField tfId;

    public ControleurAjouterVendeur(MenuAdmin vue,AdministrateurBD adminBD, TextField tfNomMag, PasswordField pf, PasswordField pfConf,
                TextField tfMail, TextField tfId, TextField tfNom, TextField tfPrenom, TextField tfAdresse, TextField tfVille,
                TextField tfCodePostal, TextField tfTel){
        this.vue = vue;
        this.adminBD = adminBD;
        this.tfId = tfId;
        this.tfNom = tfNom;
        this.tfVille = tfVille;
        this.tfNomMag = tfNomMag;
        this.pf = pf;
        this.pfConf = pfConf;
        this.tfMail = tfMail;
        this.tfPrenom = tfPrenom;
        this.tfAdresse = tfAdresse;
        this.tfCodePostal = tfCodePostal;
        this.tfTel = tfTel;
    }

    public void handle(ActionEvent event){
        String nom = this.tfNom.getText();
        String ville = this.tfVille.getText();
        String nomMag = this.tfNomMag.getText();
        String id = this.tfId.getText();
        String psw = this.pf.getText();
        String pswConf = this.pfConf.getText();
        String mail = this.tfMail.getText();
        String prenom = this.tfPrenom.getText();
        String adresse = this.tfAdresse.getText();
        String codePostal = this.tfCodePostal.getText();
        String tel = this.tfTel.getText();

        if (!this.vue.isPwdSame()) {
            this.vue.setPwdOnError(true);
            this.vue.popUpPasMemeMotDePasse().showAndWait();

        } else {
            this.vue.setPwdOnError(false);
            try {
                this.adminBD.CreerCompteVendeur(nom, prenom, id, adresse, tel, mail, psw, codePostal, ville, nomMag);
                this.vue.popUpCompteVendeurCree(id).showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}