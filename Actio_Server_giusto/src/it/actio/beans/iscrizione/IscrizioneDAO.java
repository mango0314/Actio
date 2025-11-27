package it.actio.beans.iscrizione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.actio.utils.DBManager;


public class IscrizioneDAO {

	private static Connection conn = null;
	
	public Iscrizione get(int idPersona, int idCorso) {
		String query = "SELECT * FROM iscrizione WHERE idPersona =? and idCorso = ?";

		Iscrizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  idPersona);
			ps.setInt(2, idCorso);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToIscrizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}



	public List<Integer> getAllPersone() {
		String query = "SELECT idPersona FROM iscrizione";

		List<Integer> res = new ArrayList<Integer>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_persona = rs.getInt("idPersona");
				res.add(id_persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public List<Integer> getAllCorsi() {
		String query = "SELECT idCorso FROM iscrizione";

		List<Integer> res = new ArrayList<Integer>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_corso = rs.getInt("idCorso");
				res.add(id_corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public List<Integer> getAllCorsi_byidPersona(int idPersona) {
		String query = "SELECT idCorso FROM iscrizione where idPersona = ?";

		List<Integer> res = new ArrayList<Integer>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idPersona);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_corso = rs.getInt("idCorso");
				res.add(id_corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}


	private Iscrizione recordToIscrizione(ResultSet rs) throws SQLException {
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdPersona(rs.getInt("idPersona"));
		iscrizione.setIdCorso(rs.getInt("idCorso"));
		iscrizione.setDataInizio(rs.getDate("dataInizio"));
		iscrizione.setDataFine(rs.getDate("dataFine"));
		iscrizione.setStato(rs.getInt("stato"));
		return iscrizione;
	}

	public List<Iscrizione> getAll() {
		String query = "SELECT * FROM iscrizione order by idPersona";

		List<Iscrizione> res = new ArrayList<Iscrizione>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Iscrizione iscrizione = recordToIscrizione(rs);
				res.add(iscrizione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public List<Iscrizione> getAll_bystato(int stato) {
		String query = "SELECT * FROM iscrizione where stato = ? order by idPersona";

		List<Iscrizione> res = new ArrayList<Iscrizione>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, stato);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Iscrizione iscrizione = recordToIscrizione(rs);
				res.add(iscrizione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	

	public boolean salva(Iscrizione iscrizione) {
		String query = "INSERT INTO iscrizione VALUES (?, ?, ?, ?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {

			ps = conn.prepareStatement(query);

			ps.setInt(1, iscrizione.getIdPersona());
			ps.setInt(2, iscrizione.getIdCorso());
			java.sql.Date data_inizio = new java.sql.Date(iscrizione.getDataInizio().getTime()); 
			ps.setDate(3, data_inizio);
			java.sql.Date data_fine = new java.sql.Date(iscrizione.getDataFine().getTime()); 
			ps.setDate(4, data_fine);
			ps.setInt(5, iscrizione.getStato());
			

			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean elimina(Iscrizione iscrizione) {
		String query = "DELETE FROM iscrizione WHERE idPersona = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, iscrizione.getIdPersona());
			ps.setInt(2, iscrizione.getIdCorso());

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

	public boolean modifica(Iscrizione i) {
		String query = "UPDATE iscrizione SET dataInizio=?, dataFine=?, stato = ? WHERE idPersona = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(i.getDataInizio().getTime()));
			ps.setDate(2, new java.sql.Date(i.getDataFine().getTime()));
			ps.setInt(3, i.getStato());
			ps.setInt(4, i.getIdPersona());
			ps.setInt(5, i.getIdCorso());


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
