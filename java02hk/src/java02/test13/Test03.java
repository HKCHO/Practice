package java02.test13;

public class Test03 {
  public static void main(String[] args) {
    // 인터페이스를 구현한 익명 이너 클래스 만들기
    // 일반 문법
    class MyClass2 extends Object implements MyInterface {
      @Override
      public void m2() {
        System.out.println("하하하");        
      }
    }
    
    //스냅샵 1: 클래스명 제거 + class 키워드 제거
    /*
    extends Object implements MyInterface {
      @Override
      public void m2() {
        System.out.println("하하하");        
      }
    }
    */
    
    //스냅샵 2: Object 상속 문법 생략
    //Object를 상속받지 않으면 자동으로 상속받기 때문에 Object 생략
    /*
    implements MyInterface {
      @Override
      public void m2() {
        System.out.println("하하하");        
      }
    }
    */
    
    //스냅샷 3: implements 키워드 생략
    /*
    MyInterface {
      @Override
      public void m2() {
        System.out.println("하하하");        
      }
    }*/
    
    // 스냅샷 4: 인스턴 생성 명령어 결합
    new MyInterface() {
      @Override
      public void m2() {
        System.out.println("하하하");        
      }
    }.m2();
    
  }
}










