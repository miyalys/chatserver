package chat.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {

  public static void main(String[] args){
    var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    var cc = new ClientConnection();
    cc.createClientConnection(6666);
  }
}
