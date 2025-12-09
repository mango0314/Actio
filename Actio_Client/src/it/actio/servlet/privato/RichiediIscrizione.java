package it.actio.servlet.privato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;

public class RichiediIscrizione extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserServiceStub stub;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            stub = new UserServiceStub();
        } catch (AxisFault e) {
            throw new ServletException("Errore inizializzazione UserServiceStub", e);
        }
    }

    public RichiediIscrizione() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("autenticato") == null) {
            System.out.println("[DEBUG] Sessione non valida o utente non autenticato.");
            response.setStatus(401);
            return;
        }

        // Leggi body XML
        String xml = request.getReader().lines().reduce("", (a,b) -> a + b);
        System.out.println("[DEBUG] XML ricevuto: " + xml);

        int idCorso = -1;
        String csrfTokenReq = null;

        try {
            idCorso = extractInt(xml, "idCorso");
            csrfTokenReq = extractString(xml, "csrfToken");
            System.out.println("[DEBUG] idCorso estratto: " + idCorso);
            System.out.println("[DEBUG] csrfToken ricevuto: " + csrfTokenReq);
        } catch (Exception e) {
            System.out.println("[ERROR] Errore nel parsing XML: " + e.getMessage());
            response.setStatus(400);
            response.getWriter().write("<response><ok>false</ok><error>Bad Request - parsing XML</error></response>");
            return;
        }

        String csrfSession = (String) session.getAttribute("csrfToken");
        System.out.println("[DEBUG] csrfToken sessione: " + csrfSession);
        if (csrfSession == null || !csrfSession.equals(csrfTokenReq)) {
            System.out.println("[ERROR] CSRF token non valido.");
            response.setStatus(403);
            response.getWriter().write("<response><ok>false</ok><error>CSRF token mismatch</error></response>");
            return;
        }

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            System.out.println("[ERROR] Account utente non trovato in sessione.");
            response.setStatus(401);
            return;
        }

        try {
            UserServiceStub.InvioRichiestaIscrizione req = new UserServiceStub.InvioRichiestaIscrizione();
            req.setIdCorso(idCorso);
            req.setIdPersona(account.getIdPersona());

            UserServiceStub.InvioRichiestaIscrizioneResponse resp = stub.invioRichiestaIscrizione(req);
            boolean ok = resp.get_return();

            System.out.println("[DEBUG] Risposta servizio invioRichiestaIscrizione: " + ok);

            response.setContentType("application/xml;charset=UTF-8");
            response.getWriter().write("<response><ok>" + ok + "</ok></response>");
        } catch (Exception e) {
            System.out.println("[ERROR] Eccezione durante chiamata servizio: " + e.getMessage());
            response.setStatus(500);
            response.getWriter().write("<response><ok>false</ok><error>Internal Server Error</error></response>");
        }
    }


    private int extractInt(String xml, String tag) {
        return Integer.parseInt(
            xml.replaceAll(".*<" + tag + ">(.*?)</" + tag + ">.*", "$1")
        );
    }

    private String extractString(String xml, String tag) {
        return xml.replaceAll(".*<" + tag + ">(.*?)</" + tag + ">.*", "$1");
    }
}
