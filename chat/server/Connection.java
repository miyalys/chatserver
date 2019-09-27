package chat.server;

import java.io.*;
import java.net.*;

public class Connection {

  public ServerSocket serverSocket;
  public Socket socket;
  public DataInputStream dataInputStream;
  public DataOutputStream dataOutputStream;

  public Connection(int port) {

    try {
      serverSocket = new ServerSocket(port);
      socket = serverSocket.accept(); //establishes connection
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
      try {
        serverSocket.close(); 
        socket.close();
        dataInputStream.close();
        dataOutputStream.close();
      }
      catch (IOException e) {System.out.println(e);}
    }
  }
}
