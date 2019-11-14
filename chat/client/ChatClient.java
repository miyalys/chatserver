package chat.client;

import java.util.Scanner;
import java.io.*;
import java.net.*;

import chat.libs.protocol.lexer.Lexer;
import chat.libs.protocol.parser.Parser;
import chat.libs.protocol.ChatLanguage;
import chat.libs.*;

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

  private void start() {
    scanner = new Scanner(System.in);
    var port = 6666;
    Connection con = null;

    StaticLib.clearScreen();

    System.out.println("MCON: Your favourite chat client.");

		var userName = getUserName();

    ChatLanguage cl = new ChatLanguage();
    var tt = cl.getTokenTypes();
    var lexer  = new Lexer(tt);
    var parser = new Parser(tt, cl.getCommandTypes());
    // TODO: Actually use/test Lexer + Parser
    
    System.out.println("Before con");
    try {
      con = new Connection( new Socket("localhost", port) );
    }
    catch (IOException e) {e.printStackTrace();}
    System.out.println("After con");

    // Spawn the thread for getting messages from the server
    new Thread( new ClientSocketReader(con) ).start();

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

  public static void main(String[] args) {
    new ChatClient().start();
  }
}
