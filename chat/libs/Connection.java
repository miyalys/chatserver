package chat.libs;

import java.io.*;
import java.net.*;

public class Connection {

  // Made public mostly temporarily for ease of use while testing
  public Socket socket;
  public DataInputStream in;
  public DataOutputStream out;
  public int port;

  public Connection(Socket socket) {
    this(6666, socket);
  }

  public Connection(int port, Socket socket) {
    this.port = port;

    try {

      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      //System.out.println("Connection running!");
    }
    catch(EOFException e) {} // These will happen constantly when no input is received. Ignore them
    catch(IOException e) {
      System.out.println("IO: " +e);
      close();
    }
  }

  public void close() {
    try {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e) {System.out.println("Error closing: " + e);}
  }
}
