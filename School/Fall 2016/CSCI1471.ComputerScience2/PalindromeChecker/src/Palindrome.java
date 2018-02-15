public class Palindrome
{
    public static String originalWord; //original string to check if it is a palindrome
    public static String reversedWord; //the reverse of original word
    private static final char[] PUNCTUATION = { //array of PUNCTUATION to check if there is PUNCTUATION in a given string
            '!',
            '.',
            '?',
            ',',
            ':',
            ';'
    };

    /**
     * The main palindrome method that checks if a given string is a palindrome
     * @param word : the string  to check
     * @return true : if the string is a palindrome ; false : if it is not a palindrome
     */
    public static boolean isPalindrome(String word)
    {
        originalWord = ""; //reset the original word and the reversed word every time the method is called
        reversedWord = "";
        word = word.toLowerCase(); //get rid of capital letters

        //for loop checks if there is any PUNCTUATION in the word
        for (int i = 0; i < word.length(); i++)
        {
            for (int j = 0; j < PUNCTUATION.length; j++)
            {
                if (word.charAt(i) == PUNCTUATION[j])
                {
                    word = word.replace(word.charAt(i), ' '); //if there is, replace it with a space
                }
            }
        }

        word = word.trim(); //remove trailing and leading whitespaces
        word = word.replaceAll("\\s+",""); //remove spaces in the string

        originalWord = word;
        char[] originalWordArray = word.toCharArray(); //create a character array of the original word
        char[] reversedWordArray = new char[word.length()]; //create a character array of the same length as the original word array; stores the reversed word

        //create reversed word using an array of characters (add each character starting with the last char in the original word array)
        for (int i = 0; i < reversedWordArray.length; i++)
        {
            reversedWordArray[i] = originalWordArray[(word.length() - 1) - i];
            String s = Character.toString(reversedWordArray[i]); //convert character to string
            reversedWord = reversedWord.concat(s); //concatenate the string to the reversed word
        }

        //check if the two strings are equal character by character
        for (int i = 0; i < originalWord.length(); i++)
        {
            if (!(originalWordArray[i] == reversedWordArray[i]))
            {
                return false;
            }
        }
        return true; //return true if the two words are equal to each other; false otherwise
    }
}
