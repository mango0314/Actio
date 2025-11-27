package it.actio.services;

import it.actio.beans.corso.Corso;
import it.actio.beans.corso.CorsoDAO;
import java.util.List;

public class UserService {
    
    CorsoDAO corsoDAO = new CorsoDAO();
    
   
    
    // Metodo esposto al client SOAP: ritorna array
    public Corso[] getCorsiSeguiti(int idPersona) {
        List<Corso> list = corsoDAO.getAll_seguiti(idPersona);
        return list.toArray(new Corso[0]);
    }
    
    
    // Metodo esposto al client SOAP: ritorna array
    public Corso[] getCorsi_conPostiRimasti() {
        List<Corso> list = corsoDAO.getPosti_Rimasti();
        return list.toArray(new Corso[0]);
    }
    
    public Corso[] cercaCorsi_conPostiRimasti(String keywords) {
        List<Corso> list = corsoDAO.cercaCorsiConPostiRimasti(keywords);
        return list.toArray(new Corso[0]);
    }

    
    
}
