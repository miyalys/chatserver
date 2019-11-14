package chat.libs;

public class Helper {

  // public boolean checkValidIP(String ip) {}
  // public boolean checkValidPort(int port) {}

  public static boolean isValidUserName(String input) {
    return ( input.length() <= 12 && !input.matches("[^a-zA-Z0-9_\\-]") );
  }
}
