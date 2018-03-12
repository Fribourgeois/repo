package ch.myapp.test.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MyDataSourceFactory {

	public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		InputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {

			fis = DataSource.class.getResourceAsStream("db.properties");
			if (fis == null) {
				fis = DataSource.class.getResourceAsStream("/db.properties");
			}
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}

}