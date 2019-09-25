import java.util.Optional;
import java.util.regex.Pattern;

public class TokenType {

	private String name;
	private Pattern regex;
	private boolean serverOnly;

  public TokenType(String name, Pattern regex, boolean server) {
    this.name = name;
		this.regex = regex;
		this.serverOnly = Optional.of(server);
  }

  // Should I not set the Option, or explicitly set it to some empty value? What is default?
  public TokenType(String name, Pattern regex) {
    this.name = name;
		this.regex = regex;
		//this.server = Optional.empty();
  }

  public Pattern getPattern() {
    return regex;
  }

  public boolean getServer() {
    return server;
  }
}
