package org.elsys.postfix.operations.test;

import static org.junit.Assert.*;

import org.elsys.postfix.Calculator;
import org.elsys.postfix.operations.Sin;
import org.junit.Before;
import org.junit.Test;

public class SinTest {
	private Calculator calculator;
	private Sin sin;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
		sin = new Sin(calculator, "sin");
		calculator.addOperation(sin);
	}

	@Test
	public void testSin() {
		calculator.addValue(0);
		sin.calculate();
		assertEquals(0, calculator.popValue(), 0.00001);

		calculator.addValue(3.14);
		sin.calculate();
		assertEquals(0, calculator.popValue(), 0.01);
	}
}
