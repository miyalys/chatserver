package chat.server;

import java.io.*;
import java.net.*;

import chat.libs.protocol.lexer.Lexer;
import chat.libs.protocol.parser.Parser;
import chat.libs.*;

// TODO: Maybe call this class ClientHandler to separate it from Connection?
public class ClientHandler implements Runnable {

  private final Socket socket;
  private final ChatServer chatServer;
  private Connection con;

  public ClientHandler(Socket s, ChatServer cs) {
    this.socket = s;
    this.chatServer = cs;
  }

  public Socket getSocket() { return socket; }

  public void receiveMessage(String msg) {
    chatServer.receiveMessage(msg, this);
  }

  public void removeUser() {
    chatServer.removeUser(this);
  }

  public void sendMessage(String msg) {

    try {
      con.out.writeUTF(msg);
    }
    catch (IOException e) {
      e.printStackTrace();
      return;
    }
  }

  public void run() {
    con = new Connection(socket);

    // Spawn a reader in a thread, and keep the sender in this thread
    new Thread( new ServerSocketReader(con, this) ).start();
  }
}
