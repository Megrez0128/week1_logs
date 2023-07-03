package org.example;

import org.apache.log4j.Logger;
public class Test {
	private static final Logger logger = Logger.getLogger(Test.class);
	
	public void doSomething() {
//		logger.debug("DEBUG message");
		logger.info("INFO message");
//		logger.warn("WARN message");
//		logger.error("ERROR message");
//		logger.fatal("FATAL message");
	}
}
