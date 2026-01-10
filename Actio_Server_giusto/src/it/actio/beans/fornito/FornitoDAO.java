package it.actio.beans.fornito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.actio.dto.AttivitaDTO;
import it.actio.utils.DBManager;


public class FornitoDAO {

	private static Connection conn = null;
	
	public Fornito get(int idAttivita, int idCorso) {
		String query = "SELECT * FROM fornito WHERE idAttivita =? and idCorso = ?";

		Fornito res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  idAttivita);
			ps.setInt(2, idCorso);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToFornito(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}



	public List<Integer> getAllAttivita() {
		String query = "SELECT idPersona FROM iscrizione";

		List<Integer> res = new ArrayList<Integer>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_attivita = rs.getInt("idAttivita");
				res.add(id_attivita);
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
	
	public List<Integer> getAllCorsi_byidAttivita(int idAttivita) {
		String query = "SELECT idCorso FROM fornito where idAttivita = ?";

		List<Integer> res = new ArrayList<Integer>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idAttivita);
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


	private Fornito recordToFornito(ResultSet rs) throws SQLException {
		Fornito fornito = new Fornito();
		fornito.setIdAttivita(rs.getInt("idAttività"));
		fornito.setIdCorso(rs.getInt("idCorso"));
		return fornito;
	}

	public List<Fornito> getAll() {
		String query = "SELECT * FROM fornito order by idAttivita";

		List<Fornito> res = new ArrayList<Fornito>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornito fornito = recordToFornito(rs);
				res.add(fornito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salvaNuovoCorso_Attivita(Connection conn, int idCorso, int idAttivita) throws SQLException {
	    String query = "INSERT INTO Fornito (idCorso, idAttivita) VALUES (?, ?)";
	    boolean res = false;
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, idCorso);           
	        ps.setInt(2, idAttivita);

	        int tmp = ps.executeUpdate();
			if (tmp == 1)
				res = true;
	    }
	    return res;
	}
	
	

	public boolean salva(Fornito fornito) {
		String query = "INSERT INTO fornito VALUES (?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {

			ps = conn.prepareStatement(query);

			ps.setInt(1, fornito.getIdAttivita());
			ps.setInt(2, fornito.getIdCorso());			

			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean elimina(Fornito fornito) {
		String query = "DELETE FROM fornito WHERE idAttivita = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, fornito.getIdAttivita());
			ps.setInt(2, fornito.getIdCorso());

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

	public boolean modifica(Fornito i) { // INUTILE
		String query = "UPDATE fornito SET WHERE idAttivita = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, i.getIdAttivita());
			ps.setInt(2, i.getIdCorso());


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
