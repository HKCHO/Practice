package java02.test07.episode.step02;


public class AnnoTest01 {
  public static void main(String[] args) {
    Class clazz = MyObject.class;
    
    MyAnnotation myAnno = 
        (MyAnnotation) clazz.getAnnotation(MyAnnotation.class);
    System.out.println(myAnno.value());
    System.out.println(myAnno.name());
    System.out.println(myAnno.country());
    System.out.println(myAnno.age());
    String[] langs = myAnno.language();
    for (String lang : langs) 
      System.out.println("=>" + lang);
    
  }
}
















