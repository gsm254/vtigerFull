package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertProjects {

	public static void main(String[] args) {
		Connection con = null;
		int result = 0;
		
		try {
			Driver driver = new Driver();
			
			DriverManager.registerDriver(driver);
			
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			
			Statement st = con.createStatement();
			String query = "insert into project values('TY_Project_254','gsp','21/12/2022','tecily','created',5);";
			
			result = st.executeUpdate(query);
		}
		catch(Exception e) {
			
		}
		finally {
			if(result>0) {
				System.out.println("inserted successfully");
			}
			else {
				System.out.println("failed to insert");
			}
		}
	}

}
