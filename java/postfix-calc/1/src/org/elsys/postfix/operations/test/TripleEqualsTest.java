package org.elsys.postfix.operations.test;

import static org.junit.Assert.*;

import org.elsys.postfix.Calculator;
import org.elsys.postfix.operations.TripleEquals;
import org.junit.Before;
import org.junit.Test;

public class TripleEqualsTest {
	private Calculator calculator;
	private TripleEquals tripleEquals;
	
	@Before
	public final void setUp() {
		calculator = new Calculator();
		tripleEquals = new TripleEquals(calculator, "===");
		calculator.addOperation(tripleEquals);
	}
	
	@Test
	public final void testTripleNotEquals() {
		calculator.addValue(5);
		calculator.addValue(10);
		calculator.addValue(15);
		
		tripleEquals.calculate();
		assertEquals(0, calculator.popValue(), 0.00001);
	}
	
	@Test
	public final void testTripleEqualsNot() {
		calculator.addValue(5);
		calculator.addValue(5);
		calculator.addValue(5);

		tripleEquals.calculate();
		assertEquals(5, calculator.popValue(), 0.00001);
	}
}
