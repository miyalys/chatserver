package chat.libs.protocol;

import java.util.regex.Pattern;

public class TokenType {

	private String name;
	private Pattern regex;
	private boolean serverOnly;

  public TokenType(String name, Pattern regex, boolean serverOnly) {
    this.name = name;
		this.regex = regex;
		this.serverOnly = serverOnly;
  }

  public TokenType(String name, Pattern regex) {
    this.name = name;
		this.regex = regex;
  }

  public String getName() {
    return name;
  }

  public Pattern getPattern() {
    return regex;
  }

  public boolean getServerOnly() {
    return serverOnly;
  }
}
