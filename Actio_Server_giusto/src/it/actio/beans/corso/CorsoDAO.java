package it.actio.beans.corso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import it.actio.utils.DBManager;


public class CorsoDAO {

	private static Connection conn = null;

	public Corso get(Corso corso) {
		String query = "SELECT * FROM CORSO WHERE id =?";

		Corso res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  corso.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCorso(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Corso getbyId(int id) {
		String query = "SELECT * FROM CORSO WHERE id =?";

		Corso res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCorso(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<String> getNomi() {
		String query = "SELECT nome FROM CORSO";

		Vector<String> res = new Vector<String>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome_corso = rs.getString("nome");
				res.add(nome_corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public int getUltimoId() {
		String query = "SELECT id FROM CORSO order by id desc limit 1";

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

	private Corso recordToCorso(ResultSet rs) throws SQLException {
		Corso corso = new Corso();
		corso.setId(rs.getInt("id"));
		corso.setNome(rs.getString("nome"));
		corso.setCapienza(rs.getInt("capienza"));
		corso.setDescrizione(rs.getString("descrizione"));
		return corso;
	}

	public Vector<Corso> getAll() {
		String query = "SELECT * FROM CORSO order by id";

		Vector<Corso> res = new Vector<Corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Corso corso = recordToCorso(rs);
				res.add(corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Corso> getAll_compl(int tipo) {
		String query = "SELECT * FROM ATTIVITA where tipo = ? order by id";

		Vector<Corso> res = new Vector<Corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, tipo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Corso corso = recordToCorso(rs);
				res.add(corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(Corso corso) {
		String query = "INSERT INTO CORSO VALUES ( ?, ?, ?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
//			ps = conn.prepareStatement(query);

			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, corso.getId());
			ps.setString(2, corso.getNome());
			ps.setInt(3, corso.getCapienza());
			ps.setString(4, corso.getDescrizione());

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

	public boolean elimina(Corso corso) {
		String query = "DELETE FROM Corso WHERE id = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, corso.getId());

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

	public boolean modifica(Corso a) {
		String query = "UPDATE Corso SET nome=?, capienza=?, descrizione = ? WHERE id=?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getNome());
			ps.setInt(2, a.getCapienza());
			ps.setString(3, a.getDescrizione());
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
