package java02.test11.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ChatServer extends Frame implements ActionListener {
  TextArea taContent = new TextArea();
  TextField tfInput = new TextField(30);
  Button btnSend = new Button("보내기");
  
  String username;

  ServerSocket serverSocket;
  Socket socket;
  Scanner in;
  PrintStream out;
  
  public ChatServer() {
    this.add(taContent, BorderLayout.CENTER);
    
    Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
    bottom.add(tfInput);
    bottom.add(btnSend);
    
    this.add(bottom, BorderLayout.SOUTH);
    
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          out.println("quit");
          taContent.append("클라이언트와 연결을 종료했습니다.\n");
          socket.shutdownInput();
        } catch (Exception ex) {}
        try {in.close();} catch (Exception ex) {}
        try {out.close();} catch (Exception ex) {}
        try {socket.close();} catch (Exception ex) {}
        try {serverSocket.close();} catch (Exception ex) {}
        System.exit(0);
      }
    });
    
    btnSend.addActionListener(this);
    tfInput.addActionListener(this);
  }
  
  public void service() {
    try {
      taContent.append("클라이언트의 연결을 기다리는 중...\n");
      
      serverSocket = new ServerSocket(8888);
      socket = serverSocket.accept();
      in = new Scanner(socket.getInputStream());
      out = new PrintStream(socket.getOutputStream());
      
      String[] message = in.nextLine().split(" ");
      
      taContent.append(message[1] + "님이 연결되었습니다.\n");
      
      ChatReaderThread reader = new ChatReaderThread(in, taContent);
      reader.start();
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ChatServer wnd = new ChatServer();
    wnd.setSize(400, 600);
    wnd.setVisible(true);
    wnd.service();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    taContent.append("나:" + tfInput.getText() + "\n");
    out.println(tfInput.getText());
    tfInput.setText("");
  }
}
















