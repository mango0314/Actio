package it.actio.services;

import java.sql.Connection;
import java.util.List;

import javax.security.auth.login.AccountException;

import it.actio.beans.account.AccountDAO;
import it.actio.beans.attivita.Attivita;
import it.actio.beans.attivita.AttivitaDAO;
import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import it.actio.beans.iscrizione.Iscrizione;
import it.actio.beans.iscrizione.IscrizioneDAO;
import it.actio.beans.persona.PersonaDAO;
import it.actio.dto.AttivitaDTO;
import it.actio.dto.CorsoConAttivitaDTO;
import it.actio.dto.IscrittiConDataFineDTO;
import it.actio.dto.Iscrizione_ConNomeCorso_NomeAttivitaDTO;
import it.actio.dto.UtenteDTO;
import it.actio.utils.DBManager;

public class ActivityService {
	
    CorsoDAO corsoDAO = new CorsoDAO();
    AttivitaDAO attivitaDAO = new AttivitaDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();
    AccountDAO accountDAO = new AccountDAO();

	public CorsoConAttivitaDTO[] getCorsiForniti(int idAttivita) {
        List<CorsoConAttivitaDTO> list = corsoDAO.getCorsiByAttivitaConPosti(idAttivita);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
	
	public Attivita getAttivita(int idAttivita){
    	return attivitaDAO.getbyId(idAttivita);
		}
	
	public IscrittiConDataFineDTO[] getIscritti_conDatafine(int idCorso){
		List<IscrittiConDataFineDTO> list = personaDAO.getIscrittiConDataFine(idCorso);
		return list.toArray(new IscrittiConDataFineDTO[0]);
	}
	
	public Iscrizione_ConNomeCorso_NomeAttivitaDTO[] get_Iscrizioni_ConNomePersona_Attivita(int idAttivita){
		List<Iscrizione_ConNomeCorso_NomeAttivitaDTO> list = iscrizioneDAO.getAll_Richieste_byAttivita(idAttivita);
		return list.toArray(new Iscrizione_ConNomeCorso_NomeAttivitaDTO[0]);
	}
	
	public boolean AccettaIscrizione(int idIscrizione){
		
		Iscrizione iscrizione = iscrizioneDAO.get(idIscrizione);
		int posti_rimasti = corsoDAO.getNumeroPostiRimasti(iscrizione.getIdCorso());
		if(posti_rimasti > 0){
			return iscrizioneDAO.AccettaIscrizione(idIscrizione);
		}
		else{
			System.out.println("Corso al completo");
			return false;
		}
		
	}
	
	public boolean RegistrazioneAttivita(AttivitaDTO attivita) {
        
        
        Connection conn = null;
        try {
            // 1. Check email
            System.out.println("1. Check email esistente...");
            boolean emailEsiste = accountDAO.EsistebyEmail_Ruolo(attivita.getEmail(), attivita.getRuolo());
            System.out.println("   → EsistebyEmail_Ruolo: " + emailEsiste);
            if (emailEsiste) {
                System.out.println("=== EMAIL GIA ESISTENTE ===");
                return false;
            }

            // 2. Inizia transazione
            conn = DBManager.startConnection();
            System.out.println("2. Connection OK: " + conn);
            conn.setAutoCommit(false);

            // 3. Salva PERSONA
            System.out.println("3. Salva ATTIVITA...");
            Integer idAttivita = attivitaDAO.salvaAttivitaERitornaId(conn, attivita);
            System.out.println("   → idAttivita generato: " + idAttivita);
            if (idAttivita == null) {
                System.out.println("=== ATTIVITA SALVATAGGIO FALLITO ===");
                if (conn != null) conn.rollback();
                return false;
            }

            // 4. Set idPersona su utente
            attivita.setIdAttivita(idAttivita);
            System.out.println("4. Set idAttivita=" + idAttivita + " su attivita");

            // 5. Salva ACCOUNT
            System.out.println("5. Salva ACCOUNT...");
            boolean okAccount = accountDAO.salvaAccountAttivita(conn, attivita);
            System.out.println("   → salvaAccountAttivita: " + okAccount);
            if (!okAccount) {
                System.out.println("=== ACCOUNT SALVATAGGIO FALLITO ===");
                conn.rollback();
                return false;
            }

            // 6. Commit
            conn.commit();
            System.out.println("=== REGISTRAZIONE OK! ===");
            return true;

        } catch (Exception e) {
            System.out.println("=== WS EXCEPTION: " + e.getMessage());
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (Exception ignored) {}
            return false;
        } finally {
            DBManager.closeConnection();
            System.out.println("=== WS DEBUG END ===");
        }
    }


}
