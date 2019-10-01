package chat.libs;

import java.io.*;
import java.net.*;

public class Connection {

  // Made public mostly temporarily for ease of use while testing
  public ServerSocket serverSocket;
  public Socket socket;
  public DataInputStream in;
  public DataOutputStream out;
  public int port;

  public Connection(boolean server) {
    this(6666, server);
  }

  public Connection(int port, boolean server) {
    this.port = port;

    try {

      if (server) {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept(); //establishes connection
      }
      else socket = new Socket("localhost", port);   

      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      System.out.println("Connection running!");
    }
    catch(EOFException e) {
      // Handling when client connection closed (can be voluntary or not)
      System.out.println("EOF: " + e);
      //close(server);
    }
    catch(IOException e) {
      System.out.println("IO: " +e);
      //close(server);
    }
    catch(Exception e) {
      System.out.println("Other error: " + e);
    }
    finally {
      System.out.println("Finally!");
      //close(server);
    }
  }

  public void close(boolean server) {
    try {
      in.close();
      out.close();
      socket.close();

      if (server) serverSocket.close(); 
    }
    catch (IOException e) {System.out.println("Closing: " + e);}
  }
}
