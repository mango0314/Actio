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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,   
maxFileSize       = 1024 * 1024 * 5,  
maxRequestSize    = 1024 * 1024 * 6  )   
public class AggiungiCorso extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ActivityServiceStub stub;
    
    
    private static final String UPLOAD_DIR   = "C:/actio_upload/corsi";
    
    private static final long MAX_IMAGE_BYTES = 5L * 1024 * 1024; // 5MB
   
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp == null) return null;

        String[] items = contentDisp.split(";");
        for (String s : items) {
            s = s.trim();
            if (s.startsWith("filename")) {
                String fileName = s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                               .substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
    
    private String validaESalvaImmagine(HttpServletRequest request, HttpServletResponse response, String nomeCampo, String uploadDir) 
            throws ServletException, IOException {
        
        Part immaginePart;
        try {
            immaginePart = request.getPart(nomeCampo);
        } catch (IllegalStateException ex) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=FILE_TROPPO_GRANDE");
            return null;
        }

        if (immaginePart == null || immaginePart.getSize() == 0) {
            return ""; // opzionale, OK
        }

        // 1) Dimensione
        if (immaginePart.getSize() > MAX_IMAGE_BYTES) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=IMMAGINE_TROPPO_GRANDE");
            return null;
        }

        // 2) MIME type
        String contentType = immaginePart.getContentType();
        boolean okMime = "image/jpeg".equalsIgnoreCase(contentType) || "image/png".equalsIgnoreCase(contentType);
        if (!okMime) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=FORMATO_FILE_NON_CONSENTITO");
            return null;
        }

        // 3) Estensione
        String fileName = extractFileName(immaginePart);
        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=NOME_FILE_NON_VALIDO");
            return null;
        }

        String lower = fileName.toLowerCase();
        boolean okExt = lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png");
        if (!okExt) {
            response.sendRedirect(request.getContextPath() + "/Index_privato?errore=ESTENSIONE_NON_CONSENTITA");
            return null;
        }

        // 4) Verifica immagine reale
        try (java.io.InputStream in = immaginePart.getInputStream()) {
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(in);
            if (img == null) {
                response.sendRedirect(request.getContextPath() + "/Index_privato?errore=FILE_NON_E_IMMAGINE");
                return null;
            }
        }

        // 5) Salva su disco
        String estensione = lower.substring(lower.lastIndexOf('.'));
        String nomeUnico = java.util.UUID.randomUUID().toString() + estensione;

        java.io.File uploadDirFile = new java.io.File(uploadDir);
        if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

        java.io.File fileDaSalvare = new java.io.File(uploadDirFile, nomeUnico);
        immaginePart.write(fileDaSalvare.getAbsolutePath());

        return fileDaSalvare.getAbsolutePath();
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
		
		 request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");

		
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
        
        boolean aggiunto = false; 
            
                String nomeCorso = request.getParameter("nomeCorso");
                String descrizione = request.getParameter("descrizione");
                String capienzaStr = request.getParameter("capienza");
                                      
                
                boolean datiMancanti = nomeCorso == null || nomeCorso.trim().isEmpty() ||
                        descrizione == null || descrizione.trim().isEmpty() ||
                        capienzaStr == null || capienzaStr.trim().isEmpty();

                if (datiMancanti) {
                    response.sendRedirect(request.getContextPath() + "/Index_privato?errore=DATI+MANCANTI");
                    return;
                }
                
                int capienza;
                try {
                    capienza = Integer.valueOf(capienzaStr.trim());
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/Index_privato?errore=CAPIENZA_NON_VALIDA");
                    return;
                }
                
                if (capienza < 1 ){
                	response.sendRedirect(request.getContextPath() + "/Index_privato?errore=CAPIENZA_NON_VALIDA");
                    return;
                }
                
                
                String pathFoto = validaESalvaImmagine(request, response, "fotoCorso", UPLOAD_DIR);
                if (pathFoto == null || pathFoto.isEmpty()) {
                    pathFoto = null;
                }  

                Corso corso = new Corso();
                corso.setNome(nomeCorso.trim());
                corso.setDescrizione(descrizione.trim());
                corso.setCapienza(capienza);
                corso.setFoto(pathFoto);
                
                ActivityServiceStub.AggiungiCorso req2 = new ActivityServiceStub.AggiungiCorso();
                req2.setCorso(corso);
                req2.setIdAttivita(idAttivita);
                
                ActivityServiceStub.AggiungiCorsoResponse resp2 = stub.aggiungiCorso(req2);
                boolean rispostaActivityService = resp2.get_return();
                
                if(rispostaActivityService){
                	aggiunto = true;
}
            
            if (aggiunto) {
                response.sendRedirect(request.getContextPath() + "/privato/Index_privato?success=AGGIUNTO");
            } else {
                response.sendRedirect(request.getContextPath() + "/privato/Index_privato?errore=AGGIUNTA_FALLITA");
            }
            return;


           
    }
}
