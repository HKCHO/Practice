package java02.test12;

public class Test04 {
  static class Account {
    long balance;
    
    public Account(int balance) {
      this.balance = balance;
    }
    
    private long delay() { //아무 의미없는 시간 죽이기 코드
      long l = 10L;
      double b = 3.15;
      b = b / l;
      b += System.currentTimeMillis();
      return (long)(b / 34.56);
    }

    // 여러 스레드가 동시에 접근했을 때 문제가 발생하는 코드 블록
    // => critical section (critical region)
    // => 개선 코드 Test05 참고하라
    public long withdraw(long money) {
      long temp = this.balance;delay();
      
      temp = temp - money;delay();
      
      if (temp >= 0) {delay();
        this.balance = temp;delay();
        return money;
        
      } else {delay();
        
        return 0;
      }
    }
  }
  
  static class ATM extends Thread {
    Account account;
    
    public ATM(String name, Account account) {
      this.setName(name);
      this.account = account;
    }
    
    @Override
    public void run() {
      long sum = 0;
      for (int i = 0; i < 10000; i++) {
        if (account.withdraw(100) != 0) {
          sum += 100;
        } else {
          break;
        }
      }
      System.out.println(this.getName() + ": " + sum + "원 찾았습니다.");
    }
  }
  
  public static void main(String[] args) {
    Account account = new Account(1000000);
    
    ATM a1 = new ATM("강남", account);
    ATM a2 = new ATM("양재", account);
    ATM a3 = new ATM("종로", account);
    ATM a4 = new ATM("부산", account);
    ATM a5 = new ATM("광주", account);
    ATM a6 = new ATM("강릉", account);

    a1.start();
    a2.start();
    a3.start();
    a4.start();
    a5.start();
    a6.start();
  }

}























