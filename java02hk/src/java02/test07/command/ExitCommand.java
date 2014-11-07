package java02.test07.command;

import java.util.Map;

import java02.test07.Command;
import java02.test07.ScoreDao;
import java02.test07.annotation.Component;

@Component("exit")
public class ExitCommand implements Command {

  @Override
  public String getCommandInfo() {
    return "exit";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    try {
      ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
      scoreDao.save();
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류가 발생했습니다.");
    }
  }
}










