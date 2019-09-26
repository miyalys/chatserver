package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;

public class ClientConnection {

  public void createClientConnection (int port) {
    try (
      var ss = new ServerSocket(port);
      Socket s=ss.accept(); //establishes connection
      var dis=new DataInputStream(s.getInputStream());
      ){

      while (true) {
        String str=(String)dis.readUTF();

        System.out.println("Message: "+str);
      }

    }
    catch(EOFException e) {
      // Handling when client connection closed (can be voluntary or not)
    }
    catch(IOException e) {
      System.out.println(e);
    }
  }
}
