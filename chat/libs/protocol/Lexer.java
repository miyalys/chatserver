package chat.libs.protocol;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {
  private static final List<TokenType> TOKEN_TYPES = new ArrayList<>();
  private boolean debug = true;

  // TODO: Remember to lowercase the input, so both JOIN, join and JoIn works? Or maybe not if it's entirely computer handled?
  public Lexer() {

    // Order matters!
    // Commands
    TOKEN_TYPES.add( new TokenType("join",     Pattern.compile("JOIN"), false) );
    TOKEN_TYPES.add( new TokenType("ok",       Pattern.compile("J_OK"), true) );
    TOKEN_TYPES.add( new TokenType("er",       Pattern.compile("J_ER"), true) );
    TOKEN_TYPES.add( new TokenType("data",     Pattern.compile("DATA"), false) );
    TOKEN_TYPES.add( new TokenType("imav",     Pattern.compile("IMAV"), false) );
    TOKEN_TYPES.add( new TokenType("quit",     Pattern.compile("QUIT"), false) );
    TOKEN_TYPES.add( new TokenType("list",     Pattern.compile("LIST"), true) );

    // Both client and server
    TOKEN_TYPES.add( new TokenType("ip",       Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") ) ); // server_ip. 255.255.255.255 or something above 255 is not really valid but whatever for now.
    TOKEN_TYPES.add( new TokenType("err_code", Pattern.compile("\\d+") ) ); // port or err_code
    TOKEN_TYPES.add( new TokenType("string",   Pattern.compile("[a-zA-Z0-9_\\-.,]+") ) ); // user_name, message or err_msg
    TOKEN_TYPES.add( new TokenType("colon",    Pattern.compile(":") ) ); // server_ip. 255.255.255.255 or something above 255 is not really valid but whatever for now.
  }

  // If you want to pass in a custom list of token to match. More flexible.
  public Lexer(List<TokenType> token_types) {
    TOKEN_TYPES.addAll(token_types);
  }

  public boolean hasServerOnlyCommands( List<Token> tokens ) {
    for (Token t : tokens) {
      if ( t.getType().getServerOnly() ) return true; 
    }
    return false; // If no tokens are server only
  }

  private void debug(String in) {
    if (debug) System.out.println(in);
  }


  public List<Token> lex(String input) {
    List<Token> tokensFound = new ArrayList<>();

    // Remove preceding and trailing white space. Specifically trailing white space is needed as offset will otherwise never == length.
    input = input.trim();

    int oldOffset, offset;
    oldOffset = offset = 0;
    int length = input.length();

    while (offset != length) {
      debug("Offset: " + offset);
      debug("Length: " + length);


      for (TokenType tt : TOKEN_TYPES) {
        Matcher matcher = tt.getPattern().matcher(input);
        if ( matcher.find(offset) ) {
          debug("A token was matched!");
          tokensFound.add( new Token( tt, matcher.group() ) );
          offset = matcher.end();
          break; // If a match is found break, so next elements are checked against ALL token types and in the right order.
        }
        else debug("Trying next TokenType");
      }

      /* For removing preceding whitespace. This is required for remov */
      //Matcher sm = Pattern.compile("\\s").matcher(input);
      //if ( sm.find(offset) ) { 
      //  debug("White space found. Skipping.");
      //  offset = sm.end();
      //  debug("New offset skipped to: " + offset);
      //}

      //If all token types where tried and none matched, either the Lexer is faulty or the TokenList doesn't match the input. 
      if (offset == oldOffset) return null;
      else oldOffset = offset;
    }

    return tokensFound;
  }
}
