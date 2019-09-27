package chat.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.List;
import java.util.ArrayList;

import chat.libs.User;
import java.util.Optional;

public class ChatServer {

  private final Object lock = new Object();

  private List<User> users = new ArrayList<>();

  public static void main(String[] args){
    
    var ss = new ServerSocket(6666);
    Socket s = ss.accept(); //establishes connection
    var dos = new DataOutputStream(s.getOutputStream());
    dos.writeUTF(assignedPort);

    ExecutorService threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    threadPool.submit( new ClientConnection() );
  }


  // FOR HANDLING THE USER LIST
  // TODO: ReEntrant lock might be better than synchronized? Testing needed.

  // TODO: Handle (prevent) user name clashes in some fashion, and return false in that case
  public boolean addUser(User user) {
    synchronized(lock) {
      users.add(user);
      return true;
    }
  }

  public void removeUser(User user) {
    synchronized(lock) {
      users.remove(user);
    }
  }

  public List<User> getUsers() {
    synchronized(lock) {
      return users;
    }
  }

  public Optional<User> getUser(String userName) {
    synchronized(lock) {
      for (var u : users) {
        if ( u.getUserName().equals(userName) ) return Optional.of(u);
      }
      return Optional.empty(); // In case no user by that name was found.
    }
  }
}
