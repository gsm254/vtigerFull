package testNg.practice;

import org.testng.annotations.Test;

public class DependPriority03 {
	
	@Test(priority = 3,invocationCount = 3)
	public void create() {
		System.out.println("created");
	}

	@Test
	public void zapak() {
		System.out.println("zapaked");
	}

	@Test(dependsOnMethods = "zapak", priority = 1)
	public void delete() {
		System.out.println("deleted");
	}

	@Test(dependsOnMethods = "create", priority = -1)
	public void update() {
		System.out.println("updated");
	}

}
