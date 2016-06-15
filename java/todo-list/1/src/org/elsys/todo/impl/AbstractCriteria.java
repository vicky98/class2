package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

public abstract class AbstractCriteria implements Criteria {

	@Override
	public Criteria and(Criteria other) {
		// Съединява тази критерия с подадената като деиства като &&(and)
		//пример: Тази критерия: Priority(HIGH) Другата: Status(TODO) => 
		// резултат критерия мачваща всички TODO таскове с HIGH priority
		return new AndCriteria(this, (AbstractCriteria)other); 
	}

	@Override
	public Criteria or(Criteria other) {
		// Съединява тази критерия с подадената като деиства като ||(or)
		//пример: Тази критерия: Priority(HIGH) Другата: Priority(LOW) => 
		// резултат критерия мачваща всички таскове с HIGH ИЛИ!!! LOW priority
		return new OrCriteria(this, (AbstractCriteria)other);
	}

	@Override
	public Criteria not() {
		//Обръща деиствието на тази критерия: ако е Priority(HIGH) ще даде всичко с приорити което не е HIGH
		return new NotCriteria(this);
	}
	
	abstract boolean matches(Task task);
	// Абстрактен метод които проверява дали даден таск минава според тази критерия 
	//Виж всички други критерии за имплементация.
}
