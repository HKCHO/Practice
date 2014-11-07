package java02.test07.episode.step02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* 애노테이션의 속성 선언 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
  String value();
  String name();
  
  // 값을 설정하지 않아도 기본으로 갖는 값 => 선택 속성
  // 즉, 애노테이셔을 사용할 때 값을 주지 않아도 되는 속성
  String country() default "korea";
  
  int age();
  
  String[] language();
  
}





















