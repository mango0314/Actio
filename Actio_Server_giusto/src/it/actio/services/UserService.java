package it.actio.services;

import it.actio.beans.account.Account;
import it.actio.beans.account.AccountDAO;
import it.actio.beans.attivita.Attivita;
import it.actio.beans.corso.Corso;
import it.actio.beans.orario_corso.Orario_corso;
import it.actio.beans.orario_corso.Orario_corsoDAO;
import it.actio.beans.persona.Persona;
import it.actio.beans.persona.PersonaDAO;
import it.actio.beans.iscrizione.IscrizioneDAO;
import it.actio.beans.corso.CorsoDAO;
import it.actio.dto.CorsoConAttivitaDTO;
import it.actio.dto.OrarioCorsoDTO;
import it.actio.dto.UtenteDTO;
import it.actio.utils.DBManager;
import it.actio.utils.Utility;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    
    CorsoDAO corsoDAO = new CorsoDAO();
    Orario_corsoDAO orario_corsoDAO = new Orario_corsoDAO();
    AccountDAO accountDAO = new AccountDAO();
    IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    
    
   
    
    // Metodo esposto al client SOAP: ritorna array
    public Corso[] getCorsiSeguiti(int idPersona) {
        List<Corso> list = corsoDAO.getAll_seguiti(idPersona);
        return list.toArray(new Corso[0]);
    }
    
    
    // Metodo esposto al client SOAP: ritorna array
    public CorsoConAttivitaDTO[] getCorsi_conPostiRimasti(int idPersona) {
        List<CorsoConAttivitaDTO> list = corsoDAO.getPosti_Rimasti(idPersona);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
    
    public CorsoConAttivitaDTO[] cercaCorsi_conPostiRimasti(String keywords, int idPersona) {
        List<CorsoConAttivitaDTO> list = corsoDAO.cercaCorsiConPostiRimasti(keywords, idPersona);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
    
    public CorsoConAttivitaDTO getCorso_conAttivita(int idCorso) {
    	return corsoDAO.getCorso_conAttivita(idCorso);
    	
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
    
    
    
    public Account getAccount(String email, String password){
    	return accountDAO.getAccount(email, password);
    }
    
    public boolean InvioRichiestaIscrizione(int idPersona, int idCorso){
    	return iscrizioneDAO.salvaRichiestaIscrizione(idPersona, idCorso);
    }
    
    public static boolean ValidaCredenziali(String email, String password){
        if (!Utility.EMAIL_PATTERN.matcher(email).matches() ||
            !Utility.PASSWORD_PATTERN.matcher(password).matches()) {
            return false;
        }
        return true;
    }
    
    public Persona getPersona(int idPersona){
    	return personaDAO.getbyId(idPersona);
    }
    
    public boolean RegistrazioneUtente(UtenteDTO utente) {
        System.out.println("=== WS DEBUG START ===");
        System.out.println("Email: " + utente.getEmail());
        System.out.println("Nome: " + utente.getNome());
        System.out.println("Cognome: " + utente.getCognome());
        System.out.println("Ruolo: " + utente.getRuolo());
        System.out.println("Foto: " + utente.getFotoPath());
        
        Connection conn = null;
        try {
            // 1. Check email
            System.out.println("1. Check email esistente...");
            boolean emailEsiste = accountDAO.EsistebyEmail_Ruolo(utente.getEmail(), utente.getRuolo());
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
            System.out.println("3. Salva PERSONA...");
            Integer idPersona = personaDAO.salvaPersonaERitornaId(conn, utente);
            System.out.println("   → idPersona generato: " + idPersona);
            if (idPersona == null) {
                System.out.println("=== PERSONA SALVATAGGIO FALLITO ===");
                if (conn != null) conn.rollback();
                return false;
            }

            // 4. Set idPersona su utente
            utente.setIdPersona(idPersona);
            System.out.println("4. Set idPersona=" + idPersona + " su utente");

            // 5. Salva ACCOUNT
            System.out.println("5. Salva ACCOUNT...");
            boolean okAccount = accountDAO.salvaAccountUtente(conn, utente);
            System.out.println("   → salvaAccountUtente: " + okAccount);
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
