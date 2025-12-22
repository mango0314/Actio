package it.actio.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import it.actio.activity.services.ActivityServiceStub;
import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;
import it.actio.user.services.UserServiceStub.UtenteDTO;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,   
maxFileSize       = 1024 * 1024 * 5,  
maxRequestSize    = 1024 * 1024 * 6  )   
public class Registrazione extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServiceStub stub;
    private ActivityServiceStub stub1;
    
    private static final String UPLOAD_DIR_UTENTI   = "C:/actio_upload/utente";
    private static final String UPLOAD_DIR_ATTIVITA = "C:/actio_upload/attivita";
    
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



    @Override
    public void init() throws ServletException {
        super.init();
        try {
            stub = new UserServiceStub();
            stub1 = new ActivityServiceStub();
        } catch (AxisFault e) {
            throw new ServletException("Errore inizializzazione stub", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoAccount = request.getParameter("tipoAccount");
        int tipo;
        if (tipoAccount == null) {
            response.sendRedirect("404.html");
            return;
        }
        
        try {
            tipo = Integer.parseInt(tipoAccount);
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
            return;
        }
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
       
            if (tipo == 1) {
                String nome = request.getParameter("nomeUtente");
                String cognome = request.getParameter("cognomeUtente");
                String dataNascita = request.getParameter("data_di_nascita_Utente");
                String altezzaStr = request.getParameter("altezzaUtente");
                String pesoStr = request.getParameter("pesoUtente");
                
                
            
                boolean datiMancanti = nome == null || nome.trim().isEmpty() ||
                        cognome == null || cognome.trim().isEmpty() ||
                        dataNascita == null || dataNascita.trim().isEmpty() ||
                        altezzaStr == null || altezzaStr.trim().isEmpty() ||
                        pesoStr == null || pesoStr.trim().isEmpty();

                if (datiMancanti) {
                    response.sendRedirect(request.getContextPath() + "/Index?errore=DATI+MANCANTI");
                    return;
                }
                
                int altezza;
                double peso;
                
                try {
                    altezza = Integer.valueOf(altezzaStr.trim());
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/Index?errore=ALTEZZA_NON_VALIDA");
                    return;
                }
                
                try {
                    pesoStr = pesoStr.trim().replace(',', '.');
                    peso = Double.valueOf(pesoStr);  
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/Index?errore=PESO_NON_VALIDO");
                    return;
                }
                
                if (altezza < 100 || altezza > 250 || peso < 40.0 || peso > 300.0) {
                    response.sendRedirect(request.getContextPath() + "/Index?errore=ALTEZZA_O_PESO_FUORI_RANGE");
                    return;
                }
                
                
                Part fotoPart;
                try {
                    fotoPart = request.getPart("fotoProfilo");
                } catch (IllegalStateException ex) {
                    response.sendRedirect(request.getContextPath() + "/Index?errore=FILE_TROPPO_GRANDE");
                    return;
                }

                
                String pathFoto = null;
                
                if (fotoPart != null && fotoPart.getSize() > 0) {

                    // 1) dimensione
                    if (fotoPart.getSize() > MAX_IMAGE_BYTES) {
                        response.sendRedirect(request.getContextPath() + "/Index?errore=IMMAGINE_TROPPO_GRANDE");
                        return;
                    }

                    // 2) MIME type (da header del client, quindi non perfetto ma utile)
                    String contentType = fotoPart.getContentType(); // es: image/jpeg, image/png[web:135]
                    boolean okMime = "image/jpeg".equalsIgnoreCase(contentType) || "image/png".equalsIgnoreCase(contentType);
                    if (!okMime) {
                        response.sendRedirect(request.getContextPath() + "/Index?errore=FORMATO_FILE_NON_CONSENTITO");
                        return;
                    }

                    // 3) estensione (da nome file)
                    String fileName = extractFileName(fotoPart);
                    if (fileName == null || fileName.trim().isEmpty()) {
                        response.sendRedirect(request.getContextPath() + "/Index?errore=NOME_FILE_NON_VALIDO");
                        return;
                    }

                    String lower = fileName.toLowerCase();
                    boolean okExt = lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png");
                    if (!okExt) {
                        response.sendRedirect(request.getContextPath() + "/Index?errore=ESTENSIONE_NON_CONSENTITA");
                        return;
                    }

                    // 4) controllo “reale” (consigliato): provo a leggerla come immagine
                    try (java.io.InputStream in = fotoPart.getInputStream()) {
                        java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(in);
                        if (img == null) { // non è un'immagine valida
                            response.sendRedirect(request.getContextPath() + "/Index?errore=FILE_NON_E_IMMAGINE");
                            return;
                        }
                    }

                    // 5) salva su disco con nome univoco (come stai già facendo)
                    String estensione = lower.substring(lower.lastIndexOf('.')); // ".jpg" / ".png"
                    String nomeUnico = java.util.UUID.randomUUID().toString() + estensione;

                    java.io.File uploadDir = new java.io.File(UPLOAD_DIR_UTENTI);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    java.io.File fileDaSalvare = new java.io.File(uploadDir, nomeUnico);
                    fotoPart.write(fileDaSalvare.getAbsolutePath()); // salva[web:65]

                    pathFoto = fileDaSalvare.getAbsolutePath();
                }
                
                UserServiceStub.ValidaCredenziali req = new UserServiceStub.ValidaCredenziali();
                req.setEmail(email);
                req.setPassword(password);
                
                UserServiceStub.ValidaCredenzialiResponse resp = stub.validaCredenziali(req);
                boolean valide = resp.get_return();
                
                if(!valide){
                	response.sendRedirect(request.getContextPath() + "/RichiediRegistrazione?FORMATO+CREDENZIALI+ERRATO");
                	return;
                }
                
                UtenteDTO utenteDTO = new UtenteDTO();
                
                
                
                // ... altri campi utente
                // Chiama userStub per registrare l'utente
            } else if (tipo == 0) {
                // Registrazione Attività
                String nomeAttivita = request.getParameter("nomeAttivita");
                String citta = request.getParameter("cittaAttivita");
                // ... altri campi attività
                // Chiama activityStub per registrare l'attività
            } else {
                response.sendRedirect("404.html");
                return;
            }

           
    }
}
