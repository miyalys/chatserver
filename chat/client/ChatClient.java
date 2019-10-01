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

    StaticLib.clearScreen();

    System.out.println("MCON: Your favourite chat client.");
    System.out.println("Please type in your username.");

    String input = scanner.nextLine();

    if (!StaticLib.isValidUserName(input) ) {
      System.out.println("Username invalid, try again.");
    }

    var lexer = new Lexer();
    // TODO: Actually use/test Lexer
    
    System.out.println("Before conInit");
    Connection conInit = new Connection(false);
    System.out.println("After conInit");
    int port = 0;

    try {
      port = conInit.in.readInt();
    }
    catch(IOException e) { System.out.println("readInt failed: " + e); } // This connection should be closed by the server so this exception happening is fine

    if (port == 0) {
      System.out.println("Server Error: Didn't receive port");
    }
    else {
      System.out.println("new port: " + port);
      conInit.close(false);
      Connection con = new Connection(port, false);
     
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
}
