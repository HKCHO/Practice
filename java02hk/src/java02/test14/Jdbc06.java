/*getXXX(컬럼인덱스) 사용
 * => 컬럼인덱스는 1부터 시작한다!!
 * => 문제점 : 인덱스는 코드를 해석하기 어렵게 만든다.
 * => select * from 테이블명
 *      : 컬럼 선택절에 와일드카드(*)를 사용하면 실행 속도가 느리다.
 *      : 정확하게 컬럼을 선택하여라.
 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc06 {

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

			rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
			System.out.println("서버에 질의 완료. ResultSet 준비 완료.");

			while (rs.next()) {  //인덱스는 코드를 해석하기 어렵게 하므로 권장하지 않는다.
				System.out.print(rs.getInt(1) + ") ");
				System.out.print(rs.getString(2) + ",");
				System.out.print(rs.getInt(3) + ",");
				System.out.println(rs.getInt(4));
				
			}
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
