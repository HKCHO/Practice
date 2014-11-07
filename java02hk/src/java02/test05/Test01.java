/* 명령을 처리하는 메서드를 별도의 클래스 분리 => Command 패턴
 - 새로 명령어를 추가하더라도 기존 코드를 손대지 않고 
   명령을 처리할 수 있게 하자!
 - 명령어를 처리하는 메서드를 클래스로 만든다.
 - 클래스는 외부 파일이기 때문에 추가하기 쉽다.
 */
package java02.test05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java02.test05.commands.AddCommand;
import java02.test05.commands.DeleteCommand;
import java02.test05.commands.ExitCommand;
import java02.test05.commands.HelpCommand;
import java02.test05.commands.ListCommand;
import java02.test05.commands.PlusCommand;
import java02.test05.commands.TestCommand;
import java02.test05.commands.UpdateCommand;
import java02.test05.commands.ViewCommand;

public class Test01 {
  static Scanner scanner; 
  static ScoreDao scoreDao;
  
  public static void main(String[] args) {
    // 명령어를 처리할 객체를 담아둔 맵
    HashMap<String,Command> commandMap = new HashMap<String,Command>();
    commandMap.put("list", new ListCommand());
    commandMap.put("add", new AddCommand());
    commandMap.put("update", new UpdateCommand());
    commandMap.put("view", new ViewCommand());
    commandMap.put("delete", new DeleteCommand());
    commandMap.put("exit", new ExitCommand());
    commandMap.put("help", new HelpCommand());
    commandMap.put("test", new TestCommand());
    commandMap.put("plus", new PlusCommand());
    
    scoreDao = new ScoreDao();
    try {
      scoreDao.load();
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
    }
    
    scanner = new Scanner(System.in);

    Command command = null;
    loop: 
    while (true) {
      try {
        String[] token = promptCommand();
        command = commandMap.get(token[0]);
        
        if (command == null) {
          System.out.println("해당 명령을 지원하지 않습니다.");
          continue;
        }
        
        // Command 객체에게 넘겨줄 바구니 준비
        // 해당 바구니에 Command 객체가 사용할 도구를 넣어 준다.
        HashMap<String,Object> params = 
            new HashMap<String,Object>();
        params.put("scoreDao", scoreDao);
        params.put("scanner", scanner);
        
        // 명령어 다음에 오는 파라미터 값을 ArrayList에 담는다.
        // 예) add 홍길동 100 20 90
        // 즉, 위의 예에서 "홍길동", "100", "20", "90"을 
        // ArrayList에 담는다.
        ArrayList<String> options = new ArrayList<String>();
        for (int i = 1; i < token.length; i++) {
          options.add(token[i]);
        }
        params.put("options", options);
        
        command.service(params);
        
        if (token[0].equals("exit"))
          break loop;
        
      } catch (Exception e) {
        e.printStackTrace();
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







