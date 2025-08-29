package jspboard.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class ConnectionUtil {
	
	private static ConnectionUtil connectionUtil = new ConnectionUtil();
	private DataSource dataSource;
	
	private ConnectionUtil() {
		this.dataSource = DataSourceUtil.getInstance().getDataSource();
	}
	
	public static ConnectionUtil getInstance() {
		return connectionUtil;
	}
	
	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	public static void close(Connection conn, ResultSet rs, Statement stmt) 
		throws Exception {
		if (conn!=null) conn.close();
		if (rs!=null) rs.close();
		if (stmt!=null) stmt.close();
	}

}










