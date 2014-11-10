/* Select문 실행하기
 * 
 * executeQuery(SELECT문)
 * executeUpdat(INSERT/DELETE/UPDATE문);
 * elecute(ALL);
 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc04 {

	public static void main(String[] args) throws Exception {
		Connection con = null; 
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC 드라이버 로딩됨");
			con = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/studydb", 
					"study",	
					"study"); 
			System.out.println("DBMS에 연결됨");
			
			stmt = con.createStatement();
			System.out.println("스테이트먼트 객체 준비 완료");
			
			//4. Select문 실행하기 => 서버에서 결과를 하나씩 가져오는 역할자를 리턴한다.
			//		=> 즉, java.sql.ResultSet 구현체를 리턴한다.
			rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
			System.out.println("서버에 질의 완료. ResultSet 준비 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try{rs.close();} catch (Exception ex) {}
			System.out.println("ResultSet객체의 자원 해제");
			try{stmt.close();} catch (Exception ex) {}
			System.out.println("STMT객체의 자원 해제");
			try{con.close();} catch (Exception ex) {}
			System.out.println("DBMS와 연결 끊음");
			
		}
	}
}
