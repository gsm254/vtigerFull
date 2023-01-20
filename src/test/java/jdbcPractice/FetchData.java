package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchData {

	public static void main(String[] args) throws SQLException {
		
		Connection con;
		
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","gowtham254@S");
		
		Statement st = con.createStatement();
		String query = "select sname,branch from students;";
		
		ResultSet result = st.executeQuery(query);
		
		while(result.next()) {
			System.out.println(result.getString(1)+"  "+result.getString(2));
		}
		
		con.close();
	}

}
