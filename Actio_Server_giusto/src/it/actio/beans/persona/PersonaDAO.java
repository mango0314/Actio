package it.actio.beans.persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.actio.dto.IscrittiConDataFineDTO;
import it.actio.utils.DBManager;


public class PersonaDAO {

	private static Connection conn = null;

	public Persona get(Persona persona) {
		String query = "SELECT * FROM PERSONA WHERE id =?";

		Persona res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, persona.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToPersona(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Persona getbyId(int id) {
		String query = "SELECT * FROM PERSONA WHERE id =?";

		Persona res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToPersona(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public List<String> getNomi() {
		String query = "SELECT nome FROM PERSONA";

		List<String> res = new ArrayList<String>();
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
		String query = "SELECT id FROM PERSONA order by id desc limit 1";

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

	private Persona recordToPersona(ResultSet rs) throws SQLException {
		Persona persona = new Persona();
		persona.setId(rs.getInt("id"));
		persona.setNome(rs.getString("nome"));
		persona.setCognome(rs.getString("cognome"));
		persona.setData_di_nascita(rs.getDate("data_di_nascita"));
		persona.setAltezza(rs.getInt("altezza"));
		persona.setPeso(rs.getDouble("peso"));
		persona.setCertificatolink(rs.getString("certificatolink"));
		persona.setFoto(rs.getString("logo"));
		
		return persona;
	}

	public List<Persona> getAll() {
		String query = "SELECT * FROM PERSONA order by id";

		List<Persona> res = new ArrayList<Persona>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Persona persona = recordToPersona(rs);
				res.add(persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	
	public List<IscrittiConDataFineDTO> getIscrittiConDataFine(int idCorso) {

	    List<IscrittiConDataFineDTO> list = new ArrayList<>();

	    String sql = "SELECT p.id AS idPersona, p.nome, p.cognome, p.data_di_nascita, p.altezza, p.peso, i.dataFine "
	               + "FROM iscrizione i "
	               + "JOIN persona p ON i.idPersona = p.id "
	               + "WHERE i.idCorso = ?";
	    conn = DBManager.startConnection();
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, idCorso);

	        try (ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                IscrittiConDataFineDTO dto = new IscrittiConDataFineDTO();

	                dto.setId(rs.getInt("idPersona"));
	                dto.setNome(rs.getString("nome"));
	                dto.setCognome(rs.getString("cognome"));
	                dto.setData_di_nascita(rs.getDate("data_di_nascita"));
	                dto.setAltezza(rs.getInt("altezza"));
	                dto.setPeso(rs.getDouble("peso"));
	                dto.setDataFineIscrizione(rs.getDate("dataFine"));

	                list.add(dto);
	            }
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }
		DBManager.closeConnection();

	    return list;
	}

	

	
	

	public List<Persona> getAllbyCognome(String cognome) {
		String query = "SELECT * FROM PERSONA where cognome = ? order by id";

		List<Persona> res = new ArrayList<Persona>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, cognome);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Persona persona = recordToPersona(rs);
				res.add(persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(Persona persona) {
		String query = "INSERT INTO PERSONA VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
//			ps = conn.prepareStatement(query);

			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, persona.getId());
			ps.setString(2, persona.getNome());
			ps.setString(3, persona.getCognome());
			java.sql.Date data = new java.sql.Date(persona.getData_di_nascita().getTime());
			ps.setDate(4, data);
			ps.setInt(5, persona.getAltezza());
			ps.setDouble(6, persona.getPeso());
			ps.setString(7, persona.getCertificatolink());
			ps.setString(8, persona.getFoto());

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

	public boolean elimina(Persona persona) {
		String query = "DELETE FROM Persona WHERE id = ?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, persona.getId());

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

	public boolean modifica(Persona p) {
		String query = "UPDATE Persona SET nome=?, cognome=?, data_di_nascita=?, altezza=?, peso=?, certificatolink=?, foto=? WHERE id=?";
		boolean esito = false;

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCognome());
			ps.setDate(3, new java.sql.Date(p.getData_di_nascita().getTime()));
			ps.setInt(4, p.getAltezza());
			ps.setDouble(5, p.getPeso());
			ps.setString(6, p.getCertificatolink());
			ps.setString(7, p.getFoto());
			ps.setInt(8, p.getId());



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
