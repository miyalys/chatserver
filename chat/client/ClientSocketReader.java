package chat.client;

import java.io.IOException;
import java.io.EOFException;
import chat.libs.Connection;

public class ClientSocketReader implements Runnable {
  private Connection con;

  public ClientSocketReader(Connection con) {
    this.con = con;
  }
  public void run() {
    while (true) {
      try {
        String in = con.in.readUTF();

        // Server just prints out whatever it receives, for now. In the future also handle receiving a user list etc.
        System.out.println(in);
      }
      catch (EOFException e) {} // EOF exceptions will happen constantly when no message is received, so ignore them
      catch (IOException e) { e.printStackTrace(); }
    }
  }
}
