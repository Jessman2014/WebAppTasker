package tasker.model;

import java.awt.Color;
import java.util.Date;

public class Task {
	private String description;
	private Date date;
	private Color color;
	private boolean completed;
	private int id;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private Task (Builder builder) {
		this.description = builder.description;
		this.date = builder.date;
		this.color = builder.color;
		this.completed = builder.completed;
		this.id = builder.id;
	}
	
	public static class Builder {
		private String description;
		private Date date;
		private Color color;
		private boolean completed;
		private int id;
		
		public Builder() {}
		
		public Builder description (String description) {
			this.description = description;
			return this;
		}
		
		public Builder date (Date date) {
			this.date = date;
			return this;
		}
		
		public Builder color (Color color) {
			this.color = color;
			return this;
		}
		
		public Builder completed (boolean completed) {
			this.completed = completed;
			return this;
		}
		
		public Builder id (int id) {
			this.id = id;
			return this;
		}
		
		public Task build() {
			return new Task(this);
		}
	}
}
