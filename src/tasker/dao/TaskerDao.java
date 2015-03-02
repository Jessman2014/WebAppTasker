package tasker.dao;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tasker.model.Task;
import tasker.model.User;

public class TaskerDao {
	private ArrayList<User> users = new ArrayList<>();
	
	public TaskerDao () {
	User u1 = new User("4RcjPRZyBJNk8LJW2c3A", "Bilbo", "Baggins");
	User u2 = new User("2qTxnnM542vdxRC3kYRf", "Frodo", "Hobbit");
	User u3 = new User("CSALbR3L6Tmv32D64mSa", "Gandalf", "Wizard");
	User u4 = new User("jAjZumCztLMquHW3zVMv", "Sauron", "Evil");
	users.add(u1);
	users.add(u2);
	users.add(u3);
	users.add(u4);
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	try {
		addTask("hey there", format.parse("2015-03-26"), Color.CYAN, u1);
		addTask("this is new task", format.parse("2015-03-27"), Color.BLUE, u1);
		addTask("what is up dude", format.parse("2014-03-26"), Color.GREEN, u1);
		addTask("another task", format.parse("2015-09-26"), Color.BLACK, u1);
		addTask("garage talk", format.parse("2014-08-26"), Color.YELLOW, u1);
		addTask("exception stuff", format.parse("2015-03-26"), Color.CYAN, u1);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public User getUser (String sid) {
		for (User user : users) {
			if(user.getSid().equals(sid))
				return user;
		}
		return null;
	}
	
	public void addTask(String description, Date date, Color color, User u) {
		int id;

		List<Task> tasks = u.getTasks();
		/*if (tasks == null)
			id = 0;
		else {
			Task lastTask = tasks.get(tasks.size());
			id = lastTask.getId() + 1;
		}*/
		id = tasks.size();

		Task t = new Task.Builder().description(description).date(date)
				.color(color).completed(false).id(id).build();

		tasks.add(t);
		u.setTasks(tasks);
	}
	
	public void deleteTask(User u, int id) {
		List<Task> tasks = u.getTasks();
		for (Task task : tasks) {
			if (task.getId() == id)
				tasks.remove(task);
		}
		u.setTasks(tasks);
	}
	
	public String getSid (String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user.getSid();
		}
		return "";
	}
	
	/*
	public boolean authenticateUser (String sid) {
		for (User user : users) {
			if (user.getSid().equals(sid))
				return true;
		}
		return false;
	}*/

}
