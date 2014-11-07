package java02.test07.command;

import java.util.ArrayList;
import java.util.Map;

import java02.test07.Command;
import java02.test07.Score;
import java02.test07.ScoreDao;
import java02.test07.annotation.Component;

@Component("add")
public class AddCommand implements Command {

  @Override
  public String getCommandInfo() {
    return "add";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
    
    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
        (ArrayList<String>)params.get("options");
    
    Score score = new Score(options.get(0), 
        Integer.parseInt(options.get(1)), 
        Integer.parseInt(options.get(2)), 
        Integer.parseInt(options.get(3)));
    
    scoreDao.add(score);
    System.out.println("저장하였습니다.");
  }
}










