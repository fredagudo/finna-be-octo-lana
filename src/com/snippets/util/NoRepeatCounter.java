package com.snippets.util;

import java.util.ArrayList;

/**
 * A counter that counts from 1 to max but only returns numbers whose digits
 * donÕt repeat.
 * 
 * For example, part of the output would be:
 * 
 * 8, 9, 10, 12 (11 is not valid) 98, 102, 103 (99, 100 and 101 are not valid)
 * Also:
 * 
 * Display the biggest number of digits jump (in the sequences above, itÕs 3: 98 -> 102) Display
 * the total count of numbers Give these two values for max=10000
 */

public class NoRepeatCounter {
	// start
	private int start = 0;
	// max counter
	private int max = 100;
	// highest skip in sequence
	private int greatestSkip;
	// list of numbers ignored
	private ArrayList<Integer> skipped;

	public NoRepeatCounter() {
		super();

	}

	public NoRepeatCounter(int start, int max) {
		this.skipped = new ArrayList<Integer>();
		this.start = start;
		this.max = max;
	}

	/**
	 * Check if number contains recurring numbers
	 * 
	 * @return
	 */
	public boolean recurringDigit(int counter) {
		// index of the string to be searched
		int index = 1;
		// convert to string
		String theNumber = counter + "";

		// split into single digits
		String theChars[] = theNumber.split("(?!^)");

		// for each character check if in remainder of string
		for (String aChar : theChars) {
			if (theNumber.substring(index++).contains(aChar))
				return true;
		}

		return false;
	}

	/**
	 * Output counter values from [start] to [max], skipping numbers with
	 * recurring digits i.e 11
	 * 
	 */
	public void count() {

		int lastSkipp = 0;
		for (int counter = start; counter < max + 1; counter++) {
			if (!recurringDigit(counter)) {
				System.out.print(counter +",");
				lastSkipp = 0;
			} else {
				skipped.add(counter);
				lastSkipp++;
				if (lastSkipp > greatestSkip)
					greatestSkip = lastSkipp;

			}

		}

	}

	/**
	 * Count of actual numbers displayed
	 * 
	 * @return
	 */
	public int totalDisplayed() {
		return max - start - skipped.size();
	}

	// getters and setters
	public int getGreatestJump() {
		return greatestSkip;
	}

	public void setGreatestJump(int greatestJump) {
		this.greatestSkip = greatestJump;
	}

	public ArrayList<Integer> getSkipped() {
		return skipped;
	}

	public void setSkipped(ArrayList<Integer> skipped) {
		this.skipped = skipped;
	}

	public static void main(String[] args) {

		NoRepeatCounter rep = new NoRepeatCounter(0, 105);
		rep.count();
		System.out.println("\nMax number of skipped : " + rep.getGreatestJump());
		System.out.println("Total displayed " + rep.totalDisplayed());

	}

}
