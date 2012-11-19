package org.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;

public class FootBallProcessor extends DataProcessor {

	
	public FootBallProcessor(InputStream inputStream) {
		DataProcessor.NAME_COL = 2;
		DataProcessor.VAL1_COL = 7;
		DataProcessor.VAL2_COL = 9;
		this.extractor = new FootballDataExtractor(inputStream);
	}


	/**
	 * Sort the data array by goals for and against variance
	 * 
	 */
	@Override
	public void sort() {
		Collections.sort(data, new Comparator<String[]>() {
			@Override
			public int compare(String[] strings, String[] otherStrings) {
				int a = Integer.valueOf(strings[0]);
				int b = Integer.valueOf(otherStrings[0]);
				return a < b ? -1 : a == b ? 0 : 1;
			}
		});

	}

	/**
	 * Highest variance in the list
	 */
	@Override
	public String [] highest() {
		return data.get(data.size() - 1);
	}

	/**
	 * Lowest variance in the list
	 */
	@Override
	public String [] lowest() {
		return data.get(0);
		
	}
	
	/**
	 * 
	 */
	public void displayLowestResult(){
		String [] res = lowest();
		System.out.println("Result =  " + res[NAME_COL]);
	}

	/**
	 * Process then display result
	 * 
	 * @throws IOException
	 */
	public void run(String file) throws IOException {
		process();
		displayLowestResult();
	}

	
	public static void main(String [] args){
		if(args.length != 0){
			FootBallProcessor processor;
			try {
				processor = new FootBallProcessor(new FileInputStream(new File(args[0])));
				processor.process();
				processor.displayLowestResult();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
