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
import java.lang.reflect.Method;

public class ChatClient06 extends Frame implements ActionListener {
  TextField tfServerAddr = new TextField(20);
  TextField tfName = new TextField(10);
  Button btnConnect = new Button("연결");
  TextArea taContent = new TextArea();
  TextField tfInput = new TextField(30);
  Button btnSend = new Button("보내기");
  
  String username;
  String serverAddress;
  
  public ChatClient06() {
    // 윈도우 준비
    Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
    toolbar.add(new Label("이름:"));
    toolbar.add(tfName);
    toolbar.add(new Label("서버:"));
    toolbar.add(tfServerAddr);
    toolbar.add(btnConnect);
    
    this.add(toolbar, BorderLayout.NORTH);
    
    this.add(taContent, BorderLayout.CENTER);
    
    Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
    bottom.add(tfInput);
    bottom.add(btnSend);
    
    this.add(bottom, BorderLayout.SOUTH);
    
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    
    btnConnect.addActionListener(this);
    
    btnSend.addActionListener(this);
  }
  
  public static void main(String[] args) {
    ChatClient06 wnd = new ChatClient06();
    wnd.setSize(400, 600);
    wnd.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnConnect) { //연결 버튼을 눌렀다면,
      username = tfName.getText();
      serverAddress = tfServerAddr.getText();
      System.out.println("사용자 이름:" + username);
      System.out.println("서버 주소:" + serverAddress);
      
    } else { // 보내기 버튼을 눌렀다면,
      
    }
    
  }
}
















