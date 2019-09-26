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

    if (!StaticLib.isValidUserName(input) ) {
      System.out.println("Username invalid, try again.");
    }

    var lexer = new Lexer();
   
    try (
        Socket s=new Socket(IP, 6666);  // No need to run on a custom port?
        var dos = new DataOutputStream(s.getOutputStream());
        var dis = new DataInputStream(s.getInputStream());
      ) {
   
      while (! (input = scanner.nextLine()).equals("quit") ) {
       
        dos.writeUTF(input);
        dos.flush();
      }
      // Say properly farewell to the server
      dos.writeUTF("QUIT");
      dos.flush();
    }
    // Connection lost
    catch(Exception e){ 
      System.out.println(e);
    }
  }
}
