package chat.libs.protocol;

import chat.libs.protocol.lexer.TokenType;
import chat.libs.protocol.parser.CommandType;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.regex.Pattern;

public class ChatLanguage implements Language {

  private List<TokenType> tts = new ArrayList<>();
  private List<CommandType> cts = new ArrayList<>();

  // Getter names kept short to make the parser language below more readable.
  private TokenType gtt(String name)     { return tts.stream().filter(e -> e.getName().equals(name)).findFirst().get(); }
  private CommandType gct(String name) { return cts.stream().filter(e -> e.getName().equals(name)).findFirst().get(); }

  // TODO: Remember to lowercase the input, so both JOIN, join and JoIn works? Or maybe not if it's entirely computer handled?
  public List<TokenType> getTokenTypes() {

    // Order matters!
    // Commands
    tts.add( new TokenType("join",     Pattern.compile("^JOIN")) );
    tts.add( new TokenType("ok",       Pattern.compile("^J_OK")) );
    tts.add( new TokenType("er",       Pattern.compile("^J_ER")) );
    tts.add( new TokenType("data",     Pattern.compile("^DATA")) );
    tts.add( new TokenType("imav",     Pattern.compile("^IMAV")) );
    tts.add( new TokenType("quit",     Pattern.compile("^QUIT")) );
    tts.add( new TokenType("list",     Pattern.compile("^LIST")) );

    // Both client and server
    tts.add( new TokenType("ip",       Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") ) ); // server_ip. 255.255.255.255 or something above 255 is not really valid but whatever for now.
    tts.add( new TokenType("number", Pattern.compile("^\\d+") ) );      // port or err_code
    tts.add( new TokenType("string",   Pattern.compile("^[a-zA-Z0-9\\-_]+") ) ); // user_name, message or err_msg. TODO: to contain spaces or not?
    // To create a dedicated username string or not? regex: a-zA-Z0-9\\-_   max 12 characters
    tts.add( new TokenType("colon",    Pattern.compile("^:") ) ); //
    tts.add( new TokenType("comma",    Pattern.compile("^,") ) ); //

    return tts;
  }

  // Should the lexer or parser handle/validate max number of characters?
  // Edit: Or maybe none of them should and I should handle it afterwards in the main program?


  public List<CommandType> getCommandTypes() {
    List joinTokens = new ArrayList<TokenType>(Arrays.asList( gtt("string"), gtt("comma"), gtt("ip"), gtt("colon"), gtt("number") )); // string, or should I create a dedicated username string?
    List okTokens   = new ArrayList<TokenType>(Arrays.asList( gtt("ok") ));
    List erTokens   = new ArrayList<TokenType>(Arrays.asList( gtt("er"), gtt("number"), gtt("colon"), gtt("string") ));
    List dataTokens = new ArrayList<TokenType>(Arrays.asList()); // recursive if the string TT shouldn't have spaces.
    List imavTokens = new ArrayList<TokenType>(Arrays.asList( gtt("imav") ));
    List quitTokens = new ArrayList<TokenType>(Arrays.asList( gtt("quit") ));
    List listTokens = new ArrayList<TokenType>(Arrays.asList()); // recursive!

    cts.add( new CommandType("join", joinTokens, false) ); // Need to validate port afterwards
    cts.add( new CommandType("ok", okTokens,   true ) );
    cts.add( new CommandType("er", erTokens,   true ) );
    cts.add( new CommandType("data", dataTokens, false) ); // Max 250 characters
    cts.add( new CommandType("imav", imavTokens, false) );
    cts.add( new CommandType("quit", quitTokens, false) );
    cts.add( new CommandType("list", listTokens, true ) );

    return cts;
  }

}
