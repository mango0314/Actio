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

   
public class EliminaCorso extends HttpServlet {
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
		
		int idAttivita = (int) session.getAttribute("idAttivita");
		
	 

        String csrfSession = (String) session.getAttribute("csrfToken");
        System.out.println("[DEBUG] csrfToken sessione: " + csrfSession);
        if (csrfSession == null) {
            System.out.println("[ERROR] CSRF token non valido.");
            response.setStatus(403);
            response.getWriter().write("<response><ok>false</ok><error>CSRF token mismatch</error></response>");
            return;
        }
        
        String idCorsoParam = request.getParameter("idCorso");

        
        int idCorso;
        try {
            idCorso = Integer.valueOf(idCorsoParam.trim());
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=CAPIENZA_NON_VALIDA");
            return;
        }
        
        
        try {
            // 1️⃣ RECUPERA CORSO per ottenere path foto
            System.out.println("[DEBUG] Recupero corso ID=" + idCorso);
            ActivityServiceStub.GetCorso req = new ActivityServiceStub.GetCorso();
            req.setIdCorso(idCorso);
            ActivityServiceStub.GetCorsoResponse resp = stub.getCorso(req);
            ActivityServiceStub.Corso corso = resp.get_return();
            
            if (corso == null) {
                response.sendRedirect(request.getContextPath() + "/Index_privato?errore=CORSO_NON_TROVATO");
                return;
            }

            String pathFoto = corso.getFoto();  // path dal DB
            System.out.println("[DEBUG] Path foto da DB: " + pathFoto);

            // 2️⃣ ELIMINA FOTO dal filesyste
            
                ActivityServiceStub.EliminaCorso req1 = new ActivityServiceStub.EliminaCorso();
                req1.setIdCorso(idCorso);
                req1.setIdAttivita(idAttivita);
                
                ActivityServiceStub.EliminaCorsoResponse resp1 = stub.eliminaCorso(req1);
                boolean rispostaActivityService = resp1.get_return();
                
                if(rispostaActivityService){
                	if (pathFoto != null && !pathFoto.isEmpty()) {
                        java.io.File fileFoto = new java.io.File(pathFoto);
                        if (fileFoto.exists()) {
                            if (fileFoto.delete()) {
                                System.out.println("[DEBUG] Foto eliminata: " + pathFoto);
                            } else {
                                System.out.println("[WARN] Impossibile eliminare foto: " + pathFoto);
                            }
                        }
                    }
                    response.sendRedirect(request.getContextPath() + "/Index_privato?success=CORSO_ELIMINATO");
                    return;
                }
                else{
                    response.sendRedirect(request.getContextPath() + "/Index_privato?errore=ELIMINAZIONE_FALLITA");
                    return;
                }
                
                
                
        } catch (Exception e) {
            System.out.println("[ERROR] Errore eliminazione: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=ERRORE_ELIMINAZIONE");
            return;
        }
          


    }        
    
}
