package java02.test05.commands;

import java.util.ArrayList;
import java.util.Map;
import java02.test05.Command;

public class PlusCommand implements Command {

  @Override
  public String getCommandInfo() {
    return "plus";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
        (ArrayList<String>)params.get("options");
    
    int a = Integer.parseInt(options.get(0));
    int b = Integer.parseInt(options.get(1));
    System.out.println(a + "+" + b + "=" + (a + b));
  }
}










