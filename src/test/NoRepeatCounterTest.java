package test;



import static org.junit.Assert.*;

import org.junit.Test;

import com.snippets.util.NoRepeatCounter;

public class NoRepeatCounterTest {

	public NoRepeatCounterTest() {

	}

	/**
	 * Test for duplicate digits in a number
	 * 
	 * @param counter
	 */
	@Test
	public void hasDupsTest() {

		assertFalse(new NoRepeatCounter().recurringDigit(123));
		assertFalse(new NoRepeatCounter().recurringDigit(1));
		assertTrue(new NoRepeatCounter().recurringDigit(11));
		assertTrue(new NoRepeatCounter().recurringDigit(1203421));
		assertFalse(new NoRepeatCounter().recurringDigit(1234567890));
		assertTrue(new NoRepeatCounter().recurringDigit(01203421));
	}

	@Test
	public void resultsTest() {
		NoRepeatCounter rep = new NoRepeatCounter(1, 100);
		rep.count();
		assertEquals(2, rep.getGreatestJump());
		assertEquals(89, rep.totalDisplayed());

		rep = new NoRepeatCounter(0, 20);
		rep.count();
		assertEquals(1, rep.getGreatestJump());
		assertEquals(19, rep.totalDisplayed());

		rep = new NoRepeatCounter(1, 200);
		rep.count();
		assertEquals(10, rep.getGreatestJump());
		assertEquals(161, rep.totalDisplayed());

		rep = new NoRepeatCounter(1, 105);
		rep.count();
		assertEquals(3, rep.getGreatestJump());
		assertEquals(93, rep.totalDisplayed());

	}

}
