package chat.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import chat.libs.User;
import chat.libs.Connection;
import java.util.Optional;
import java.io.IOException;


public class ChatServer {

  private static final Object lock = new Object();

  private static List<User> users = new ArrayList<>();
  private static List<Integer> ports = new ArrayList<>( Arrays.asList(6666,6667,6668,6669,6670) );

  public static void main(String[] args){

    Connection conn = new Connection();
    try {
      conn.dataOutputStream.writeUTF( Integer.toString( getAvailablePort() ) );
    }
    catch(IOException e) {System.out.println(e);}
    
    ExecutorService threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    threadPool.submit( new ClientConnection() );
  }


  // FOR HANDLING THE USER LIST
  // TODO: ReEntrant lock might be better than synchronized? Testing needed.

  // TODO: Handle (prevent) user name clashes in some fashion, and return false in that case
  public static boolean addUser(User user) {
    synchronized(lock) {
      users.add(user);
      return true;
    }
  }

  public static void removeUser(User user) {
    synchronized(lock) {
      users.remove(user);
    }
  }

  public static List<User> getUsers() {
    synchronized(lock) {
      return users;
    }
  }

  public static Optional<User> getUser(String userName) {
    synchronized(lock) {
      for (var u : users) {
        if ( u.getUserName().equals(userName) ) return Optional.of(u);
      }
      return Optional.empty(); // In case no user by that name was found.
    }
  }

  public static int getAvailablePort() {
    return ports.remove(0);
  }

  public static void makePortAvailable(int port) {
    ports.add(port); 
  }
}
