package org.elsys.todo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.elsys.todo.Criteria;
import org.elsys.todo.Priority;
import org.elsys.todo.Status;
import org.elsys.todo.Task;
import org.elsys.todo.TodoList;

public class TodoListImpl implements TodoList {

	private List<Task> tasks;

	/**
	 * ������  | ��������                     |�������.| ������
	 * TODO    | Do OOP homework              | Low    | school, programming
	 * TODO    | Get 8 hours of sleep.        | Low    | health
	 * DOING   | Party hard.                  | Normal | social
	 * DONE    | Netflix and chill.           | Normal | tv shows
	 * TODO    | Find missing socks.          | Low    | meh

	 * @param input
	 */
	public TodoListImpl(String input) {
		
		tasks = new ArrayList<>();// �������������� ����� � �������
		String[] stringTasks = input.split("\n"); // ����� ��� �� ������ � �������� ������
		
		for(String currTaskLine: stringTasks) { // ��������� stringTask�
			// ��������� �� | �� �� ������ ���������� ����� �� �����
			String[] taskProperties = currTaskLine.split("\\|");  
			
			for (int i = 0; i < taskProperties.length; i++) {
				// ������ ���������� �� ������� ���� �� ��������
				taskProperties[i] = taskProperties[i].trim();  
			}
			
			//������� ������ ����� � ���������� ����������(��� ���� ����������� �� �������)
			Status status = Status.valueOf(taskProperties[0].toUpperCase());
			String description = taskProperties[1];
			Priority priority = Priority.valueOf(taskProperties[2].toUpperCase());
			String[] tags = taskProperties[3].split(", ");
			
			// �������� ��� ��������� �� ����� ��������� ���� ����
			tasks.add(new TaskImpl(status, description, priority, tags));
		}
	}

	public TodoListImpl(List<Task> filtered) {
		this.tasks = filtered; //����� ����������� ����� �� ������ � ������ ������ ���� � �������
	}

	@Override
	public Boolean isCompleted() {
		Boolean completed = true; // �������� �� ������ ������� �� ������
		
		for(Task task: tasks) {
			if(task.getStatus() != Status.DONE) {
				// ��� ������� ����� ����� �� � ����� ������� � ������� �� �� ��� ������ � �����
				completed = false; 
				break;
			}
		}
		
		return completed;
	}

	@Override
	public Double percentageCompleted() {
		Double percentage = 0.0;
		int doneTasks = 0;
		int total = tasks.size(); // ����� ����� ��� � �����
		
		for(Task task: tasks) {
			if(task.getStatus() == Status.DONE) {
				doneTasks++; // ����� ������ ������ �������
			}
		}
		
		percentage = doneTasks / total * 100.0; // ������� ������� : ����������������/��������� * 100.
		
		return percentage;
	}

	@Override
	public List<Task> getTasks() {
		return tasks;// ������� ����� � �������
	}

	@Override
	public TodoList filter(Criteria criteria) {
		//������������� �� ���������� �������� � ����� �� �� ����� matches ���������.
		AbstractCriteria abstractCriteria = (AbstractCriteria) criteria;
	
		List<Task> filtered = new ArrayList<>();// ��� ���� � ���������� �������
		
		for(Task task : tasks) {
			if(abstractCriteria.matches(task)) { 
				filtered.add(task);// ��� ���������� ����� ����� �� �������� ��� ����������� ����.
			}
		}
		
		return new TodoListImpl(filtered);//������� ��� ���� � ������������ �������(��� ������ �����������)
	}

	@Override
	public TodoList join(TodoList other) {
		List <Task> joined = new ArrayList<>();
		
		for (Task task:tasks) {
			joined.add(task);//�������� ������ ������� �� ����� ���� ��� �����.
		}
		
		for (Task task : other.getTasks()) { //��������� ������ ���� 
			if(!joined.contains(task)) {// ��� ����� ���� �� � ������(�� � ��� � ����� ����)
				joined.add(task);//�� ��������
			}
		}
		
		// ������� ����� ���� ����� ���� �� ������ � other ��������� ��� ��������� ��.
		return new TodoListImpl(joined); 
	}

}
