/* 데이터 보관처리
 - File I/O API를 사용하여 데이터를 저장하고 꺼낸다.
 - load(), save() 메서드 준비
 
 - CSV(comma seperated value) 문자열을 가지고 객체를 초기화할 수 있도록 
   Score13에 생성자 추가한다.
 */
package java02.test04;

import java.util.Scanner;

public class Test01 {
  static Scanner scanner; 
  static ScoreDao scoreDao;

  public static void main(String[] args) {
    scoreDao = new ScoreDao();
    try {
      scoreDao.load();
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
    }
    
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
          doView(Integer.parseInt(token[1]));
          break;
        case "delete":
          doDelete(Integer.parseInt(token[1]));
          break;
        case "update":
          doUpdate(Integer.parseInt(token[1]));
          break;
        case "help":
          doHelp();
          break;
        case "exit":
          doExit();
          break loop;
        default:
          System.out.println("이 명령어를 지원하지 않습니다.");
        }

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
      }
    }
    scanner.close();
  }

  private static void doExit() {
    try {
      scoreDao.save();
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류가 발생했습니다.");
    }
  }

  private static void doUpdate(int index) 
      throws CloneNotSupportedException {
    Score score = scoreDao.getData(index);
    if (score == null) {
      System.out.println("해당 인덱스의 성적 정보를 찾을 수 없습니다.");
      return;
    }
    
    Score tempScore = score.clone();
    
    String text = null;
    System.out.printf("이름(%s):", score.getName());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempScore.setName(text);
    
    System.out.printf("국어(%d):", score.getKor());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempScore.setKor(Integer.parseInt(text));
    
    System.out.printf("영어(%d):", score.getEng());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempScore.setEng(Integer.parseInt(text)); 
    
    System.out.printf("수학(%d):", score.getMath());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempScore.setMath(Integer.parseInt(text));
    
    System.out.print("정말 변경하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      scoreDao.change(index, tempScore);
      System.out.println("변경하였습니다.");
    } else {
      System.out.println("변경 취소하였습니다.");
    }
    
  }

  private static void doView(int index) {
    Score score = scoreDao.getData(index);
    if (score == null) {
      System.out.println("해당 인덱스의 성적 정보를 찾을 수 없습니다.");
      return;
    }
    
    System.out.println("인덱스: " + index);
    System.out.println("이름: " + score.getName());
    System.out.println("국어: " + score.getKor());
    System.out.println("영어: " + score.getEng());
    System.out.println("수학: " + score.getMath());
    System.out.println("총점: " + score.getSum());
    System.out.println("평균: " + score.getAverage());
  }

  private static void doDelete(int index) {
    Score score = scoreDao.getData(index);
    if (score == null) {
      System.out.println("해당 인덱스의 성적 정보를 찾을 수 없습니다.");
      return;
    }
    
    System.out.print(score.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      scoreDao.delete(index);
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("삭제 취소하였습니다.");
    }
    
  }

  private static void doList() {
    int index = 0;
    for (Score score : scoreDao.getList()) {
      System.out.printf("%-3d %-10s %3d %3d %3d\n", 
          index, score.getName(), score.getKor(), 
          score.getEng(), score.getMath());
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
    
    scoreDao.add(score);
    System.out.println("저장하였습니다.");
  }

  private static String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }


}







