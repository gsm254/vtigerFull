package testNg.practice;

import org.testng.annotations.Test;

public class DependPriority2 {
	@Test(priority = 3)
	public void create() {
		int i = 1/0;
		System.out.println("created");
	}

	@Test
	public void zapak() {
		System.out.println("zapaked");
	}

	@Test(dependsOnMethods = "create", priority = 1)
	public void delete() {
		System.out.println("deleted");
	}

	@Test(dependsOnMethods = "create", priority = -1)
	public void update() {
		System.out.println("updated");
	}

}
