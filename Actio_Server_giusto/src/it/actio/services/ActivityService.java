package it.actio.services;

import java.util.List;

import it.actio.beans.attivita.Attivita;
import it.actio.beans.attivita.AttivitaDAO;
import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import it.actio.beans.iscrizione.Iscrizione;
import it.actio.beans.iscrizione.IscrizioneDAO;
import it.actio.beans.persona.PersonaDAO;
import it.actio.dto.CorsoConAttivitaDTO;
import it.actio.dto.IscrittiConDataFineDTO;
import it.actio.dto.Iscrizione_ConNomeCorso_NomeAttivitaDTO;

public class ActivityService {
	
    CorsoDAO corsoDAO = new CorsoDAO();
    AttivitaDAO attivitaDAO = new AttivitaDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();

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

}
