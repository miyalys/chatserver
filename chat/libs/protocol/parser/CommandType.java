package chat.libs.protocol.parser;

import chat.libs.protocol.lexer.TokenType;

import java.util.List;

public class CommandType {
    
	private String name;
  private List<TokenType> tokenTypes;
	private boolean serverOnly;

  public CommandType(String name, List<TokenType> tokenTypes, boolean serverOnly) {
		this.name = name;
		this.tokenTypes = tokenTypes;
    this.serverOnly = serverOnly;
  }

  public String getName() { return name; }
  public List<TokenType> getTokenTypes() { return tokenTypes; }
  public boolean getServerOnly() { return serverOnly; }

}
