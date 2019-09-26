package chat.client;

import java.util.Scanner;
import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;
import chat.libs.StaticLib;

public class ChatClient { 

  private static String IP = "localhost";
 
  public static void main(String[] args) {
    var scanner = new Scanner(System.in);

    StaticLib.clearScreen();

    System.out.println("MCON: Your favourite chat client.");
    System.out.println("Please type in your username.");

    String input = scanner.nextLine();

    var lexer = new Lexer();
   
    try (
        Socket s=new Socket(IP, 6666);  // No need to run on a custom port?
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
      ) {
   
      while (! (input = scanner.nextLine()).equals("quit") ) {
       
        dout.writeUTF(input);
        dout.flush();
      }
    }

    catch(Exception e){System.out.println(e);}

  }
}
