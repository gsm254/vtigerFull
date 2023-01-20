package testNg.practice;

import org.testng.annotations.Test;

public class TestAllAnnotaions extends BaseClassAnnotaions {

	@Test(invocationCount = 3)
	public void annoTest() {
		System.out.println("script executed");
	}
	@Test
	public void annoTest2() {
		System.out.println("script2 executed");
	}
}
