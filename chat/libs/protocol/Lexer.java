package chat.libs.protocol;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {
  private static final List<TokenType> TOKEN_TYPES = new ArrayList<>();

  // TODO: Remember to lowercase the input, so both JOIN, join and JoIn works? Or maybe not if it's entirely computer handled?
  public Lexer() {

    // Order matters!
    TOKEN_TYPES.add( new TokenType("join", Pattern.compile("/\\bJOIN\\b/"), false) ); // Commands
    TOKEN_TYPES.add( new TokenType("ok",   Pattern.compile("/\\bJ_OK\\b/"), true) );
    TOKEN_TYPES.add( new TokenType("er",   Pattern.compile("/\\bJ_ER\\b/"), true) );
    TOKEN_TYPES.add( new TokenType("data", Pattern.compile("/\\bDATA\\b/"), false) );
    TOKEN_TYPES.add( new TokenType("imav", Pattern.compile("/\\bIMAV\\b/"), false) );
    TOKEN_TYPES.add( new TokenType("quit", Pattern.compile("/\\bQUIT\\b/"), false) );
    TOKEN_TYPES.add( new TokenType("list", Pattern.compile("/\\bLIST\\b/"), true) );

    // Both client and server
    TOKEN_TYPES.add( new TokenType("ip",       Pattern.compile("/\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b/") ) ); // server_ip
    TOKEN_TYPES.add( new TokenType("err_code", Pattern.compile("/\\b\\d+\\b/") ) ); // port or err_code
    TOKEN_TYPES.add( new TokenType("string",   Pattern.compile("/\\b[a-zA-Z0-9_-]+\\b/") ) ); // user_name, message or err_msg
  }

  // If you want to pass in a custom list of token to match. More flexible.
  public Lexer(List<TokenType> token_types) {
    TOKEN_TYPES.addAll(token_types);
  }

  public boolean hasServerOnlyCommands( List<Token> tokens ) {
    for (Token t : tokens) {
      if ( t.getType().getServerOnly() ) return true; 
    }
    // If no tokens are server only
    return false;
  }


  public List<Token> lex(String input) {
    List<Token> tokensFound = new ArrayList<>();

    int offset = 0;
    int length = input.length();

    while (offset != length) {

      for (TokenType tt : TOKEN_TYPES) {
        Matcher matcher = tt.getPattern().matcher(input);
        if ( matcher.find(offset) ) {
          tokensFound.add( new Token( tt, matcher.group() ) );
          offset = matcher.end();
        }
      }
    }

    return tokensFound;
  }
}
