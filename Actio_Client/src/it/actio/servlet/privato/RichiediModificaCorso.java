package it.actio.servlet.privato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.activity.services.ActivityServiceStub;
import it.actio.activity.services.ActivityServiceStub.Corso;
import it.actio.user.services.UserServiceStub;

/**
 * Servlet implementation class Index
 */
public class RichiediModificaCorso extends HttpServlet {
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
    public RichiediModificaCorso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		String idParam =  request.getParameter("idCorso");
		int idCorso = Integer.parseInt(idParam);
		
		ActivityServiceStub.GetCorso req = new ActivityServiceStub.GetCorso();
		req.setIdCorso(idCorso);
		
		ActivityServiceStub.GetCorsoResponse resp = stub.getCorso(req);
		Corso corso = resp.get_return();
		
		request.setAttribute("corso", corso);
		request.getRequestDispatcher( "/WEB-INF/privato/richiedimodificacorso.jsp").forward(request, response);

	}

	

}
