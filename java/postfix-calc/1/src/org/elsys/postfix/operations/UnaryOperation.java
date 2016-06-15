package org.elsys.postfix.operations;

import org.elsys.postfix.Calculator;

public abstract class UnaryOperation extends AbstractOperation {
	abstract protected Double eval(Double value);

	public UnaryOperation(Calculator calculator, String token) {
		super(calculator, token);
	}

	@Override
	public void calculate() {
		try {
			Double value = getCalculator().popValue();
			getCalculator().addValue(eval(value));
			System.out.println(eval(value));
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Not enough values in stack!");
		}
	}

}
