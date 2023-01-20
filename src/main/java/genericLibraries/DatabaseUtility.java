package genericLibraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn = null;

	public void connectToDb() throws SQLException {
		Driver d = new Driver();

		DriverManager.registerDriver(d);
		conn = DriverManager.getConnection(IPathConstants.DBURL, IPathConstants.DBUSERNAME, IPathConstants.DBPASSWORD);
	}
	
	public String getDataFromDb(String query,String columnIndex,String expData) throws SQLException {
		ResultSet result = conn.createStatement().executeQuery(query);
		boolean flag = false;
		
		while(result.next()) {
			String data = result.getString(columnIndex);
			System.out.println(data);
			if(data.equalsIgnoreCase(expData)) {
				flag=true;
				break;
				
			}
			
		}
		if(flag) {
			System.out.println(expData+" project is created");
			return expData;
		}
		else {
			System.out.println("project not created");
			return "";
		}
	}
	public void closeDb() throws SQLException {
		conn.close();
	}

}
