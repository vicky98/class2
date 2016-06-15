package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

public abstract class AbstractCriteria implements Criteria {

	@Override
	public Criteria and(Criteria other) {
		// ��������� ���� �������� � ���������� ���� ������� ���� &&(and)
		//������: ���� ��������: Priority(HIGH) �������: Status(TODO) => 
		// �������� �������� ������� ������ TODO ������� � HIGH priority
		return new AndCriteria(this, (AbstractCriteria)other); 
	}

	@Override
	public Criteria or(Criteria other) {
		// ��������� ���� �������� � ���������� ���� ������� ���� ||(or)
		//������: ���� ��������: Priority(HIGH) �������: Priority(LOW) => 
		// �������� �������� ������� ������ ������� � HIGH ���!!! LOW priority
		return new OrCriteria(this, (AbstractCriteria)other);
	}

	@Override
	public Criteria not() {
		//������ ���������� �� ���� ��������: ��� � Priority(HIGH) �� ���� ������ � �������� ����� �� � HIGH
		return new NotCriteria(this);
	}
	
	abstract boolean matches(Task task);
	// ���������� ����� ����� ��������� ���� ����� ���� ������ ������ ���� �������� 
	//��� ������ ����� �������� �� �������������.
}
