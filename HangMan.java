import java.util.ArrayList;

/* For this class, you will have to:
 * 1. Write the constructor
 * 2. Write the getWordSoFar() method
 * 3. Write the guess() method
 * 4. Write the didWin() method
 * 5. Write the didLose() method
 * 6. Write the printMan() method
 * 7. (Exceeding for 85%+) Add a scanner to the PlayHangman Class so the player can have different play modes
 *     - Ask player what mode they want (either a word randomly selected from a predefined array of words
 *                                       or based on user input)
 *     - If they select random mode, assign the word randomly
 *     - If they select user input, prompt the user to type in the word (like a 2-player game)
 */ 

public class HangMan
{
  private ArrayList<String> word; //List of individual letters in a word to guess
  private ArrayList<Boolean> correct; //holds whether the user has guessed individual letters correctly or not
  private ArrayList<String> lettersWrong;
  private int guessesLeft; //how many guesses the user has left
  
  //1.  creates a game of HangMan where wordToGuess holds the String that the player needs to guess
  public HangMan(String wordToGuess) {
    word = new ArrayList<String>();
    correct = new ArrayList<Boolean>();
    guessesLeft = 8;  //Default number of guesses when a game is created is 8
    for(int i = 0; i < wordToGuess.length(); i++){
      word.add(wordToGuess.substring(i,i+1));
      correct.add(false);
      lettersWrong = new ArrayList<String>();
    }
    
    //a. Initialize the two ArrayList's word and correct.
    //b. Fill the word ArrayList with each of the one letter substrings from wordToGuess
    //c. Fill the correct ArrayList with false - the number of falses should match the length of wordToGuess
  }
  
  //2.  this method should return how much of the String the user has guessed so far, leaving ? marks
  //for letters the user has not yet guessed
  //
  //For example: 
  // word = {"A", "N", "G", "R", "Y"}
  // correct = {false, true, false, true, true}
  //
  //In this case, the method should return ?N?RY in this case
  public String getWordSoFar() {
    String soFar = "";
    for(int i = 0; i < word.size(); i++){
      if(correct.get(i)){
        soFar += word.get(i);
      }else{soFar += "?";}
    }
    
    return soFar;
  }
  
  //3.  User guesses that an individual letter is in the word
  //  a.  correct should be updated with true values for every matching letter
  //  b.  guessesLeft should be decremented with an incorrect guess
  //  c.  returns true if you guessed correctly, false otherwise

  public boolean guess(String letter) {
    boolean tru = false;
    for(int i = 0; i < word.size();i++){
      if(word.get(i).equals(letter.toUpperCase())){
        correct.set(i,true);
        tru = true;
      }
    }
    if(tru){return true;}
    guessesLeft--;
    lettersWrong.add(letter.toUpperCase());
    return false;
  }
  
  //4.  if the player has guessed all the words, print the arraylist to show the correct answer
  //returns whether the word has been totally guessed
  public boolean didWin() {
    String wordS = "";
    for(int i = 0; i < correct.size(); i++){
      if(!correct.get(i)){
        return false;
      } else {
        wordS += word.get(i);
      }
    }
    System.out.println(wordS);
    return true;
  }
  
  
  //5.  returns whether the player has run out of guesses without correctly guessing the word
  public boolean didLose() {
    if(guessesLeft == 0){
      return true;
    }
    return false;
  }
  
  //6.  prints a hangman visual to represent your "life" in the game
  public void printMan() {
    /* * * * * * * *
     * | - - - - "
     * | " " " | "
     * | " " " O "
     * | " " \ | /
     * | " " " | "
     * | " " " | "
     * | " " / " \
     * */
    String[][] hangman = new String[7][6];
    for(int row = 0; row < hangman.length; row++){
      for(int column = 0; column < hangman[row].length; column++){
        if(column == 0){
          hangman[row][column] = "|";
        } else if(row == 0 && column < hangman[row].length-1){
          hangman[row][column] = "-";
        } else if(row == 1 && column == 4){
          hangman[row][column] = "|";
        }else{
          hangman[row][column] = " ";
        }
        if(guessesLeft == 7){
         hangman[2][4] = "O";
        } else if(guessesLeft == 6){
          hangman[2][4] = "O";
         hangman[3][4] = "|";
        } if(guessesLeft == 5){
          hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
        } else if(guessesLeft == 4){
          hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
          hangman[5][4] = "|";
        } else if(guessesLeft == 3){
         hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
          hangman[5][4] = "|";
          hangman[3][3] = "\\";
        } else if(guessesLeft == 2){
         hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
          hangman[5][4] = "|";
          hangman[3][3] = "\\";
          hangman[3][5] = "/";
        } else if(guessesLeft == 1){
         hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
          hangman[5][4] = "|";
          hangman[3][3] = "\\";
          hangman[3][5] = "/";
          hangman[6][3] = "/";
        } else if(guessesLeft == 0){
          hangman[2][4] = "O";
          hangman[3][4] = "|";
          hangman[4][4] = "|";
          hangman[5][4] = "|";
          hangman[3][3] = "\\";
          hangman[3][5] = "/";
          hangman[6][3] = "/";
         hangman[6][5] = "\\";
        }
      }
    }
    for(int row = 0; row < hangman.length; row++){
      for(int column = 0; column < hangman[row].length; column++){
        System.out.print(hangman[row][column]);
        System.out.print(" ");
      }
      System.out.println("");
    }
    String w = "";
    for(String i : lettersWrong){
      w += "| " + i + " | ";
    }
    System.out.println("Letters guessed: " + w);
  }
  
  //This is an accessor for the guessesLeft variable.
  //Nothing needs to change
  public int guessesLeft() {
    return guessesLeft;
  }
}