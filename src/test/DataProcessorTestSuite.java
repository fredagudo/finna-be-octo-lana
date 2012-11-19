package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.FootBallDataExtractorTest;
import test.FootBallProcessorTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	  FootBallDataExtractorTest.class, 
	  FootBallProcessorTest.class}
	)

public class DataProcessorTestSuite {

}
