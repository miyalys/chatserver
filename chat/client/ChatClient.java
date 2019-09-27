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
    
    Connection conInit = new Connection(false);
    int port = 0;

    try {
      String sport = conInit.dataInputStream.readUTF();
      port = Integer.parseInt(sport);
    }
    catch(IOException e) { System.out.println(e); } // This connection should be closed by the server so this exception happening is fine

    if (port == 0) {
      System.out.println("Server Error");
    }
    else {
      Connection con = new Connection(port, false);
     
      try {
     
        while (! (input = scanner.nextLine()).equals("quit") ) {
         
          con.dataOutputStream.writeUTF(input);
          con.dataOutputStream.flush();
        }
        // Say properly farewell to the server
        con.dataOutputStream.writeUTF("QUIT");
        con.dataOutputStream.flush();
      }
      // Connection lost...or?
      catch(IOException e){ 
        System.out.println(e);
      }
    }
  }
}
