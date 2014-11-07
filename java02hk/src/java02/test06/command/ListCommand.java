package java02.test06.command;

import java.util.Map;
import java02.test06.Command;
import java02.test06.Score;
import java02.test06.ScoreDao;

public class ListCommand implements Command {

  @Override
  public String getCommandInfo() {
    return "list";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
    int index = 0;
    for (Score score : scoreDao.getList()) {
      System.out.printf("%-3d %-10s %3d %3d %3d\n", 
          index, score.getName(), score.getKor(), 
          score.getEng(), score.getMath());
      index++;
    }
  }
}










