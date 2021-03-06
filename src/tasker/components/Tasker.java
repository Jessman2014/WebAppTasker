package tasker.components;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tasker.dao.TaskerDao;
import tasker.model.Task;
import tasker.model.User;

/**Validates users using their cookie given from Login
 * Responds to get requests with a table of the user's tasks.
 * Paths with delete or filter tasks will give that functionality.
 * Post requests will add a new task.
 * @author Jesse Dahir-Kanehl
 *
 */

@SuppressWarnings("serial")
@WebServlet("/tasks")
public class Tasker extends HttpServlet {
	private TaskerDao data = Login.getDatabase();
	private String header = "<head>\n" + 
			"    <meta http-equiv=\"Content-Type\" content=\"text/html\">\n" + 
			"    <title>Survey</title>\n" + 
			"    <link rel=\"stylesheet\" href=\"tasker.css\" type=\"text/css\" /> \n" + 
			"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\n" + 
			"\n" + 
			"    <script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\n" + 
			"    <script src=\"http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js\"></script>\n" +
			"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>\n" + 
			"</head>";	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = Login.validated(request);
		if (u == null) {
			response.sendRedirect("/tasker/login.html");
		}
		else {
			String id = request.getParameter("delete");
			String filter = request.getParameter("filter");
			if(filter != null)
				u.setFilter(filter);
			else
				filter = u.getFilter();
			if (id != null)
				data.deleteTask(u, Integer.parseInt(id));
			List<Task> tasks = u.getTasks();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<!DOCTYPE html>");
			writer.println(header);
			writer.print("<body><div class=\"container\">");
			writer.print("<div class=\"row\"> <div class=\"col-lg-2\">");
			writer.print("<h1>Tasker</h1> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-12\">");
			//starts first inline form
			writer.print("<form class=\"form-inline\" method=\"post\" action=\"/tasker/tasks\">");
			writer.print("<input name=\"description\" type=\"text\" placeholder=\"task\" class=\"form-control\" required>");
			writer.print("<input name=\"color\" type=\"color\" placeholder=\"black\">");
			writer.print("<input name=\"date\" type=\"date\" placeholder=\"yyyy-MM-dd\" class=\"form-control\" required>");
			writer.print("<input name=\"submit\" type=\"submit\" class=\"form-control\" value=\"add\"> ");
			writer.print("</form> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-4\">");
			//second filter form
			writer.print("<form method=\"get\" action=\"/tasker/tasks\">");
			writer.print("<input name=\"filter\" type=\"hidden\" value=\"");
			if (filter.equals("incomplete"))
				writer.print("all");
			else
				writer.print("incomplete");
			writer.print("\"> <input type=\"submit\" class=\"form-control\" value=\"");
			if (filter.equals("incomplete"))
				writer.print("Show all");
			else
				writer.print("Show only incomplete");
			writer.print("\"> </form> </div> </div>");
			writer.print("<div class=\"row\"> <div class=\"col-md-9\">");
			//table with each row as a form
			writer.print("<table class=\"table table-bordered table-striped\"> <tr> <th>Description</th> <th>Color</th> <th>Due</th> <th>Completed</th> <th></th> </tr>");
			for (Task task : tasks) {
				if (!(filter.equals("incomplete") && task.isCompleted())) {
					writer.print("<tr> <form method=\"get\" action=\"/tasker/tasks\">");
					writer.print("<td>" + task.getDescription() + "</td> <td> <input type=\"color\" value=\"");
					//colors must be formatted as hex values
					Color color = task.getColor();
					Formatter f = new Formatter(new StringBuffer("#"));
					f.format("%02X", color.getRed());
					f.format("%02X", color.getGreen());
					f.format("%02X", color.getBlue());
					writer.print(f + "\"></td>");
					writer.print("<td>" + TaskerDao.format.format(task.getDate()) + "</td> <td>");
					if (task.isCompleted())
						writer.print("yes");
					else
						writer.print("no");
					writer.print("</td> <td> <input type=\"hidden\" name=\"delete\" value=\"" + task.getId() + "\">");
					writer.print("<input type=\"submit\" value=\"x\"> </td> </form> </tr>");
					f.close();
				}
			}
			writer.print("</table> </div> </div> </div> </body> </html>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = Login.validated(request);
		if (u == null) {
			response.sendRedirect("/tasker/login.html");
		}
		else {
			try {
				String description = request.getParameter("description");
				String dateStr = request.getParameter("date");
				String colorStr = request.getParameter("color");
				Color color = Color.decode(colorStr);
				Date date = TaskerDao.format.parse(dateStr);
				data.addTask(description, date, color, u);
			} catch (ParseException e) {
				//If the date doesn't parse correctly it will catch it here
				e.printStackTrace();
			}
			response.sendRedirect("/tasker/tasks");
		}
	}
	
	

}
