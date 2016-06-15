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
	 * Статус  | Описание                     |Приорит.| Тагове
	 * TODO    | Do OOP homework              | Low    | school, programming
	 * TODO    | Get 8 hours of sleep.        | Low    | health
	 * DOING   | Party hard.                  | Normal | social
	 * DONE    | Netflix and chill.           | Normal | tv shows
	 * TODO    | Find missing socks.          | Low    | meh

	 * @param input
	 */
	public TodoListImpl(String input) {
		
		tasks = new ArrayList<>();// инициализираме листа с таскове
		String[] stringTasks = input.split("\n"); // Всеки ред от инпута в различен стринг
		
		for(String currTaskLine: stringTasks) { // Обхождаме stringTaskс
			// Разделяме на | за да вземем различните части от таска
			String[] taskProperties = currTaskLine.split("\\|");  
			
			for (int i = 0; i < taskProperties.length; i++) {
				// Махаме спейсовете от частите като ги тримваме
				taskProperties[i] = taskProperties[i].trim();  
			}
			
			//Слагаме всички части в правилната променлива(Виж горе структурата на стринга)
			Status status = Status.valueOf(taskProperties[0].toUpperCase());
			String description = taskProperties[1];
			Priority priority = Priority.valueOf(taskProperties[2].toUpperCase());
			String[] tags = taskProperties[3].split(", ");
			
			// Добавяме към тасковете на листа парснатия вече таск
			tasks.add(new TaskImpl(status, description, priority, tags));
		}
	}

	public TodoListImpl(List<Task> filtered) {
		this.tasks = filtered; //Втори конструктор който не парсва а просто приема лист с таскове
	}

	@Override
	public Boolean isCompleted() {
		Boolean completed = true; // Приемаме че всички таскове са готови
		
		for(Task task: tasks) {
			if(task.getStatus() != Status.DONE) {
				// ако намерим някой който не е готов спираме и казваме че не сме готови с листа
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
		int total = tasks.size(); // Колко таска има в листа
		
		for(Task task: tasks) {
			if(task.getStatus() == Status.DONE) {
				doneTasks++; // Броим всички готови таскове
			}
		}
		
		percentage = doneTasks / total * 100.0; // смятаме процент : направениТаскове/целияБрой * 100.
		
		return percentage;
	}

	@Override
	public List<Task> getTasks() {
		return tasks;// Взимаме листа с таскове
	}

	@Override
	public TodoList filter(Criteria criteria) {
		//Преобразавуме от интерфейса критерия в класа за да имаме matches функцията.
		AbstractCriteria abstractCriteria = (AbstractCriteria) criteria;
	
		List<Task> filtered = new ArrayList<>();// Нов лист с филтриране таскове
		
		for(Task task : tasks) {
			if(abstractCriteria.matches(task)) { 
				filtered.add(task);// Ако критерията пусне таска го добавяме към филтрирания лист.
			}
		}
		
		return new TodoListImpl(filtered);//Връщаме нов лист с филтрираните таскове(виж малкия конструктор)
	}

	@Override
	public TodoList join(TodoList other) {
		List <Task> joined = new ArrayList<>();
		
		for (Task task:tasks) {
			joined.add(task);//Добавяме всички таскове от нашия лист към новия.
		}
		
		for (Task task : other.getTasks()) { //обхождаме другия лист 
			if(!joined.contains(task)) {// ако таска вече не е сложен(не е бил в нашия лист)
				joined.add(task);//го добавяме
			}
		}
		
		// Връщаме новия лист което сбор от нашите и other тасковете без повтарящи се.
		return new TodoListImpl(joined); 
	}

}
