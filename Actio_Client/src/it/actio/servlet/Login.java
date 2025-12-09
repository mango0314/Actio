package it.actio.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;

import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;


/**
 * Servlet implementation class Index_privato
 */
public class Login extends HttpServlet {
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
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubù
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
	
		UserServiceStub.ValidaCredenziali req = new UserServiceStub.ValidaCredenziali();
		req.setEmail(email);
		req.setPassword(password);
		
		UserServiceStub.ValidaCredenzialiResponse resp = stub.validaCredenziali(req);
		
		boolean valide = resp.get_return();
		
		if (!valide){ //credenziali non valide
			response.sendRedirect(request.getContextPath() + "RichiediLogin?errore=FORMATO+CREDENZIALI+NON+VALIDO");
			return;
		}
		
		UserServiceStub.GetAccount req1 = new UserServiceStub.GetAccount();
		req1.setEmail(email);
		req1.setPassword(password);
		
		UserServiceStub.GetAccountResponse resp1 = stub.getAccount(req1);
		Account account = resp1.get_return();
		
		if (account != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("autenticato", true);
			session.setAttribute("ruolo", account.getRuolo());
			session.setAttribute("account", account);
			String csrfToken = java.util.UUID.randomUUID().toString();
			session.setAttribute("csrfToken", csrfToken);

			response.sendRedirect(request.getContextPath() + "/privato/Index_privato");
			return;
			
		}
		response.sendRedirect("RichiediLogin?errore=Credenziali+errate");
	
		}
	}


