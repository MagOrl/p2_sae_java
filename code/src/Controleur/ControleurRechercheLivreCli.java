import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRechercheLivreCli implements EventHandler<ActionEvent> {
    private MenuClient vue;
    private ClientBD modele;

    public ControleurRechercheLivreCli(MenuClient menuClient, ClientBD modele) {
        this.vue = menuClient;
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent arg0) {
        this.vue.setNormal();
        if (this.vue.getValCrit() == null || this.vue.getLibActuelle() == null) {
            this.vue.setRed();
        } else {
            switch (this.vue.getValCrit()) {
                case "Titre":
                    try {
                        this.vue.setGpLivRech(new GridPaneResultatRech(
                                this.modele.rechercheCritere("titre", "", this.vue.getValRech(), "",
                                        this.modele.trouveLibrairie(this.vue.getLibActuelle())),
                                0, vue, modele));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    this.vue.majVBoxCenter(this.vue.getGpLivRech());
                    break;

                case "Auteur":
                    try {
                        this.vue.setGpLivRech(
                                new GridPaneResultatRech(
                                        this.modele.rechercheCritere("auteur", "", "", this.vue.getValRech(),
                                                this.modele.trouveLibrairie(this.vue.getLibActuelle())),
                                        0, vue, modele));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    this.vue.majVBoxCenter(this.vue.getGpLivRech());
                    break;

                case "ISBN":
                    try {
                        this.vue.setGpLivRech(
                                new GridPaneResultatRech(this.modele.rechercheCritere("isbn", this.vue.getValRech(), "",
                                        "", this.modele.trouveLibrairie(this.vue.getLibActuelle())), 0, vue, modele));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    this.vue.majVBoxCenter(this.vue.getGpLivRech());
                    break;

                default:
                    return;
            }
        }
        // } catch (NullPointerException e) {
        // this.vue.popPasDeLibrairie().show();
        // } catch (SQLException e) {
        // this.vue.popUpAjouterLivreSQLException().show();
        // }
    }
}
