/* 명령어를 처리하는 Command 객체를 자동 생성하여
 * commandMap에 등록하기
 - 1) application-context.properties 파일에 명령어 처리 클래스 정보를 둔다.
 - 2) 이 프로퍼티 파일을 읽고 클래스를 로딩하여 인스턴스를 생성한다.
 - 3) 생성한 인스턴스를 commandMap에 등록한다.
 */
package java02.test06;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Test01 {
  Scanner scanner; 
  ScoreDao scoreDao;
  HashMap<String,Command> commandMap;
  
  public void init() throws Exception {
    commandMap = new HashMap<String,Command>();
    
    Properties props = new Properties();
    props.load(new FileReader("application-context.properties"));
    Set keySet = props.keySet();
    
    String classname = null;
    Class clazz = null;
    Command command = null;
    
    for (Object key : keySet) {
      classname = (String)props.get(key);
      clazz = Class.forName(classname.trim());
      command = (Command)clazz.newInstance();
      commandMap.put((String)key, command);
    }
    
    scoreDao = new ScoreDao();
    try {
      scoreDao.load();
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
    }
    
    scanner = new Scanner(System.in);
  }
  
  public void service() {
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
        
        HashMap<String,Object> params = 
            new HashMap<String,Object>();
        params.put("scoreDao", scoreDao);
        params.put("scanner", scanner);
        
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
  }
  
  public void destroy() {
    scanner.close();
  }

  private String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }

  public static void main(String[] args) throws Exception {
    Test01 app = new Test01();
    app.init();
    app.service();
    app.destroy();
  }

}







