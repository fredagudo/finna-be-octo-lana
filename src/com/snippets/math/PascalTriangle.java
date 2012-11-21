package com.snippets.math;

/**
 * Class representing pascals triangle.
 * 										1
 * 									1		1
 * 								1		2		1
 * 							1		3		3		1
 * 						1		4		6		4		1
 * 					1		5		10		10		5		1
 * 				1		6		15		20		15		6		1
 * 			1		7		21		35		25		21		7		1
 * 
 * @author fred
 * 
 */
public class PascalTriangle {

	public int triangle[][];
	private int rows = 0;

	public PascalTriangle(int rows) {
		this.rows = rows;
		triangle = new int[rows][];
	}

	/**
	 * calculate value at row / position
	 * 
	 * @param row
	 * @param pos
	 * @return
	 */
	public int calcPascal(int row, int pos) {

		// check boundaries which are always 1
		if (pos == 1)  // left boundary
			return 1;
		else if (row == pos)  // right boundary
			return 1;
		else
			return calcPascal(row - 1, pos - 1) + calcPascal(row - 1, pos);
	}

	/**
	 * 
	 * Create the triangle to n number of rows
	 */
	public void create() {
		for (int i = 1; i < rows + 1; i++) {
			// for every row n there is n positions
			triangle[i - 1] = new int[i];

			// build up positions
			for (int c = 1; c < i + 1; c++) {
				// System.out.print(calcPascal(i, c));
				triangle[i - 1][c - 1] = calcPascal(i, c);
			}
		}
	}

	/**
	 * Output to screen
	 */
	public void print() {
		int spaceCounter = triangle.length;

		for (int row = 0; row < triangle.length; row++) {

			// print spaces for formatting
			for (int i = 0; i < spaceCounter; i++) {
				System.out.print(" ");
			}

			// print value at position
			for (int pos : triangle[row]) {
				System.out.print(pos + " ");
			}
			spaceCounter--;
			// new line formatting
			System.out.println();
		}
	}

}
