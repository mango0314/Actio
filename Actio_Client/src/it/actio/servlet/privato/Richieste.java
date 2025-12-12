package it.actio.servlet.privato;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.activity.services.ActivityServiceStub;
import it.actio.activity.services.ActivityServiceStub.Iscrizione_ConNomeCorso_NomeAttivitaDTO;
import it.actio.user.services.UserServiceStub.Account;

/**
 * Servlet implementation class Index_privato
 */
public class Richieste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private ActivityServiceStub stub;
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    try {
	        
	        stub = new ActivityServiceStub();
	    } catch (AxisFault e) {
	        throw new ServletException("Errore inizializzazione Stub", e);
	    }
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Richieste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubù
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);

		
			HttpSession session = request.getSession(false); 
		
		if (session.getAttribute("autenticato") == null) {
			response.sendRedirect("/RichiediLogin?errore=1"); 
			
			return;
		} 
		
		int ruolo = (int) session.getAttribute("ruolo");
		
		Account account = (Account) session.getAttribute("account");
		
		int idAttivita = account.getIdAttivita();
		

		ActivityServiceStub.Get_Iscrizioni_ConNomePersona_Attivita req = new ActivityServiceStub.Get_Iscrizioni_ConNomePersona_Attivita();
		req.setIdAttivita(idAttivita);;
		
		ActivityServiceStub.Get_Iscrizioni_ConNomePersona_AttivitaResponse resp = stub.get_Iscrizioni_ConNomePersona_Attivita(req);
		Iscrizione_ConNomeCorso_NomeAttivitaDTO[] iscrizioni_conNomeCorso_Attivita = resp.get_return();
		
			
		
		request.setAttribute("iscrizioni_conNomeCorso_Attivita", iscrizioni_conNomeCorso_Attivita);
		request.getRequestDispatcher("/WEB-INF/privato/richieste.jsp").forward(request, response);
		
	}

}
