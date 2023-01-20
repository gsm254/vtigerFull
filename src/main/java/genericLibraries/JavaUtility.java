package genericLibraries;

import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNum() {
		Random r = new Random();
		int random = r.nextInt(300);

		return random;
	}

	public String getSystemDate() {
		Date d = new Date();
		String date = d.toString();
		date = date.replaceAll(":", "-");

		return date;
	}

}
