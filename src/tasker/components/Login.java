package tasker.components;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private HashMap<String, String> logins = new HashMap<>();
	
	public Login () {
		logins.put("Bilbo", "Baggins");
		logins.put("Frodo", "Hobbit");
		logins.put("Gandalf", "Wizard");
		logins.put("Sauron", "Evil");
	}
	
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, java.io.IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (logins.containsKey(username) && logins.get(username).equals(password)) {
			Cookie validate = new Cookie ("username", username);
			response.addCookie(validate);
			String context = request.getContextPath();
			response.sendRedirect(response.encodeRedirectURL(context + "/tasks"));
		}
		else
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}
