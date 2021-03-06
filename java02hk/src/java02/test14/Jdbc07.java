/*INSERT 실행
 * 
 *  => executeUpdate()를 실행한다.
 *  
 *  리눅스에서는 한글 값이 깨질 수 있다.
 *  해결책 => URL에 JDBC를 연결할 때, 연결정보에 문자집합을 설정해야한다.
			ex) jdbc:mysql://host:port/schema?useUnicode=true&characterEncoding=utf8

 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc07 {

	public static void main(String[] args) throws Exception {
		Connection con = null; 
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC 드라이버 로딩됨");
			con = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/studydb" +
					"?useUnicode=true&characterEncoding=utf8", 
					"study",	
					"study"); 
			System.out.println("DBMS에 연결됨");

			stmt = con.createStatement();
			System.out.println("스테이트먼트 객체 준비 완료");

			stmt.executeUpdate("INSERT INTO PRODUCTS(PNAME,QTY,MKNO)"
					+ " VALUES('넥서스10', 99, 6)");
			System.out.println("서버에 질의 완료. ResultSet 준비 완료.");

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
