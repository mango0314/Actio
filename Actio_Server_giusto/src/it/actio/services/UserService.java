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
import it.actio.utils.Utility;
 

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




    
    
}
