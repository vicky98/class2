package org.elsys.postfix.operations;

import org.elsys.postfix.Calculator;

public class TripleEquals extends TertiaryOperation implements Operation {
	public TripleEquals(Calculator calculator, String token) {
		super(calculator, token);
	}

	@Override
	protected Double eval(Double value1, Double value2, Double value3) {
		if(value1.compareTo(value2) == 0 && value2.compareTo(value3) == 0)
			return value1;
		
		return 0.0;
	}
}
