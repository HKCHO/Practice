/* 데이터 보관처리
 - File I/O API를 사용하여 데이터를 저장하고 꺼낸다.
 - load(), save() 메서드 준비
 
 - CSV(comma seperated value) 문자열을 가지고 객체를 초기화할 수 있도록 
   Score13에 생성자 추가한다.
 */
package java02.test03;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test14 {
  static Scanner scanner; 
  static ArrayList<Score13> list = new ArrayList<Score13>();

  public static void main(String[] args) {
    load();
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
          save();
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

  private static void load() {
    Scanner dataScanner = null;
    try {
      dataScanner = new Scanner(new FileReader("test14.dat"));
      
      while (true) {
        try { 
          list.add(new Score13(dataScanner.nextLine()));
        } catch (NoSuchElementException e) {
          break;
        }
      }
      System.out.println("데이터가 로딩되었습니다.");
      
    } catch (Exception e) {
      System.out.println("데이터가 로딩이 실패하였습니다.");
      list.clear();
    } finally {
      try {dataScanner.close();} catch (Exception e) {}
    }
    
  }

  private static void save() {
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(
                new FileWriter("test14.dat"));
      for (Score13 score : list) {
        out.write(score.getName() + "," +
            score.getKor() + "," + 
            score.getEng() + "," +
            score.getMath() + "\n");
      }
      System.out.println("파일에 저장하였습니다.");
      
    } catch (IOException e) {
      System.out.println("파일 저장 중 오류 발생!");
      
    } finally {
      try {out.close();} catch (Exception ex) {}
    }
  }
  
  
  private static void doUpdate(int index) 
      throws CloneNotSupportedException {
    if (!isValid(index)) return;
    
    Score13 score = list.get(index);
    Score13 tempScore = score.clone();
    
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
      list.set(index, tempScore);
      System.out.println("변경하였습니다.");
    } else {
      System.out.println("변경 취소하였습니다.");
    }
    
  }

  private static boolean isValid(int index) {
    if (index < 0 || index >= list.size()) {
      System.out.println("존재하지 않는 인덱스입니다.");
      return false;
    } else {
      return true;
    }
  }
  
  private static void doView(int index) {
    if (!isValid(index)) {
      return;
    }
    
    Score13 score = list.get(index);
    
    System.out.println("인덱스: " + index);
    System.out.println("이름: " + score.getName());
    System.out.println("국어: " + score.getKor());
    System.out.println("영어: " + score.getEng());
    System.out.println("수학: " + score.getMath());
    System.out.println("총점: " + score.getSum());
    System.out.println("평균: " + score.getAverage());
  }

  private static void doDelete(int index) {
    if (!isValid(index)) {
      return;
    }
    
    Score13 score = list.get(index);
    
    System.out.print(score.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      list.remove(index);
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("삭제 취소하였습니다.");
    }
    
  }

  private static void doList() {
    int index = 0;
    for (Score13 score : list) {
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
    Score13 score = new Score13(token[1], 
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







