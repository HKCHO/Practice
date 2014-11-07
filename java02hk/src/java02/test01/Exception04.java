/* Error 계열 예외는 예외 처리를 하지 않아도 컴파일 오류가
   발생하지 않는다.
   => 시스템에서 발생하는 오류 => 애플리케이션에서 처리할 방법은 없을 것이다.
   
 - 문법: throw new Error("오류 내용");
 - 메서드 선언부: 예외 객체를 지정할 필요가 없다.  
 - 호출자: try... catch...를 사용하지 않아도 된다.
 
 - 의미: "이건 시스템 오류라서 내가 처리할 문제가 아니야. 난 몰라!"
        " 물론, 내가 처리할 수도 있지"
 */
package java02.test01;

public class Exception04 {
  public static int divide(int a, int b) {
    if (b == 0) 
      throw new Error("0으로 나누지 마세요.");
    return a / b;
  }
  
  /*
  public static void main(String[] args) {
    // Error 계열의 예외는 애플리케이션에서 어찌할 바가 아니다.
    // 그래서 try... catch...를 사용하지 않는다.
    // 결국 divide()에서 발생한 예외는 JVM에게 전달된다.
    int result = divide(10, 0);
    
  }
  */
  
 //그러나 Error 계열의 예외도 try... catch...로 잡을 수 있다.
 public static void main(String[] args) {
   try {
     int result = divide(10, 0);
     
   } catch (Error ex) {
     System.out.println("0이드래요~");
   }
 }

}








