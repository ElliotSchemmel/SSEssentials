package com.ss.jb5.two;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SampleSingleton {

	private static Connection conn = null;
	
	private static SampleSingleton instance = null;
	
	public static SampleSingleton getInstance() {
		if (instance == null) instance = new SampleSingleton();
		return instance;
	}
	
}
