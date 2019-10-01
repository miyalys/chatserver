package chat.libs;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.lang.InterruptedException;

/* V 1.0 */
public class StaticLib {

  private static Scanner input = new Scanner(System.in);

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  public static void pressEnterToContinue() {
    System.out.println("PRESS ENTER TO CONTINUE");
    input.nextLine();
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  public static String getInput() {
    System.out.print("> ");
    return input.nextLine();
  }

  /**
    *Some Unicode character ranges that contain digits:
    '\u0030' through '\u0039', ISO-LATIN-1 digits ('0' through '9')
    '\u0660' through '\u0669', Arabic-Indic digits
    '\u06F0' through '\u06F9', Extended Arabic-Indic digits
    '\u0966' through '\u096F', Devanagari digits
    '\uFF10' through '\uFF19', Fullwidth digits
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  public static boolean isNumericalValue(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (!Character.isDigit(input.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isArabicNumericalValue(String input) {
    return ( !input.matches("[^0-9]") );
  }

  public static boolean isValidUserName(String input) {
    return ( input.length() <= 12 || !input.matches("[^a-zA-Z0-9_\\-]") );
  }

  /**
  A character is considered to be a letter if its general category type, provided by getType(ch), is any of the following:
  UPPERCASE_LETTER
  LOWERCASE_LETTER
  TITLECASE_LETTER
  MODIFIER_LETTER: General category "Lm" in the Unicode specification: https://www.compart.com/en/unicode/category/Lm
  OTHER_LETTER: General category "Lo" in the Unicode specification: https://www.compart.com/en/unicode/category/Lo
  */
  public static boolean hasOnlyLetters(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (!Character.isLetter(input.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean hasOnlyLatinLetters(String input) {
    return ( !input.matches("[^a-zA-Z]") );
  }

  // Apparently alphanumeric implies latin characters and arabic numerals
  public static boolean isAlphaNumeric(String input) {
    return ( isAlphaNumeric(input) && hasOnlyLatinLetters(input) );
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  public static int getNumericalInputLoop() {
    int value = 0;

    while (true) {
      String input = getInput();

      if (input.length() > 0 && isNumericalValue(input)) {
        value = Integer.parseInt(input);
        break;
      }
      else {
        System.out.println("You need to input a numerical value!");
      }
    }

    return value;
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  // Returns -1 if not numerical or not positive
  public static int checkNumericalPositiveInput(String input) {
    int value = -1;

    if (input.length() > 0 && isNumericalValue(input)) {
      int number = Integer.parseInt(input);
      if (number > 0) value = number;
    }
    else {
      System.out.println("You need to input a numerical value!");
    }

    return value;
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  public static int getNumericalInputRangeLoop(int min, int max) {
    int value = getNumericalInputLoop();

    while (value < min || value > max) {

      System.out.println("Numerical value must be in the range of (" + min + ", " + max + ")");
      value = getNumericalInputLoop();    
    }
    return value;
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  // Cross platform screen clearing
  public static void clearScreen() {
    if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1) {
      try {
          new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
      catch (IOException | InterruptedException e) {}
    }
    else {  // Works for Linux and macOS
      String ansiClear = "\033[H\033[2J";
      System.out.print(ansiClear);
      System.out.flush();
      //Runtime.getRuntime().exec("clear");
    }
  }

  /**
    *
    * @param  name desc
    * @param  name desc
    * @return      desc
    */
  // Used for ASCII images
  public static void printFilePath(String path) {
      File file = new File(path);
      printFile(file);
  }

  /**
    * Reads a file line by line and prints it to the terminal.
    * @param  file The file that should be printed to screen.
    */
  public static void printFile(File file) {
    try {
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        System.out.println( scanner.nextLine());
      }
    }
    catch (IOException e) { System.out.println(e); }
  }

}
