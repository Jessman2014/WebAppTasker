package tasker.components;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tasker.dao.TaskerDao;

@SuppressWarnings("serial")
@WebServlet("/tasks")
public class Tasker extends HttpServlet {
	TaskerDao data = Login.getDatabase();
	
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Login.validated(request)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Login.validated(request)) {
			
		}
	}
	
	

}
