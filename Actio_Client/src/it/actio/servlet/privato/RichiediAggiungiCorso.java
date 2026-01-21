package it.actio.servlet.privato;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.actio.activity.services.ActivityServiceStub.Corso;

/**
 * Servlet implementation class Index
 */
public class RichiediAggiungiCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediAggiungiCorso() {
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

		
			HttpSession session = request.getSession(false); 
		
		if (session.getAttribute("autenticato") == null) {
			response.sendRedirect("/RichiediLogin?errore=1"); 
			
			return;
		} 
		
		
		Corso corso = new Corso();
		
		request.setAttribute("corso", corso);
		request.getRequestDispatcher( "/WEB-INF/privato/richiedimodificacorso.jsp").forward(request, response);

	}

	

}
