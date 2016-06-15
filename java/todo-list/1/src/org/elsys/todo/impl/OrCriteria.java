package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

class OrCriteria extends AbstractCriteria implements Criteria {

	private AbstractCriteria left;
	private AbstractCriteria right;

	//Комбинира две критерии в тази като ги запазва в left и right.
	OrCriteria(AbstractCriteria left, AbstractCriteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	boolean matches(Task task) {
		// Ако една от двете критерии минава то тази комбинация от двете също минава.
		return left.matches(task) || right.matches(task);
	}

}

