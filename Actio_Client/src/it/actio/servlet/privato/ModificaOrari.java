package it.actio.servlet.privato;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.ParseException;

import org.apache.axis2.AxisFault;
import it.actio.activity.services.ActivityServiceStub;
import it.actio.user.services.UserServiceStub.Account;
import it.actio.user.services.UserServiceStub.UtenteDTO;
import it.actio.activity.services.ActivityServiceStub.AttivitaDTO;
import it.actio.activity.services.ActivityServiceStub.Corso;
import it.actio.activity.services.ActivityServiceStub.OrarioCorsoDTO;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


   
public class ModificaOrari extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ActivityServiceStub stub;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    private LocalTime parseTimeFlexible(String timeStr) throws DateTimeParseException {
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new DateTimeParseException("Stringa vuota", timeStr, 0);
        }
        
        timeStr = timeStr.trim();
        
        // Prova prima "HH:mm:ss"
        if (timeStr.length() == 8 && timeStr.charAt(2) == ':' && timeStr.charAt(5) == ':') {
            return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        
        // Poi prova "HH:mm"
        if (timeStr.length() == 5 && timeStr.charAt(2) == ':') {
            return LocalTime.parse(timeStr, TIME_FORMATTER);
        }
        
        // Se non corrisponde a nessuno dei due, prova comunque con LocalTime.parse()
        return LocalTime.parse(timeStr);
    }
    
 
    @Override
    public void init() throws ServletException {
        super.init();
     
        try {
            stub = new ActivityServiceStub();
        } catch (AxisFault e) {
            throw new ServletException("Errore inizializzazione stub", e);
        }
    }
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);

		
			HttpSession session = request.getSession(false); 
		
		if (session.getAttribute("autenticato") == null) {
			response.sendRedirect("/RichiediLogin?errore=1"); 
			
			return;
		}
		
		int idAttivita = (int) session.getAttribute("idAttivita");
		
		int corsoId = -1;
		
		try {
            String corsoIdStr = request.getParameter("corso_id");
            if (corsoIdStr == null || corsoIdStr.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/Index_privato?errore=DATI_MANCANTI");
                return;
            }
            
           corsoId = Integer.parseInt(corsoIdStr);
            
		
	 

        String csrfSession = (String) session.getAttribute("csrfToken");
        System.out.println("[DEBUG] csrfToken sessione: " + csrfSession);
        if (csrfSession == null) {
            System.out.println("[ERROR] CSRF token non valido.");
            response.setStatus(403);
            response.getWriter().write("<response><ok>false</ok><error>CSRF token mismatch</error></response>");
            return;
        }
        
        boolean modificato = false; 
            
        String[] giorni = request.getParameterValues("giorno[]");
        String[] iniziStr = request.getParameterValues("oraInizio[]");
        String[] finiStr = request.getParameterValues("oraFine[]");
                                      
                
        if (giorni == null || iniziStr == null || finiStr == null || 
                giorni.length < 1 || iniziStr.length < 1 || finiStr.length < 1) {
                response.sendRedirect(request.getContextPath() + "/privato/Index_privato?errore=DATI_MANCANTI");
                return;
            }
            
            // --- 4. COSTRUZIONE LISTA ---
            List<OrarioCorsoDTO> listaNuova = new ArrayList<>();
            
            for (int i = 0; i < giorni.length; i++) {
                try {
                	LocalTime inizio = parseTimeFlexible(iniziStr[i].trim());
                    LocalTime fine = parseTimeFlexible(finiStr[i].trim());
                    
                    // Validazione logica
                    if (!fine.isAfter(inizio)) {
                        response.sendRedirect(request.getContextPath() + 
                            "/privato/RichiediModificaOrario?idCorso=" + corsoId + 
                            "&errore=ORARIO_FINE_PRIMA_DI_INIZIO");
                        return;
                    }
                    
                    // ✅ CONVERSIONE SEMPLICE: LocalTime → String "HH:mm:ss"
                    String orarioInizioStr = String.format("%02d:%02d:00", inizio.getHour(), inizio.getMinute());
                    String orarioFineStr = String.format("%02d:%02d:00", fine.getHour(), fine.getMinute());
                    
                    // Crea DTO con String
                    OrarioCorsoDTO dto = new OrarioCorsoDTO();
                    dto.setGiornoSettimana(Integer.parseInt(giorni[i]));
                    dto.setOrarioInizio(orarioInizioStr);  // ← String, non Time!
                    dto.setOrarioFine(orarioFineStr);      // ← String, non Time!
                    
                    listaNuova.add(dto);
                    
                        
                    	} catch (DateTimeParseException e) {
                            // Formato orario non valido
                            response.sendRedirect(request.getContextPath() + 
                                "/privato/RichiediModificaOrario?idCorso=" + corsoId + 
                                "&errore=FORMATO_ORARIO_INVALIDO");
                            return;
                        }
                    }
                    
                    
                
                OrarioCorsoDTO[] arrayOrari = listaNuova.toArray(new OrarioCorsoDTO[0]);

                
                ActivityServiceStub.SostituisciOrariCorso req2 = new ActivityServiceStub.SostituisciOrariCorso();
                req2.setNuoviOrari(arrayOrari);
                req2.setIdCorso(corsoId);
                req2.setIdAttivita(idAttivita);
                
                ActivityServiceStub.SostituisciOrariCorsoResponse resp2 = stub.sostituisciOrariCorso(req2);
                boolean rispostaActivityService = resp2.get_return();
                
                if(rispostaActivityService){
                	modificato = true;
}
            
            if (modificato) {
                response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + corsoId +"&success=MODIFICATO");
            } else {
                response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + corsoId +"&errore=MODIFICA_FALLITA");
            }
            return;

		 } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + corsoId +"&errore=FORMATO_DATI_ERRATO");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + corsoId +"&errore=ERRORE_SALVATAGGIO_ORARI");
	        }
           
    }
}
