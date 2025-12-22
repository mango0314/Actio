package it.actio.servlet.privato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.activity.services.ActivityServiceStub;
import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;

public class AccettaIscrizione extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ActivityServiceStub stub;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            stub = new ActivityServiceStub();
        } catch (AxisFault e) {
            throw new ServletException("Errore inizializzazione UserServiceStub", e);
        }
    }

    public AccettaIscrizione() {
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
            response.sendRedirect(request.getContextPath() + "/RichiediLogin");
            return;
        }

        String ruolo = String.valueOf(session.getAttribute("ruolo"));
        if (!"0".equals(ruolo)) { // solo attività
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // CSRF
        String tokenSession = (String) session.getAttribute("csrfToken");
        String tokenRequest = request.getParameter("csrfToken");

        if (tokenSession == null || !tokenSession.equals(tokenRequest)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int idIscrizione = Integer.parseInt(request.getParameter("idIscrizione"));

        ActivityServiceStub.AccettaIscrizione req = new ActivityServiceStub.AccettaIscrizione();
        req.setIdIscrizione(idIscrizione);
        
        ActivityServiceStub.AccettaIscrizioneResponse resp = stub.accettaIscrizione(req);
        boolean accettazione = resp.get_return();
        
        if(accettazione==true){
        response.sendRedirect(request.getContextPath() + "/privato/Richieste");
        return;
        }
        else{
        	response.sendRedirect(request.getContextPath() + "/privato/Index_privato?errore");
        }
    }
}