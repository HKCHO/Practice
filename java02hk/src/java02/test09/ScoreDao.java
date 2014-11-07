/* DAO(Data Access Object)
 - 데이터의 퍼시스턴스(Persistence) 담당
   => 데이터의 보관(등록,조회,변경,삭제) 
 * 
 */
package java02.test09;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScoreDao {
  ArrayList<Score> list = new ArrayList<Score>();
  String filename;
  
  public ScoreDao() {
    filename = "score.dat";
  }
  
  public ScoreDao(String filename) {
    this.filename = filename;
  }

  public void load() throws Exception {
    Scanner dataScanner = null;
    try {
      dataScanner = new Scanner(new FileReader(filename));
      
      while (true) {
        try { 
          list.add(new Score(dataScanner.nextLine()));
        } catch (NoSuchElementException e) {
          break;
        }
      }
    } catch (Exception e) {
      list.clear();
      throw e;
    } finally {
      try {dataScanner.close();} catch (Exception e) {}
    }
  }

  public void save() throws Exception {
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(new FileWriter(filename));
      for (Score score : list) {
        out.write(score.getName() + "," +
            score.getKor() + "," + 
            score.getEng() + "," +
            score.getMath() + "\n");
      }
      
    } catch (IOException e) {
      throw e;
      
    } finally {
      try {out.close();} catch (Exception ex) {}
    }
  }
  
  private boolean isValid(int index) {
    if (index < 0 || index >= list.size()) {
      return false;
    } else {
      return true;
    }
  }
  
  public Score getData(int index) {
    if (isValid(index)) {
      return list.get(index);
    } else {
      return null;
    }
  }
  
  public void change(int index, Score data) {
    list.set(index, data);
  }
  
  public void delete(int index) {
    list.remove(index);
  }
  
  public List<Score> getList() {
    return list;
  }
  
  public void add(Score data) {
    list.add(data);
  }
}


















