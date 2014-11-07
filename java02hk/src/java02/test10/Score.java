/* 복제 기능 추가
 - 1) Object에서 상속 받은 clone() 메서드 오버라이드 한다.
 - 2) Object의 clone()은 protected 접근 범위를 갖는다.
      이것을 public으로 바꾼다. 그래야 외부에서 호출할 수 있다. 
 - 3) 리턴 타입을 해당 클래스로 변경한다.
 - 4) clone()을 호출할 수 있으려면, 
      해당 클래스에 Cloneable 인터페이스를 지정해야 한다.
      
 Cloneable 인터페이스
 - Serializable 인터페이스와 유사하다.
 - 메서드가 없다.
 - 단지 해당 클래스가 복제할 수 있음을 표시하는 용도로 사용한다.
 
 */
package java02.test10;

import java.io.Serializable;

public class Score implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  
  private String name;
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float average;
  
  public Score() {}
  
  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    compute();
  }
  
  public Score(String csv) {
    String[] token = csv.split(",");
    this.name = token[0]; 
    this.kor = Integer.parseInt(token[1]); 
    this.eng = Integer.parseInt(token[2]); 
    this.math = Integer.parseInt(token[3]);
    compute();
  }
  
  @Override
  public Score clone() throws CloneNotSupportedException {
    return (Score)super.clone();
  }

  private void compute() {
    this.sum = kor + eng + math;
    this.average = sum / 3.0f;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKor() {
    return kor;
  }

  public void setKor(int kor) {
    this.kor = kor;
    compute();
  }

  public int getEng() {
    return eng;
  }

  public void setEng(int eng) {
    this.eng = eng;
    compute();
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
    compute();
  }

  public int getSum() {
    return sum;
  }

  public float getAverage() {
    return average;
  }
  
  
}