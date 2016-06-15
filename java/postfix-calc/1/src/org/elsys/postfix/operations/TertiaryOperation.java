package org.elsys.postfix.operations;

import org.elsys.postfix.Calculator;

public abstract class TertiaryOperation extends AbstractOperation {
	abstract protected Double eval(Double value1, Double value2, Double value3);

	public TertiaryOperation(Calculator calculator, String token) {
		super(calculator, token);
	}

	@Override
	public void calculate() {
		try{
			Double value1 = getCalculator().popValue();
			Double value2 = getCalculator().popValue();
			Double value3 = getCalculator().popValue();
			getCalculator().addValue(eval(value1, value2, value3));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enoguh values in stack!");
		}
	}
}
