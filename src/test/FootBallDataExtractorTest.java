package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.processor.DataExtractor;
import org.processor.FootballDataExtractor;
import org.processor.StreamDataExtractor;

public class FootBallDataExtractorTest {
	private DataExtractor extractor;

	@Before
	public void setUpBeforeClass() throws Exception {
		extractor = new FootballDataExtractor(null);
	}

	/**
	 * Test all possible input string options for the filter
	 */
	@Test
	public void filter() {
		assertNull(extractor.filter(null));
		assertNull(extractor.filter(""));
		assertNotNull(extractor.filter("10. Blackburn       38    12  10  16    55  -  51    46"));
		assertNull(extractor.filter(" -------------------------------------------------------"));
		assertNull(extractor.filter("Source <a"));
		assertNull(extractor.filter("href=\"http://sunsite.tut.fi/rec/riku/soccer_data/tab/93_94/table.eng0.01_02.html\">sunsite.tut.fi/rec/riku/soccer_data/tab/93_94/table.eng0.01_02.html</a>"));
		assertNull(extractor.filter("<pre>"));
	}
	
	
	@Test
	public void extract() throws IOException{
		
		String str = "<pre>";
				
		((StreamDataExtractor)extractor).setInputStream(new ByteArrayInputStream(str.getBytes()));
		List<String[]> res = extractor.extract();
		assertTrue(res.isEmpty());
		
		str = "";
		((StreamDataExtractor)extractor).setInputStream(new ByteArrayInputStream(str.getBytes()));
		res = extractor.extract();
		assertTrue(res.isEmpty());

	    str = "2. Liverpool       38    24   8   6    67  -  30    80\n" +
	    "" +
	    "3. Manchester_U    38    24   5   9    87  -  45    77\n" +
	    "4. Newcastle       38    21   8   9    74  -  52    71\n";

		((StreamDataExtractor)extractor).setInputStream(new ByteArrayInputStream(str.getBytes()));
		 res = extractor.extract();
		assertFalse(res.isEmpty());
		assertEquals(3,res.size());
	}
	
	@Test
	public void split(){

		String[]  res = ((StreamDataExtractor)extractor).split("11. Southampton     38    12   9  17    46  -  54    45");
		assertEquals(10, res.length);
		
	}
}
