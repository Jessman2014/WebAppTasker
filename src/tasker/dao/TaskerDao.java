package tasker.dao;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tasker.model.Task;
import tasker.model.User;

public class TaskerDao {
	private static ArrayList<User> users = new ArrayList<>();
	
	public void addTask (String description, Date date, Color color, User u) {
		int id;
		
		List<Task> tasks = u.getTasks();
		if (tasks == null)
			id = 0;
		else {
			Task lastTask = tasks.get(tasks.size());
			id = lastTask.getId() + 1;
		}
		
		Task t = new Task.Builder()
		.description(description)
		.date(date)
		.color(color)
		.completed(false)
		.id(id)
		.build();
		
		tasks.add(t);
		u.setTasks(tasks);
	}
	
	public void addUser (User u) {
		users.add(u);
	}
	
	public String getSid (String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user.getSid();
		}
		return "";
	}
	
	public boolean authenticateUser (String sid) {
		for (User user : users) {
			if (user.getSid().equals(sid))
				return true;
		}
		return false;
	}

}
