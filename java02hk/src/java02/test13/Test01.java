/* Inner class
 * => 무분별한 클래스 노출을 막기 위해
 * => 특정 클래스 안에서만 사용하는 클래스인 경우
 *    그 클래스 내부에서 정의함으로써
 *    외부에 노출되는 것을 막을 수 있다.
 *    => 관리가 쉽다.
 * 
 * 클래스 종류
 * 1. 패키지 멤버 클래스 <== 일반적으로 만드는 클래스
 * 2. 중첩(inner) 클래스
 *   1) top level inner class
 *   2) member inner class
 *   3) local inner class
 *   4) anonymous inner class
 *   
 */
package java02.test13;

//0) 패키지 멤버 클래스
// public 또는 default 접근 제어만 가능
// => private, protected 안됨!
// 참고: 이너 클래스는 특정 클래스의 멤버이기 때문에
//        => 메서드나 변수처럼 모든 접근 제어를 지정할 수 있다.
//        => public, (default), protected, private 
public class Test01 {
  static int i;
  int j;
  
  static void x() {
    i = 100; // ok
    //j = 100; // error => static 메서드나 블록은 this라는 내장 변수가 없다.
  }
  
  void y() {
    i = 100; // ok => 인스턴스 메서드나 블록은 클래스 변수를 바로 접근할 수 있다.
    j = 100; // ok => 인스턴스 메서드나 블록은 this라는 내장 변수가 있다.
  }
  
  //1) top level inner class
  // static 으로 선언된 inner class
  // static이기 때문에 다른 클래스에서도 사용할 수 있다.
  // => 작은 크기의 클래스들을 묶고 싶을 때 top level 이너 클래스를 사용한다.
  // => 작은 크기의 클래스를 패키지에 나열하면 관리가 번거롭다.
  static class TopLevelInnerClass {
    // inner 클래스는 바깥 클래스의 멤버에 접근할 수 있다.
    // inner 클래스도 메서드나 변수와 같이 그 클래스의 멤버이기 때문이다.
    // static 영역에서는 오로지 바깥 클래스의 static 멤버(변수,메서드)만
    // 접근할 수 있다.
    public void test() {
      i = 10; // ok => 바깥 클래스의 static 변수를 사용할 수 있다.
      //j = 200; // error => 바깥 클래스의 인스턴스 주소를 알 수 없다.
    }
  }
  
  //2) member inner class
  class MemberInnerClass {
    // 멤버 이너 클래스는 내부적으로 바깥 클래스의 인스턴스 주소를 갖고 있다.
    // 바깥 클래스의 인스턴스 주소 => 바깥클래스명.this
    public void test() {
      i = 200; // 당연히 non-static 블록에서는 static 멤버 사용 가능.
      
      // member 이너 클래스에서는 
      // 바깥 클래스의 인스턴스 멤버를 사용할 수 있다.
      j = 300;
      
      // 위 코드는 컴파일 할 때 다음과 같이 바뀐다.
      //Test01.this.j = 400;
      
      // 만약 이너 클래스에 같은 이름을 가진 변수가 있다면,
      // 구분하기 위해 개발자가 다음과 같이 명시적으로 선언해야 한다.
      Test01.this.j = 400; 
    }
  }
  
  public static void main(String[] args) {
    //3) local inner class
    // 특정 메서드에서만 사용 가능
    class LocalInnerClass {
      public void test() {
        i = 20;
        
        //static 블록은 바깥 클래스의 인스턴스 주소를 가리킬 수 없다.
        //Test01.this.j = 20; // error
      }
    }
  }
  
  public void instanceMethod() {
    class LocalInnerClass {
      public void test() {
        i = 20;
        Test01.this.j = 20; // OK
      }
    }
  }

}









