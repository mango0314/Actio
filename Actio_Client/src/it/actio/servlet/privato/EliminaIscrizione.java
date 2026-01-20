package it.actio.servlet.privato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.text.ParseException;

import org.apache.axis2.AxisFault;
import it.actio.activity.services.ActivityServiceStub;
import it.actio.user.services.UserServiceStub.Account;
import it.actio.user.services.UserServiceStub.UtenteDTO;
import it.actio.activity.services.ActivityServiceStub.AttivitaDTO;
import it.actio.activity.services.ActivityServiceStub.Corso;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

   
public class EliminaIscrizione extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ActivityServiceStub stub;
    
    
   
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
		
	 

        String csrfSession = (String) session.getAttribute("csrfToken");
        System.out.println("[DEBUG] csrfToken sessione: " + csrfSession);
        if (csrfSession == null) {
            System.out.println("[ERROR] CSRF token non valido.");
            response.setStatus(403);
            response.getWriter().write("<response><ok>false</ok><error>CSRF token mismatch</error></response>");
            return;
        }
        
        String tokenRequest = request.getParameter("csrfToken");

        if (csrfSession == null || !csrfSession.equals(tokenRequest)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        String idPersonaParam = request.getParameter("idPersona");
        String idCorsoParam = request.getParameter("idCorso");
        
        int idCorso;
        try {
            idCorso = Integer.valueOf(idCorsoParam.trim());
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=IDCorso_NON_VALIDO");
            return;
        }
        

        
        int idPersona;
        try {
            idPersona = Integer.valueOf(idPersonaParam.trim());
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=IDPERSONA_NON_VALIDA");
            return;
        }
        
        
        try {
            
            
                ActivityServiceStub.EliminaIscrizione req1 = new ActivityServiceStub.EliminaIscrizione();
                req1.setIdPersona(idPersona);
                req1.setIdCorso(idCorso);
                
                ActivityServiceStub.EliminaIscrizioneResponse resp1 = stub.eliminaIscrizione(req1);
                boolean rispostaActivityService = resp1.get_return();
                
                if(rispostaActivityService){
                	
                    response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + idCorso +"&success=CORSO_ELIMINATO");
                    return;
                }
                else{
                    response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + idCorso +"&errore=ELIMINAZIONE_FALLITA");
                    return;
                }
                
                
                
        } catch (Exception e) {
            System.out.println("[ERROR] Errore eliminazione: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/privato/DettaglioCorso?idCorso=" + idCorso +"&errore=ERRORE_ELIMINAZIONE");
            return;
        }
          


    }        
    
}
