package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.lexer.Lexer;
import chat.libs.protocol.parser.Parser;
import chat.libs.Connection;

// TODO: Maybe call this class ClientHandler to separate it from Connection?
public class ClientHandler implements Runnable {

  private final Socket socket;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    Connection con = new Connection(socket);

    while (true) {
      try {
        String str = con.in.readUTF();
        System.out.println(str);
      } catch (EOFException e) {
        System.out.println("Client with socket " + socket + " closed the connection.");
        return;
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }
    }
  }
}
