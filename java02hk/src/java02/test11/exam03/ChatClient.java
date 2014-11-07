/* 1대 1 채팅 클라이언트 프로그램
 * 
 */
package java02.test11.exam03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  static Scanner keyboard = new Scanner(System.in);
  static String name;
  static String serverAddress;
  
  public static void main(String[] args) throws Exception {
    Socket socket = null;
    PrintStream out = null;
    Scanner in = null;

    try {
      System.out.print("이름: ");
      name = keyboard.nextLine();
      
      System.out.print("서버 주소: ");
      serverAddress = keyboard.nextLine();
      
      System.out.println("서버와 연결 중...");
      
      socket = new Socket(serverAddress, 8888);
      
      System.out.println("서버와 연결 성공!");
      
      in = new Scanner(socket.getInputStream());
      out = new PrintStream(socket.getOutputStream());
      
      ChatReaderThread readerThread = new ChatReaderThread(in);
      readerThread.start();
      
      String message = null, line = null;
      
      out.println("hello " + name);
      
      while (true) {
        message = prompt();
        out.println(message); 
        
        if (message.equalsIgnoreCase("quit"))
          break;
      }
      
      
    } catch (Exception e) {
      // 오류 발생 무시!
    } finally {
      try {in.close();} catch (Exception e) {} 
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
      try {keyboard.close();} catch (Exception e) {}
    }
  }
  
  private static String prompt() {
    System.out.print(">");
    String message = keyboard.nextLine();
    return message;
  }

}
















