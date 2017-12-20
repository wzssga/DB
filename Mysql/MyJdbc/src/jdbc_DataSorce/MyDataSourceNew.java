package jdbc_DataSorce;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedList;

import javax.activation.DataSource;

import jdbc_Utils.JdbcUtils;
/**
 * 自定义线程池（经改造-使用MyConnection，close()方法已经经过修改）
 * @author wang
 * 
 */
public class MyDataSourceNew implements DataSource {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	static {
		for (int i = 0; i < 3; i++) {
			Connection conn = JdbcUtils.getConnection();
			MyConnection myconn = new MyConnection(conn,pool);//此时conn已经过改造成为myconn
			pool.add(myconn);
		}

	}

	public  Connection getConnection() {
		if (pool.isEmpty()) {
			for (int i = 0; i < 3; i++) {
				Connection conn = JdbcUtils.getConnection();
				MyConnection myconn = new MyConnection(conn,pool);//此时conn已经过改造成为myconn
				pool.add(myconn);
			}
		}
		return pool.removeFirst();
	}
    
	public void release(Connection conn) {
		pool.add(conn);
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
