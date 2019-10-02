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

import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {

  private static final Object lock = new Object();
  private static List<User> users = new ArrayList<>();
  private static final int PORT = 6666;

  public static void main(String[] args){

    ExecutorService threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    ServerSocket serverSocket = null;
    Socket socket = null;

    try {
      serverSocket = new ServerSocket(PORT);
    }
    catch (IOException e) {e.printStackTrace(); }

    // System.out.println("Before con");
    while (users.size() <= 5) {

      synchronized(serverSocket) {
        try {
          socket = serverSocket.accept(); // establishes connection
        }
        catch (IOException e) { e.printStackTrace(); }
      }
      System.out.println("After con: Found a new client!");

      // Spawn a new thread for that user
      threadPool.submit( new ClientConnection(socket) );
    }
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
}
