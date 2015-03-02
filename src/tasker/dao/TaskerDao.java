package tasker.dao;

import java.util.ArrayList;
import java.util.List;

import tasker.model.Task;
import tasker.model.User;

public class TaskerDao {
	private static ArrayList<User> users = new ArrayList<>();
	
	public void addTask (Task t, User u) {
		List<Task> tasks = u.getTasks();
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
