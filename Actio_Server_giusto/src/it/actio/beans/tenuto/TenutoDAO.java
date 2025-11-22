package it.actio.beans.tenuto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.actio.utils.DBManager;


public class TenutoDAO {

	private static Connection conn = null;
	
	public Tenuto get(int idPersona, int idCorso) {
		String query = "SELECT * FROM tenuto WHERE idPersona =? and idCorso = ?";

		Tenuto res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  idPersona);
			ps.setInt(2, idCorso);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToTenuto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}



	public Vector<Integer> getAllPersone() {
		String query = "SELECT idPersona FROM tenuto";

		Vector<Integer> res = new Vector<Integer>();
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
	
	public Vector<Integer> getAllCorsi() {
		String query = "SELECT idCorso FROM tenuto";

		Vector<Integer> res = new Vector<Integer>();
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
	
	public Vector<Integer> getAllCorsi_byidPersona(int idPersona) {
		String query = "SELECT idCorso FROM tenuto where idPersona = ?";

		Vector<Integer> res = new Vector<Integer>();
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


	private Tenuto recordToTenuto(ResultSet rs) throws SQLException {
		Tenuto tenuto = new Tenuto();
		tenuto.setIdPersona(rs.getInt("idPersona"));
		tenuto.setIdCorso(rs.getInt("idCorso"));
		return tenuto;
	}

	public Vector<Tenuto> getAll() {
		String query = "SELECT * FROM tenuto order by idPersona";

		Vector<Tenuto> res = new Vector<Tenuto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Tenuto tenuto = recordToTenuto(rs);
				res.add(tenuto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}


	public boolean salva(Tenuto tenuto) {
		String query = "INSERT INTO iscrizione VALUES (?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {

			ps = conn.prepareStatement(query);

			ps.setInt(1, tenuto.getIdPersona());
			ps.setInt(2, tenuto.getIdCorso());

			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean elimina(Tenuto tenuto) {
		String query = "DELETE FROM tenuto WHERE idPersona = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, tenuto.getIdPersona());
			ps.setInt(2, tenuto.getIdCorso());

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

	public boolean modifica(Tenuto i) { // INUTILE ORA
		String query = "UPDATE tenuto SET  WHERE idPersona = ? and idCorso = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			
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
