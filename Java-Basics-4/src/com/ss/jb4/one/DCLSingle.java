package com.ss.jb4.one;

public class DCLSingle {

	// instance of singleton
	private static volatile DCLSingle instance;
	
	public static DCLSingle getInstance() {
		// first check
		if (instance == null) {
			// thread lock this block
			synchronized(DCLSingle.class) {
				// second check inside of sync
				if (instance == null) {
					instance = new DCLSingle();
				}
			}
		}
		return instance;
	}
	
	// private constructor
	private DCLSingle() {
	}
}