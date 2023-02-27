package com.noorteck.qa.utils;

public class DemoEnviorment {

	public static void main(String[] args) {
		
		/**
		 * When we create Enviorment class obj 
		 * inside the constructor it will load the json file
		 * and store inside map of maps
		 * 
		 * 
		 */
		Environment obj=new Environment();
		
		/**
		 * setEnvironment() method filters the region and stores 
		 * in map then we can access the map using getEnvData
		 * by passing the key to retrieve the value
		 * 
		 */

		obj.setEnvironment("scrum");
		/**
		 * returns the value of the key
		 */
		System.out.println(obj.getEnvData("username"));
	}
}
