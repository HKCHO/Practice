/* 자바 사용되는 코드는 메서드 블록으로 분리한다.
 - 사용자에게서 명령어를 입력 받는 부분
 - 여러 클래스 메서드에서 공통으로 사용하는 객체는 클래스 변수로 만든다.
 */
package java02.test03;

import java.util.Scanner;

public class Test03 {
  static Scanner scanner; 
  
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    
    loop: 
    while (true) {
      String[] token = promptCommand();

      switch (token[0]) {
      case "add":
        System.out.println("저장하였습니다.");
        break;
      case "list":
        System.out.println("목록");
        break;
      case "view":
        System.out.println("상세정보");
        break;
      case "delete":
        System.out.println("삭제하였습니다.");
        break;
      case "update":
        System.out.println("변경하였습니다.");
        break;
      case "help":
        System.out.println("명령");
        break;
      case "exit":
        System.out.println("파일에 저장하였습니다.");
        break loop;
      default:
        System.out.println("이 명령어를 지원하지 않습니다.");
      }
    }
    scanner.close();
  }

  private static String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }


}
