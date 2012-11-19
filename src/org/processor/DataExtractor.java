package org.processor;

import java.io.IOException;
import java.util.List;

public interface DataExtractor {
	
	/**
	 * Extract data from input source
	 * @return
	 * @throws IOException
	 */
	List<String[]> extract() throws IOException;
	
	/**
	 * Filter input source data according to a pattern
	 * @param inputRow
	 * @return
	 */
	public String filter(String inputRow);
}
