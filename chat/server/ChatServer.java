package chat.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {

  public static void main(String[] args){
    ExecutorService threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    threadPool.submit( new ClientConnection(6666) );
  }
}
