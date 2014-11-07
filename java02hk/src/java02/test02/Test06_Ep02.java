/* 재귀 호출의 이해
 * 
 */
package java02.test02;

public class Test06_Ep02 {
  // Quiz: 1부터 50까지 더하라! 합계 출력.
  
  // 함수 호출 통해
  public static void main(String[] args) {
    System.out.println(f(100));
  }
  
  public static int f(int no) {
    if (no == 1)
      return no;
    return no + f(no - 1);
  }
  

}




