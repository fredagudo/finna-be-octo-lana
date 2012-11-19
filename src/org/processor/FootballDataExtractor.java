package org.processor;

import java.io.InputStream;

/**
 * Concrete implementation of DataExtractor for football data
 * 
 * @author fred
 *
 */
public class FootballDataExtractor extends StreamDataExtractor {

	private final static String FILTER_PATTERN = "\\s\\_?\\d+\\.?";
	private final static String SPLIT_PATTERN = "\\s+";;

	
	public FootballDataExtractor(InputStream inputStream) {
		super(inputStream);
	}

	/**
	 * set the pattern to split record into columns
	 */
	@Override
	public void setFilterPattern() {
		this.filterPattern = FILTER_PATTERN;

	}

	/**
	 * set the filter criteria
	 * 
	 */
	@Override
	public void setSplitPattern() {
		this.splitPattern = SPLIT_PATTERN;
	}
	
	
	
	

}
