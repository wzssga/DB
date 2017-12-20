package DB学习;

import java.sql.Connection;

import com.mysql.jdbc.Statement;

public class JdbcUtilsText {

	public static void main(String[] args) {
		try {
			Connection conn = JdbcUtils.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			String sql = "insert into student(name,sex) values ('wz','nan');";	
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

	}

}
