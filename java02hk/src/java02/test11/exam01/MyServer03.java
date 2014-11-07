/* 클라이언트와 여러 번 데이터 주고 받기
 */
package java02.test11.exam01;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer03 {
  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) throws Exception {
    System.out.println("서버 소켓 생성");
    ServerSocket ss = new ServerSocket(8888, 2);

    System.out.println("클라이언트의 연결을 기다리는 중...");
    Socket socket = ss.accept();
    System.out.println("대기중에 있는 클라이언트와 연결됨");
    
    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());
    
    String message = null, line = null;
    while(true) {
      line = in.nextLine();
      System.out.println(line);
      
      if (line.equalsIgnoreCase("quit")) {
        out.println("goodbye");
        break;
      }
      
      message = prompt();
      out.println(message);
    }
    
    in.close();
    out.close();
    socket.close();
    ss.close();
    keyboard.close();
  }

  private static String prompt() {
    System.out.print(">");
    String message = keyboard.nextLine();
    return message;
  }

}













