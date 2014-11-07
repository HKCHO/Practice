/* 예외 처리를 위한 문법이 없었을 때
 - 메서드의 리턴 값을 통해 호출자에게 예외 상황을 알렸다.
 - 따라서 호출자는 늘 리턴 값을 검사해야 했다. 
   => 늘 메서드의 리턴 값을 체크하는 if문이 존재
   => 작업 코드와 에러 처리 코드가 뒤 섞여 있다.
   => 리턴 값이 때론 실제 계산 결과일 수도 있다.
 */
package java02.test01;

public class Exception01 {
  public static float divide(float a, float b) {
    if (b == 0) { // 예외 상황 발생
      return -999999.0f; // 특별한 값을 리턴.
    }
    return a / b;
  }
  
  
  public static void main(String[] args) {
    float result = divide(999999, -1);
    
    if (result != -999999.0f) {
      System.out.println("결과는:" + result);
      
    } else {
      System.out.println("계산 중, 오류가 발생!");
    }

  }

}










