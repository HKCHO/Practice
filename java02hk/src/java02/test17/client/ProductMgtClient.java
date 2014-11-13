package java02.test17.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ProductMgtClient {

  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    
    Socket socket = null;
    Scanner in = null;
    PrintStream out = null;
    
    String command = null;
    String message = null;
    
    while (true) {
      System.out.print("명령> ");
      command = keyboard.nextLine();
      
      if (command.startsWith("exit")) {
        break;
      }
      try {
        socket = new Socket("192.168.0.175", 8888);
        in = new Scanner(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
        
        out.println(command);
        while (true) {
          message = in.nextLine();
          if (message.equals("")) {
            break;
          }
          System.out.println(message);
        }
      } catch (Exception ex) {
        System.out.println("명령어 실행 중 오류 발생!");
        
      } finally {
        try {in.close();} catch (Exception e) {}
        try {out.close();} catch (Exception e) {}
        try {socket.close();} catch (Exception e) {}
      }
    }
    
    try {keyboard.close();} catch (Exception e) {}
  }

}














