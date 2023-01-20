package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class UpdateData {
	
	public static void main(String[] args) throws SQLException {
		
		Connection con=null;
		int result=0;
		
		try {
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","gowtham254@S");
		
		Statement st = con.createStatement();
		String query = "insert into students values('Suresh','rajajinagar','sdet',1);";
		
		 result = st.executeUpdate(query);
		}
		catch(Exception e){
			
		}
		finally {
			if(result>0){
				System.out.println("data inserted succesfully");
			}
			else {
				System.out.println("data inserted");
			}
			con.close();
		}
		
		
		
	}

}
