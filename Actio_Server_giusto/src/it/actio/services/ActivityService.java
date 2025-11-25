package it.actio.services;

import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import java.util.Vector;

public class ActivityService {
	
	CorsoDAO corsoDAO = new CorsoDAO();
	
	
	public Vector<Corso> getCorsiSeguiti (int idPersona){
		return corsoDAO.getAll_seguiti(idPersona);
	}

}
