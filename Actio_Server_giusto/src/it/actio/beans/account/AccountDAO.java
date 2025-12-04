package it.actio.beans.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.actio.utils.DBManager;


public class AccountDAO {

	private static Connection conn = null;

	public Account get(Account account) {
		String query = "SELECT * FROM ACCOUNT WHERE username =?";

		Account res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, account.getUsername());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public Account getAccount(String email, String password) {
		String query = "SELECT * FROM ACCOUNT WHERE email =? and BINARY password = ?";

		Account res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public int getId(String username) {
		String query = "SELECT id FROM ACCOUNT WHERE username =?";

		int res = -1;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean getEsiste(Account account) {
		String query = "SELECT * FROM ACCOUNT WHERE username =?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, account.getUsername());

			ResultSet rs = ps.executeQuery();

			esito = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean getEsistebypassword(Account account) {
		String query = "SELECT * FROM ACCOUNT WHERE username =? and BINARY password=?";
		boolean res = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public int getRuolo(String username, String password) {
		String query = "SELECT ruolo FROM ACCOUNT WHERE username = ? and BINARY password = ?";

		int ruolo = -1;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ruolo = rs.getInt("ruolo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return ruolo;
	}

	public int get_idPersona(String username, String password) {
		String query = "SELECT idPersona FROM ACCOUNT WHERE username = ? and BINARY password = ?";

		int sq = -1;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sq = rs.getInt("idPersona");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return sq;
	}

	public List<String> getUsername() {
		String query = "SELECT username FROM ACCOUNT";

		List<String> res = new ArrayList<String>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				res.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	private Account recordToAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setEmail(rs.getString("email"));
		account.setPassword(rs.getString("password"));
		account.setRuolo(rs.getInt("ruolo"));
		if (rs.getObject("idPersona") != null)
			account.setIdPersona(rs.getInt("idPersona"));
		if (rs.getObject("idAttivita") != null)
			account.setIdAttivita(rs.getInt("idAttivita"));

		return account;
	}

	public List<Account> getAll() {
		String query = "SELECT * FROM ACCOUNT order by id";

		List<Account> res = new ArrayList<Account>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = recordToAccount(rs);
				res.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(Account account) {
		String query = "INSERT INTO ACCOUNT (id, username, password, ruolo, idPersona, idAttivita) VALUES ( ?, ?, ?, ?, ? ,?)";
		System.out.println("DEBUG-DAO: Query = " + query);
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
//			ps = conn.prepareStatement(query);

			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, account.getId());

			ps.setString(2, account.getUsername());
			ps.setString(3, account.getPassword());
			ps.setInt(4, account.getRuolo());
			if (account.getIdPersona() != null)
				ps.setInt(4, account.getIdPersona());
			else
				ps.setNull(4, java.sql.Types.INTEGER);
			
			if (account.getIdAttivita() != null)
				ps.setInt(4, account.getIdAttivita());
			else
				ps.setNull(4, java.sql.Types.INTEGER);

			System.out.println("DEBUG-DAO: ps[1] = " + account.getUsername());
			System.out.println("DEBUG-DAO: ps[2] = " + account.getPassword());
			System.out.println("DEBUG-DAO: ps[3] = " + account.getRuolo());
			System.out.println("DEBUG-DAO: ps[4] = " + account.getIdPersona());
			System.out.println("DEBUG-DAO: ps[4] = " + account.getIdAttivita());

			int tmp = ps.executeUpdate();

			if (tmp == 1)
				esito = true;
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			// System.out.println("Chiave inserita "+rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean elimina(Account account) {
		String query = "DELETE FROM Account WHERE id = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, account.getUsername());

			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;

		} catch (SQLException e) {
			esito = false;
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean modifica(Account a) {
		String query = "UPDATE Account SET password=?, ruolo=?, idPersona = ?, idAttivita = ? WHERE id=?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(5, a.getId());
			ps.setString(1, a.getPassword());
			if (a.getIdPersona() != null)
				ps.setInt(3, a.getIdPersona());
			else
				ps.setNull(3, java.sql.Types.INTEGER);
			if (a.getIdAttivita() != null)
				ps.setInt(4, a.getIdAttivita());
			else
				ps.setNull(4, java.sql.Types.INTEGER);

			ps.setInt(2, a.getRuolo());

			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}
