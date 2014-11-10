/* 드라이버 로딩(Driver Loading)
 * JDBC Driver 정보를 담은 Main Class Loading
 *   => java.sql.Driver Interface를 구현한 Class를 Loading해야한다.
 *    => 이 클래스를 통해 Connection 객체를 얻을 수 있다.
 *    => 점조직 같은 형태다.
 */
package java02.test14;

import java.sql.DriverManager;

public class Jdbc01 {
	
	public static void main(String[] args) throws Exception {
		//방법1.  Class.forName()사용
		Class.forName("com.mysql.jdbc.Driver");   //강사님 강력 추천
		
		/*방법2. Driver Class에 Instance를 만들고 명시적으로 등록하기.
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); */
		

		
		//방법2의 단점 - 다른 DBMS로 접속하려면 코드를 변경해야한다.
		
	}
	
}
