import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;

/**
 * This class handles all of the encoding or decoding for the GUI application
 * This is the main Cipher class where the most relevant code is found
 */
public class CCipher
{
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //standard alphabet
    private static final char[] PUNCTUATION = {'!', '.', ',', ';', ':', '?', '/', '\\', '\'', '-', '`', '~'}; //punctuation to ignore
    private static int letterIndex = 0; //the original letter index
    private static int shiftedIndex = 0; //the new shifted letter index after the key has been applied

    /**
     * Method to encode a string based on the provided key
     * @param str : the string to encode
     * @param key : the key to shift the letters of the string
     * @return encodedString : the final encoded string
     */
    public static String encode(String str, int key) {
        String encodedString = ""; //empty string will hold the new encoded string
        str = str.toUpperCase(); //convert input string to uppercase to make it easier to work with

        //Replace all PUNCTUATION with spaces
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < PUNCTUATION.length; j++)
            {
                if (str.charAt(i) == PUNCTUATION[j])
                {
                    str = str.replace(str.charAt(i), ' ');
                }
            }
        }

        int letterIndex; //keep track of the letterIndex in the ALPHABET character array
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ' ') //if the character is a space, add a space to the encoded string
            {
                encodedString += " ";
            }
            else
            {
                letterIndex = ALPHABET.indexOf(str.charAt(i)); //get the original letter index of the character
                int shiftedIndex = (key + letterIndex) % 26; //calculate the new shifted index for the character
                char encodedCharacter = ALPHABET.charAt(shiftedIndex); //find the new character in the alphabet array
                encodedString += encodedCharacter; //add the new character to the encoded string
            }
        }
        return encodedString; //the final encoded string
    }

    /**
     * Method to decode a string based on the provided key
     * @param str : the string to decode
     * @param key : the key to shift the letters of the string
     * @return decodedString : the final decoded string
     */
    public static String decode(String str, int key) {
        String decodedString = ""; //empty string will hold the decoded string
        str = str.toUpperCase(); //convert input string to uppercase to make it easier to work with

        //Replace all PUNCTUATION with spaces
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < PUNCTUATION.length; j++) {
                if (str.charAt(i) == PUNCTUATION[j]) {
                    str = str.replace(str.charAt(i), ' ');
                }
            }
        }

        //Decode the string
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) //if the character is a letter
            {
                if (str.charAt(i) == ' ') //if the character is a space, add a space to the decoded string
                {
                    decodedString += " ";
                } else {
                    letterIndex = ALPHABET.indexOf(str.charAt(i)); //get the character index in the alphabet
                    shiftedIndex = (letterIndex - key) % 26; //apply the shift to the character's index
                    if (shiftedIndex < 0) //if the new index is less than 0, add the length of the alphabet (26) to the key
                    {
                        shiftedIndex = ALPHABET.length() + shiftedIndex;
                    }
                    char decodedCharacter = ALPHABET.charAt(shiftedIndex); //get the decoded character in the alphabet
                    decodedString += decodedCharacter; //add the decoded character to the string
                }
            } else {
                decodedString += str.charAt(i); //if it is not a letter, just add the character to the string and continue
            }
        }
        return decodedString; //return the new decoded string
    }

    /**
     * Method to decode a string without knowing the original key
     * @param str : the string to decode
     * @return decodedSting : All possible shifts of the original string
     */
    public static String decode(String str)
    {
        String decodedString = ""; //empty string will hold the decoded string
        str = str.toUpperCase(); //convert input string to uppercase to make it easier to work with

        //Replace all PUNCTUATION with spaces
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < PUNCTUATION.length; j++) {
                if (str.charAt(i) == PUNCTUATION[j]) {
                    str = str.replace(str.charAt(i), ' ');
                }
            }
        }

        for (int key = 1; key <= ALPHABET.length(); key++) //iterate through the alphabet array
        {
            decodedString += String.format("Key %d: ", key);
            for (int j = 0; j < str.length(); j++) //iterate through the original string
            {
                if (Character.isLetter(str.charAt(j))) //if the character is a letter
                {
                    if (str.charAt(j) == ' ') //if the character is a space, add a space to the decoded string
                    {
                        decodedString += " ";
                    }
                    else //character is not a space, it is a plain letter
                    {
                        letterIndex = ALPHABET.indexOf(str.charAt(j)); //get the current index in the alphabet array
                        shiftedIndex = (letterIndex - key) % 26; //calculate the shift index for the letter
                        if (shiftedIndex < 0) //if the new index is less than 0, add the length of the alphabet (26) to the key
                        {
                            shiftedIndex += ALPHABET.length();
                        }
                        char decodedCharacter = ALPHABET.charAt(shiftedIndex); //get the new shifted character
                        decodedString += decodedCharacter; //append it to the string
                    }
                }
                else
                {
                    decodedString += str.charAt(j); //if it is not a letter, just add the character to the string and continue
                }
            }
            decodedString += "\n"; //output a new line to signify the end of an iteration test
        }
        return decodedString; //return the new decoded string
    }

    /**
     * Method to encode a provided file using the provided key
     * @param input : the input file to encode
     * @param key : the key to apply to the text in the file
     * @return output : the new encoded file
     * @throws IOException : if something went wrong and the file could not be read/written from/to
     */
    public static File encode(File input, int key) throws IOException {
        File output;
        String path = input.getPath(); //the full path to the file
        String fileDirectory = input.getParentFile().getAbsolutePath(); //the directory in which the file resides
        String fileNameOnly = path.replace(fileDirectory, "").replace("\\", ""); //just the name of the file (i.e. relative path)
        String outputFile = "E-".concat(fileNameOnly);
        String outputFilePath = fileDirectory.concat("\\").concat(outputFile);

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(path));
            outputStream = new PrintWriter(new FileWriter(outputFilePath));

            String newLine;
            String encodedTemp;
            while ((newLine = inputStream.readLine()) != null) {
                String[] tokens = newLine.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    String temp = tokens[i];
                    encodedTemp = encode(temp, key);
                    outputStream.print(encodedTemp + " ");
                }
                outputStream.println();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("IO Exception");
            alert.showAndWait();
        } finally {
            output = new File(outputFilePath);
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return output;
    }

    /**
     * Method to decode a provided file using the provided key
     * @param input : the input file to decode
     * @param key : the key to apply to the text in the file
     * @return output : the new decoded file
     * @throws IOException : if something went wrong and the file could not be read/written from/to
     */
    public static File decode(File input, int key) throws IOException {
        File output; //the decoded file that will be generated
        String path = input.getPath(); //the full path to the input file
        String fileDirectory = input.getParentFile().getAbsolutePath(); //the directory in which the input file resides
        String fileNameOnly = path.replace(fileDirectory, "").replace("\\", ""); //just the name of the intput file (i.e. relative path)
        String outputFile = "D-".concat(fileNameOnly); //the name of the output file
        String outputFilePath = fileDirectory.concat("\\").concat(outputFile); //the path to the output file

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(path)); //reader to read a file in
            outputStream = new PrintWriter(new FileWriter(outputFilePath)); //writer to write to a file

            String newLine; //each line of the file
            String decodedTemp; //the decoded line of the file
            while ((newLine = inputStream.readLine()) != null) //while not at the end of the file
            {
                String[] tokens = newLine.split(" "); //get the words in the line
                for (int i = 0; i < tokens.length; i++) //iterate through the words and decode them one by one
                {
                    String temp = tokens[i];
                    decodedTemp = decode(temp, key);
                    outputStream.print(decodedTemp + " ");
                }
                outputStream.println(); //print a new line once the end of the newLine has been reached
            }
        } catch (IOException e) {
            //Show alert box to indicate something went wrong
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("IO Exception");
            alert.showAndWait();
        } finally {
            output = new File(outputFilePath); //create the new file to be returned by this method
            if (inputStream != null) {
                inputStream.close(); //close the input
            }
            if (outputStream != null) {
                outputStream.close(); //close the output
            }
        }
        return output; //return the decoded file
    }

    /**
     * Method to decode a file without knowing the original key
     * @param input : the file to decode
     * @return output : a decoded text file
     * @throws IOException : if something went wrong and the file could not be read/written from/to
     */
    public static File decode(File input) throws IOException {
        File output; //the decoded file that will be generated
        String path = input.getPath(); //the full path to the input file
        String fileDirectory = input.getParentFile().getAbsolutePath(); //the directory in which the input file resides
        String fileNameOnly = path.replace(fileDirectory, "").replace("\\", ""); //just the name of the intput file (i.e. relative path)
        String outputFile = "D-".concat(fileNameOnly); //the name of the output file
        String outputFilePath = fileDirectory.concat("\\").concat(outputFile); //the path to the output file
        String decodedString = "";

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(path)); //reader to read a file in
            outputStream = new PrintWriter(new FileWriter(outputFilePath)); //writer to write to a file

            String newLine; //each line of the file
            while ((newLine = inputStream.readLine()) != null) //while not at the end of the file
            {
                //Replace all PUNCTUATION with spaces
                for (int i = 0; i < newLine.length(); i++)
                {
                    for (int j = 0; j < PUNCTUATION.length; j++) {
                        if (newLine.charAt(i) == PUNCTUATION[j]) {
                            newLine = newLine.replace(newLine.charAt(i), ' ');
                        }
                    }
                }
                for (int key = 1; key <= ALPHABET.length(); key++) //iterate through the alphabet array
                {
                    decodedString += String.format("Key %d: ", key);
                    for (int j = 0; j < newLine.length(); j++) //iterate through the original string
                    {
                        if (Character.isLetter(newLine.charAt(j))) //if the character is a letter
                        {
                            if (newLine.charAt(j) == ' ') //if the character is a space, add a space to the decoded string
                            {
                                decodedString += " ";
                            }
                            else //character is not a space, it is a plain letter
                            {
                                letterIndex = ALPHABET.indexOf(newLine.charAt(j)); //get the current index in the alphabet array
                                shiftedIndex = (letterIndex - key) % 26; //calculate the shift index for the letter
                                if (shiftedIndex < 0) //if the new index is less than 0, add the length of the alphabet (26) to the key
                                {
                                    shiftedIndex += ALPHABET.length();
                                }
                                char decodedCharacter = ALPHABET.charAt(shiftedIndex); //get the new shifted character
                                decodedString += decodedCharacter; //append it to the string
                            }
                        }
                        else
                        {
                            decodedString += newLine.charAt(j); //if it is not a letter, just add the character to the string and continue
                        }
                    }
                    outputStream.print(decodedString);
                    outputStream.println();
                    decodedString = "";
                }
            }
        } catch (IOException e) {
            //Show alert box to indicate something went wrong
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.setTitle("IO Exception");
            alert.showAndWait();
        } finally {
            output = new File(outputFilePath); //create the new file to be returned by this method
            if (inputStream != null) {
                inputStream.close(); //close the input
            }
            if (outputStream != null) {
                outputStream.close(); //close the output
            }
        }
        return output; //return the decoded file
    }
}
