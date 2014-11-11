package java02.test17.server.command;

import java.util.Map;
import java02.test17.server.ProductDao;
import java02.test17.server.annotation.Command;
import java02.test17.server.annotation.Component;

@Component
public class GeneralCommand {
  ProductDao productDao;
  
  public void setProductDao(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Command("exit")
  public void doExit(Map<String, Object> params) throws Exception {
    System.out.println("안녕히 가세요.");
  }
  
  @Command("help")
  public void doHelp(Map<String, Object> params) throws Exception {
    System.out.println("list");
    System.out.println("view 제품번호");
    System.out.println("add");
    System.out.println("delete 제품번호");
    System.out.println("update 제품번호");
    System.out.println("exit");
  }
  
  
}









