package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;
import chat.libs.Connection;

public class ClientConnection implements Runnable {

  private int port;

  public ClientConnection(int port) {
    this.port = port;
  }

  public void run() {
      Connection con = new Connection(port, true);

      while (true) {
        String str = "";
        try {
          str = con.dataInputStream.readUTF();
        }
        catch(IOException e) {System.out.println(e);}

        System.out.println("Message: " + str);
      }
    
  }
}
