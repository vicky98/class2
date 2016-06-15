package org.elsys.todo.impl;

import java.util.Arrays;

import org.elsys.todo.Priority;
import org.elsys.todo.Status;
import org.elsys.todo.Task;

public class TaskImpl implements Task {

	private Status status;
	private String description;
	private Priority priority;
	private String[] tags;
	
	public TaskImpl(Status status, String description,
			Priority priority, String[] tags) {
		this.status = status;
		this.description = description;
		this.priority = priority;
		this.tags = tags;
	}

	
	// Тези функции идват от интерфейса и се използват за достъп до частните(private) променливите.
	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Priority getPriority() {
		return priority;
	}

	@Override
	public String[] getTags() {
		return tags;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskImpl other = (TaskImpl) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (priority != other.priority)
			return false;
		if (status != other.status)
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		return true;
	}
	
	
}
