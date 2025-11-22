package it.actio.beans.attivita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import it.actio.utils.DBManager;


public class AttivitaDAO {

	private static Connection conn = null;

	public Attivita get(Attivita attivita) {
		String query = "SELECT * FROM ATTIVITA WHERE id =?";

		Attivita res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, attivita.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToAttivita(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Attivita getbyId(int id) {
		String query = "SELECT * FROM ATTIVITA WHERE id =?";

		Attivita res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToAttivita(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<String> getNomi() {
		String query = "SELECT nome FROM ATTIVITA";

		Vector<String> res = new Vector<String>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome_attivita = rs.getString("nome");
				res.add(nome_attivita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public int getUltimoId() {
		String query = "SELECT id FROM ATTIVITA order by id desc limit 1";

		int res = -1;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);

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

	private Attivita recordToAttivita(ResultSet rs) throws SQLException {
		Attivita attivita = new Attivita();
		attivita.setId(rs.getInt("id"));
		attivita.setNome(rs.getString("nome"));
		attivita.setLogo(rs.getString("logo"));
		attivita.setTipo(rs.getInt("tipo"));
		attivita.setCitta(rs.getString("citta"));
		return attivita;
	}

	public Vector<Attivita> getAll() {
		String query = "SELECT * FROM ATTIVITA order by id";

		Vector<Attivita> res = new Vector<Attivita>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Attivita attivita = recordToAttivita(rs);
				res.add(attivita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Attivita> getAllbytipo(int tipo) {
		String query = "SELECT * FROM ATTIVITA where tipo = ? order by id";

		Vector<Attivita> res = new Vector<Attivita>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, tipo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Attivita attivita = recordToAttivita(rs);
				res.add(attivita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public Vector<Attivita> getAllbycitta(String citta) {
		String query = "SELECT * FROM ATTIVITA where citta = ? order by id";

		Vector<Attivita> res = new Vector<Attivita>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, citta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Attivita attivita = recordToAttivita(rs);
				res.add(attivita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(Attivita attivita) {
		String query = "INSERT INTO ATTIVITA VALUES ( ?, ?, ?, ?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
//			ps = conn.prepareStatement(query);

			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, attivita.getId());
			ps.setString(2, attivita.getNome());
			ps.setString(3, attivita.getLogo());
			ps.setInt(4, attivita.getTipo());
			ps.setString(5, attivita.getCitta());
			

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

	public boolean elimina(Attivita attivita) {
		String query = "DELETE FROM Attivita WHERE id = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, attivita.getId());

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

	public boolean modifica(Attivita a) {
		String query = "UPDATE Attivita SET nome=?, logo=?, tipo = ?, citta = ? WHERE id=?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getNome());
			ps.setString(2, a.getLogo());
			ps.setInt(3, a.getTipo());
			ps.setInt(4, a.getId());


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
