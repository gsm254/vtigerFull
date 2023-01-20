package genericLibraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	Properties prop;
	FileInputStream fis;
	{
		try
		{
			FileInputStream fis = new FileInputStream(IPathConstants.FILEPATH);
			prop = new Properties();
			prop.load(fis);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String readDataFromProperty(String key) throws IOException {

		String value = prop.getProperty(key);
		return value;

	}

	public void writeDataIntoProperty(String key, String value) {
		prop.setProperty(key, value);
	}
}
