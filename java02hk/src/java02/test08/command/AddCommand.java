package java02.test08.command;

import java.util.ArrayList;
import java.util.Map;

import java02.test08.Command;
import java02.test08.Score;
import java02.test08.ScoreDao;
import java02.test08.annotation.Component;

@Component("add")
public class AddCommand implements Command {
  ScoreDao scoreDao;
  
  public void setScoreDao(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  
  @Override
  public String getCommandInfo() {
    return "add";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
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










