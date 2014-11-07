package java02.test11.exam03;

import java.util.Scanner;

public class ChatReaderThread extends Thread {
  Scanner in;
  
  public ChatReaderThread(Scanner in) {
    this.in = in;
  }
  
  @Override
  public void run() {
    try {
      String message = null;
      while (true) {
        message = in.nextLine();
        System.out.println("\n==> " + message);
      }
    } catch (Exception e) {
      System.out.println("데이터 수신중 오류 발생!");
    }
  }
}











