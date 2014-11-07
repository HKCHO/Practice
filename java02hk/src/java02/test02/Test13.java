/* Test12에서 출력한 값을 읽기
 - DataOutputStream으로 출력한 값을 읽기
 * 
 * 
 */
package java02.test02;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Test13 {
  
  static class Score {
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
  
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("test12.dat");
    DataInputStream in2 = new DataInputStream(in);
    
    Score obj = new Score();
    obj.name = in2.readUTF();
    obj.kor = in2.readInt();
    obj.eng = in2.readInt();
    obj.math = in2.readInt();
    obj.sum = in2.readInt();
    obj.average = in2.readFloat();
    
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
  
  public static void main01(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("test12.dat");
    
    Score obj = new Score();
    
    int length = (in.read() << 8) | in.read();
    
    byte[] buf = new byte[length];
    for (int i = 0; i < length; i++) {
      buf[i] = (byte)in.read();
    }
    
    obj.name = new String(buf, "UTF-8");
    obj.kor = (in.read() << 24) | (in.read() << 16) |
              (in.read() << 8) | in.read();
    obj.eng = (in.read() << 24) | (in.read() << 16) |
        (in.read() << 8) | in.read();
    obj.math = (in.read() << 24) | (in.read() << 16) |
        (in.read() << 8) | in.read();
    obj.sum = (in.read() << 24) | (in.read() << 16) |
        (in.read() << 8) | in.read();
    obj.average = (in.read() << 24) | (in.read() << 16) |
        (in.read() << 8) | (in.read());
    
    
    System.out.println(obj.name);
    System.out.println(obj.kor);
    System.out.println(obj.eng);
    System.out.println(obj.math);
    System.out.println(obj.sum);
    System.out.println(obj.average);
    
    
    // 닫을 때 꺼꾸로 닫는다.
    in.close();
  }
  
 

}























