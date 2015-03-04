package tasker.model;

import java.util.List;

/**Represents an user object with a builder.
 * @author Jesse Dahir-Kanehl
 *
 */

public class User {
	private String sid;
	private String username;
	private String password;
	private String filter;
	private List<Task> tasks;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
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
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
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
		this.filter = builder.filter;
		this.tasks = builder.tasks;
	}
	
	public static class Builder {
		private String sid;
		private String username;
		private String password;
		private String filter;
		private List<Task> tasks;
		
		public Builder() {}
		
		public Builder sid (String sid) {
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
		
		public Builder filter (String filter) {
			this.filter = filter;
			return this;
		}
		
		public Builder task (List<Task> tasks) {
			this.tasks = tasks;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
	
}
