package org.elsys.postfix.operations;

import org.elsys.postfix.Calculator;
import java.lang.Math;

public class Sin extends UnaryOperation implements Operation {
	public Sin(Calculator calculator, String token) {
		super(calculator, token);
	}

	// @Override
	protected Double eval(Double value) {
		return Math.sin(value);
	}
}
