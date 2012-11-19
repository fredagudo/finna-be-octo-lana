package org.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Abstract class that defines the methods to extract data from an input stream
 * filter the relevant lines of data and extract them into a list of arrays
 * 
 * @author fred
 * 
 */
public abstract class StreamDataExtractor implements DataExtractor {
	/**
	 * @uml.property name="filterPattern"
	 */
	protected String filterPattern = null;

	/**
	 * @uml.property name="splitPattern"
	 */
	String splitPattern = "\\s+";
	/**
	 * @uml.property name="inputStream"
	 */
	InputStream inputStream = null;

	/**
	 * Set the inout stream
	 * 
	 * @param inputStream
	 */
	public StreamDataExtractor(InputStream inputStream) {
		this.inputStream = inputStream;
		setFilterPattern();
		setSplitPattern();
	}

	/**
	 * Extract lines matching pattern
	 * 
	 * @param inputRow
	 * @return matching lines
	 */
	public String filter(String inputRow) {
		if (inputRow != null) {
			inputRow = inputRow.trim();

			if (inputRow != null && inputRow.length() > 0) {
				Pattern pat = Pattern.compile(filterPattern);

				Matcher match = pat.matcher(inputRow);
				if (match.find()) {
					return inputRow;

				}
			}
		}
		return null;
	}

	/**
	 * Extract relevant data from input stream, create new array with one extra
	 * for variance column
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String[]> extract() throws IOException {
		ArrayList<String[]> records = new ArrayList<String[]>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String str = null;

		try {
			while ((str = reader.readLine()) != null) {
				String[] splitArraay = split(filter(str));
				if (splitArraay != null) {
					int arrayLength = splitArraay.length;

					// create new array with one extra for variance
					String[] dest = new String[arrayLength + 1];
					System.arraycopy(splitArraay, 0, dest, 1, arrayLength);
					records.add(dest);
				}
			}

			return records;
		} catch (IOException e) {

			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Split the input row into array columns
	 */
	public String[] split(String inputRow) {
		return (inputRow != null ? inputRow.split(splitPattern) : null);
	}

	/**
	 * Set the pattern used for filtering the records
	 */
	abstract public void setFilterPattern();

	/**
	 * Set the pattern used for splitting the record into columns
	 */
	abstract public void setSplitPattern();
	
	
	
	// getters and setters
	public String getFilterPattern() {
		return filterPattern;
	}

	public void setFilterPattern(String filterPattern) {
		this.filterPattern = filterPattern;
	}

	public String getSplitPattern() {
		return splitPattern;
	}

	public void setSplitPattern(String splitPattern) {
		this.splitPattern = splitPattern;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
}
