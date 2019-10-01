package chat.libs;

import java.net.Socket;

public class User {
   
	private String userName;
	private Socket sock;
	private int heartbeat;

  public User(String userName, Socket sock, int heartbeat) {
   	this.userName = userName;
   	this.sock = sock;
   	this.heartbeat = 0;
  }

  public User(Socket sock) {
    this.sock = sock;
    this.heartbeat = 0;
  }

  public String getUserName() {
    return userName;
  }

  public Socket getSocket() {
    return sock;
  }

  public int getHeartbeat() {
    return heartbeat;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setSocket(Socket sock) {
    this.sock = sock;
  }

  public void setHeartbeat(int heartbeat) {
    this.heartbeat = heartbeat;
  }
}
