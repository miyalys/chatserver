package chat.client;

// Send IMAV to the server every sixty seconds
public class Heartbeat implements Runnable {
  private Connection con;

  public Heartbeat(Connection con) {
    this.con = con;
  }

  aliveFrequencyMs=6000
  public void run() {
    //while (true) {
      //"IMAV"
      //Thread.sleep(aliveFrequencyMs);
    //}
  } 
}
