import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdministrateurBD {

  private ConnexionMySQL connexion;
  private Statement st;

  public AdministrateurBD(ConnexionMySQL laConnexion) {
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
    } catch (SQLException e) {
      e.printStackTrace();
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
   * @param idmag    : l'id de la librairie
   */
  public void ajouteNouvelleLibrairie(String nommag, String villemag, String idmag) throws SQLException {
    Magasin magasin = new Magasin(Integer.parseInt(idmag), nommag, villemag);
    PreparedStatement ps = this.connexion.prepareStatement("insert ignore into MAGASIN values(?,?,?)");
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
  public void AjouterLivre(String isbn, String titre, String nbpages, String datepubli, String prix, String qte,
      Magasin mag) throws SQLException {
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
    psPosseder.executeUpdate();

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
    PreparedStatement ps = this.connexion.prepareStatement(("DELETE FROM POSSEDER where isbn = ? and idmag = ?"));
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

  public List<List<Livre>> rechercheCritere(String critere, String isbn, String titre, String auteur, Magasin mag)
      throws SQLException {
    List<List<Livre>> res = new ArrayList<>();
    this.st = connexion.createStatement();
    String requete = "select * from LIVRE natural join POSSEDER";
    switch (critere) {
      case "isbn":
        requete += " where isbn = '" + isbn + "'";
        break;

      case "auteur":
        requete += " natural join ECRIRE natural join AUTEUR where idmag = '" + mag.getId() + "' and (nomauteur like '%"
            + auteur + "%' or levenshtein('" + auteur + "', nomauteur) between 0 and 3) order by nomauteur";
        break;

      case "titre":
        requete += " where idmag = '" + mag.getId() + "' and (titre like '%" + titre + "%' or levenshtein('" + titre
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

  public List<List<Livre>> afficheStock(Magasin mag) {
    int cpt = 0;
    List<List<Livre>> res = new ArrayList<>();
    try {
      this.st = connexion.createStatement();
      ResultSet rs = this.st.executeQuery(
          "select isbn, titre, nbpages, datepubli, prix, qte from POSSEDER natural join LIVRE where idmag = "
              + mag.getId() + " order by titre");
      List<Livre> page = new ArrayList<>();
      while (rs.next()) {
        Livre livre = new Livre(rs.getString("isbn"), rs.getString("titre"), rs.getInt("nbpages"),
            rs.getString("datepubli"), rs.getDouble("prix"), rs.getInt("qte"));
        page.add(livre);
        cpt++;
        if (cpt == 10) {
          res.add(page);
          page = new ArrayList<>();
          cpt = 0;
        }
      }
    } catch (SQLException e) {
      System.out.println("Une erreur est survenue lors de la recherche");
    }
    return res;

  }
}