package it.actio.servlet.privato;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.services.UserServiceStub;

import it.actio.services.UserServiceStub.CorsoConAttivitaDTO;

/**
 * Servlet implementation class Index_privato
 */
public class Esplora extends HttpServlet {
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Esplora() {
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
		
		String keywords = request.getParameter("parole_chiave");
		
		if( keywords != null && !keywords.trim().isEmpty()){
		
	
			UserServiceStub.CercaCorsi_conPostiRimasti req = new UserServiceStub.CercaCorsi_conPostiRimasti();
			req.setKeywords(keywords);
			
			UserServiceStub.CercaCorsi_conPostiRimastiResponse resp = stub.cercaCorsi_conPostiRimasti(req);
			
			CorsoConAttivitaDTO[] corsiCercati_conPostiRimasti = resp.get_return();
			
			request.setAttribute("corsi_conPostiRimasti", corsiCercati_conPostiRimasti);
			request.getRequestDispatcher("/WEB-INF/privato/esplora.jsp").forward(request, response);
			return;
		}
		else{
		
			UserServiceStub.GetCorsi_conPostiRimasti req = new UserServiceStub.GetCorsi_conPostiRimasti();
			
			
			UserServiceStub.GetCorsi_conPostiRimastiResponse resp = stub.getCorsi_conPostiRimasti(req);
			
			CorsoConAttivitaDTO[] corsi_conPostiRimasti = resp.get_return();
			
			request.setAttribute("corsi_conPostiRimasti", corsi_conPostiRimasti);
			request.getRequestDispatcher("/WEB-INF/privato/esplora.jsp").forward(request, response);
		}
	}

}
