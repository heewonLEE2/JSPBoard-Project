package jspboard.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceUtil {
	
	private static DataSourceUtil dataSourceUtil = new DataSourceUtil();
	private static Context context;
	private static DataSource dataSource;
	
	static {
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	private DataSourceUtil() {
	}
	
	public static DataSourceUtil getInstance() {
		return dataSourceUtil;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

}








