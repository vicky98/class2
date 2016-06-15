package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Priority;
import org.elsys.todo.Task;

public class TagsCriteria extends AbstractCriteria
implements Criteria {

	private String[] tags;
	
	public TagsCriteria(String[] tags) {
		this.tags = tags;
	}
	
	boolean matches(Task task) {
		
		//������� �������� �� ����� � ��� ����� �� ��� � � ���� �� �������� �� ���������� � �����
		for(String taskTag : task.getTags()) {
			for(String myTag : this.tags) {
				if(taskTag.equals(myTag)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
