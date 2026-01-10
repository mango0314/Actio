package it.actio.beans.corso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.actio.beans.corso.Corso;
import it.actio.dto.AttivitaDTO;
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
	
	public int getCapienzaByIscrizione(int idIscrizione) {

	    String query =
	        "SELECT c.capienza " +
	        "FROM corso c " +
	        "JOIN iscrizione i ON c.id = i.idCorso " +
	        "WHERE i.id = ?";

	    int capienza = 0;

	    try (Connection conn = DBManager.startConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, idIscrizione);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                capienza = rs.getInt("capienza");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    DBManager.closeConnection();
	    return capienza;
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
	
	public int getNumeroPostiRimasti(int idCorso) {

	    String query =
	        "SELECT c.capienza - COUNT(i.idPersona) AS posti_rimasti " +
	        "FROM corso c " +
	        "LEFT JOIN iscrizione i ON c.id = i.idCorso AND i.stato = 2 " +
	        "WHERE c.id = ? " +
	        "GROUP BY c.capienza";

	    int postiRimasti = 0;

	    try (Connection conn = DBManager.startConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, idCorso);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                postiRimasti = rs.getInt("posti_rimasti");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    DBManager.closeConnection();
	    return postiRimasti;
	}

	
	public List<CorsoConAttivitaDTO> getPosti_Rimasti(int idPersona) {

	    String query =
	        "SELECT c.id, c.nome AS nome_corso, c.descrizione, c.capienza, " +
	        "a.nome AS nome_attivita, " +
	        "COUNT(i2.idPersona) AS iscritti, " +
	        "(c.capienza - COUNT(i2.idPersona)) AS posti_rimasti, " +

	        // Recupera lo stato dell’utente (NULL se non ha fatto richiesta)
	        "ix.stato AS stato_richiesta " +

	        "FROM Corso c " +
	        "LEFT JOIN Fornito f ON c.id = f.idCorso " +
	        "LEFT JOIN Attivita a ON f.idAttivita = a.id " +
	        "LEFT JOIN Iscrizione i2 ON c.id = i2.idCorso AND i2.stato = 2 " + // iscritti effettivi
	        "LEFT JOIN Iscrizione ix ON c.id = ix.idCorso AND ix.idPersona = ? " + // stato dell’utente
	        "WHERE NOT EXISTS ( " +
	        "    SELECT 1 FROM Iscrizione ix2 " +
	        "    WHERE ix2.idCorso = c.id " +
	        "      AND ix2.idPersona = ? " +
	        "      AND ix2.stato = 2 " +
	        ") " +
	        "GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome, ix.stato";

	    List<CorsoConAttivitaDTO> res = new ArrayList<>();

	    try (Connection conn = DBManager.startConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, idPersona);  // per LEFT JOIN ix
	        ps.setInt(2, idPersona);  // per NOT EXISTS

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            CorsoConAttivitaDTO dto = new CorsoConAttivitaDTO();

	            dto.setId(rs.getInt("id"));
	            dto.setNomeCorso(rs.getString("nome_corso"));
	            dto.setDescrizione(rs.getString("descrizione"));
	            dto.setCapienza(rs.getInt("capienza"));
	            dto.setNomeAttivita(rs.getString("nome_attivita"));
	            dto.setPostiRimasti(rs.getInt("posti_rimasti"));

	            // mappiamo stato_richiesta: può essere NULL
	            Integer stato = (Integer) rs.getObject("stato_richiesta");
	            if (stato == null) {
	            	   // Non dovrebbe mai succedere, ma se succede metto -1 (nessuna richiesta)
	            	   dto.setStato(-1);
	            	} else {
	            	   dto.setStato(stato);
	            	}

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
	

	public boolean EsisteCorso_byAttivita(String nomeCorso, int idAttivita) {
		String query = "SELECT * FROM CORSO c join fornito f on f.idCorso = c.id WHERE c.nome = ? and idAttivita = ?";

		boolean res = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, nomeCorso);
			ps.setInt(2, idAttivita);
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
	
	public List<CorsoConAttivitaDTO> getCorsiByAttivitaConPosti(int idAttivita) {
	    List<CorsoConAttivitaDTO> corsi = new ArrayList<>();

	    String query = "SELECT c.id, c.nome AS nome_corso, c.descrizione, c.capienza, a.nome AS nome_attivita, " +
	                   "COUNT(i.idPersona) AS iscritti, " +
	                   "(c.capienza - COUNT(i.idPersona)) AS posti_rimasti " +
	                   "FROM Corso c " +
	                   "JOIN Fornito f ON c.id = f.idCorso " +
	                   "JOIN Attivita a ON f.idAttivita = a.id " +
	                   "LEFT JOIN Iscrizione i ON c.id = i.idCorso AND i.stato = 2 " +
	                   "WHERE a.id = ? " +
	                   "GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome";

	    try (Connection conn = DBManager.startConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setInt(1, idAttivita);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                CorsoConAttivitaDTO dto = new CorsoConAttivitaDTO();
	                dto.setId(rs.getInt("id"));
	                dto.setNomeCorso(rs.getString("nome_corso"));
	                dto.setDescrizione(rs.getString("descrizione"));
	                dto.setCapienza(rs.getInt("capienza"));
	                dto.setNomeAttivita(rs.getString("nome_attivita"));
	                dto.setPostiRimasti(rs.getInt("posti_rimasti"));
	                corsi.add(dto);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Meglio loggare con un logger serio in produzione
	    }
		DBManager.closeConnection();

	    return corsi;
	}

	
	public List<Corso> getAll_seguiti(int idPersona) {
		String query = "SELECT c.id, c.nome, c.capienza, c.descrizione, i.dataInizio, i.dataFine, i.stato FROM Corso c join iscrizione i on i.idCorso = c.id where idPersona = ? and i.stato = 2 order by id";

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
	        return risultati;
	    }

	    String[] keywords = keyword.trim().split("\\s+");

	    StringBuilder queryBuilder = new StringBuilder();
	    queryBuilder.append(
	        "SELECT c.id, c.nome AS nome_corso, c.descrizione, c.capienza, " +
	        "a.nome AS nome_attivita, " +
	        "COUNT(i2.idPersona) AS iscritti, " +
	        "(c.capienza - COUNT(i2.idPersona)) AS posti_rimasti, " +

	        // QUI AGGIUNGI LO STATO DELL'UTENTE (NULL se nessuna iscrizione)
	        "ix.stato AS stato_richiesta " +

	        "FROM Corso c " +
	        "LEFT JOIN Fornito f ON c.id = f.idCorso " +
	        "LEFT JOIN Attivita a ON f.idAttivita = a.id " +
	        "LEFT JOIN Iscrizione i2 ON c.id = i2.idCorso AND i2.stato = 2 " + // conteggio iscritti approvati
	        "LEFT JOIN Iscrizione ix ON c.id = ix.idCorso AND ix.idPersona = ? " + // stato dell’utente
	        "WHERE NOT EXISTS ( " +
	        "    SELECT 1 FROM Iscrizione ix2 " +
	        "    WHERE ix2.idCorso = c.id AND ix2.idPersona = ? AND ix2.stato = 2 " +
	        ") AND "
	    );

	    for (int i = 0; i < keywords.length; i++) {
	        if (i > 0) queryBuilder.append(" AND ");
	        queryBuilder.append("(c.nome LIKE ? OR a.nome LIKE ?)");
	    }

	    queryBuilder.append(" GROUP BY c.id, c.nome, c.descrizione, c.capienza, a.nome, ix.stato");

	    String query = queryBuilder.toString();

	    try (
	        Connection conn = DBManager.startConnection();
	        PreparedStatement ps = conn.prepareStatement(query)
	    ) {

	        int index = 1;
	        ps.setInt(index++, idPersona); // per la LEFT JOIN ix
	        ps.setInt(index++, idPersona); // per la NOT EXISTS

	        for (String kw : keywords) {
	            String pattern = "%" + kw + "%";
	            ps.setString(index++, pattern);
	            ps.setString(index++, pattern);
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                CorsoConAttivitaDTO dto = new CorsoConAttivitaDTO();

	                dto.setId(rs.getInt("id"));
	                dto.setNomeCorso(rs.getString("nome_corso"));
	                dto.setDescrizione(rs.getString("descrizione"));
	                dto.setCapienza(rs.getInt("capienza"));
	                dto.setNomeAttivita(rs.getString("nome_attivita"));
	                dto.setPostiRimasti(rs.getInt("posti_rimasti"));

	                // qui imposti lo stato dell’utente (può essere NULL → no richiesta)
	                dto.setStato(rs.getObject("stato_richiesta") == null
	                    ? 0  // 0 = nessuna richiesta
	                    : rs.getInt("stato_richiesta"));

	                risultati.add(dto);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return risultati;
	}


	
	public Integer salvaCorsoERitornaId(Connection conn, Corso corso) throws SQLException {

	    String query = "INSERT INTO Corso (id, nome, foto, capienza, descrizione) " +
	                   "VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setNull(1, java.sql.Types.INTEGER); // id AUTO_INCREMENT
	        ps.setString(2, corso.getNome());
	        ps.setString(3, corso.getFoto());
	        ps.setInt(4, corso.getCapienza());
	        ps.setString(5, corso.getDescrizione()); 

	        int tmp = ps.executeUpdate();
	        if (tmp != 1) {
	            return null;
	        }

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (!rs.next()) {
	                return null;
	            }
	            return rs.getInt(1); 
	        }
	    }
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
