package it.actio.services;

import it.actio.beans.corso.Corso;
import it.actio.beans.orario_corso.Orario_corso;
import it.actio.beans.orario_corso.Orario_corsoDAO;

import it.actio.beans.corso.CorsoDAO;
import it.actio.dto.CorsoConAttivitaDTO;
import it.actio.dto.OrarioCorsoDTO;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    
    CorsoDAO corsoDAO = new CorsoDAO();
    Orario_corsoDAO orario_corsoDAO = new Orario_corsoDAO();
    
   
    
    // Metodo esposto al client SOAP: ritorna array
    public Corso[] getCorsiSeguiti(int idPersona) {
        List<Corso> list = corsoDAO.getAll_seguiti(idPersona);
        return list.toArray(new Corso[0]);
    }
    
    
    // Metodo esposto al client SOAP: ritorna array
    public CorsoConAttivitaDTO[] getCorsi_conPostiRimasti() {
        List<CorsoConAttivitaDTO> list = corsoDAO.getPosti_Rimasti();
        return list.toArray(new CorsoConAttivitaDTO[0]);
    }
    
    public CorsoConAttivitaDTO[] cercaCorsi_conPostiRimasti(String keywords) {
        List<CorsoConAttivitaDTO> list = corsoDAO.cercaCorsiConPostiRimasti(keywords);
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
            dto.setGiornoSettimanaLabel(mapGiorno(oc.getGiorno_settimana()));
            dto.setOrarioInizio(formatTime(oc.getOrarioInizio()));
            dto.setOrarioFine(formatTime(oc.getOrarioFine()));
            dtoList.add(dto);
        }
    	
    	return dtoList.toArray(new OrarioCorsoDTO[0]);
    }
    
    private String mapGiorno(int giorno) {
        switch (giorno) {
            case 0: return "Lunedì";
            case 1: return "Martedì";
            case 2: return "Mercoledì";
            case 3: return "Giovedì";
            case 4: return "Venerdì";
            case 5: return "Sabato";
            case 6: return "Domenica";
            default: return "Sconosciuto";
        }
    }
    
    private String formatTime(Time t) {
        return t != null ? t.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null;
    }




    
    
}
