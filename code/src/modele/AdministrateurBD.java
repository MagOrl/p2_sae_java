import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class AdministrateurBD {

  private ConnexionMySQL connexion;
  private Statement st;

  public AdministrateurBD(ConnexionMySQL laConnexion) throws SQLException {
    this.connexion = laConnexion;
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
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fonction qui va regarder si le compte de l'administrateur est présent dans la
   * base
   * de donnée.
   * 
   * @param identifiant : l'identifiant de l'admin
   * @param mdp         : le mot de passe de l'admin
   * @return boolean : true le compte existe dans la base de donnée, false sinon
   */
  public boolean connectAdmin(String identifiant, String mdp) throws SQLException {
    this.st = this.connexion.createStatement();
    ResultSet rs = this.st
        .executeQuery(
            "SELECT * FROM CLIENT WHERE identifiant = '" + identifiant + "'and motdepasse ='" + mdp + "'");
    return rs.next();
  }

  /**
   * Fonction qui à partir d'un identifiant et un mot de passe, trouve
   * l'administrateur
   * correspondant
   * 
   * @param identifiant : l'identifiant de l'admin
   * @param mdp         : le mot de passe de l'admin
   * @return Administrateur : l'administrateur, retourne null si l'admin n'a pas
   *         pu être trouvé
   */
  public Administrateur trouveAdmin(String identifiant, String mdp) throws SQLException {
    Administrateur admin = null;

    this.st = this.connexion.createStatement();
    ResultSet rs = this.st
        .executeQuery(
            "SELECT * FROM CLIENT WHERE identifiant ='" + identifiant + "'" + "and motdepasse ='" + mdp + "'");
    while (rs.next()) {
      admin = new Administrateur(clientMax(), rs.getString("nomcli"), rs.getString("prenomcli"), identifiant,
          rs.getString("adressecli"), rs.getInt("tel"), rs.getString("email"), mdp, rs.getString("codepostal"),
          rs.getString("villecli"));
    }
    rs.close();

    return admin;
  }

  /**
   * Fonction qui v a créer un administrateur dans la base de donnée
   * 
   * @param identifiant : l'identifiant de l'admin
   * @param nom         : le nom de l'admin
   * @param prenom      : le prénom de l'admin
   * @param adresse     : l'adresse de l'admin
   * @param codepostal  : le code postal de l'admin
   * @param ville       : la ville de l'admin
   * @param email       : l'email de l'admin
   * @param tel         : le téléphone de l'admin
   * @param mdp         : le mot de passe de l'admin
   * @return int : l'id du compte
   */
  public int creeClient(String identif, String nom, String prenom, String adresse, String codepostal, String ville,
      String email, String tel, String mdp) throws SQLException {
    int numCli = clientMax() + 1;
    this.st = this.connexion.createStatement();
    PreparedStatement ps = this.connexion.prepareStatement("insert into CLIENT values (?,?,?,?,?,?,?,?,?,?)");
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
   * Fonction qui v a créer un vendeur dans la base de donnée
   * 
   * @param nom         : le nom du vendeur
   * @param prenom      : le prénom du vendeur
   * @param identifiant : l'identifiant du vendeur
   * @param adresse     : l'adresse du vendeur
   * @param tel         : le téléphone du vendeur
   * @param email       : l'email du vendeur
   * @param mdp         : le mot de passe du vendeur
   * @param codepostal  : le code postal du vendeur
   * @param ville       : la ville du vendeur
   * @param nommage     : le nom du magasin du vendeur
   * @return Vendeur : le nouveau vendeur
   */
  public Vendeur CreerCompteVendeur(String nom, String prenom, String identifiant, String adresse, String tel,
      String email, String mdp, String codePostal, String ville, String nommag)
      throws SQLException, NumberFormatException {
    int numVendeur = clientMax() + 1;
    Magasin mag = null;
    this.st = this.connexion.createStatement();
    ResultSet rs = this.st.executeQuery("select idmag, villemag from MAGASIN where nommag ='" + nommag + "'");
    while (rs.next()) {
      mag = new Magasin(rs.getInt("idmag"), nommag, rs.getString("villemag"));
    }
    PreparedStatement ps = this.connexion.prepareStatement("insert into CLIENT values (?,?,?,?,?,?,?,?,?,?)");
    ps.setInt(1, numVendeur);
    ps.setString(2, identifiant);
    ps.setString(3, nom);
    ps.setString(4, prenom);
    ps.setString(5, adresse);
    ps.setString(6, codePostal);
    ps.setString(7, ville);
    ps.setString(8, email);
    ps.setInt(9, Integer.parseInt(tel));
    ps.setString(10, mdp);
    ps.executeUpdate();
    Integer telToInt = Integer.valueOf(tel);
    rs.close();
    return new Vendeur(numVendeur, nom, prenom, identifiant, adresse, telToInt, email, mdp, codePostal, ville, mag);
  }

  /**
   * Fonction qui va créer un nouveau numéro de client maximum, par rapport au
   * numéro maximum déjà présent
   * 
   * @return int : le nouveau numéro de client maximum
   */
  public int clientMax() throws SQLException {
    int max = 0;
    this.st = this.connexion.createStatement();
    ResultSet rs = this.st.executeQuery("select max(idcli) nbclimax from CLIENT");
    while (rs.next()) {
      max = rs.getInt("nbclimax");
    }
    rs.close();
    return max;
  }

  /**
   * Fonction qui va ajouter un nouvelle librairie au réseau
   * 
   * @param nommag   : le nom de la librairie
   * @param villemag : la ville de la librairie
   */
  public void ajouteNouvelleLibrairie(String nommag, String villemag) throws SQLException {
    Magasin magasin = new Magasin(idmagMax(), nommag, villemag);
    PreparedStatement ps = this.connexion.prepareStatement("insert into MAGASIN values(?,?,?)");
    ps.setInt(1, magasin.getId());
    ps.setString(2, magasin.getNom());
    ps.setString(3, magasin.getVille());
    ps.executeUpdate();
  }

  /**
   * Fonction qui va créer un nouvel identifiant de librairie maximum,
   * par rapport au numéro maximum déjà présent
   * 
   * @return String : le nouvel identifiant de librairie maximum
   */
  public int idmagMax() throws SQLException {
    Integer idMax = 0;
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery("select max(idmag) as idMax from MAGASIN");
    while (rs.next()) {
      idMax = rs.getInt("idMax") + 1;
    }
    rs.close();
    return idMax;
  }

  /**
   * Fonction qui va créer la liste des librairie présentes
   * sur le réseau
   * 
   * @return List<String> : la liste des nom des librairie du réseau
   */
  public List<String> choixLibrairie() throws SQLException {
    List<String> lesLibrairies = new ArrayList<>();
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery("select nommag from MAGASIN");
    while (rs.next()) {
      lesLibrairies.add(rs.getString("nommag"));
    }
    rs.close();
    return lesLibrairies;
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
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery("select idmag, villemag from MAGASIN where nommag =" + '"' + nommag + '"');
    while (rs.next()) {
      mag = new Magasin(rs.getInt("idmag"), nommag, rs.getString("villemag"));
    }
    return mag;
  }

  /**
   * Fonction qui va ajouter un nouveau livre à une librairie passée en paramètre
   * 
   * @param isbn      : l'identifiant du livre
   * @param titre     : le titre du livre
   * @param auteur    : l'auteur du livre
   * @param editeur   : l'éditeur du livre
   * @param theme     : le thème du livre
   * @param nbpages   : le nombre de pages du livre
   * @param datepubli : la date de publication du livre
   * @param prix      : le prix du livre
   * @param qte       : la quantité du livre à ajouter
   * @param mag       : la librairie dans laquelle ajouter le livre
   */
  public void AjouterLivre(String isbn, String titre, String auteur, String editeur, String theme, String nbpages,
      String datepubli, String prix, String qte, Magasin mag) throws SQLException {
    Livre livre = new Livre(isbn, titre, Integer.parseInt(nbpages), datepubli, Double.parseDouble(prix),
        Integer.parseInt(qte));

    PreparedStatement psLivre = this.connexion.prepareStatement("insert ignore into LIVRE values(?,?,?,?,?)");
    psLivre.setString(1, livre.getIsbn());
    psLivre.setString(2, livre.getTitre());
    psLivre.setInt(3, livre.getNbPages());
    psLivre.setString(4, (livre.getDatePubli()));
    psLivre.setDouble(5, livre.getPrix());
    psLivre.executeUpdate();

    PreparedStatement psPosseder = this.connexion.prepareStatement("insert into POSSEDER values(?,?,?)");
    psPosseder.setInt(1, mag.getId());
    psPosseder.setString(2, isbn);
    psPosseder.setInt(3, Integer.parseInt(qte));

    // PreparedStatement ps
    // System.out.println("Une erreur est survenue lors de l'ajout du livre veuillez
    // réessayer");
  }

  /**
   * Fonction qui va supprimer un livre d'une librairie passée en paramètre
   * 
   * @param isbn : l'identifiant du livre
   * @param mag  : la librairie dans laquelle supprimer le livre
   * @return boolean : true si le livre a été supprimé, false sinon
   */
  public boolean SupprimerLivre(String isbn, Magasin mag) throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st
        .executeQuery("select * from POSSEDER where isbn = '" + isbn + "'" + " and idmag = '" + mag.getId() + "'");
    if (!rs.next()) {
      return false;
    }
    PreparedStatement ps = this.connexion
        .prepareStatement(("UPDATE POSSEDER SET qte = 0 where isbn = ? and idmag = ?"));
    ps.setString(1, isbn);
    ps.setInt(2, mag.getId());
    ps.executeUpdate();
    return true;
  }

  /**
   * Fonction qui va mettre à jour la quantité d'un livre que possède
   * une librairie passeé en paramètre
   * 
   * @param isbn : l'identifiant du livre
   * @param mag  : la librairie dans laquelle modifier la quantité le livre
   * @param qte  : la quantité de livre à ajouter ou à enlever
   * @return boolean : true si la quantité à été modifiée, false sinon
   */
  public boolean majQteLivre(String isbn, Magasin mag, int qte)
      throws SQLException, NumberFormatException, QteInfAZeroException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st
        .executeQuery("select qte from POSSEDER where isbn = '" + isbn + "'" + " and idmag = '" + mag.getId() + "'");
    if (!rs.next()) {
      rs.close();
      return false;
    }
    if (rs.getInt("qte") + qte < 0) {
      throw new QteInfAZeroException();
    }

    PreparedStatement ps = this.connexion
        .prepareStatement("UPDATE POSSEDER SET qte = qte + ? WHERE isbn = ? and idmag = ?");
    ps.setInt(1, qte);
    ps.setString(2, isbn);
    ps.setInt(3, mag.getId());
    ps.executeUpdate();
    rs.close();
    return true;
  }

  /**
   * Fonction qui va afficher tout les livres que possède un librairie
   * 
   * @param mag : la librairie dont il faut afficher le stock
   */
  public void afficherStockLibrairie(Magasin mag) throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery(
        "select isbn, titre, nbpages, datepubli, prix, qte from LIVRE natural join POSSEDER natural join MAGASIN where idmag = "
            + mag.getId());
    if (!rs.next()) {
      System.out.println("------------------------------------------------------------");
      System.out.println("La libraire actuelle (" + mag.getNom() + ") ne contient aucun livre");
      System.out.println("------------------------------------------------------------");
    }
    while (rs.next()) {
      Livre livreActuel = new Livre(rs.getString("isbn"), rs.getString("titre"), rs.getInt("nbpages"),
          rs.getString("datepubli"), rs.getDouble("prix"), rs.getInt("qte"));
      System.out.println("------------------------------------------------------------");
      System.out.println(livreActuel);
      System.out.println("------------------------------------------------------------");
    }
    // System.out.println("Une erreur est survenue lors de l'affichage du stock");

  }

  public HashMap<String, HashMap<String, Number>> requeteNbVendMagAnne() throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery(
        "select distinct nommag as Magasin, YEAR(datecom) as annee, sum(qte) as qte from MAGASIN natural join COMMANDE natural join DETAILCOMMANDE group by Magasin, annee order by annee");
    HashMap<String, HashMap<String, Number>> p = new HashMap<>();
    while (rs.next()) {
      p.putIfAbsent(rs.getString("annee"), new HashMap<String, Number>());
      p.get(rs.getString("annee")).put(rs.getString("Magasin"), rs.getInt("qte"));
    }
    rs.close();
    return p;
  }

  /**
   * Fonction qui va afficher le pourcentage du chiffre d'affaire de chaque thème
   * pour une année
   * 
   * @param annee : l'annee à analyser
   */
  public ArrayList<Map.Entry<String, Integer>> requeteCAThemeAnnee() throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery(
        "Select nomclass as Theme, sum(prixvente*qte) as Montant From COMMANDE Natural Join DETAILCOMMANDE Natural Join LIVRE Natural Join THEMES Natural Join CLASSIFICATION Where Year(datecom) = 2025 Group by Floor(iddewey / 100) * 100");
    ArrayList<Map.Entry<String, Integer>> listo = new ArrayList<>();
    while (rs.next()) {
      listo.add(new AbstractMap.SimpleEntry<>(rs.getString("Theme"), rs.getInt("montant")));
    }
    return listo;
  }

  /**
   * Fonction qui va afficher l'évolution du chiffre d'affaire des magasins par
   * mois, pour une année
   * 
   * @param annee : l'annee à analyser
   */
  public HashMap<String, HashMap<String, Number>> requeteEvoCAMag() throws SQLException {
    this.st = connexion.createStatement();
    HashMap<String, HashMap<String, Number>> listo = new HashMap<>();
    ResultSet rs = this.st.executeQuery(
        "Select MONTH(datecom) as mois, nommag as Magasin, sum(qte*prixvente) as CA From MAGASIN Natural Join COMMANDE Natural Join DETAILCOMMANDE Where YEAR(datecom) = 2024 Group by mois, Magasin");
    while (rs.next()) {
      listo.putIfAbsent(rs.getString("Magasin"), new HashMap<String, Number>());
      listo.get(rs.getString("Magasin")).put(rs.getString("mois"), rs.getInt("CA"));
    }
    return listo;

  }

  /**
   * Fonction qui va afficher la comparaison entre le nombre de ventes en lignes
   * et le nombre de ventes en magasin par année
   */
  public HashMap<String, HashMap<String, Number>> requeteCompVenteLMAnnee() throws SQLException {
    this.st = connexion.createStatement();
    HashMap<String, HashMap<String, Number>> listo = new HashMap<>();
    ResultSet rs = this.st.executeQuery(
        "select YEAR(datecom) as annee, enligne as typevente, sum(qte*prix) as montant from COMMANDE natural join DETAILCOMMANDE natural join LIVRE where YEAR(datecom)<>2025 group by annee, typevente order by annee DESC");

    while (rs.next()) {
      listo.putIfAbsent(rs.getString("typevente"), new HashMap<String, Number>());
      listo.get(rs.getString("typevente")).put(rs.getString("annee"), rs.getInt("montant"));

    }
    return listo;
  }

  /**
   * Fonction qui va afficher les dix éditeurs le plus importants en nombre
   * d'auteurs
   */
  public ArrayList<Map.Entry<String, Integer>> requeteDixeditPlusImportants() throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery(
        "select nomedit as Editeur, count(idauteur) as nbauteurs from EDITEUR natural join EDITER natural join LIVRE natural join ECRIRE natural join AUTEUR group by nomedit order by nbauteurs desc limit 10");
    ArrayList<Map.Entry<String, Integer>> listo = new ArrayList<>();
    while (rs.next()) {
      listo.add(new AbstractMap.SimpleEntry<>(rs.getString("Editeur"), rs.getInt("nbauteurs")));
    }
    return listo;
  }

  /**
   * Fonction qui va afficher l'origine des clients ayant acheté des livres d'un
   * auteur passé en paramètre
   * 
   * @param auteur : l'auteur à analyser
   */
  public ArrayList<Map.Entry<String, Integer>> requeteOrigineClientAuteur() throws SQLException {
    this.st = connexion.createStatement();
    ArrayList<Map.Entry<String, Integer>> listo = new ArrayList<>();
    ResultSet rs = this.st.executeQuery(
        "select villecli as ville, sum(qte) as qte from CLIENT natural join COMMANDE natural join DETAILCOMMANDE natural join LIVRE natural join ECRIRE natural join AUTEUR where nomauteur = 'René Goscinny'  group by villecli");

    while (rs.next()) {
      listo.add(new AbstractMap.SimpleEntry<>(rs.getString("ville"), rs.getInt("qte")));
    }
    return listo;
  }

  /**
   * Fonction qui va afficher la valeur du stock de chaque magasin du réseau
   */
  public ArrayList<Map.Entry<String, Integer>> requeteValeurStockMag() throws SQLException {
    this.st = connexion.createStatement();
    ArrayList<Map.Entry<String, Integer>> listo = new ArrayList<>();
    ResultSet rs = this.st.executeQuery(
        "select nommag as Magasin, sum(qte*prix) as total from MAGASIN natural join POSSEDER natural join LIVRE group by nommag");
    while (rs.next()) {
      listo.add(new AbstractMap.SimpleEntry<>(rs.getString("Magasin"), rs.getInt("total")));
    }
    return listo;
  }

  /**
   * Fonction qui va afficher l'évolution du chiffre d'affaire par client chaque
   * ann
   */
  public HashMap<String, HashMap<String, Number>> requeteEvoCAClient() throws SQLException {
    this.st = connexion.createStatement();
    ResultSet rs = this.st.executeQuery(
        "with MaxCAParClient as (select idcli, YEAR(datecom) as annee, sum(qte*prixvente) as CA from CLIENT natural join COMMANDE natural join DETAILCOMMANDE natural join LIVRE group by YEAR(datecom), idcli) select annee, max(CA) as maximum, min(CA) as minimum, avg(CA) as moyenne from MaxCAParClient group by annee");
    HashMap<String, HashMap<String, Number>> p = new HashMap<>();
    while (rs.next()) {
      p.putIfAbsent("maximum", new HashMap<String, Number>()); 
      p.putIfAbsent("minimum", new HashMap<String, Number>()); 
      p.putIfAbsent("moyenne", new HashMap<String, Number>()); 
      p.get("maximum").put(rs.getString("annee"), rs.getInt(2)); 
      p.get("minimum").put(rs.getString("annee"), rs.getInt(3));
      p.get("moyenne").put(rs.getString("annee"), rs.getInt(4));
      
    }
    return p;
  }
}