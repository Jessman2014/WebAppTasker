package tasker.components;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
			writer.print("<input name=\"submit\" type=\"submit\" value=\"add\" class=\"form-control\"> ");
			writer.print("</form> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-4\">");
			writer.print("<form method=\"get\" action=\"/tasker/tasks\">");
			writer.print("<input name=\"show\" type=\"submit\" value=\"Show only incomplete\" class=\"form-control\">");
			writer.print("</form> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-9\">");
			writer.print("<form method=\"get\" action=\"/tasker/tasks\">");
			writer.print("<table class=\"table\"> <tr> <th>Description</th> <th>Color</th> <th>Due</th> <th>Completed</th> <th></th>");
			writer.print("</table> </form> </div> </div> </div> </body> </html>");
		}
		else
			response.sendRedirect("/tasker/login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Login.validated(request)) {
			String description = request.getParameter("description");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String dateStr = request.getParameter("date");
			
			try {
				Date date = format.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.addTask(description, date, color, u);
		}
		else
			response.sendRedirect("/login.html");
	}
	
	

}
