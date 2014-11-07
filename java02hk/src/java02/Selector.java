package java02;

import java.util.ArrayList;

/* 10/22 과제 대상: 김종서, 반기백, 김다은, 신경민 */
public class Selector {

  public static void main(String[] args) throws Exception {
    String[] students = new String[]{
        "@권영근", "배연정", "송민준", "@지용근", "정해창", "@장대혁",
        "@조민석", "김지영", "박기남", "이용호", "@염정우", "김종서",
        "@김해경", "@한진수", "양기욱", "@이주형", "@허정아", "김광철",
        "반기백", "@윤용식", "@김원기", "나정담", "@현나래", "김기현",
        "@조현권", "강다현", "김다은", "신경민", "@윤종익", "@정세라"
    };
    
    int no = -1;
    
    ArrayList<String> selected = new ArrayList<String>();
    
    for (int i = 0; i < students.length; i++) {
      //no = (int)(Math.random() * 30);
      selected.add(students[i]);
    }
    
    ping(selected);
    
  }
  
  public static void ping(ArrayList<String> list) throws Exception {
    
    int count = 1;
    /*
    for (String name : list) {
      System.out.print(name + ",");
      if ((count % 5) == 0)
        System.out.println();
      count++;
    }*/
    
    Thread.sleep(800);
    
    if (list.size() == 1) {
      System.out.print("^^ 당첨 => ");
      Thread.sleep(3000);
      System.out.println(list.get(0));
      return;
    }
    
    String name = list.remove((int)(Math.random() * list.size())); 
    
    if (!name.startsWith("@")) {
      System.out.println("\n------------------");                                                                                                                                           
      System.out.println(name);
    }
    ping(list);
    
    return;
  }

}











