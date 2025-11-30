package it.actio.servlet.privato;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;

import it.actio.services.UserServiceStub;

import it.actio.services.UserServiceStub.CorsoConAttivitaDTO;
import it.actio.services.UserServiceStub.OrarioCorsoDTO;

/**
 * Servlet implementation class Index_privato
 */
public class DettaglioCorso extends HttpServlet {
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
    public DettaglioCorso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubù
		
		String idCorsoParam =  request.getParameter("idCorso");
		
		int idCorso = Integer.parseInt(idCorsoParam);		
		
	
		UserServiceStub.GetCorso_conAttivita req = new UserServiceStub.GetCorso_conAttivita();
		req.setIdCorso(idCorso);
		
		UserServiceStub.GetCorso_conAttivitaResponse resp = stub.getCorso_conAttivita(req);
		CorsoConAttivitaDTO corso_conAttivita_e_PostiRimasti = resp.get_return();
		
		UserServiceStub.GetOrari req1 = new UserServiceStub.GetOrari();
		req1.setIdCorso(idCorso);
		
		UserServiceStub.GetOrariResponse resp1 = stub.getOrari(req1);
		OrarioCorsoDTO[] orario_delCorso = resp1.get_return();
		
		request.setAttribute("corso_conAttivita_e_PostiRimasti", corso_conAttivita_e_PostiRimasti);
		request.setAttribute("orario_delCorso", orario_delCorso);
		request.getRequestDispatcher("WEB-INF/privato/dettaglio_corso.jsp").forward(request, response);
		
	}

}
