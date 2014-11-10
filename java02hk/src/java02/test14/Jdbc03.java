/* Statement 객체 얻기
   =>DMBS에 SQL문을 보내는 역할을 수행한다.
   =>java.sql.Connection 구현체를 통해서 얻을 수 있다.
 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc03 {

	public static void main(String[] args) throws Exception {
		Connection con = null; 
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC 드라이버 로딩됨");
			con = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/studydb", 
					"study",	
					"study"); 
			System.out.println("DBMS에 연결됨");
			
			//3. Statement 객체 얻기
			stmt = con.createStatement();
			System.out.println("스테이트먼트 객체 준비 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try{stmt.close();} catch (Exception ex) {}
			System.out.println("STMT객체의 자원 해제");
			try{con.close();} catch (Exception ex) {}
			System.out.println("DBMS와 연결 끊음");
			
		}
	}
}
