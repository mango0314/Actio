package it.actio.beans.corso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.actio.beans.corso.Corso;
import it.actio.dto.CorsoConAttivitaDTO;
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
	
	public CorsoConAttivitaDTO getCorso_conAttivita(int idCorso) {
		String query = "SELECT c.id, c.nome as nome_corso, c.descrizione, c.capienza, a.nome as nome_attivita, COUNT(i.idPersona) AS iscritti,"
				+ "(c.capienza - COUNT(i.idPersona)) AS posti_rimasti FROM Corso c "
				+ "LEFT JOIN Fornito f on c.id = f.idCorso "
				+ "LEFT JOIN Attivita a on f.idAttivita = a.id "
				+ "LEFT JOIN Iscrizione i ON c.id = i.idCorso "
				+ "WHERE c.id = ? "
				+ "GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome;";

		 CorsoConAttivitaDTO res = new CorsoConAttivitaDTO();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idCorso);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                res.setId(rs.getInt("id"));
                res.setNomeCorso(rs.getString("nome_corso"));
                res.setDescrizione(rs.getString("descrizione"));
                res.setCapienza(rs.getInt("capienza"));
                res.setNomeAttivita(rs.getString("nome_attivita"));
                res.setPostiRimasti(rs.getInt("posti_rimasti"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public List<CorsoConAttivitaDTO> getPosti_Rimasti(int idPersona) {

	    String query = "SELECT c.id, c.nome as nome_corso, c.descrizione, c.capienza, " +
	                   "a.nome as nome_attivita, COUNT(i.idPersona) AS iscritti, " +
	                   "(c.capienza - COUNT(i.idPersona)) AS posti_rimasti " +
	                   "FROM Corso c " +
	                   "LEFT JOIN Fornito f ON c.id = f.idCorso " +
	                   "LEFT JOIN Attivita a ON f.idAttivita = a.id " +
	                   "LEFT JOIN Iscrizione i ON c.id = i.idCorso AND i.stato = 2 " +
	                   "WHERE NOT EXISTS ( " +
	                   "    SELECT 1 FROM Iscrizione ix " +
	                   "    WHERE ix.idCorso = c.id " +
	                   "      AND ix.idPersona = ? " +
	                   "      AND ix.stato = 2 " +
	                   ") " +
	                   "GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome";

	    List<CorsoConAttivitaDTO> res = new ArrayList<>();

	    conn = DBManager.startConnection();

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setInt(1, idPersona);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            CorsoConAttivitaDTO dto = new CorsoConAttivitaDTO();
	            dto.setId(rs.getInt("id"));
	            dto.setNomeCorso(rs.getString("nome_corso"));
	            dto.setDescrizione(rs.getString("descrizione"));
	            dto.setCapienza(rs.getInt("capienza"));
	            dto.setNomeAttivita(rs.getString("nome_attivita"));
	            dto.setPostiRimasti(rs.getInt("posti_rimasti"));
	            res.add(dto);
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

	public List<String> getNomi() {
		String query = "SELECT nome FROM CORSO";

		List<String> res = new ArrayList<String>();
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

	public List<Corso> getAll() {
		String query = "SELECT * FROM CORSO order by id";

		List<Corso> res = new ArrayList<Corso>();
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
	
	public List<Corso> getAll_seguiti(int idPersona) {
		String query = "SELECT id, nome,capienza, descrizione, dataInizio, dataFine, stato FROM Corso c join iscrizione i on i.idCorso = c.id where idPersona = ? order by id";

		List<Corso> res = new ArrayList<Corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idPersona);
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

	public List<Corso> getAll_compl(int tipo) {
		String query = "SELECT * FROM ATTIVITA where tipo = ? order by id";

		List<Corso> res = new ArrayList<Corso>();
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

	    public List<CorsoConAttivitaDTO> cercaCorsiConPostiRimasti(String keyword, int idPersona) {
	        List<CorsoConAttivitaDTO> risultati = new ArrayList<>();

	        if (keyword == null || keyword.trim().isEmpty()) {
	            return risultati; // ritorna lista vuota se keyword nulla o vuota
	        }

	        String[] keywords = keyword.trim().split("\\s+");

	        StringBuilder queryBuilder = new StringBuilder();
	        queryBuilder.append(
	        	    "SELECT c.id, c.nome as nome_corso, c.descrizione, c.capienza, a.nome AS nome_attivita, " +
	        	    "COUNT(i.idPersona) AS iscritti, " +
	        	    "(c.capienza - COUNT(i.idPersona)) AS posti_rimasti " +
	        	    "FROM Corso c " +
	        	    "LEFT JOIN Fornito f ON c.id = f.idCorso " +
	        	    "LEFT JOIN Attivita a ON f.idAttivita = a.id " +
	        	    "LEFT JOIN Iscrizione i ON c.id = i.idCorso AND i.stato = 2 " +
	        	    "WHERE NOT EXISTS ( " +
	        	    "    SELECT 1 FROM Iscrizione ix " +
	        	    "    WHERE ix.idCorso = c.id AND ix.idPersona = ? AND ix.stato = 2 " +
	        	    ") AND "
	        	);

	        // Costruzione dinamica della WHERE con AND per ogni parola
	        for (int i = 0; i < keywords.length; i++) {
	            if (i > 0) {
	                queryBuilder.append(" AND ");
	            }
	            queryBuilder.append("(c.nome LIKE ? OR a.nome LIKE ?)");
	        }

	        queryBuilder.append(" GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome");

	        String query = queryBuilder.toString();

	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            conn = DBManager.startConnection();
	            ps = conn.prepareStatement(query);
	            
	            

	            int paramIndex = 1;
	            
	            ps.setInt(paramIndex++, idPersona);
	            
	            
	            for (String kw : keywords) {
	                String pattern = "%" + kw + "%";
	                ps.setString(paramIndex++, pattern);
	                ps.setString(paramIndex++, pattern);
	            }

	            rs = ps.executeQuery();

	            while (rs.next()) {
	            	CorsoConAttivitaDTO dto = new CorsoConAttivitaDTO();
	                dto.setId(rs.getInt("id"));
	                dto.setNomeCorso(rs.getString("nome_corso"));
	                dto.setDescrizione(rs.getString("descrizione"));
	                dto.setCapienza(rs.getInt("capienza"));
	                dto.setNomeAttivita(rs.getString("nome_attivita"));
	                dto.setPostiRimasti(rs.getInt("posti_rimasti"));
	                risultati.add(dto);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Meglio loggare o rilanciare eccezioni personalizzate
	        } finally {
	            DBManager.closeConnection();
	        }

	        return risultati;
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
