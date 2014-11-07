/* Quiz
 - 예외 처리 강화
 파일을 읽을 때,
 1) 파일이 존재하지 않으면, 다음 문장 출력
    xxxx.xxx 파일이 존재하지 않습니다.
 2) 디렉토리라면 
    xxxx.xxx 는 파일이 아니라 디렉토리입니다.

 파일을 쓸 때,
 1) 파일이 이미 존재한다면,
    xxxx.xxx  파일이 이미 존재합니다. 덮어 쓰시겠습니까?(y/n)y
    xxxx.xxx 파일을 출력하였습니다.

 2) 파일이 존재하지 않는다면,
    xxxx.xxx 파일을 출력하였습니다.
 */
package java02.test02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Test03 {

  public static void main(String[] args)  {
    FileInputStream in = null;
    FileOutputStream out = null;
    Scanner scanner = new Scanner(System.in);
    try{
      File file = new File(args[0]);
      if (!file.exists()) {
        System.out.println(args[0] + " 파일이 존재하지 않습니다.");
        return;
      } else if (file.isDirectory()) {
        System.out.println(args[0] + "는 파일이 아니라 디렉토리입니다.");
        return;
      }

      in = new FileInputStream(file); 

      int index = args[0].lastIndexOf('.');
      String newFileName = args[0].substring(0, index)
          + "-01" + args[0].substring(index);

      File outputFile = new File(newFileName);


      if(outputFile.exists()){
        System.out.println("파일이 이미 존재합니다. 덮어 쓰시겠습니까?(y/n)");
        if(!scanner.nextLine().equals("y")){
          return;
        }
      }

      out = new FileOutputStream(outputFile);
      int b = 1;

      while ((b = in.read()) != -1 ) {
        out.write(b);
      } 

    } catch (IOException ex) {   
      System.out.println("읽기 오류!");

    } finally {
      try {in.close();} catch (Exception ex) {}
      try {out.close();} catch (Exception ex) {}
    }
  }

}






