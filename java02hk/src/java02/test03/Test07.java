/* add 명령어 처리 구현
 - 사용자가 입력한 값을 저장할 Entity 역할 클래스 정의
 - Entity : 데이터를 표현하는 역할을 수행하는 클래스
   Control: Entity와 Boundary를 중계. 비즈니스 로직 수행
   Boundary: 사용자에게 UI 제공하는 역할.
 - Entity 클래스 => 사용자 데이터 타입(개발자가 임의적으로 만든 데이터 타입)을 
                   정의한 클래스
 - Score
 */
package java02.test03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Test07 {
  static Scanner scanner; 
  static ArrayList<Score> list = new ArrayList<Score>();

  // Entity 클래스 => 사용자(개발자) 정의 데이터 타입 
  static class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;
    
    public Score() {}
    
    public Score(String name, int kor, int eng, int math) {
      this.name = name;
      this.kor = kor;
      this.eng = eng;
      this.math = math;
    }
  }
  
  public static void main(String[] args) {

    scanner = new Scanner(System.in);

    loop: 
    while (true) {
      try {
        String[] token = promptCommand();

        switch (token[0]) {
        case "add":
          doAdd(token);
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
          doHelp();
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

  private static void doHelp() {
    System.out.println("list");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
  }

  private static void doAdd(String[] token) {
    Score score = new Score(token[1], 
        Integer.parseInt(token[2]), 
        Integer.parseInt(token[3]), 
        Integer.parseInt(token[4]));
    
    list.add(score);
    System.out.println("저장하였습니다.");
  }

  private static String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }


}







