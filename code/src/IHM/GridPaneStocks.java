import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GridPaneStocks extends GridPane{
    
    private AdministrateurBD modele;
    private List<List<Livre>> stocks;
    private int index;
    private MenuGererStocksGlobaux vue;
    private Map<Button, Livre> mapStocks;

    public GridPaneStocks(){

    }

    public GridPaneStocks(List<List<Livre>> stocks, int index, MenuGererStocksGlobaux vue, AdministrateurBD modele){
        super();
        this.setHgap(5.0);
        this.setVgap(7.0);
        this.stocks = stocks;
        this.index = index;
        this.vue = vue;
        this.modele = modele;
        this.mapStocks = new HashMap<>();
        int cptGp = 0;

        creeMapStocks();

        if(this.stocks.size() == 0){
            Text text = new Text("Aucun Livre n'a été trouvé");
            text.setFont(Font.font("Arial", 17));
            this.add(text,5,5);
        }else{
            for(Livre livre : this.stocks.get(this.index)){
                for(Button btSruppr : this.mapStocks.keySet()){
                    if(this.mapStocks.get(btSruppr).equals(livre)){
                        Text text = new Text(livre.toString());
                        text.setFont(Font.font("Arial", 17));
                        this.add(text, 0, cptGp);
                        this.add(btSruppr, 1, cptGp);
                        cptGp ++;
                    }
                }
            }
        }
    }

    public void majGPStocks(){
        this.getChildren().clear();
        int cptGp = 0;
        for(Livre livre : this.stocks.get(this.index)){
             for(Button btSruppr : this.mapStocks.keySet()){
                 if(this.mapStocks.get(btSruppr).equals(livre)){
                     Text text = new Text(livre.toStringStock());
                     text.setFont(Font.font("Arial", 17));
                     this.add(text, 0, cptGp);
                     this.add(btSruppr, 1, cptGp);
                     cptGp ++;
                    }
                }
            }  
    }


    public void creeMapStocks(){
        for(List<Livre> page : stocks){
            for(Livre livre : page){
                Button livreBT = new Button("Supprimer");
                livreBT.setOnAction(new ControleurSupprimerLivre(this.vue, this.modele, livreBT));
                livreBT.setStyle(AppliLib.styleBouton);
                livreBT.setSkin(new MyButtonSkin(livreBT));
                this.mapStocks.put(livreBT, livre);
            }
        }
        this.vue.setStocks(this.mapStocks);

    }

    public Livre getLivreSuppr(Button bt){
        return this.mapStocks.get(bt);
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public List<List<Livre>> getStock(){
        return this.stocks;
    }




}
