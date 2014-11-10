/*ResultSet 객체를 통해 서버에서 결과를 가져오기
 * 
 * next() => 서버에서 결과를 가져온다. 성공이면 true, 실패면 false
 * getInt(컬럼명) 또는 getInt(컬럼인덱스)  => 가져온 데이터의 컬럼값을 리턴한다.
 * getXXX() => 컬럼의 데이터 형에 따라 XXX의 이름이 바뀐다.
 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc05 {

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

			//5.결과 가져오기
			// => 현재 결과를 가져온 상태가 아닙니다.
			while (rs.next()) {
				//결과를 가져왔다면 데이터는 resultSet 객체에 들어있다.
				System.out.print(rs.getInt("PNO") + ") ");
				System.out.print(rs.getString("PNAME") + ",");
				System.out.print(rs.getInt("QTY") + ",");
				System.out.println(rs.getInt("MKNO"));
				
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
