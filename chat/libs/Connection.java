package chat.libs;

import java.io.*;
import java.net.*;

public class Connection {

  // Made public mostly temporarily for ease of use while testing
  public ServerSocket serverSocket;
  public Socket socket;
  public DataInputStream dataInputStream;
  public DataOutputStream dataOutputStream;

  public Connection(boolean server) {
    this(6666, server);
  }

  public Connection(int port, boolean server) {

    try {

      if (server) {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept(); //establishes connection
      }
      else socket = new Socket("localhost", port);   

      dataInputStream = new DataInputStream(socket.getInputStream());
      dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    catch(EOFException e) {
      // Handling when client connection closed (can be voluntary or not)
    }
    catch(IOException e) {
      System.out.println(e);
    }
    finally {
      close(server);
    }
  }

  public void close(boolean server) {
    try {
      socket.close();
      dataInputStream.close();
      dataOutputStream.close();

      if (server) serverSocket.close(); 
    }
    catch (IOException e) {System.out.println(e);}
  }
}
