/* 인스턴스 출력하기 => 
 - 인스턴스 값을 통째로 출력하기 => ObjectOutputStream 
 - 용어
   Serialize(Marshaling) => 바이트 배열로 만드는 것.
   Deserialize(Unmarshaling) => 바이트 배열을 가지고 인스턴스를 만드는 것. 
 */
package java02.test02;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//@SuppressWarnings("serial")
// 인스턴스 값들을 바이트 배열로 만드려면 Serialize를 허용해야 한다.
// java.io.Serializable 인터페이스를 구현하면 된다.
// Serializable 인터페이스
// 1) 메서드가 없다.
// 2) 단지 바이트 배열로 출력할 수 있다는 표시용이다.
class Score implements Serializable {
  // 인스턴스의 값을 출력할 때 클래스 버전 번호도 출력된다.
  // 읽어 들이는 쪽에서 자신이 갖고 있는 클래스와 같은 버전인지 검사할 때 
  // 이 값을 사용한다.
  private static final long serialVersionUID = 1L;
  
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float average;
  
  public Score() {}
  
  public Score(String n, int k, int e, int m) {
    name = n; kor = k; eng = e; math = m;
    sum = k + e + m;
    average = sum / 3.0f;
  }
}

public class Test14 {
  
  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("test14.dat");
    ObjectOutputStream out2 = new ObjectOutputStream(out);
    
    Score obj = new Score("홍길동", 100, 90, 80);
    
    // 인스턴스의 값을 바이트 배열로 만들어서 출력한다.
    // 단, 해당 클래스가 허락해야 한다.
    out2.writeObject(obj);
    
    // 닫을 때 꺼꾸로 닫는다.
    out2.close(); 
    out.close();
  }
  
 

}























