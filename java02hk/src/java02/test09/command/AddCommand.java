package java02.test09.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java02.test09.Score;
import java02.test09.ScoreDao;
import java02.test09.annotation.Command;
import java02.test09.annotation.Component;

@Component("add")
public class AddCommand {
  ScoreDao scoreDao;
  
  public void setScoreDao(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  
  // 이 메서드는 add 명령을 처리하는 기능을 수행한다.
  // 따라서 Command 애노테이션을 붙인다.
  @Command
  public void ohora(Map<String, Object> params) throws Exception {
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
  
  @Command
  public void m() {}
  
  public void m2() {}
  
  @Command
  public void m3() {}
  
  public static void main(String[] args) {
    
    // AddCommand 클래스에서 @Command 애노테이션이 붙은 메서드를 모두 찾아라.
    // withAnnotation() => 검색 조건을 담고 있는 객체를 생성하여 리턴한다.
    
    // getMethods(타입, 조건, 조건, 조건, ...);
    
    /*
    Set<Method> methods = ReflectionUtils.getMethods(
        AddCommand.class, // 메서드를 찾을 클래스 타입 
        ReflectionUtils.withAnnotation(Command.class) // 조건 
        );
    
    for (Method m : methods) {
      System.out.println("==>" + m.getName());
    }*/
    
    Class clazz = AddCommand.class;
    
    Method[] methods = clazz.getMethods();
    for (Method m : methods) {
      if (m.getAnnotation(Command.class) != null) {
        System.out.println(m.getName());
      }
    }
    
    
  }
}

























