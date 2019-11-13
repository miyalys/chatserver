package chat.client;

import chat.libs.Connection;

// Send IMAV to the server every sixty seconds
public class Heartbeat implements Runnable {
  private Connection con;
  private int aliveFrequencyMs=60000;

  public Heartbeat(Connection con) {
    this.con = con;
  }

  public void run() {
    //while (true) {
      //"IMAV"
      //Thread.sleep(aliveFrequencyMs);
    //}
  } 
}
