package chat.libs.protocol.lexer;

import java.util.regex.Pattern;

public class TokenType {

	private String name;
	private Pattern regex;

  public TokenType(String name, Pattern regex) {
    this.name = name;
		this.regex = regex;
  }

  public String getName() { return name; }
  public Pattern getPattern() { return regex; }
}
