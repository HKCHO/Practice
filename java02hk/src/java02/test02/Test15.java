/* Deserialize => 바이트 배열을 가지고 인스턴스 생성
 * 
 * 
 */
package java02.test02;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test15 {
  /* Test14.java 소스에서 생성한 Score 클래스 사용 */
  
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("test14.dat");
    ObjectInputStream in2 = new ObjectInputStream(in);
    
    Score obj = (Score)in2.readObject();
    
    System.out.println(obj.name);
    System.out.println(obj.kor);
    System.out.println(obj.eng);
    System.out.println(obj.math);
    System.out.println(obj.sum);
    System.out.println(obj.average);
    
    
    // 닫을 때 꺼꾸로 닫는다.
    in2.close();
    in.close();
  }
}























