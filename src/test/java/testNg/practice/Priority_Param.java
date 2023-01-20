package testNg.practice;

import org.testng.annotations.Test;

public class Priority_Param {
	
	/**
	 * priority (lower to higher)
	 * by default(priority is 0) it execute according to ascii values of method name
	 * 
	 * 
	 */
	@Test(priority = 0)
	public void create() {
		
	}
	@Test(priority = 1)
	public void delete() {
		
	}
	@Test(priority = 2)
	public void update() {
		
	}

}
