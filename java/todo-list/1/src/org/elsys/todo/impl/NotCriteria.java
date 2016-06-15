package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

class NotCriteria extends AbstractCriteria implements Criteria {

	private AbstractCriteria criteria;

	//Приема една критерия и действа като обратната на нея.
	NotCriteria(AbstractCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	boolean matches(Task task) {
		//обръща критерията
		return !criteria.matches(task);
	}

}

