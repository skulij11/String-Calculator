package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class CalculatorTest {

	/*public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }*/

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testOne2Number() {
		assertEquals(25, Calculator.add("25"));
	}
	
	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}
	
	@Test
	public void testMultipleNumbers(){
		assertEquals(21, Calculator.add("1,2,3,4,5,6"));
	}	

	@Test
	public void testNewLineNumbers(){
		assertEquals(6, Calculator.add("1\n2,3"));
	}

	@Test
	public void testNewLine2Numbers(){
		assertEquals(6, Calculator.add("1,2\n3"));
	}

	@Test
	public void testNewLine3Numbers(){
		assertEquals(10, Calculator.add("1\n2,3\n4"));
	}

	@Test
	public void testNewLine4Numbers(){
		assertEquals(3, Calculator.add("1\n2"));
	}

	@Rule
	public	 ExpectedException thrown = ExpectedException.none();

	@Test
	public void oneNegativeNumberThrowsException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negatives not allowed: -1");
		Calculator.add("-1,2");
	}

	@Test
	public void moreThanOneNegativeNumbersThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negatives not allowed: -4,-5");
		Calculator.add("2,-4,3,-5");
	}

	@Test
	public void moreThanOne2NegativeNumbersThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negatives not allowed: -1,-2,-3");
		Calculator.add("0,-1,-2,-3,1,2");
	}

	@Test
	public void ignoreOver1000() {
		assertEquals(2, Calculator.add("1001,2"));
	}
	
	@Test
	public void ignore2Over1000() {
		assertEquals(1003, Calculator.add("1001,1000,3"));
	}

	
	@Test
	public void ignore3Over1000() {
		assertEquals(0, Calculator.add("2000,1500"));
	}

	@Test
	public void ignore4Over1000() {
		assertEquals(0, Calculator.add("1500"));
	}

	@Test
	public void anotherDelimiter() {
		assertEquals(3, Calculator.add("//;\n1;2"));
	}

	@Test
	public void anotherDelimiter2() {
		assertEquals(10, Calculator.add("//:\n2:5:3"));
	}

	@Test
	public void anotherDelimiter3() {
		assertEquals(12, Calculator.add("//a\n2a4a6"));
	}

	@Test
	public void anotherDelimiter4() {
		assertEquals(12, Calculator.add("//\\+\n2+4+6"));
	}

	@Test
	public void anotherDelimiter5() {
		assertEquals(22, Calculator.add("//\\*\n2*4*6*10"));
	}

	@Test
	public void anotherDelimiter6() {
		assertEquals(22, Calculator.add("//\\?\n2?4?6?10"));
	}

	@Test
	public void anotherDelimiterIgnoreOver1000() {
		assertEquals(12, Calculator.add("//#\n2#4#6#2000"));
	}

	@Test
	public void anotherDelimiterIgnoreOver10002() {
		assertEquals(17, Calculator.add("//#\n2#4,6\n3#2#3000"));
	}

	
	@Test
	public void anotherDelimiterNegativeNumbersThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negatives not allowed: -4,-4,-5");
		Calculator.add("//!\n2,-4!3!-4\n-5");
	}
}