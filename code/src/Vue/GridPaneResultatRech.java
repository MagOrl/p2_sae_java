import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GridPaneResultatRech extends GridPane {
    /**
     * le modèle du client pour ses requètes associée
     */
    private ClientBD modele;
    /**
     * le stock de livre dans sont panier
     */
    private List<List<Livre>> stocks;
    /**
     * l'indexe de la page
     */
    private int index;
    /**
     * le menu du client
     */
    private MenuClient vue;
    /**
     * vue en dictionnaire du panier
     */
    private Map<Button, Livre> mapStocks;

    public GridPaneResultatRech() {
    }

    /**
     * Construteur qui initalise les attribut et crée la première page de livre
     * 
     * @param stocks
     * @param index
     * @param vue
     * @param modele
     */
    public GridPaneResultatRech(List<List<Livre>> stocks, int index, MenuClient vue, ClientBD modele) {
        this.setHgap(5.0);
        this.setVgap(7.0);
        this.stocks = stocks;
        this.index = index;
        this.vue = vue;
        this.modele = modele;
        this.mapStocks = new HashMap<>();
        int cptGp = 0;
        this.setPadding(new Insets(30));

        creeMapStocks();

        if (this.stocks.size() == 0) {
            Text text = new Text("Aucun Livre n'a été trouvé");
            text.setFont(Font.font("Arial", 17));
            this.add(text, 5, 5);
        } else {
            for (Livre livre : this.stocks.get(this.index)) {
                for (Button btSruppr : this.mapStocks.keySet()) {
                    if (this.mapStocks.get(btSruppr).equals(livre)) {
                        Text text = new Text(livre.toString());
                        text.setFont(Font.font("Arial", 17));
                        this.add(text, 0, cptGp);
                        this.add(btSruppr, 1, cptGp);
                        cptGp++;
                    }
                }
            }
        }
    }

    /**
     * met à jour le gridpane
     */
    public void majGPStocks() {
        this.getChildren().clear();
        int cptGp = 0;
        for (Livre livre : this.stocks.get(this.index)) {
            for (Button btSruppr : this.mapStocks.keySet()) {
                if (this.mapStocks.get(btSruppr).equals(livre)) {
                    Text text = new Text(livre.toString());
                    text.setFont(Font.font("Arial", 17));
                    this.add(text, 0, cptGp);
                    this.add(btSruppr, 1, cptGp);
                    cptGp++;
                }
            }
        }
    }

    /**
     * créée une page
     */
    public void creeMapStocks() {
        for (List<Livre> page : stocks) {
            for (Livre livre : page) {
                Button livreBT = new Button("+");
                livreBT.setOnAction(new ControleurAjouterPanier(vue, livre));
                livreBT.setStyle(AppliLib.styleBouton);
                livreBT.setSkin(new MyButtonSkin(livreBT));
                this.mapStocks.put(livreBT, livre);
            }
        }
    }

    /**
     * retourne l'indexe de la page
     * 
     * @return
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * met un index à la page
     * 
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * donne le panier
     * 
     * @return
     */
    public List<List<Livre>> getStock() {
        return this.stocks;
    }

}
