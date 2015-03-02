package tasker.model;

import java.util.List;

public class User {
	private int sid;
	private String username;
	private String password;
	private List<Task> tasks;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	private User (Builder builder) {
		this.sid = builder.sid;
		this.username = builder.username;
		this.password = builder.password;
		this.tasks = builder.tasks;
	}
	
	public static class Builder {
		private int sid;
		private String username;
		private String password;
		private List<Task> tasks;
		
		public Builder() {}
		
		public Builder sid (int sid) {
			this.sid = sid;
			return this;
		}
		
		public Builder username (String username) {
			this.username = username;
			return this;
		}
		
		public Builder password (String password) {
			this.password = password;
			return this;
		}
		
		public Builder tasks (List<Task> task) {
			this.tasks = tasks;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}
