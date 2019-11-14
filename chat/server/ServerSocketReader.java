package chat.server;

import chat.libs.Connection;

import java.io.EOFException;
import java.io.IOException;

public class ServerSocketReader implements Runnable {
  private Connection con;
  private final ClientHandler clientHandler;

  public ServerSocketReader(Connection con, ClientHandler ch) {
    this.con = con;
    this.clientHandler = ch;
  }

  public void run() {
    while (true) {
      try {
        String msg = con.in.readUTF();
        //System.out.println(str);
        clientHandler.receiveMessage(msg);
      } catch (EOFException e) {
        clientHandler.removeUser();
        System.out.println("Client with socket " + clientHandler.getSocket() + " closed the connection.");
        return;
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }
    }
  }
}
