
//imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class xkcdpwgen {

  public static void main(String[] args) throws IOException {
    System.out.println(gen(args)); // takes in command line
  }

  public static String gen(String[] input) throws IOException {
    int wordCount, capCount, numberCount, symbolCount; //ints to keep track off

    wordCount = 4;//set default number as 4
    capCount = 0;
    numberCount = 0;
    symbolCount = 0;
    
    //takes in the input and recognizes the number after if there is one
    for (int i = 0; i < input.length; i++) {
      if (input[i].equalsIgnoreCase("-h") || input[i].equalsIgnoreCase("--help")) {
        return "How to use\n"
            + "Type in 'xkcdpwgen' with either -h, -c, -n, -s, --help, --words, --numbers, --symbols\n"
            + " with a number in front of it\n"
            + "You can also choose to not put a number in front and it will generate a\n"
            + "4 word password. If you do use them, -h and --help provides this message\n"
            + "-w and --words sets the number of words\n"
            + "-c and --caps capitalizes the first letters of a # of random words\n"
            + "-n and --n sets the number of words in the password\n"
            + "-s and --symbols inserts a # of random symbols into the password";
      }
      else if (input[i].equals("-w") || input[i].equals("--words")) {
        wordCount = Integer.parseInt(input[++i]);
      }
      else if (input[i].equals("-c") || input[i].equals("--caps")) {
        capCount = Integer.parseInt(input[++i]);
      }
      else if (input[i].equals("-n") || input[i].equals("--numbers")) {
        numberCount = Integer.parseInt(input[++i]);
      }
      else if (input[i].equals("-s") || input[i].equals("--symbols")) {
        symbolCount = Integer.parseInt(input[++i]);
      }
    }

    //error statements if the user puts in a bad number
    if (wordCount < 1) {
      throw new IllegalArgumentException("Word count must be at least 1");
    }
    if (capCount < 0) {
      throw new IllegalArgumentException("Cap count must be at least 0");
    }
    if (numberCount < 0) {
      throw new IllegalArgumentException("Number count must be at least 0");
    }
    if (symbolCount < 0) {
      throw new IllegalArgumentException("Symbol count must be at least 0");
    }
    if (capCount > wordCount) {
      throw new IllegalArgumentException("Cap count cannot be higher than the actual word count");
    }

    String LoWords[] = new String[wordCount];// List of words stored in an array
    for (int i = 0; i < wordCount; i++) {
      LoWords[i] = Files.readAllLines(Paths.get("words.txt")).get(new Random().nextInt(58110));                                                                                                                                                                                           // array
    }

    //for loop to make the first letter equal
    for (int x = capCount; x > 0; x--) {
      int randomWord = new Random().nextInt(wordCount); //picks a random word

      //condition to make sure it doesnt make the same word uppercase again
      if (LoWords[randomWord].substring(0, 1).toUpperCase() != LoWords[randomWord].substring(0,
          1)) {
        randomWord++;
      }
      else {
        //makes a random word uppercase for the first letter
        LoWords[randomWord] = LoWords[randomWord].substring(0, 1).toUpperCase()
            .concat(LoWords[randomWord].substring(1));
      }
    }

    for (int x = numberCount; x > 0; x--) {
      int randomWord = new Random().nextInt(wordCount); //the random word that it is being placed next to
      int randomNumber = new Random().nextInt(10); //decides the random number being placed in
      int randomPlacement = new Random().nextInt(3); //decides if the number is in the beginning, middle or end

      if (randomPlacement == 0) {
        LoWords[randomWord] = LoWords[randomWord] + randomNumber; //end of number
      }
      else if (randomPlacement == 1) {
        // in the middle
        LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
            + randomNumber + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
      }
      else {
        //behind the number
        LoWords[randomWord] = randomNumber + LoWords[randomWord];
      }
    }
    //list of if and else ifs to add random symbols
    for (int x = symbolCount; x > 0; x--) {
      int randomSymbol = new Random().nextInt(10); //picking a random symbol
      int randomWord = new Random().nextInt(wordCount); //picking a random word
      int randomPlacement = new Random().nextInt(3); //where the number is being placed

      if (randomSymbol == 0) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + ")";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = ")" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + ")" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 1) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "!";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "!" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "!" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 2) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "@";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "@" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "@" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 3) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "#";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "#" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "#" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 4) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "$";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "$" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "$" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 5) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "%";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "%" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "%" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 6) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "^";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "^" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "^" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 7) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "&";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "&" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "&" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else if (randomSymbol == 8) {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "*";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "*" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "*" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
      else {
        if (randomPlacement == 0) {
          LoWords[randomWord] = LoWords[randomWord] + "(";
        }
        else if (randomPlacement == 1) {
          LoWords[randomWord] = "(" + LoWords[randomWord];
        }
        else {
          LoWords[randomWord] = LoWords[randomWord].substring(0, (LoWords[randomWord].length() / 2))
              + "(" + (LoWords[randomWord].substring(LoWords[randomWord].length() / 2));
        }
      }
    }
    String password = ""; //empty string
    
    //adding to the actual string to return
    for (int i = 0; i < wordCount; i++) {
      password = password + LoWords[i];
    }
    return password;
  }
}
