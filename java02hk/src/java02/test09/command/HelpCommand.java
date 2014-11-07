package java02.test09.command;

import java.util.Map;
import java02.test09.annotation.Command;
import java02.test09.annotation.Component;

@Component("help")
public class HelpCommand {

  @Command
  public void doHelp(Map<String, Object> params) throws Exception {
    System.out.println("list");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
  }
}










