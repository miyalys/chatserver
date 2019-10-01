package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;
import chat.libs.Connection;


public class ClientConnection implements Runnable {

  private Socket socket;

  public ClientConnection(Socket socket) {
    this.socket = socket;
  }

  public void run() {
      Connection con = new Connection(socket);

      while (true) {
        String str = "";
        try {
          str = con.in.readUTF();
        }
        catch(IOException e) {System.out.println(e);}

        System.out.println("Message: " + str);
      }
    
  }
}
