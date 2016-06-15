package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

abstract class AndCriteria extends AbstractCriteria implements Criteria {

	private AbstractCriteria left;
	private AbstractCriteria right;

	//��������� ��� �������� � ���� ���� �� ������� � left � right.
	AndCriteria(AbstractCriteria left, AbstractCriteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	boolean matches(Task task) {
		// ��� � ����� �������� ������� �� ���� ���������� �� ����� ���� ������.
		return left.matches(task) && right.matches(task);
	}

}

