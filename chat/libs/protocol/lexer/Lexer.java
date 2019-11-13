package chat.libs.protocol.lexer;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {
  private static final List<TokenType> TOKEN_TYPES = new ArrayList<>();
  private boolean debug = false;

  public Lexer(List<TokenType> tokenTypes) {
    TOKEN_TYPES.addAll(tokenTypes);
  }

  private void debug(String in) {
    if (debug) System.out.println(in);
  }

  public List<Token> lex(String input) {
    List<Token> tokensFound = new ArrayList<>();

    // Remove preceding and trailing white space.
    input = input.trim();
    String postInput = input;

    while (input.length() != 0) {

      //debug("Offset: " + offset);
      //debug("Length: " + length);
      ////debug("Current input: " + input.substring(offset));
      //debug("Current input: " + input);
      
      for (TokenType tt : TOKEN_TYPES) {
        Matcher matcher = tt.getPattern().matcher(input);
        if ( matcher.find() ) {
          debug("TokenType " + tt.getName() + " matched!");
          tokensFound.add( new Token( tt, matcher.group() ) );
          // Find offset of the end of the match, and remove preceding whitespace
          postInput = input.substring(matcher.end()).trim();
          break; // If a match is found break, so next elements are checked against ALL token types and in the right order.
        }
        else debug("TokenType " + tt.getName() + " didn't match.");
      }

      //If all token types where tried and none matched, either the Lexer is faulty or the TokenList doesn't match the input. 
      if (input.equals(postInput)) return null;
      else input = postInput;
    }

    return tokensFound;
  }
}
