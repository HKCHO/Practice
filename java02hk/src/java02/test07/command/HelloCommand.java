package java02.test07.command;

import java.util.Map;
import java02.test07.Command;
import java02.test07.annotation.Component;

@Component("hello")
public class HelloCommand implements Command {

  @Override
  public String getCommandInfo() {
    return "hello";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    System.out.println("Hello....~~~~~^^");
  }

}
