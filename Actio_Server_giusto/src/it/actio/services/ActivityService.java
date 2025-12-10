package it.actio.services;

import java.util.List;

import it.actio.beans.attivita.Attivita;
import it.actio.beans.attivita.AttivitaDAO;
import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import it.actio.dto.CorsoConAttivitaDTO;

public class ActivityService {
	
    CorsoDAO corsoDAO = new CorsoDAO();
    AttivitaDAO attivitaDAO = new AttivitaDAO();

	public CorsoConAttivitaDTO[] getCorsiForniti(int idAttivita) {
        List<CorsoConAttivitaDTO> list = corsoDAO.getCorsiByAttivitaConPosti(idAttivita);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
	
	public Attivita getAttivita(int idAttivita){
    	return attivitaDAO.getbyId(idAttivita);
		}

}
