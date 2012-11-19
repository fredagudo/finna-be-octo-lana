package org.processor;

import java.io.IOException;
import java.util.List;

public abstract class DataProcessor {

	public static int NAME_COL = 3; // row name column
	protected static int VAL1_COL = 0; // variance input1 column
	protected static int VAL2_COL = 0; // variance input2 column

	/**
	 * @uml.property name="extractor"
	 * @uml.associationEnd
	 */
	protected StreamDataExtractor extractor;
	/**
	 * @uml.property name="data"
	 */
	public List<String[]> data;

	/**
	 * Process the data
	 * 
	 * @throws IOException
	 */
	public void process() throws IOException {
		data = extractor.extract();
		buildVarienceList();
		sort();
	}
	
	/**
	 * Update data list with variance for each record in the first position
	 * 
	 */
	public void buildVarienceList() {
		for (String[] rec : data) {	
			rec[0] = calcVariance(rec[VAL1_COL], rec[VAL2_COL]);
		}
		
	}

	/**
	 * Calc the positive variance between 2 numbers
	 * 
	 * @param val1
	 * @param val2
	 * @return Variance
	 */
	protected String calcVariance(String val1, String val2) {

		return String.valueOf(calcVariance(Integer.parseInt(val1), Integer.parseInt(val2)));
	}
	
	

	/**
	 * Calc the positive variance between 2 numbers
	 * 
	 * @param val1
	 * @param val2
	 * @return Variance
	 */
	protected int calcVariance(int val1, int val2) {
		int variance = val1 - val2;
		return variance = (variance < 0 ? -variance : variance);
	}

	abstract public void sort();

	abstract public String [] highest();

	abstract public String [] lowest();

}
