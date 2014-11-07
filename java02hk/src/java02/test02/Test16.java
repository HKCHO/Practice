/* Buffer의 사용
 - 한 바이트씩 데이터를 읽는 것 보다,
   여러 바이트를 한꺼번에 읽는 것이 속도가 더 빠르다.
 - 이유:
   데이터 읽기 속도 = Data Seek Time + Data Transfer Time
   예) 퀀텀 하드
   Data Seek Time(데이터의 위치를 찾는 시간): 4.2ms => 0.0042초
   Data Transfer time(데이터 전송 시간): 3Gb / sec => 0.000003초 / 1byte
   
   1byte 읽기 시간 = 0.0042 + 0.000003초 = 0.004203초 
   100byte 읽기 시간 = 0.004203초 * 100 = 0.4203초
   
   한번 찾았을 때 100바이트 일기 = 0.0042 + (0.000003 * 100)
                              = 0.0042 + 0.0003 = 0.0045초
                              
   메모리(10ns) = 0.00000001초 
   1억 * 0.00000001초 = 1초
   1억 * 0.004203초 = 420300 초 = 4.8일
                              
 */
package java02.test02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Test16 {
  // BufferedInputStream 도구 사용
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("/home/bit/test.pdf");
    BufferedInputStream in2 = new BufferedInputStream(in);
    
    int b;
    
    long start = System.currentTimeMillis();
    
    while((b = in2.read()) != -1) {}
    long end = System.currentTimeMillis();
    
    System.out.println(end - start);
    
    in2.close();
    in.close();
  }
  
 //버퍼 사용후
 public static void main02(String[] args) throws Exception {
   FileInputStream in = new FileInputStream("/home/bit/test.pdf");
   
   byte[] buf = new byte[2048];
   int len = 0;
   
   long start = System.currentTimeMillis();
   
   while((len = in.read(buf)) != -1) {}
   long end = System.currentTimeMillis();
   
   System.out.println(end - start);
   
   in.close();
 }
 
  // 버퍼 사용전
  public static void main01(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("/home/bit/test.pdf");
    int b;
    long start = System.currentTimeMillis();
    while((b = in.read()) != -1) {}
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    in.close();
  }
}

























