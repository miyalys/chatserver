package chat.server;

import java.io.*;   
import java.net.*;   

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {   

  public static void main(String[] args){   
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    ChatServer cs = new ChatServer();
    cs.createClientConnection(6666);
  }   

  private void createClientConnection (int port) {
    try ( 
      var ss = new ServerSocket(port);
      Socket s=ss.accept(); //establishes connection    
      var dis=new DataInputStream(s.getInputStream()); 
      ){
       
      while (true) {
        String str=(String)dis.readUTF();   
         
        System.out.println("Message: "+str);   
      }
       
    }
    catch(EOFException e) {
      // Handling when client connection closed (can be voluntary or not)
    }
    catch(IOException e) {
      System.out.println(e);
    }
  }
}
