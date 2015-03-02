package tasker.components;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tasker.dao.TaskerDao;
import tasker.model.User;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static TaskerDao database = new TaskerDao();
	
	public static TaskerDao getDatabase() {
		return database;
	}
	
	/*User u1 = new User.Builder()
		.sid()
		.username("Bilbo")
		.password("Baggins")
		.build();
	
	User u2 = new User.Builder()
		.sid()
		.username("Frodo")
		.password("Hobbit")
		.build();
	
	User u3 = new User.Builder()
		.sid()
		.username("Gandalf")
		.password("Wizard")
		.build();
	
	User u4 = new User.Builder()
		.sid()
		.username("Sauron")
		.password("Evil")
		.build();
	*/
	public static User validated (HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("sid")) {
				String sid = cookies[i].getValue();
				return database.getUser(sid);
			}
		}
		return null;
	}
	
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, java.io.IOException {
		if (Login.validated(request) == null)
			response.sendRedirect("/tasker/login.html");
		else
			response.sendRedirect("/tasker/tasks");
	}
	
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sid = database.getSid(username, password);
		if (!sid.equals("")) {
			Cookie validate = new Cookie ("sid", sid);
			response.addCookie(validate);
			response.sendRedirect("/tasker/tasks");
		}
		else
			response.sendRedirect("/tasker/login.html?error=1");
	}
}
