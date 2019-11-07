package chat.client;

import java.util.Scanner;
import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;
import chat.libs.StaticLib;
import chat.libs.Connection;

public class ChatClient { 

  private static String IP = "localhost";
	private static Scanner scanner;

  // Move to staticlib or someone else? together with isValidUserName at least.
  private static String getUserName() {

    System.out.println("Please type in your username.");
    
    String input = scanner.nextLine();

    while (!StaticLib.isValidUserName(input) ) {
			System.out.println("Invalid user name. The user name may only contain letters, numbers, - and _.");
			System.out.println("Please type in another one:");
			input = scanner.nextLine();
    }
		System.out.println(input);
		return input;
  }

  private class readFromServer implements Runnable {
    private Connection con;

    public readFromServer(Connection con) {
      this.con = con;
    }
    public void run() {
      
    } 
  }


  public static void main(String[] args) {

    scanner = new Scanner(System.in);
    var port = 6666;
    Connection con = null;

    StaticLib.clearScreen();

    System.out.println("MCON: Your favourite chat client.");

		var userName = getUserName();

    var lexer = new Lexer();
    // TODO: Actually use/test Lexer
    
    System.out.println("Before con");
    try {
      con = new Connection(new Socket("localhost", port) );
    }
    catch (IOException e) {e.printStackTrace();}
    System.out.println("After con");

    try {
			String input;
   
      while (! (input = scanner.nextLine()).equals("quit") ) {
       
        con.out.writeUTF("DATA " + userName + ": " + input);
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
