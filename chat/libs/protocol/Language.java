package chat.libs.protocol;

import chat.libs.protocol.lexer.TokenType;
import chat.libs.protocol.parser.CommandType;

import java.util.List;

public interface Language {
  public List<TokenType> getTokenTypes();
  public List<CommandType> getCommandTypes();
}
