package chat.libs.protocol.parser;

import chat.libs.protocol.lexer.Token;

import java.util.List;

public class Command {

	private CommandType type;
	private List<Token> value;

  public Command(CommandType type, List<Token> value) {
		this.type = type;
		this.value = value;
  }

  public CommandType getType() {
    return type;
  }

  public List<Token> getValue() {
    return value;
  }
  
}
