import java.util.Scanner;
/**
 Program description: Hangman Game
 Author: Gülfem Küpeli
 E-mail address: 210401024@ogr.ikcu.edu.tr
 Homework Number: 04
 Last Changed: 16/12/2023
 */


public class Hangman {
    private String secret_word;
    private StringBuilder disguised_word;
    private int guesses_made;
    private int incorrect_guesses;
    private static final int max_incorrect_guesses = 6;
    private boolean isGameOver;

    //precondition--> takes the string word input
    //postconditions-->initializes the game with word
    public Hangman(String word) {
        secret_word = word.toLowerCase();
        disguised_word = new StringBuilder(secret_word.replaceAll("[a-zA-Z]", "?"));

        incorrect_guesses = 0;
        guesses_made = 0;

        isGameOver = false;
    }

    //This keeps track the incorrect guess number
    // Preconditions: No
    // Postconditions: Updates 'isGameOver' if the maximum incorrect guesses are made or the word is found.
    private void checkGameStatus() {
        if (incorrect_guesses >= max_incorrect_guesses || isFound()) {
            isGameOver = true;
        }
    }

    // gets the current disguised word with guessed letters revealed
    // Preconditions: No
    // Postconditions: Returns a String representation of the disguised word.
    public String getDisguisedWord() {
        return disguised_word.toString();
    }

    // says the secret word
    // Preconditions: No
    // Postconditions: Returns the secret word
    public String getSecretWord() {
        return secret_word;
    }

    // says the number of guesses made in the game
    // Preconditions: No
    // Postconditions: Returns the count of guesses made
    public int getGuessCount() {
        return guesses_made;
    }

    //preconditions--> c(ASCII charc.)
    //postconditions-->checks if guessed letter is in word
    public void makeGuess(char c) {
        if (!isGameOver) {
            c = Character.toLowerCase(c);
            if (secret_word.indexOf(c) != -1) {
                for (int i = 0; i < secret_word.length(); i++) {
                    if (secret_word.charAt(i) == c) {
                        disguised_word.setCharAt(i, c);
                    }
                }
            } else {
                incorrect_guesses++;
            }
            guesses_made++;
            checkGameStatus();
        }
    }

    // Checks if the word is fully revealed
    //no preconditions
    //postconditions--> if character has guessed it returns true
    private boolean isFound() {
        return disguised_word.indexOf("?") == -1;
    }

    //this main method is testing for the Hangman class
    //no preconditions
    //postconditions-->runs the Hangman game
    public static void main(String[] args) {
        String secret_word = "programming";
        Hangman hangmanGame = new Hangman(secret_word);
        Scanner scanner = new Scanner(System.in);

        while (!hangmanGame.isGameOver) {
            System.out.println("//////////////////////////");
            System.out.println("Disguised word: " + hangmanGame.getDisguisedWord());
            System.out.println("Guess count: " + hangmanGame.getGuessCount());
            System.out.println("Incorrect guesses: " + hangmanGame.incorrect_guesses);


            System.out.print("Enter your guess: ");
            char guess = scanner.next().charAt(0);
            hangmanGame.makeGuess(guess);
        }

        if (hangmanGame.isFound()) {
            System.out.println("Congratulations! You found the word: " + hangmanGame.getSecretWord());
        } else {
            System.out.println("Oh no. You don't have any guesses left. The word was: " + hangmanGame.getSecretWord());
        }

        scanner.close();
    }
}
