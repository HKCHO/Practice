/* File 기본 사용
 * 
 */
package java02.test02;

import java.io.File;

public class Test05 {
  public static void main(String[] args) throws Exception{
    File f = new File("../../java63/java01");
    
    // 결과:/home/bit/git/java63/java02/../../java63/java01
    System.out.println(f.getAbsolutePath());
    
    // 결과:/home/bit/git/java63/java01
    System.out.println(f.getCanonicalPath());
    
    // 결과:../../java63/java01
    System.out.println(f.getPath());
    
    // 결과:java01
    System.out.println(f.getName());
    
    // 결과:../../java63
    System.out.println(f.getParent());
    
  }
}














