package tasker.dao;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tasker.model.Task;
import tasker.model.User;

/**Stores data for pages to use.
 * Has functionality for retrieving user information
 *  and adding or deleting tasks.
 * @author Jesse Dahir-Kanehl
 *
 */

public class TaskerDao {
	private ArrayList<User> users = new ArrayList<>();
	//date format for all pages to use
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	//this is where to manage users
	public TaskerDao () {
		User u1 = new User.Builder()
		.sid("4RcjPRZyBJNk8LJW2c3A")
		.username("Bilbo")
		.password("Baggins")
		.filter("all")
		.task(new ArrayList<Task>())
		.build();
	
	User u2 = new User.Builder()
		.sid("2qTxnnM542vdxRC3kYRf")
		.username("Frodo")
		.password("Hobbit")
		.filter("all")
		.task(new ArrayList<Task>())
		.build();
	
	User u3 = new User.Builder()
		.sid("CSALbR3L6Tmv32D64mSa")
		.username("Gandalf")
		.password("Wizard")
		.filter("all")
		.task(new ArrayList<Task>())
		.build();
	
	User u4 = new User.Builder()
		.sid("jAjZumCztLMquHW3zVMv")
		.username("Sauron")
		.password("Evil")
		.filter("all")
		.task(new ArrayList<Task>())
		.build();
		
	users.add(u1);
	users.add(u2);
	users.add(u3);
	users.add(u4);
	}
	
	public User getUser (String sid) {
		for (User user : users) {
			if(user.getSid().equals(sid))
				return user;
		}
		return null;
	}
	
	public String getSid (String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user.getSid();
		}
		return "";
	}
	
	//Tasks are assigned the highest number available 
	// and are considered complete if the date is in the past.
	public void addTask(String description, Date date, Color color, User u) {
		int id;
		boolean completed = false;
		Date today = new Date();
		format.format(today);
		
		List<Task> tasks = u.getTasks();
		if (tasks.size() == 0)
			id = 0;
		else {
			Task lastTask = tasks.get(tasks.size()-1);
			id = lastTask.getId() + 1;
		}
		if (date.before(today))
			completed = true;

		Task t = new Task.Builder().description(description).date(date)
				.color(color).completed(completed).id(id).build();

		tasks.add(t);
		u.setTasks(tasks);
	}
	
	public void deleteTask(User u, int id) {
		List<Task> tasks = u.getTasks();
		int removalId = -1;
		for (Task task : tasks) {
			if (task.getId() == id)
				removalId = tasks.indexOf(task);
		}
		if (removalId > -1)
			tasks.remove(removalId);
		u.setTasks(tasks);
	}
}
