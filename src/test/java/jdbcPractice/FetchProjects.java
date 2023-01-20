package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchProjects {

	public static void main(String[] args) {
		Connection con = null;
		
	
		ResultSet result;
		try {
			Driver driver = new Driver();
			
			DriverManager.registerDriver(driver);
			
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			
			Statement st = con.createStatement();
			String query = "select * from project;";
			
			result = st.executeQuery(query);
			
			while(result.next()) {
				System.out.println(result.getString(1)+"  "+result.getString(4));
			}
		}
		catch(Exception e) {
			
		}
			
	}

}
