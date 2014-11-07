package java02.test11.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatClient01 extends Frame {
  TextField serverAddr = new TextField(20);
  TextField name = new TextField(10);
  Button connectBtn = new Button("연결");
  TextArea content = new TextArea();
  TextField input = new TextField(30);
  Button sendBtn = new Button("보내기");
  
  public ChatClient01() {
    // 윈도우 준비
    Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
    toolbar.add(new Label("이름:"));
    toolbar.add(name);
    toolbar.add(new Label("서버:"));
    toolbar.add(serverAddr);
    toolbar.add(connectBtn);
    
    this.add(toolbar, BorderLayout.NORTH);
    
    this.add(content, BorderLayout.CENTER);
    
    Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
    bottom.add(input);
    bottom.add(sendBtn);
    
    this.add(bottom, BorderLayout.SOUTH);
    
    // 리스너 등록
    //1) 윈도우 이벤트를 처리할 리스너 객체 등록
    // WindowListener 인터페이스를 구현한 객체여야 한다.
    this.addWindowListener(new MyWindowListener());
    
    // ActionEvent는 버튼을 눌렀을 때 발생하는 이벤트이다.
    //connectBtn.addActionListener(new MyConnectListener());
    
    connectBtn.addActionListener(new MyConnectListener());
    
    // 보내기 버튼을 눌렀을 때,
    sendBtn.addActionListener(new MySendListener());
  }
  
  public static void main(String[] args) {
    ChatClient01 wnd = new ChatClient01();
    wnd.setSize(400, 600);
    wnd.setVisible(true);
  }
  
  // WindowListener를 직접 구현하지 말고, 
  // 미리 구현한 WindowAdapter를 상속 받아라!
  class MyWindowListener extends WindowAdapter {
    // 다음 메서드는 윈도우에서 close 버튼을 눌렀을 때 호출된다.
    @Override
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    } 
  }
  
  class MyConnectListener implements ActionListener {
    // 다음 메서드는 버튼을 누를 때마다 호출된다.
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("연결 버튼 눌렀네..");
      
    }
  }
  
  class MySendListener implements ActionListener {
    // 다음 메서드는 버튼을 누를 때마다 호출된다.
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("보내기 버튼 눌렀네..");
      
    }
  }
}
















