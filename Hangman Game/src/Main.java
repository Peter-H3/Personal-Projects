import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filePath = "words.txt"; // Name of text file

        ArrayList<String> words = new ArrayList<>(); // Array list for storing the words

        // Adds all the words from the file to the array list
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not find file");
        }
        catch(IOException e) {
            System.out.println("Something went wrong");
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size())); // Selects random word from array list

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>(); // Stores each letter of the word
        int wrongGuesses = 0; // Stores the number wrong guesses

        // Adds underscore as a placeholder for each character in the word
        for(int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("************************");

        // Game continues as long as the user has less than 6 wrong guesses
        while(wrongGuesses < 6) {

            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println();

            System.out.print("Word: ");

            for(char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            // The letter is revealed if the user guesses it
            if(word.indexOf(guess) >= 0) {
                System.out.println("Correct guess!\n");

                for(int i = 0; i < word.length(); i++) {

                    if(word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }

                // If all the letters are guessed, game ends as user wins
                if(!wordState.contains('_')) {
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.println("You win!");
                    System.out.println("The word was " + word);
                    break;
                }
            }
            else {
                System.out.println("Wrong guess!");
                wrongGuesses++;
                System.out.println((6 - wrongGuesses) + " guesses remaining\n");
            }
        }

        // If wrong guesses exceeds 6, the game ends and the word is revealed
        if(wrongGuesses >= 6) {
            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println("\nGame over!");
            System.out.println("The word was: " + word);
        }

        scanner.close();
    }
    // Gets correct hangman art based on how many wrong guesses the user has
    static String getHangmanArt(int wrongGuesses) {

        return switch(wrongGuesses) {
            case 0 -> """
                    
                    
                    
                      """;
            case 1 -> """
                       o
                    
                    
                      """;
            case 2 -> """
                       o
                       |
                    
                      """;
            case 3 -> """
                       o
                      /|     
                    
                      """;
            case 4 -> """
                       o
                      /|\\    
                    
                      """;
            case 5 -> """
                       o
                      /|\\    
                      /
                      """;
            case 6 -> """
                       o
                      /|\\    
                      / \\
                      """;
            default -> "";
        };
    }
}
