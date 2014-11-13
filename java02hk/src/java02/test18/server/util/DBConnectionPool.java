package java02.test18.server.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;

public class DBConnectionPool {
	String driver;
	String url;
	String username;
	String password;

	ArrayList<Connection> conList = new ArrayList<>();


	public DBConnectionPool(String driver, String url, String username,
			String password) throws Exception {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;

		Class.forName(driver);
	}
	public Connection getConnection() throws Exception {
		if(conList.size() > 0){
			return conList.remove(0);
		} else {
			return DriverManager.getConnection(url, username, password);
		}
	}

	public void returnConnection(Connection con) {
		try {
			if(!con.isClosed()) {
				conList.add(con);
			}
		} catch (Exception ex) {}
	}//returnConnection

	public void CloseAll(){
		for (Connection con : conList){
			try{con.close();} catch (Exception ex) {}
		}
	}//CloseAll
}
