package java02.test10.command;

import java.util.Map;

import java02.test10.ProductDao;
import java02.test10.annotation.Command;
import java02.test10.annotation.Component;

@Component
public class GeneralCommand {
  ProductDao scoreDao;
  
  public void setScoreDao(ProductDao scoreDao) {
    this.scoreDao = scoreDao;
  }

  @Command("exit")
  public void doExit(Map<String, Object> params) throws Exception {
    try {
      scoreDao.save();
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류가 발생했습니다.");
    }
  }
  
  @Command("help")
  public void doHelp(Map<String, Object> params) throws Exception {
    System.out.println("list");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
  }
  
  
}
