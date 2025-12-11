package it.actio.servlet.privato;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.activity.services.ActivityServiceStub;
import it.actio.activity.services.ActivityServiceStub.Attivita;
import it.actio.activity.services.ActivityServiceStub.CorsoConAttivitaDTO;
import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;
import it.actio.user.services.UserServiceStub.Corso;
import it.actio.user.services.UserServiceStub.Persona;

/**
 * Servlet implementation class Index_privato
 */
public class Index_privato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private UserServiceStub stub;
private ActivityServiceStub stub2;
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    try {
	        stub = new UserServiceStub();
	        stub2 = new ActivityServiceStub();
	    } catch (AxisFault e) {
	        throw new ServletException("Errore inizializzazione Stub", e);
	    }
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index_privato() {
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
		
		Account account = (Account) session.getAttribute("account");
		
		if (account.getRuolo() == 0){
			int idAttivita = account.getIdAttivita();
			
			//recupero i corsi forniti
			
			ActivityServiceStub.GetCorsiForniti req1 = new ActivityServiceStub.GetCorsiForniti();
			req1.setIdAttivita(idAttivita);
			
			ActivityServiceStub.GetCorsiFornitiResponse resp1 = stub2.getCorsiForniti(req1);
			
			CorsoConAttivitaDTO[] corsi_forniti = resp1.get_return();
			
			//recupero l'attivita
			ActivityServiceStub stub2 = new ActivityServiceStub();
			ActivityServiceStub.GetAttivita req2 = new ActivityServiceStub.GetAttivita();
			req2.setIdAttivita(idAttivita);
			
			ActivityServiceStub.GetAttivitaResponse resp2 = stub2.getAttivita(req2);
			Attivita attivita = resp2.get_return();
			
			request.setAttribute("corsiForniti", corsi_forniti);
			request.setAttribute("attivita", attivita);
					
			request.getRequestDispatcher("/WEB-INF/privato/index_privato.jsp").forward(request, response);
			return;
		}
		int idPersona = account.getIdPersona();
		
		//recupero i corsi seguiti
		
		UserServiceStub.GetCorsiSeguiti req = new UserServiceStub.GetCorsiSeguiti();
		req.setIdPersona(idPersona);
		
		UserServiceStub.GetCorsiSeguitiResponse resp = stub.getCorsiSeguiti(req);
		
		Corso[] corsi_seguiti = resp.get_return();
		
		//recupero l'utente
		
		UserServiceStub.GetPersona req3 = new UserServiceStub.GetPersona();
		req3.setIdPersona(idPersona);
		
		UserServiceStub.GetPersonaResponse resp3 = stub.getPersona(req3);
		
		Persona persona = resp3.get_return();
		
		request.setAttribute("corsiSeguiti", corsi_seguiti);
		request.setAttribute("persona", persona);	
		request.getRequestDispatcher("/WEB-INF/privato/index_privato.jsp").forward(request, response);
	}

}
