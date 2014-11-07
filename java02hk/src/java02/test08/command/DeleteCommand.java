package java02.test08.command;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import java02.test08.Command;
import java02.test08.Score;
import java02.test08.ScoreDao;
import java02.test08.annotation.Component;

@Component("delete")
public class DeleteCommand implements Command {
  ScoreDao scoreDao;
  Scanner scanner;
  
  public void setScoreDao(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  
  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public String getCommandInfo() {
    return "delete";
  }

  @Override
  public void service(Map<String, Object> params) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
        (ArrayList<String>)params.get("options");
    
    int index = Integer.parseInt(options.get(0));
    
    Score score = scoreDao.getData(index);
    if (score == null) {
      System.out.println("해당 인덱스의 성적 정보를 찾을 수 없습니다.");
      return;
    }
    
    System.out.print(score.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      scoreDao.delete(index);
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("삭제 취소하였습니다.");
    }
  }
}










