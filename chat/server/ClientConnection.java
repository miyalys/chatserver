package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.Lexer;

public class ClientConnection implements Runnable {

  private int port = 6666;

  public ClientConnection(int port) {
    this.port = port;
  }

  public void run() {
    try (
      var ss = new ServerSocket(port);
      Socket s = ss.accept(); //establishes connection
      var dis = new DataInputStream(s.getInputStream());
      var dos = new DataOutputStream(s.getOutputStream());
      ){

      while (true) {
        String str = dis.readUTF();

        System.out.println("Message: " + str);
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
