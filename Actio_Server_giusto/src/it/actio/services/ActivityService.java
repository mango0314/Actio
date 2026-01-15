package it.actio.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import it.actio.beans.account.AccountDAO;
import it.actio.beans.attivita.Attivita;
import it.actio.beans.attivita.AttivitaDAO;
import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import it.actio.beans.fornito.FornitoDAO;
import it.actio.beans.iscrizione.Iscrizione;
import it.actio.beans.iscrizione.IscrizioneDAO;
import it.actio.beans.orario_corso.Orario_corso;
import it.actio.beans.orario_corso.Orario_corsoDAO;
import it.actio.beans.persona.PersonaDAO;
import it.actio.dto.AttivitaDTO;
import it.actio.dto.CorsoConAttivitaDTO;
import it.actio.dto.IscrittiConDataFineDTO;
import it.actio.dto.Iscrizione_ConNomeCorso_NomeAttivitaDTO;
import it.actio.dto.OrarioCorsoDTO;
import it.actio.dto.UtenteDTO;
import it.actio.utils.DBManager;
import it.actio.utils.Utility;

public class ActivityService {
	
    CorsoDAO corsoDAO = new CorsoDAO();
    AttivitaDAO attivitaDAO = new AttivitaDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();
    AccountDAO accountDAO = new AccountDAO();
    FornitoDAO fornitoDAO = new FornitoDAO();
    Orario_corsoDAO orario_corsoDAO = new Orario_corsoDAO();

	public CorsoConAttivitaDTO[] getCorsiForniti(int idAttivita) {
        List<CorsoConAttivitaDTO> list = corsoDAO.getCorsiByAttivitaConPosti(idAttivita);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
	
	public OrarioCorsoDTO[] getOrari(int idCorso){
    	List<Orario_corso> list = orario_corsoDAO.getAllbyidCorso(idCorso);
    	
    	List<OrarioCorsoDTO> dtoList = new ArrayList<>();
    	
    	for (Orario_corso oc : list) {
            OrarioCorsoDTO dto = new OrarioCorsoDTO();
            dto.setId(oc.getId());
            dto.setIdCorso(oc.getIdCorso());
            dto.setGiornoSettimana(oc.getGiorno_settimana());
            dto.setGiornoSettimanaLabel(Utility.mapGiorno(oc.getGiorno_settimana()));
            dto.setOrarioInizio(Utility.formatTime(oc.getOrarioInizio()));
            dto.setOrarioFine(Utility.formatTime(oc.getOrarioFine()));
            dtoList.add(dto);
        }
    	
    	return dtoList.toArray(new OrarioCorsoDTO[0]);
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
	
	public Corso getCorso(int idCorso){
		return corsoDAO.getbyId(idCorso);
	}
	
	
	public boolean aggiungiCorso(Corso corso, int idAttivita){
		Connection conn = null;
        try {
            // 1. Check email
            System.out.println("1. Check email esistente...");
            boolean corsoEsiste = corsoDAO.EsisteCorso_byAttivita(corso.getNome(), idAttivita);
            System.out.println("   → EsisteCorso_byAttivita: " + corsoEsiste);
            if (corsoEsiste) {
                System.out.println("=== CORSO GIA ESISTENTE ===");
                return false;
            }

            // 2. Inizia transazione
            conn = DBManager.startConnection();
            System.out.println("2. Connection OK: " + conn);
            conn.setAutoCommit(false);

            // 3. Salva PERSONA
            System.out.println("3. Salva CORSO...");
            Integer idCorso = corsoDAO.salvaCorsoERitornaId(conn, corso);
            System.out.println("   → idCorso generato: " + idCorso);
            if (idCorso == null) {
                System.out.println("=== CORSO SALVATAGGIO FALLITO ===");
                if (conn != null) conn.rollback();
                return false;
            }


            // 5. Salva FORNITO
            System.out.println("5. Salva FORNITO...");
            boolean okFornito = fornitoDAO.salvaNuovoCorso_Attivita(conn, idCorso, idAttivita);
            System.out.println("   → salvaNuovoCorso_Attivita: " + okFornito);
            if (!okFornito) {
                System.out.println("=== FORNITO SALVATAGGIO FALLITO ===");
                conn.rollback();
                return false;
            }

            // 6. Commit
            conn.commit();
            System.out.println("=== SALVATAGGIO OK! ===");
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
	
	public boolean EliminaCorso(int idCorso, int idAttivita){
		try{
			
			Corso corso = corsoDAO.getbyId(idCorso);
			if(!fornitoDAO.esiste(idAttivita, idCorso)){
				System.out.println("CORSO NON ESISTENTE IN QUESTA ATTIVITA");
				return false;
			}
		
            
            //eliminazione corso
            boolean eliminazioneCorso = corsoDAO.elimina(corso);
            if(eliminazioneCorso == false){
            	System.out.println("ELIMINAZIONE CORSO FALLITA");
                return false;
            }
            
          
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean modificaCorso(Corso corso, int idAttivita){
		boolean modificato = false;
        try {
            // 1. Check email
            System.out.println("1. Check corso esistente...");
            boolean corsoEsiste = corsoDAO.EsisteCorso_byAttivita(corso.getNome(), idAttivita);
            System.out.println("   → EsisteCorso_byAttivita: " + corsoEsiste);
            if (!corsoEsiste) {
                System.out.println("=== CORSO INESISTENTE ===");
                return false;
            }


            // 3. Salva PERSONA
            System.out.println("3. modifica CORSO...");
            modificato = corsoDAO.modifica(corso);
            if (modificato == false) {
                System.out.println("=== CORSO MODIFICA FALLITA ===");
                return false;
            }


           

        } catch (Exception e) {
            System.out.println("=== WS EXCEPTION: " + e.getMessage());
            e.printStackTrace();  
        }
		return modificato; 
		
	}


}
