package org.elsys.todo;

public interface Task { 
// ��������� �� �������. 
// ����� ���� ���������� ���� ��������� � implements ������ �� ��� �������� ������:

	Status getStatus();

	String getDescription();

	Priority getPriority();

	String[] getTags();
}
