import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;

public class GradeLevel
{
    private static BufferedReader inputStream = null;
    private static PrintWriter outputStream = null;

    private static int numOfWords = 0;
    private static int numOfSentences = 0;
    private static int numOfSyllables = 0;
    private static double fkReadingLevel = 0;
    private static String roudedFkReadingLevel = "";

    private static final char[] DELIMITERS = {'!', '.', '?'};
    private static final char[] EXCLUDE_CHARACTERS = {'!', '.', '?', '-', ',', ':', ';', '{', '}', '[', ']',
                                                      '/', '\\', '`', '\'', '~', '<', '>', '|', '+', '*', '@',
                                                      '#', '$', '%', '^', '&'};

    /**
     * Method to simply output the contents of an input file in text format
     * @param inputFile : the file to read from
     * @return String : the files contents in string format
     * @throws IOException : if something went wrong while trying to read from the file
     */
    public static String outputFileContents(File inputFile) throws IOException
    {
        String filesContents = ""; //string that will hold the files contents
        try
        {
            inputStream = new BufferedReader(new FileReader(inputFile));
            String newLine;
            while ((newLine = inputStream.readLine()) != null) //read each line until EOF has been reached
            {
                filesContents += newLine; //add each line to the string
                filesContents += "\n"; //add a new line to the string
            }

        }
        catch (IOException e) //if something went wrong while trying to read the input file
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("IOException");
            alert.showAndWait();
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close(); //close the input stream
            }
        }
        return filesContents; //the files contents in string format
    }

    /**
     * Method to get the number of words in the input text file
     * @param inputFile : the file to read from and get the number of words
     * @return integer : the number of words in the text file
     * @throws IOException : if something went wrong while trying to read from the file
     */
    public static int getWords(File inputFile) throws IOException
    {
        numOfWords = 0;
        try
        {
            inputStream = new BufferedReader(new FileReader(inputFile));
            String newLine;

            while ((newLine = inputStream.readLine()) != null)
            {
                if (!newLine.isEmpty())
                {
                    String[] tokens = newLine.split(" ");
                    int wordsInLine = tokens.length;
                    numOfWords += wordsInLine;
                }
            }
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("IOException");
            alert.showAndWait();
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return numOfWords;
    }

    /**
     * Method to get the number of sentences in the input text file.
     * Assumes that a sentence ends with either a (?), (!), or (.)
     * @param inputFile : the file to read from and get the number of sentences
     * @return integer : the number of sentences in the text
     * @throws IOException : if something went wrong while trying to read from the file
     */
    public static int getSentences(File inputFile) throws IOException
    {
        numOfSentences = 0;
        try
        {
            inputStream = new BufferedReader(new FileReader(inputFile));
            String newLine;
            while ((newLine = inputStream.readLine()) != null)
            {
                for (int i = 0; i < newLine.length(); i++)
                {
                    for (int j = 0; j < DELIMITERS.length; j++)
                    {
                        if (newLine.charAt(i) == DELIMITERS[j])
                        {
                            numOfSentences++;
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("IOException");
            alert.showAndWait();
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return numOfSentences;
    }

    /**
     * Method to get the total number of syllables in the input text file.
     * Assumes that the number of syllables in a words calculated by: # of characters / 4
     * @param inputFile : the file to read from and get the number of sentences
     * @return integer : the number of syllables in the text
     * @throws IOException : if something went wrong while trying to read from the file
     */
    public static int getSyllables(File inputFile) throws IOException
    {
        int characters; //the characters in each word
        int excludeCharacters; //the number of characters to exclude from each word; because of punctuation
        numOfSyllables = 0; //the total number of syllables in the text
        try
        {
            inputStream = new BufferedReader(new FileReader(inputFile));
            String newLine;

            while ((newLine = inputStream.readLine()) != null)
            {
                if (!newLine.isEmpty()) //while the line is not an empty line
                {
                    String[] tokens = newLine.split(" "); //get the words in the line
                    for (int i = 0; i < tokens.length; i++) //iterate through the words
                    {
                        String word = tokens[i];
                        excludeCharacters = 0; //reset exclude characters
                        for (int j = 0; j < word.length(); j++) //iterate through each word
                        {
                            for (int k = 0; k < EXCLUDE_CHARACTERS.length; k++) //iterate through the exclude characters array
                            {
                                if (word.charAt(j) == EXCLUDE_CHARACTERS[k])
                                {
                                    excludeCharacters++; //if the exclude character is in the word, +1 to excludeCharacters
                                }
                            }
                        }
                        //take absolute value in case there are more exclude characters than regular characters in the word
                        characters = Math.abs(word.length() - excludeCharacters);
                        if (characters > 4) //if the number of characters in the word is more than 4, then add this calculation to the syllables
                        {
                            numOfSyllables += (characters / 4);
                        }
                        else
                        {
                            numOfSyllables++; //if the number of characters is less than or equal to 4, than just add one to the syllables
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("IOException");
            alert.showAndWait();
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return numOfSyllables; //the total number of syllables in the text
    }

    /**
     * Method to calculate the reading level based on the characteristics of a given text.
     * @return String : a string that is formatted with the grade level to two decimal places
     *         "N/A" : if there was an error calculating the grade level
     */
    public static String readingLevel()
    {
        fkReadingLevel = 0;
        try
        {
            //formula to calculate the reading grade level
            fkReadingLevel = 0.39 * (numOfWords / numOfSentences) + 11.8 * (numOfSyllables / numOfWords) - 15.59;
            roudedFkReadingLevel = String.format("%.2f", fkReadingLevel);
            return roudedFkReadingLevel;
        } catch (ArithmeticException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error trying to calculate the grade level.", ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("Divide By Zero!");
            alert.showAndWait();
        }
        return "N/A";
    }

    /**
     * Method to save the generated data to a new text document
     * @param outputPath : the output file to save the data to
     * @param inputPath : the path to the original file
     * @throws IOException : if something went wrong while trying to write to a new file
     */
    public static void saveFile(String outputPath, String inputPath) throws IOException
    {
        try
        {
            outputStream = new PrintWriter(new FileWriter(outputPath));
            outputStream.println("Original File: " + inputPath);
            outputStream.println("Words in Text: " + numOfWords);
            outputStream.println("Sentences in Text: " + numOfSentences);
            outputStream.println("Syllables in Text: " + numOfSyllables);
            outputStream.println("Flesch-Kincaid Reading Level of the Text: " + roudedFkReadingLevel);
            outputStream.println("**MAY NOT BE ENTIRELY ACCURATE**");
            outputStream.println("**SEE ABOUT SECTION IN THE PROGRAM FOR MORE INFORMATION**");
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("Exception");
            alert.setHeaderText("IOException");
            alert.showAndWait();
        }
        finally
        {
            if (outputStream != null)
            {
                outputStream.close();
            }
        }
    }
}