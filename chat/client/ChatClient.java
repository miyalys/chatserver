package chat.client;

import java.util.Scanner;
import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;
import chat.libs.StaticLib;
import chat.libs.Connection;

public class ChatClient { 

  private static String IP = "localhost";
 
  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var port = 6666;

    StaticLib.clearScreen();

    System.out.println("MCON: Your favourite chat client.");
    System.out.println("Please type in your username.");

    String input = scanner.nextLine();

    if (!StaticLib.isValidUserName(input) ) {
      System.out.println("Username invalid, try again.");
    }

    var lexer = new Lexer();
    // TODO: Actually use/test Lexer
    
    System.out.println("Before con");
    Connection con = null;
    try {
      con = new Connection(new Socket("localhost", port) );
    }
    catch (IOException e) {e.printStackTrace();}
    System.out.println("After con");

    try {
   
      while (! (input = scanner.nextLine()).equals("quit") ) {
       
        con.out.writeUTF(input);
        con.out.flush();
      }
      // Say properly farewell to the server
      con.out.writeUTF("QUIT");
      con.out.flush();
    }
    // Connection lost...or?
    catch(IOException e){ 
      System.out.println(e);
    }
  }
}
