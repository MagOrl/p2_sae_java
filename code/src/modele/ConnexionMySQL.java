import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql = null;
	private boolean connecte = false;

	public ConnexionMySQL() throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		this.mysql = DriverManager.getConnection("jdbc:mariadb://" + nomServeur + ":3306/" + nomBase, nomLogin,
				motDePasse);
		this.connecte = this.mysql != null;
	}

	public void close() throws SQLException {
		// fermer la connexion
		this.mysql.close();
		this.connecte = false;

	}

	public boolean isConnecte() {
		return this.connecte;
	}

	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException {
		return this.mysql.prepareStatement(requete);
	}

	public void setAutoCommit(Boolean bol) throws SQLException {
		this.mysql.setAutoCommit(bol);
	}

	public void deconnecter() throws SQLException {
		this.mysql.close();
	}

}
