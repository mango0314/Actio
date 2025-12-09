package it.actio.services;

import java.util.List;

import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import it.actio.dto.CorsoConAttivitaDTO;

public class ActivityService {
	
    CorsoDAO corsoDAO = new CorsoDAO();

	public CorsoConAttivitaDTO[] getCorsiForniti(int idAttivita) {
        List<CorsoConAttivitaDTO> list = corsoDAO.getCorsiByAttivitaConPosti(idAttivita);
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }

}
