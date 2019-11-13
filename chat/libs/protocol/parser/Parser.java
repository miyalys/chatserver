package chat.libs.protocol.parser;

import chat.libs.protocol.lexer.TokenType;

import java.util.List;
import java.util.ArrayList;


public class Parser {

  private List<TokenType> tokenTypes = new ArrayList<>();
  private List<CommandType> commandTypes = new ArrayList<>();

  public Parser(List<TokenType> tts, List<CommandType> cts) {
    tokenTypes = tts;
		commandTypes = cts;
  }

  // public boolean hasServerOnlyCommands( List<Command> commands ) {
  //   for (Command c : commands) {
  //     if ( t.getType().getServerOnly() ) return true; 
  //   }
  //   return false; // If no Commands are server only
  // }

  // public Command parseSingle(String lexed) {
  //   return new Command("mjav", );
  // } 
}
