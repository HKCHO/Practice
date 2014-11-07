package java02.test13;

public class Test02 {

  public static void main(String[] args) {
    // 일반적인 이너 클래스
    class MyClass2 extends MyClass {
      @Override
      public void m() {
        super.m();
        System.out.println("ohora");
      }
    }
    
    MyClass x = new MyClass2();
    x.m();
    
    //익명 이너 클래스 만들기
    //snapshot 1: 이름 생략
    /*
    class extends MyClass {
      @Override
      public void m() {
        super.m();
        System.out.println("ohora");
      }
    }
    
    // new 명령으로 인스턴스 생성 불가
    MyClass x2 = new ();
    */
    
    //snapshot 2: 클래스 정의와 인스턴스 생성 문장을 합친다.
    //이때, 서브 클래스이 이름이 없기 때문에 생성자는 
    //수퍼 클래스의 생성자를 지정한다.
    /*
    new class exMyClasstends MyClass() {
      @Override
      public void m() {
        super.m();
        System.out.println("ohora");
      }
    }
    */
    
    //snapshot 3: class 키워드와 extends 키워드를 제거한다.
    //이때, 서브 클래스이 이름이 없기 때문에 생성자는 
    //수퍼 클래스의 생성자를 지정한다.
    /*
    new MyClass() {
      @Override
      public void m() {
        super.m();
        System.out.println("ohora");
      }
    }
    */
    
    
    
    //4) ananymous inner class
    //한 번만 사용하는 인스턴스를 만들 때는
    //익명 이너 클래스로 만들어라.
    
    // 기존 클래스 상속 받아서 익명 이너 클래스 만들기
    // 문법: new 수퍼클래스() {}
    // 익명 이너 클래스는 클래스를 별도로 정의할 수 없다. => 이름이 없기 때문에.
    // 이너 클래스를 정의하자마자 즉시 인스턴스를 생성해야 한다.
    // 생성자는 수퍼 클래스의 생성자를 호출한다.
    MyClass p = new MyClass() { // MyClass를 상속 받은 익명 서브 클래스
      //상속 받은 메서드 오버라이딩
      @Override
      public void m() {
        super.m(); // 기존 메서드 호출
        System.out.println("ok"); // 출력 추가
      }
    };

    p.m();
    
    // 익명 이너 클래스의 생성자를 지정할 때 수퍼 클래스의 생성자를 지정한다.
    // 즉, 다음은 MyClass의 다른 생성자를 호출하는 예이다.
    new MyClass(30){
      @Override
      public void m() {
        i *= i;
        super.m();
      }
    }.m();
  }

}































