/* 예외 상황 처리
 - 명령어 개수를 잘못 참조하는 경우 등 예외 상황 처리

 */
package java02.test03;

import java.util.Scanner;

public class Test05 {
  static Scanner scanner; 

  public static void main(String[] args) {

    scanner = new Scanner(System.in);

    loop: 
    while (true) {
      try {
        String[] token = promptCommand();

        switch (token[0]) {
        case "add":
          System.out.println("이름:" + token[1]);
          System.out.println("국어:" + token[2]);
          System.out.println("영어:" + token[3]);
          System.out.println("수학:" + token[4]);
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
          System.out.println("list");
          System.out.println("view 인덱스");
          System.out.println("add 이름 국어 영어 수학");
          System.out.println("delete 인덱스");
          System.out.println("update 인덱스");
          System.out.println("exit");
          break;
        case "exit":
          System.out.println("파일에 저장하였습니다.");
          break loop;
        default:
          System.out.println("이 명령어를 지원하지 않습니다.");
        }

      } catch (Exception e) {
        //e.printStackTrace();
        System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
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







