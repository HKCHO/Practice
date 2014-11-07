/* 바이너리 데이터 출력
 * 
 */
package java02.test02;

import java.io.FileOutputStream;

public class Test09 {

  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("test09.dat");
    char[] str = {'A', 'B', 'C', '가', '각', '간'};
    for (char c : str) {
      out.write(c);
    }
    out.close();
  }

}













