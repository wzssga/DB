package jdbc_DataSorce;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedList;

import javax.activation.DataSource;

import jdbc_Utils.JdbcUtils;
/**
 * 自定义线程池（未经改造）
 * @author wang
 * 
 */
public class MyDataSource implements DataSource {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	static {
		for (int i = 0; i < 3; i++) {
			Connection conn = JdbcUtils.getConnection();
			pool.add(conn);
		}

	}

	public  Connection getConnection() {
		if (pool.isEmpty()) {
			for (int i = 0; i < 3; i++) {
				Connection conn = JdbcUtils.getConnection();
				pool.add(conn);
			}
		}
		return pool.removeFirst();
	}
    
	public  void release(Connection conn) {
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
