package chat.server;

import chat.server.ClientHandler;

public class User {
   
	private String userName;
	private ClientHandler clientHandler;
	private int heartbeat;

  // Not initialized with a username as the connection is created before we have a username. It's set afterwards.
  public User(ClientHandler c) {
    clientHandler = c;
    // How to implement heartbeat exactly? Should it start at zero or current time?
    heartbeat = 0;
  }

  public String getUserName() {
    return userName;
  }

  public ClientHandler getClientHandler() {
    return clientHandler;
  }

  public int getHeartbeat() {
    return heartbeat;
  }

  public void setUserName(String u) {
    userName = u;
  }

  public void setClientHandler(ClientHandler c) {
    clientHandler = c;
  }

  public void setHeartbeat(int h) {
    heartbeat = h;
  }
}
