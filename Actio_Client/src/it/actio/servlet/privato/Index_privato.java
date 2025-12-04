package it.actio.servlet.privato;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.actio.services.UserServiceStub;
import it.actio.services.UserServiceStub.Corso;

/**
 * Servlet implementation class Index_privato
 */
public class Index_privato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
			HttpSession session = request.getSession(false); 
		
		if (session.getAttribute("autenticato") == null) {
			response.sendRedirect("/RichiediLogin?errore=1"); 
			
			return;
		} 
		int idPersona = 1;
		
		UserServiceStub stub = new UserServiceStub();
		UserServiceStub.GetCorsiSeguiti req = new UserServiceStub.GetCorsiSeguiti();
		req.setIdPersona(idPersona);
		
		UserServiceStub.GetCorsiSeguitiResponse resp = stub.getCorsiSeguiti(req);
		
		Corso[] corsi_seguiti = resp.get_return();
		
		request.setAttribute("corsiSeguiti", corsi_seguiti);
				
		request.getRequestDispatcher("/WEB-INF/privato/index_privato.jsp").forward(request, response);
	}

}
