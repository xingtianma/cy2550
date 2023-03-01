import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class xkcdpwgen {

  public static void main(String[] args) throws IOException {
    System.out.println(gen(args));
  }

  public static String gen(String[] input) throws IOException {
    int w = 4;
    int c = 0;
    int n = 0;
    int s = 0;
    
    // process input
    for (int x = 0; x < input.length; x++) {
      if (input[x].equalsIgnoreCase("-h")) {
        return "usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\r\n"
            + "                \r\n"
            + "Generate a secure, memorable password using the XKCD method\r\n"
            + "                \r\n" + "optional arguments:\r\n"
            + "    -h, --help            show this help message and exit\r\n"
            + "    -w WORDS, --words WORDS\r\n"
            + "                          include WORDS words in the password (default=4)\r\n"
            + "    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\r\n"
            + "                          (default=0)\r\n" + "    -n NUMBERS, --numbers NUMBERS\r\n"
            + "                          insert NUMBERS random numbers in the password\r\n"
            + "                          (default=0)\r\n" + "    -s SYMBOLS, --symbols SYMBOLS\r\n"
            + "                          insert SYMBOLS random symbols in the password\r\n"
            + "                          (default=0)";
      }
      else if (input[x].equalsIgnoreCase("-h") || input[x].equalsIgnoreCase("--help")) {
        w = Integer.parseInt(input[x + 1]);
        x++;
      }
      else if (input[x].equalsIgnoreCase("-c") || input[x].equalsIgnoreCase("--caps")) {
        c = Integer.parseInt(input[x + 1]);
        x++;
      }
      else if (input[x].equalsIgnoreCase("-n") || input[x].equalsIgnoreCase("--numbers")) {
        n = Integer.parseInt(input[x + 1]);
        x++;
      }
      else if (input[x].equalsIgnoreCase("-s") || input[x].equalsIgnoreCase("--symbols")) {
        s = Integer.parseInt(input[x + 1]);
        x++;
      }
      
      if (w < 1) {
        throw new IllegalArgumentException("Words must be 1 or greater");
      }
      if (c < 0) {
        throw new IllegalArgumentException("Caps must be 0 or greater");
      }
      if (c > w) {
        throw new IllegalArgumentException("Caps must be less than the number of words");
      }
      if (n < 0) {
        throw new IllegalArgumentException("Numbbers must be at least 0");
      }
      if (s < 0) {
        throw new IllegalArgumentException("Symbols must be at least 0");
      }

      String wordList[] = new String[w];
      int randInt = new Random().nextInt(58110);
      for (int z = 0; z < w; z++) {
        wordList[x] = Files.readAllLines(Paths.get("word.txt")).get(randInt);
      }

      if (c > 0) {
        while (c != 0) {
          int capNum = new Random().nextInt(w);

          while (Character.isUpperCase(wordList[capNum].charAt(0))) {
            capNum = new Random().nextInt(w);
          }
          wordList[capNum] = wordList[capNum].substring(0, 1).toUpperCase()
              + wordList[capNum].substring(1);
          c--;
        }
      }
      while (n != 0) {
        int numInt = new Random().nextInt(w);

        if (new Random().nextInt(1) == 1) {
          wordList[numInt] = new Random().nextInt(9) + wordList[numInt];
        }
        else {
          wordList[numInt] = wordList[numInt] + new Random().nextInt(9);
        }
      }

      int newRand = new Random().nextInt(5);

      if (newRand == 0) {
        while (s != 0) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "!";
          }
          else {
            wordList[a] = "!" + wordList[a];
          }
        }
      }
      if (newRand == 1) {
        while (s != 0) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "@";
          }
          else {
            wordList[a] = "@" + wordList[a];
          }
        }
      }
      if (newRand == 2) {
        while (s != 2) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "#";
          }
          else {
            wordList[a] = "#" + wordList[a];
          }
        }
      }
      if (newRand == 3) {
        while (s != 3) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "$";
          }
          else {
            wordList[a] = "$" + wordList[a];
          }
        }
      }
      if (newRand == 4) {
        while (s != 4) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "^";
          }
          else {
            wordList[a] = "^" + wordList[a];
          }
        }
      }
      else {
        while (s != 0) {
          int a = new Random().nextInt(w);

          if (new Random().nextInt(1) == 1) {
            wordList[a] = wordList[a] + "*";
          }
          else {
            wordList[a] = "*" + wordList[a];
          }
        }
      }
    }
    String password = "";
    
    String newWord[] = new String[w];
    
    for(int z = 0; z < w; z++) {
      newWord[z] = Files.readAllLines(Paths.get("words.txt")).get(new Random().nextInt(58110));
    }
    
    for(int u = 0; u < w; u++) {
      password = password + newWord[u];
    }
    
    return password;
  }
}
