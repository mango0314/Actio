package it.actio.beans.orario_corso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import it.actio.utils.DBManager;

public class Orario_corsoDAO {

	private static Connection conn = null;

	public Orario_corso get(Orario_corso orario_corso) {
		String query = "SELECT * FROM orario_corso WHERE id =?";

		Orario_corso res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orario_corso.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToOrario_corso(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Orario_corso getbyId(int id) {
		String query = "SELECT * FROM orario_corso WHERE id =?";

		Orario_corso res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToOrario_corso(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}


	public int getUltimoId() {
		String query = "SELECT id FROM orario_corso order by id desc limit 1";

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

	private Orario_corso recordToOrario_corso(ResultSet rs) throws SQLException {
		Orario_corso orario_corso = new Orario_corso();
		orario_corso.setId(rs.getInt("id"));
		orario_corso.setIdCorso(rs.getInt("idCorso"));
		orario_corso.setGiorno_settimana(rs.getInt("giorno_settimana"));
		orario_corso.setOrarioInizio(rs.getTime("orarioInizio"));
		orario_corso.setOrarioFine(rs.getTime("orarioFine"));
		
		return orario_corso;
	}

	public Vector<Orario_corso> getAll() {
		String query = "SELECT * FROM ORARIO_CORSO order by id";

		Vector<Orario_corso> res = new Vector<Orario_corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Orario_corso orario_corso = recordToOrario_corso(rs);
				res.add(orario_corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Orario_corso> getAllbyidCorso(int idCorso) {
		String query = "SELECT * FROM ORARIO_CORSO where idCorso = ? order by id";

		Vector<Orario_corso> res = new Vector<Orario_corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idCorso);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Orario_corso orario_corso = recordToOrario_corso(rs);
				res.add(orario_corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(Orario_corso orario_corso) {
		String query = "INSERT INTO ORARIO_CORSO VALUES (?, ?, ?, ?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
//			ps = conn.prepareStatement(query);

			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, orario_corso.getId());
			ps.setInt(2, orario_corso.getIdCorso());
			ps.setInt(3, orario_corso.getGiorno_settimana());
			ps.setTime(4, orario_corso.getOrarioInizio());
			ps.setTime(5, orario_corso.getOrarioFine());

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

	public boolean elimina(Orario_corso orario_corso) {
		String query = "DELETE FROM orario_corso WHERE id = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orario_corso.getId());

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

	public boolean modifica(Orario_corso o) {
		String query = "UPDATE Orario_corso SET idCorso=?, giorno_settimana=?, orarioInizio=?, orarioFine=? WHERE id=?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, o.getIdCorso());
			ps.setInt(2, o.getGiorno_settimana());
			ps.setTime(3, o.getOrarioInizio());
			ps.setTime(4, o.getOrarioFine());
			ps.setInt(5, o.getId());



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
