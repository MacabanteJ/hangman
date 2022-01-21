import java.util.Scanner;
  

public class PlayHangMan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String[] allWords = {"Banana", "Orange", "Racecar", "Flugelhorn", "Textbook","Tonsils","Takahashi"};
    String guess = "";
    String word = "";
    String choice = "";
    
    System.out.println("------------------------------");
    System.out.println("Welcome to Hangman.");
    System.out.println("Type 'R' to play the random word gamemode, type 'T' to play \n the two player gamemode.");
    choice = in.nextLine();
    while(!(choice.toUpperCase().trim().equals("R") || choice.toUpperCase().trim().equals("T"))){
      System.out.println("Type an actual choice, idiot.");
      choice = in.nextLine();
    }
    if(choice.toUpperCase().trim().equals("R")){
      word = allWords[(int)(Math.random()*(allWords.length+1))];
    }else if(choice.toUpperCase().trim().equals("T")){
      System.out.println("Pass the computer to the player that will type the word.");
      while(!choice.toUpperCase().trim().equals("Y")){
      System.out.println("Word: ");
      word = in.nextLine();
      System.out.println("Are you sure that " + word + " is a suitable word? Y/N");
      choice = in.nextLine();
      while(!(choice.toUpperCase().trim().equals("Y") || choice.toUpperCase().trim().equals("N"))){
        System.out.println("Bro, you know the problem already.");
        choice = in.nextLine();
      }
      }
    }
    
    HangMan game = new HangMan(word.toUpperCase());
    
    while(!game.didLose() && !game.didWin()) {
      
      System.out.println("Your word so far is " + game.getWordSoFar());
      game.printMan();
      boolean singleLetter = false;
      while(!singleLetter) {
        System.out.print("Guess a letter");
        guess = in.next();
        if(guess.length() != 1) {
          System.out.println("Not valid guess. Guess a letter");
        } else singleLetter = true;
      }
      
      if(game.guess(guess))
        System.out.println("Good Guess!");
      else
        System.out.println("Sorry =(");
      System.out.println(game.guessesLeft() + " guesses remaining.");
    }
    
    if(game.didLose()) {
      System.out.println("You Lost =(");
      game.printMan();
      System.out.println("The word was " + word);
    }
    else {
      System.out.println("You won! =)");
    }
    in.close();
  }
}