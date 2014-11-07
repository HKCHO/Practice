/* list 명령 처리
 */
package java02.test03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Test08 {
  static Scanner scanner; 
  static ArrayList<Score> list = new ArrayList<Score>();

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
          doList();
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

  private static void doList() {
    int index = 0;
    for (Score score : list) {
      System.out.printf("%-3d %-10s %3d %3d %3d\n", 
          index, score.name, score.kor, score.eng, score.math);
      index++;
    }
    
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







