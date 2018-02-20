import java.util.Random;
import java.util.Scanner;

public class Hangman
{
    public static void main(String[] args)
    {
        String word = getRandomWord();
        int guesses = word.length() - 1;
        int originalGuesses = guesses;
        String userLetter;
        Scanner player = new Scanner(System.in);
        
        //Create partialWord: i.e. if word is BLUE, partial word
        // starts as "----"
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
        {
            builder.append("-");
        }
        String partialWord = builder.toString();
    
        builder = new StringBuilder();
        
        System.out.println("Welcome to Hangman!");
        System.out.println("Let's see if you can guess my word, shall we?");
        System.out.printf("I'll let you have %d guesses. If you guess wrong" +
                " I'll have to take one away!\n", guesses);
        System.out.println("Ready? Let's go...");
        
        //Game loop
        while (guesses > 0 && !word.equals(partialWord))
        {
            System.out.printf("\nYour current word is: %s\n", partialWord);
            System.out.print("Guess a letter: ");
            userLetter = player.next().toUpperCase();
            
            //Checking for invalid guess
            if (userLetter.length() > 1 ||
                    Character.isDigit(userLetter.toCharArray()[0]))
            {
                System.out.println("You must only enter one character! No" +
                        " numbers or symbols allowed.\n");
            }
            //letter is not in partialWord and is a valid letter in word
            else if (word.contains(userLetter) && !partialWord.contains(userLetter))
            {
                partialWord = replaceChar(word, partialWord, userLetter.toCharArray());
                System.out.printf("You now have %d guesses left.\n", guesses);
                builder.append(userLetter).append("   "); //to keep track of
                // already guessed letters
            }
            else if (partialWord.contains(userLetter)) //if letter already in
                // partialWord
            {
                System.out.printf("You've already found all of the %s's\n", userLetter);
            }
            else
            {
                --guesses;
                System.out.printf("Sorry, there are no %s's in the word :" +
                        "(\n", userLetter);
                System.out.printf("You now have %d guesses left.\n", guesses);
            }
            
            if (!builder.toString().contains(userLetter))
            {
                builder.append(userLetter).append("   "); //to keep track of
                // already guessed letters
            }
            System.out.printf("So far, you've guessed these letters: %s\n",
                    builder.toString());
        }
        
        if (word.equals(partialWord))
        {
            System.out.printf("\nCongratulations! You guessed the word! It " +
                    "was " +
                    "indeed %s.\n", word);
            System.out.printf("And you only used %d guesses to get there!",
                    (originalGuesses - guesses));
        }
        else
        {
            System.out.printf("Sorry, you've been hung! The word I was " +
                    "looking for was %s.\n", word);
        }
    }
    
    private static String replaceChar(String word, String partialWord, char[] guess)
    {
        char[] wordChars = word.toCharArray();
        char[] partialChars = partialWord.toCharArray();
        
        for (int i = 0; i < wordChars.length; i++)
        {
            if (wordChars[i] == guess[0])
            {
                partialChars[i] = guess[0];
            }
        }
        return new String(partialChars);
    }
    
    /**
     * Method to get a random word.
     * @return : a word randomly selected from 10 choices
     */
    private static String getRandomWord()
    {
        Random rg = new Random();
        int index = rg.nextInt(10);
        if(index == 0) return "BUOY";
        if(index == 1) return "COMPUTER";
        if(index == 2) return "CONNOISSEUR";
        if(index == 3) return "DEHYDRATE";
        if(index == 4) return "FUZZY";
        if(index == 5) return "HUBBUB";
        if(index == 6) return "KEYHOLE";
        if(index == 7) return "QUAGMIRE";
        if(index == 8) return "SLITHER";
        if(index == 9) return "ZIRCON";
        return "DEFAULT";
    }
}
