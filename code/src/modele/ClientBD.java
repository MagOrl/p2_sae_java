
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientBD {

    private ConnexionMySQL laConnexion;
    private Statement st;

    public ClientBD(ConnexionMySQL laConnexion) throws SQLException{
        this.laConnexion = laConnexion;
        try {
            List<String> data = new ArrayList<>();
            // laConnexion.connecter("localhost", "Librairie", "root", "mypassword");
            // laConnexion.connecter("servinfo-maria", "DBarsamerzoev", "arsamerzoev",
            // "arsamerzoev");
            File cache = new File(".cachePourBaseDeDonne");
            Scanner reader = new Scanner(cache);
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
            laConnexion.connecter(data.get(0), data.get(1), data.get(2), data.get(3));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * permet de créée un compte client
     * 
     * @param identif    sont intiutle de compte
     * @param nom        sont nom
     * @param prenom     sont prénom
     * @param adresse
     * @param codepostal
     * @param ville
     * @param email
     * @param tel
     * @param mdp
     * @return l'id du client
     * @throws SQLException
     */
    public int creeClient(String identif, String nom, String prenom, String adresse, String codepostal, String ville,
            String email, String tel, String mdp) throws SQLException {
        int numCli = clientMax() + 1;
        this.st = this.laConnexion.createStatement();
        PreparedStatement ps = this.laConnexion.prepareStatement("insert into CLIENT values (?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, numCli);
        ps.setString(2, identif);
        ps.setString(3, nom);
        ps.setString(4, prenom);
        ps.setString(5, adresse);
        ps.setString(6, codepostal);
        ps.setString(7, ville);
        ps.setString(8, email);
        ps.setInt(9, Integer.parseInt(tel));
        ps.setString(10, mdp);
        ps.executeUpdate();
        return numCli;
    }

    /**
     * permet de trouver un client dans la base de données selon numcli
     * 
     * @param numcli
     * @return le client
     * @throws SQLException
     */
    public Client trouveClient(int numcli) throws SQLException {
        Client cli = new Client();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT * FROM CLIENT WHERE numcli =" + numcli);
        while (rs.next()) {
            cli = new Client(numcli, rs.getString("nomcli"), rs.getString("prenomcli"), rs.getString("identifiant"),
                    rs.getString("adressecli"), rs.getInt("tel"), rs.getString("email"), rs.getString("motdepasse"),
                    rs.getString("codepostal"), rs.getString("villecli"));
        }
        rs.close();
        return cli;
    }

    /**
     * permet de trouver un client selon sont indentifiant et sont mot de passe
     * 
     * @param identif
     * @param mdp
     * @return
     * @throws SQLException
     */
    public Client trouveClient(String identif, String mdp) throws SQLException {
        Client cli = new Client();
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery(
                "SELECT * FROM CLIENT WHERE identifiant ='" + identif + "'" + "and motdepasse ='" + mdp + "'");
        while (rs.next()) {
            cli = new Client(rs.getInt("idcli"), rs.getString("nomcli"), rs.getString("prenomcli"),
                    rs.getString("identifiant"),
                    rs.getString("adressecli"), rs.getInt("tel"), rs.getString("email"), rs.getString("motdepasse"),
                    rs.getString("codepostal"), rs.getString("villecli"));
        }
        rs.close();
        return cli;
    }

    /**
     * permet de verifier que le client c'est bien connecter
     * 
     * @param identif
     * @param mdp
     * @return
     * @throws SQLException
     */
    public boolean connectClient(String identif, String mdp) throws SQLException {
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st
                .executeQuery(
                        "SELECT * FROM CLIENT WHERE identifiant = '" + identif + "'and motdepasse ='" + mdp + "'");
        return rs.next();
    }

    /**
     * permet de savoir combien il y a de client dans la base de données
     * c'est pratique pour pouvoir attribuer un ID
     * 
     * @return nombre de client dans la base de donnée
     * @throws SQLException
     */
    public int clientMax() throws SQLException {
        int max = 0;
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select max(idcli) nbclimax from CLIENT");
        while (rs.next()) {
            max = rs.getInt("nbclimax");
        }
        rs.close();
        return max;
    }

    /**
     * met à jour les information du client
     * 
     * @param cli
     * @throws SQLException
     */
    public void majClient(Client cli) throws SQLException {
        PreparedStatement ps = this.laConnexion
                .prepareStatement("UPDATE CLIENT SET identifiant = ?, motdepasse= ?, email = ?,tel =? WHERE idcli = ?");
        ps.setString(1, cli.getIdentifiant());
        ps.setString(2, cli.getMdp());
        ps.setString(3, cli.getEmail());
        ps.setInt(4, cli.getTel());
        ps.setInt(5, cli.getNumCompte());
        ps.executeUpdate();
    }

    /**
     * pertmet d'afficher l'historique des commandes effectuée par la client
     * 
     * @param cli
     * @return l'historique des commandes ou "Aucune commande effectuer pour le
     *         moment" si l'utilisateur n'a toujours pas fait de commandes
     * @throws SQLException
     */
    public Map<String, List<String>> historiqueCommande(Client cli) throws SQLException {
        this.st = laConnexion.createStatement();
        Map<String, List<String>> historique = new HashMap<>();
        List<String> listeCommande = new ArrayList<>();
        String key = "";
        ResultSet rs = this.st.executeQuery(
                "select numcom,numlig,datecom, enligne, livraison,titre,qte,prixvente FROM COMMANDE NATURAL JOIN DETAILCOMMANDE NATURAL JOIN LIVRE WHERE idcli = "
                        + cli.getNumCompte() + " ORDER BY datecom");
        if (!rs.next()) {
            return historique;
        }
        rs.beforeFirst();
        int numcomSave = -1;
        int cpt = 0;
        int nblig = nbLigneRequetes(rs);
        while (rs.next()) {
            --nblig;
            String enligne = rs.getString("enLigne").equals("O") ? "en ligne" : "en magasin";
            String livraison = rs.getString("livraison").equals("M") ? "récuperé en magasin" : "livré à domicile";
            if (rs.getInt("numcom") == numcomSave) {
                listeCommande.add(rs.getString("numlig") + ". Livre : " + rs.getString("titre") + " | Prix : "
                        + rs.getInt("prixvente")
                        + "€ | Quantité : " + rs.getInt("qte"));
                cpt = cpt + (rs.getInt("prixvente") * rs.getInt("qte"));
            } else {
                if (numcomSave != -1) {
                    listeCommande.add("Prix total : " + cpt + " €");
                    historique.put(key, listeCommande);
                    listeCommande = new ArrayList<>();
                    cpt = 0;
                }
                numcomSave = rs.getInt("numcom");
                key = "Commande " + rs.getInt("numcom") + "\n"
                        + "Effectuée " + rs.getString("datecom") + " " + enligne + " et " + livraison;
                listeCommande.add(rs.getString("numlig") + ". Livre : " + rs.getString("titre") + " | Prix : "
                        + rs.getInt("prixvente")
                        + "€ | Quantité : " + rs.getInt("qte"));
                cpt = cpt + (rs.getInt("prixvente") * rs.getInt("qte"));
            }
            if (nblig<=0) {
                historique.put(key, listeCommande); // fix car ne prennait pas la dernière val 
            }
        }
        
        rs.close();
        return historique;
    }


    /**
     * permet d'afficher les themes des livres
     * 
     * @return
     * @throws SQLException
     */
    public HashMap<Integer, String> afficheThemes() throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT * FROM CLASSIFICATION GROUP BY FLOOR(iddewey/100)");
        HashMap<Integer, String> res = new HashMap<>();
        int i = 0;
        while (rs.next()) {
            res.put(i, rs.getString("nomclass"));
            i++;
        }
        rs.close();
        return res;

    }

    /**
     * permet d'avoir une liste de catalogue de livres (catalogue ayant une capacité
     * de 10) selon le thème et un magasin donnée
     * 
     * @param thm
     * @param mag
     * @return
     * @throws SQLException
     */
    public List<List<Livre>> rechercheTheme(int thm, Magasin mag) throws SQLException {
        thm = thm * 100;
        this.st = laConnexion.createStatement();
        String query = "SELECT LIVRE.*,qte FROM LIVRE NATURAL JOIN THEMES NATURAL JOIN POSSEDER WHERE iddewey>="
                + thm + " and iddewey<=" + (thm + 90) + " and idmag =" + mag.getId();
        ResultSet rs = this.st.executeQuery(query);
        List<List<Livre>> catalogue = new ArrayList<>();
        int taille = nbLigneRequetes(rs);
        for (int i = 0; i < taille; ++i) {
            catalogue.add(new ArrayList<>());
            for (int y = 0; y < 10; ++y) {
                if (rs.next()) {
                    catalogue.get(i).add(new Livre(rs.getString("isbn"), rs.getString("titre"), rs.getInt("nbpages"),
                            rs.getString("datepubli"), rs.getInt("prix"), rs.getInt("qte")));
                }
            }
        }
        rs.close();
        taille = catalogue.size() - 1;
        while (taille >= 0) {
            if (catalogue.get(taille).isEmpty())
                catalogue.remove(catalogue.get(taille));
            --taille;
        }
        return catalogue;

    }

    /**
     * permet d'avoir le nombre de ligne que fait une requète
     * 
     * @param rs
     * @return le nombre de lignes
     * @throws SQLException
     */
    public int nbLigneRequetes(ResultSet rs) throws SQLException {
        int res = 0;
        while (rs.next()) {
            ++res;
        }
        rs.beforeFirst();
        return res;
    }

    /**
     * permet de commander l'integralité du panier du client
     * 
     * @param livreMag
     * @param cli
     * @param envoie
     * @throws SQLException
     */
    public void commandeLivre(HashMap<Integer, List<Livre>> livreMag, Client cli, String envoie) throws SQLException {
        PreparedStatement ps1 = this.laConnexion
                .prepareStatement("UPDATE POSSEDER SET qte = ? WHERE isbn = ?");
        for (int i : livreMag.keySet()) {
            for (Livre liv : livreMag.get(i)) {
                ps1.setInt(1, getQteLivre(liv) - liv.getQte());
                ps1.setString(2, liv.getIsbn());
                ps1.execute();
                if (getQteLivre(liv) - liv.getQte() <= 0) {
                    suppLivrePosseder(liv.getIsbn(), i);
                }
            }
        }

        int numco = getMaxCommande();
        PreparedStatement ps2 = this.laConnexion
                .prepareStatement("insert into COMMANDE values (?,?,?,?,?,?)");
        PreparedStatement ps3 = this.laConnexion
                .prepareStatement("insert into DETAILCOMMANDE values (?,?,?,?,?)");
        for (Integer idmag : livreMag.keySet()) {
            numco++;
            ps2.setInt(1, numco);
            ps2.setDate(2, new Date(System.currentTimeMillis()));
            ps2.setString(3, "O");
            ps2.setString(4, envoie);
            ps2.setInt(5, cli.getNumCompte());
            ps2.setInt(6, idmag);
            ps2.execute();
            for (int i = 0; i < livreMag.get(idmag).size(); ++i) {
                ps3.setInt(1, numco);
                ps3.setInt(2, i + 1);
                ps3.setInt(3, livreMag.get(idmag).get(i).getQte());
                ps3.setDouble(4, livreMag.get(idmag).get(i).getPrix());
                ps3.setString(5, livreMag.get(idmag).get(i).getIsbn());
                ps3.executeUpdate();
            }
        }
        livreMag.clear();
    }

    /**
     * fonction qui permet d'avoir les informations sur les magasin enregister dans
     * la base de données
     * 
     * @return
     * @throws SQLException
     */
    public HashMap<Integer, Magasin> afficheMagasin() throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT * FROM MAGASIN");
        HashMap<Integer, Magasin> res = new HashMap<>();
        int i = 0;
        while (rs.next()) {
            res.put(i, new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag")));
            i++;
        }
        rs.close();
        return res;
    }

    /**
     * permet d'avoir la quantité de livre dans un magasin
     * 
     * @param liv
     * @return
     * @throws SQLException
     */
    public int getQteLivre(Livre liv) throws SQLException {
        int res = -1;
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT qte FROM POSSEDER WHERE isbn = " + liv.getIsbn());
        while (rs.next()) {
            res = rs.getInt("qte");
        }
        rs.close();
        return res;
    }

    /**
     * permet d'avoir le nombre maximal de commande (utiliser pour l'attribution
     * d'ID)
     * 
     * @return
     * @throws SQLException
     */
    public int getMaxCommande() throws SQLException {
        int res = -1;
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT max(numcom)max FROM COMMANDE");
        while (rs.next()) {
            res = rs.getInt("max");
        }
        rs.close();
        return res;
    }

    /**
     * permet de trouver un magasin selon un livre
     * 
     * @param liv
     * @return l'id du magasin
     * @throws SQLException
     */
    public int trouveMagasin(Livre liv) throws SQLException {
        int res = 0;
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT idmag FROM POSSEDER WHERE isbn = " + liv.getIsbn());
        while (rs.next()) {
            res = rs.getInt("idmag");
        }
        rs.close();
        return res;
    }

    /**
     * supprime un livre de la table posseder
     * 
     * @param isbn
     * @param idmag
     * @throws SQLException
     */
    public void suppLivrePosseder(String isbn, int idmag) throws SQLException {
        this.st.executeUpdate("DELETE FROM POSSEDER where isbn = " + isbn + " and idmag = " + idmag);
    }

    /**
     * fonction qui regarde les commandes qu'ont plusieurs client et qui ne sagit
     * pas du client mit en paramètre
     * et retourne les livres les plus commander
     * 
     * @param cli
     * @param mag
     * @return
     * @throws SQLException
     */
    public List<List<Livre>> onVousRecommande(Client cli, Magasin mag) throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st
                .executeQuery(
                        "select LIVRE.*,count(isbn) nbLiv,POSSEDER.qte from LIVRE natural join DETAILCOMMANDE natural join COMMANDE natural join POSSEDER where idcli !="
                                + cli.getNumCompte() + " and idmag = " + mag.getId()
                                + " group by isbn order by nbLiv DESC");
        List<List<Livre>> catalogue = new ArrayList<>();
        int taille = nbLigneRequetes(rs);
        for (int i = 0; i < taille; ++i) {
            catalogue.add(new ArrayList<>());
            for (int y = 0; y < 10; ++y) {
                if (rs.next()) {
                    catalogue.get(i).add(new Livre(rs.getString("isbn"), rs.getString("titre"), rs.getInt("nbpages"),
                            rs.getString("datepubli"), rs.getInt("prix"), rs.getInt("qte")));
                }
            }
        }
        rs.close();
        taille = catalogue.size() - 1;
        while (taille >= 0) {
            if (catalogue.get(taille).isEmpty())
                catalogue.remove(catalogue.get(taille));
            --taille;
        }
        return catalogue;

    }

    public List<List<Livre>> rechercheCritere(String critere, String isbn, String titre, String auteur, Magasin mag)
            throws SQLException {
        List<List<Livre>> res = new ArrayList<>();
        this.st = laConnexion.createStatement();
        String requete = "select * from LIVRE natural join POSSEDER";
        switch (critere) {
            case "isbn":
                requete += " where isbn = '" + isbn + "'";
                break;

            case "auteur":
                requete += " natural join ECRIRE natural join AUTEUR where idmag = '" + mag.getId()
                        + "' and (nomauteur like '%"
                        + auteur + "%' or levenshtein('" + auteur + "', nomauteur) between 0 and 3) order by nomauteur";
                break;

            case "titre":
                requete += " where idmag = '" + mag.getId() + "' and (titre like '%" + titre + "%' or levenshtein('"
                        + titre
                        + "', titre) between 0 and 3) order by titre";
                break;

            default:
                return res;
        }
        ResultSet rs = this.st.executeQuery(requete);
        int tailleR = nbLigneRequetes(rs);
        for (int i = 0; i < tailleR; ++i) {
            res.add(new ArrayList<>());
            for (int y = 0; y < 10; ++y) {
                if (rs.next()) {
                    res.get(i).add(new Livre(rs.getString("isbn"), rs.getString("titre"), rs.getInt("nbpages"),
                            rs.getString("datepubli"), rs.getDouble("prix"), rs.getInt("qte")));
                }
            }
        }
        rs.close();
        tailleR = res.size() - 1;
        while (tailleR >= 0) {
            if (res.get(tailleR).isEmpty())
                res.remove(res.get(tailleR));
            --tailleR;
        }
        return res;
    }

    /**
     * Fonction qui à partir d'un nom de librairie va trouver la librairie
     * correspondante
     * 
     * @param nommag : le nom de la librairie à trouver
     * @return Magasin : la librairie correspondante (null si aucune n'a été trouvé
     *         pour ce nom de librairie)
     */
    public Magasin trouveLibrairie(String nommag) throws SQLException {
        Magasin mag = null;
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select idmag, villemag from MAGASIN where nommag =" + '"' + nommag + '"');
        while (rs.next()) {
            mag = new Magasin(rs.getInt("idmag"), nommag, rs.getString("villemag"));
        }
        return mag;
    }

    /**
     * Fonction qui à partir d'un nom de librairie va trouver la librairie
     * correspondante
     * 
     * @param idmag : idmag de la librairie à trouver
     * @return Magasin : la librairie correspondante (null si aucune n'a été trouvé
     *         pour ce nom de librairie)
     */
    public Magasin trouveLibrairie(int idmag) throws SQLException {
        Magasin mag = null;
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select * from MAGASIN where idmag =" + '"' + idmag + '"');
        while (rs.next()) {
            mag = new Magasin(idmag, rs.getString("nommag"), rs.getString("villemag"));
        }
        return mag;
    }
}