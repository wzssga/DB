package jdbcText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc_DataSorce.MyDataSource;
import jdbc_Utils.JdbcUtils;

public class TextMyDataSource {
	/**
	 * 添加用户 使用未改造过的connection
	 * 
	 * @throws SQLException
	 */
	public void textAdd() throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		MyDataSource dataSource = new MyDataSource();

		try {
			conn = dataSource.getConnection();

			String sql = "insert into student values (?,?);";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "zxt");
			pstat.setString(2, "nv");
			int row = pstat.executeUpdate();
			if (row > 0) {
				System.out.println("添加成功！");
			} else {
				System.out.println("添加失敗！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			dataSource.release(conn);
		}

	}

	/**
	 * 添加用户 使用改造过的connection
	 * 
	 * @throws SQLException
	 */
	public void textAddNew() throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		MyDataSource dataSource = new MyDataSource();

		try {
			conn = dataSource.getConnection();
			String sql = "insert into student values (?,?);";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "1zxt");
			pstat.setString(2, "nv");
			int row = pstat.executeUpdate();
			if (row > 0) {
				System.out.println("添加成功！");
			} else {
				System.out.println("添加失敗！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.release(conn, pstat, null);
		}

	}

	public static void main(String[] args) throws SQLException {
		TextMyDataSource text = new TextMyDataSource();
		text.textAddNew();
	}

}
