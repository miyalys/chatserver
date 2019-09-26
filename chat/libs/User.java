public class User {
   
	private String userName;
	private int port;
	private int heartbeat;

  public User(String userName, int port, int heartbeat) {
   	this.userName = userName;
   	this.port = port;
   	this.heartbeat = heartbeat;
  } 

  public String getUserName() {
    return userName;
  }

  public int getPort() {
    return port;
  }

  public int getHeartbeat() {
    return heartbeat;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setHeartbeat(int heartbeat) {
    this.heartbeat = heartbeat;
  }
}
