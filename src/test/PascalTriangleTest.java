package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.snippets.math.PascalTriangle;

public class PascalTriangleTest {
	PascalTriangle pt;

	public PascalTriangleTest() {
	}

	@Before
	public void setup() {
		this.pt = new PascalTriangle(7);
		pt.create();
		pt.print();

	}

	@Test
	public void createTest() {

		// check that correct values are in correct position
		assertEquals(1, pt.triangle[0][0]);
		assertEquals(1, pt.triangle[6][0]);
		assertEquals(1, pt.triangle[6][6]);
		assertEquals(2, pt.triangle[2][1]);
		assertEquals(5, pt.triangle[5][4]);
		assertEquals(20, pt.triangle[6][3]);

	}

	@Test
	public void calcPascalTest() {
		assertEquals(1, pt.calcPascal(0, 0));
		assertEquals(1, pt.calcPascal(6, 6));
		assertEquals(1, pt.calcPascal(2, 1));
		assertEquals(4, pt.calcPascal(5, 4));
		assertEquals(10, pt.calcPascal(6, 3));
	}
}
