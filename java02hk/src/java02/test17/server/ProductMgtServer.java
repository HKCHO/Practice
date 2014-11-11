package java02.test17.server;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java02.test17.server.annotation.Command;
import java02.test17.server.annotation.Component;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductMgtServer {
  static class CommandInfo {
    public Object instance;
    public Method method;
  }
  
  Scanner scanner; 
  ProductDao productDao;
  HashMap<String,CommandInfo> commandMap;
  
  public void init() throws Exception {
    productDao = new ProductDao();
    scanner = new Scanner(System.in);
    commandMap = new HashMap<>();
    
    Reflections reflections = new Reflections("java02.test15");
    Set<Class<?>> clazzList = 
        reflections.getTypesAnnotatedWith(Component.class);
    
    Object command = null;
    Component component = null;
    Method method = null;
    CommandInfo commandInfo = null;
    Command commandAnno = null;
    
    for (Class clazz : clazzList) {
      component = (Component) clazz.getAnnotation(Component.class);
      command = clazz.newInstance();

      Set<Method> methods = ReflectionUtils.getMethods(
          clazz,
          ReflectionUtils.withAnnotation(Command.class));
      
      for (Method m :methods) {
        commandAnno = m.getAnnotation(Command.class);
        commandInfo = new CommandInfo();
        commandInfo.instance = command;
        commandInfo.method = m;
        commandMap.put(commandAnno.value(), commandInfo);
      }
      
      try { 
        method = clazz.getMethod("setProductDao", ProductDao.class);
        method.invoke(command, productDao);
      } catch (Exception e) {}
      
      try { 
        method = clazz.getMethod("setScanner", Scanner.class);
        method.invoke(command, scanner);
      } catch (Exception e) {}
    }
    
    
  }
  
  public void service() {
    CommandInfo commandInfo = null;
    loop: 
    while (true) {
      try {
        String[] token = promptCommand();
        commandInfo = commandMap.get(token[0]);
        
        if (commandInfo == null) {
          System.out.println("해당 명령을 지원하지 않습니다.");
          continue;
        }
        
        HashMap<String,Object> params = 
            new HashMap<String,Object>();
        
        ArrayList<String> options = new ArrayList<String>();
        for (int i = 1; i < token.length; i++) {
          options.add(token[i]);
        }
        params.put("options", options);
        
        commandInfo.method.invoke(commandInfo.instance, params);
        
        if (token[0].equals("exit"))
          break loop;
        
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
      }
    }
  }
  
  public void destroy() {
    scanner.close();
  }

  private String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }

  public static void main(String[] args) throws Exception {
    ProductMgtServer app = new ProductMgtServer();
    app.init();
    app.service();
    app.destroy();
  }

}







