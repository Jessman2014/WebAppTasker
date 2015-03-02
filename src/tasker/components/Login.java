package tasker.components;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import survey.components.PrintWriter;
import tasker.dao.TaskerDao;
import tasker.model.User;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static TaskerDao database = new TaskerDao();
	
	public static TaskerDao getDatabase() {
		return database;
	}
	
	User u1 = new User.Builder()
		.sid("4RcjPRZyBJNk8LJW2c3A")
		.username("Bilbo")
		.password("Baggins")
		.build();
	
	User u2 = new User.Builder()
		.sid("2qTxnnM542vdxRC3kYRf")
		.username("Frodo")
		.password("Hobbit")
		.build();
	
	User u3 = new User.Builder()
		.sid("CSALbR3L6Tmv32D64mSa")
		.username("Gandalf")
		.password("Wizard")
		.build();
	
	User u4 = new User.Builder()
		.sid("jAjZumCztLMquHW3zVMv")
		.username("Sauron")
		.password("Evil")
		.build();
	
	String header = "<head>\n" + 
			"    <meta http-equiv=\"Content-Type\" content=\"text/html\">\n" + 
			"    <title>Survey</title>\n" + 
			"    <link rel=\"stylesheet\" href=\"tasker.css\" type=\"text/css\" /> \n" + 
			"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\n" + 
			"\n" + 
			"    <script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\n" + 
			"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>\n" + 
			"    <script src=\"tasker.js\"></script>\n" + 
			"</head>";
	
	public static boolean validated (HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("sid")) {
				String sid = cookies[i].getValue();
				return data.authenticateUser(sid);
			}
		}
		return false;
	}
	
	
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, java.io.IOException {
		response.sendRedirect("/login.html");
	}
	
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException {
		database.addUser(u1);
		database.addUser(u2);
		database.addUser(u3);
		database.addUser(u4);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sid = database.getSid(username, password);
		if (sid != "") {
			Cookie validate = new Cookie ("sid", sid);
			response.addCookie(validate);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<!DOCTYPE html>");
			writer.println(header);
			writer.print("<body><div class=\"container\">");
			writer.print("<div class=\"row\"> <div class=\"col-lg-2\">");
			writer.print("<h1>Tasker</h1> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-9\">");
			writer.print("<form class=\"form-inline\" method=\"post\" action=\"/tasker/Tasker\">");
			writer.print("<input name=\"description\" type=\"text\" placeholder=\"task\" class=\"form-control\">");
			writer.print("<input name=\"color\" type=\"color\" placeholder=\"black\" class=\"form-control\">");
			writer.print("<input name=\"date\" type=\"date\" placeholder=\"mm/dd/yyyy\" class=\"form-control\">");
			writer.print("<input name=\"submit\" type=\"submit\" value=\"add\" class=\"form-control\">");
		}
		else
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		
		
		
		
	}
}
