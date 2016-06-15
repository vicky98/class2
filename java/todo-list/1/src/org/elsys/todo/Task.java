package org.elsys.todo;

public interface Task { 
// Интерфейс за таскове. 
// Всеки клас наследяващ този интерфеис с implements трябва да има следните методи:

	Status getStatus();

	String getDescription();

	Priority getPriority();

	String[] getTags();
}
