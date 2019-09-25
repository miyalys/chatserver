import java.io.*;   
import java.net.*;   

import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {   

  public static void main(String[] args){   
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
  }   

  private void createClientConnection implements Runnable() {
    try {   
      ServerSocket ss=new ServerSocket(6666);   
       
      Socket s=ss.accept();//establishes connection    
       
      DataInputStream dis=new DataInputStream(s.getInputStream()); 
       
      String  str=(String)dis.readUTF();   
       
      System.out.println("Message: "+str);   
       
      ss.close();   
    }
    catch(Exception e) {
      System.out.println(e);
    }
  }
}
