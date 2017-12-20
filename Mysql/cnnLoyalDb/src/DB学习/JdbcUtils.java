package DB学习;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.omg.CORBA.PRIVATE_MEMBER;

public class JdbcUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn=null;
	
	
	//��ȡ�����ļ�properties---����һ
/*	static{
		
		ResourceBundle bundle = ResourceBundle.getBundle("DbTest_zh_CN");
		
		driver = bundle.getString("jdbc.driver");
		url = bundle.getString("jdbc.url");
		user = bundle.getString("jdbc.user");
		password = bundle.getString("jdbc.password");
		
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
		
	}
    */
	
	//��ȡ�����ļ�properties---������
	static{
		try {
			
			InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("DbTest_zh_CN.properties");//�������𣺴˴�Ҫ�Ӻ�׺.properties
			//ʹ��properties��������ʹ��load������������ָ����
			Properties props = new Properties();
			props.load(is);
			
			driver = props.getProperty("jdbc.driver");
			url = props.getProperty("jdbc.url");
			user = props.getProperty("jdbc.user");
			password = props.getProperty("jdbc.password");
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	
	
	public static Connection getConnection(){
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			
			System.out.println("Connection���ӳɹ���");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	

}
