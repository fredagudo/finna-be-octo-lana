package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.processor.DataProcessor;
import org.processor.FootBallProcessor;

/**
 * Footballprocessor test class
 * 
 * @author fred
 * 
 */
public class FootBallProcessorTest {
	DataProcessor processor;
	List<String[]> list;
	StringBuilder inputString;

	@Before
	public void before() {
		processor = new FootBallProcessor(null);
		list = new ArrayList<String[]>();
		list.add(new String[] { "11", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th" });
		list.add(new String[] { "4", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th" });
		list.add(new String[] { "1", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th" });
		list.add(new String[] { "44", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th" });
		processor.data = list;
		processor.sort();

	}

	@Test
	public void sortByVarience() {

		String[] res = list.get(0);
		assertSame(res[0], "1");
		res = list.get(1);
		assertSame(res[0], "4");
		res = list.get(2);
		assertSame(res[0], "11");
		res = list.get(3);
		assertSame(res[0], "44");

	}

	@Test
	public void highest() {
		String res [] = processor.highest();
		assertEquals( "44",res[0]);
	}

	@Test
	public void lowest() {
		String res [] = processor.lowest();
		assertEquals( "1",res[0]);
	}

	@Test 
	public void process() throws IOException{
		inputString = new StringBuilder("Source <a\n")	
		.append("href=\"http://sunsite.tut.fi/rec/riku/soccer_data/tab/93_94/table.eng0.01_02.html\">sunsite.tut.fi/rec/riku/soccer_data/tab/93_94/table.eng0.01_02.html</a>\n").append("  \n")
		.append("<pre>\n")
		.append("       Team            P     W    L   D    F      A     Pts\n")
		.append("    1. Arsenal         38    26   9   3    79  -  36    87\n")
		.append("    2. Liverpool       38    24   8   6    67  -  30    80\n")
		.append("    3. Manchester_U    38    24   5   9    87  -  45    77\n")
		.append("    4. Newcastle       38    21   8   9    74  -  52    71\n")
		.append("    5. Leeds           38    18  12   8    53  -  37    66\n")
		.append("    6. Chelsea         38    17  13   8    66  -  38    64\n")
		.append("    7. West_Ham        38    15   8  15    48  -  57    53\n")
		.append("    8. Aston_Villa     38    12  14  12    46  -  47    50\n")
		.append("    9. Tottenham       38    14   8  16    49  -  53    50\n")
		.append("   10. Blackburn       38    12  10  16    55  -  51    46\n")
		.append("   11. Southampton     38    12   9  17    46  -  54    45\n")
		.append("   12. Middlesbrough   38    12   9  17    35  -  47    45\n")
		.append("   13. Fulham          38    10  14  14    36  -  44    44\n")
		.append("   14. Charlton        38    10  14  14    38  -  49    44\n")
		.append("   15. Everton         38    11  10  17    45  -  57    43\n")
		.append("   16. Bolton          38     9  13  16    44  -  62    40\n")
		.append("   17. Sunderland      38    10  10  18    29  -  51    40\n")
		.append("   -------------------------------------------------------\n")
		.append("  18. Ipswich         38     9   9  20    41  -  64    36\n")
		.append("  19. Derby           38     8   6  24    33  -  63    30\n")
		.append("  20. Leicester       38     5  13  20    30  -  64    28\n")
		.append("</pre>Å\n");

		processor = new FootBallProcessor(new ByteArrayInputStream(inputString.toString().getBytes()));
		processor.process();

		String res [] = processor.lowest();
		assertEquals("1", res[0]);
		assertEquals("Aston_Villa", res[DataProcessor.NAME_COL]);
		
		res  = processor.highest();
		assertEquals("43", res[0]);
		assertEquals("Arsenal", res[DataProcessor.NAME_COL]);
		

	}
}
