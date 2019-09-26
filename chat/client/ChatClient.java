package chat.client;

import java.util.Scanner;
import java.io.*;   
import java.net.*;   

import chat.protocol.Lexer;

public class ChatClient {   

  private static String IP = "localhost";
 
  public static void main(String[] args) {   

    var lexer = new Lexer();
   
    try {
     
      Socket s=new Socket(IP, 6666);   
       
      DataOutputStream  
       
      dout=new DataOutputStream(s.getOutputStream());   
      var scanner = new Scanner(System.in);

      String input;

      while (! (input = scanner.nextLine()).equals("quit") ) {
       
        dout.writeUTF(input);   
         
        dout.flush();   
      }
      dout.close();
      s.close();   
    }
   
    catch(Exception e){System.out.println(e);}  
     
  }
}
