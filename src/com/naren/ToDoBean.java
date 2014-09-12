package com.naren;

import java.util.Date;

public class ToDoBean {

		String task;
		Date date = new Date();
		
		public String getTask() {
			return task;
		}
		public void setTask(String task) {
			this.task = task;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
	
}
