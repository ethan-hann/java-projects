import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GradeLevelGUI extends Application
{
    private static Stage window; //main window
    private BorderPane mainLayout = new BorderPane(); //main layout format


    private FileChooser fileChooser = new FileChooser(); //to input a file to the program
    private File inputFile; //the file the user selects to calculate the grade level of
    private String inputPath; //the path to the file
    private String outputPath;
    private String lastPath;
    private String[] recentFiles = new String[3];
    private int recentFilesIndex = 0;

    private TextArea fileContents = new TextArea();
    private Label pathLabel = new Label(); //label to display the chosen path
    private Label numOfWords = new Label("Words: ");
    private Label numOfSentences = new Label("Sentences: ");
    private Label numOfSyllables = new Label("Syllables: ");
    private Label readingLevel = new Label("Flesch-Kincaid Reading Level: ");
    private Label saveLabel = new Label("");
    private Button calculate = new Button("Analyze Text");
    private Button clearAll = new Button("Clear All");

    private String howToUseString = "Usage:\n" +
            "1. Select a file to calculate the reading level of using the File drop down menu.\n" +
            "\t-> The file path will be displayed above this large text area.\n" +
            "\t-> The file's contents will be displayed inside this large text area.\n" +
            "2. Press the Analyze Text button.\n" +
            "\t-> Information about the text will be displayed above the button, along with the calculated reading level.\n" +
            "3. To save the generated information, select the Save Generated Data option from the File drop down menu.\n" +
            "\t-> The path to the saved file will be displayed below the Open file path.";

    private String whatIsFCString = "The Flesch–Kincaid readability tests are readability tests designed to indicate how difficult " +
            "a reading passage in English is to understand. There are two tests, the Flesch Reading Ease, and the Flesch–Kincaid Grade Level. " +
            "Although they use the same core measures (word length and sentence length), they have different weighting factors. " +
            "These readability tests are used extensively in the field of education. The Flesch–Kincaid Grade Level Formula instead " +
            "presents a score as a U.S. grade level, making it easier for teachers, parents, librarians, and others to judge the readability " +
            "level of various books and texts. It can also mean the number of years of education generally required to understand this text, " +
            "relevant when the formula results in a number greater than 10. \n" +
            "The grade level is calculated with the following formula:\n" +
            "0.39 * (total words / total sentences) + 11.8 * (total syllables / total words) - 15.59\n" +
            "The result is a number that corresponds with a U.S. grade level.";

    private String aboutString = "This program was written as a programming assignment for my Computer Science class. " +
            "The requirement was to write a program that would calculate the Flesch-Kincaid Reading Level of a given text. " +
            "It was not required to make a GUI for the program, but since I've been learning JavaFX, I thought I should put " +
            "what I learned to use. The GUI application was designed entirely by me and all programming was also done by me.\n\n" +
            "**NOTE**\n" +
            "This program calculates the grade level under the assumption that the number of syllables in each word " +
            "is found by taking the number of characters in the word and dividing by 4.\n" +
            "This means that the grade level calculated will be slightly off. It will still be within +/- 1.6% of the correct value though.\n" +
            "**********************************\n" +
            "*    (c) 2016 Ethan Hann           *\n" +
            "* Assignment for CSCI 1471    *\n" +
            "*       Lines of Code: 652           *\n" +
            "**********************************\n";

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Grade Reading Level"); //title of window
        window.setResizable(false); //is window resizable?
        window.getIcons().add(new Image(GradeLevelGUI.class.getResourceAsStream("book.png"))); //get window icon

        createMenu();
        createMainContent();

        Scene scene = new Scene(mainLayout, 900, 600); //initial scene of the window; where all the layouts are
        scene.getStylesheets().add("com/ethanhann/GradeLevel/UITheme.css");
        window.setScene(scene);
        window.show();
    }

    private void createMenu()
    {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openFile = new MenuItem("Open...");
        MenuItem saveData = new MenuItem("Save Generated Data...");
        Menu recentFilesMenu = new Menu("Recent Files");
        MenuItem file1 = new MenuItem("...");
        MenuItem file2 = new MenuItem("...");
        MenuItem file3 = new MenuItem("...");
        MenuItem exitProgram = new MenuItem("Exit");

        Menu helpMenu = new Menu("Help");
        MenuItem howToUse = new MenuItem("How to Use Program");
        MenuItem whatIsFC = new MenuItem("What is the Flesch-Kincaid Reading Level");
        MenuItem about = new MenuItem("About the Program");
        recentFilesMenu.getItems().addAll(file1, file2, file3);

        //ensure only text files can be opened
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*txt)", "*txt"));

        openFile.setOnAction(e ->
        {
            fileChooser.setTitle("Open File");

            inputFile = fileChooser.showOpenDialog(window); //show the open file dialog
            inputPath = inputFile.getPath();
            lastPath = inputFile.getParent();
            File lastDirectory = new File(lastPath);
            if (!lastDirectory.canRead())
            {
                lastDirectory = new File(System.getProperty("user.home"));
            }
            fileChooser.setInitialDirectory(lastDirectory); //set new initial directory equal to the directory of the first file opened

            try
            {
                fileContents.clear(); //clear the text area
                fileContents.setText(GradeLevel.outputFileContents(inputFile)); //output the content of the text file in the text area
                if (recentFilesIndex >= 3) //if the index is greater than 3, reset it to 0; this is so we can keep track of the three most recent files
                {
                    recentFilesIndex = 0;
                }
                recentFiles[recentFilesIndex] = inputPath; //change the value in the recentFiles array to the most recent value
                recentFilesIndex++; //increase the index of the recentFiles array
                file1.setText(recentFiles[0]); //set text for the recent file menu items
                file2.setText(recentFiles[1]);
                file3.setText(recentFiles[2]);
                numOfWords.setText("Words: ");
                numOfSentences.setText("Sentences: ");
                numOfSyllables.setText("Syllables: ");
                readingLevel.setText("Flesch-Kincaid Reading Level: ");
            }catch (IOException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                alert.setTitle("Exception");
                alert.setHeaderText("Something Went Wrong...");
                alert.showAndWait();
            }
            pathLabel.setText("Opened File: " + inputPath);
        });

        //action for the first recent file
        file1.setOnAction(e ->
        {
            inputFile = new File(recentFiles[0]);
            inputPath = inputFile.getPath();
            fileContents.clear(); //clear the text area
            try
            {
                fileContents.setText(GradeLevel.outputFileContents(inputFile)); //output the content of the text file in the text area
                numOfWords.setText("Words: ");
                numOfSentences.setText("Sentences: ");
                numOfSyllables.setText("Syllables: ");
                readingLevel.setText("Flesch-Kincaid Reading Level: ");
            }
            catch (IOException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                alert.setTitle("Exception");
                alert.setHeaderText("Something Went Wrong...");
                alert.showAndWait();
            }
            pathLabel.setText("Opened File: " + inputPath);

        });

        //action for the second recent file
        file2.setOnAction(e ->
        {
            inputFile = new File(recentFiles[1]);
            inputPath = inputFile.getPath();
            fileContents.clear(); //clear the text area
            try
            {
                fileContents.setText(GradeLevel.outputFileContents(inputFile)); //output the content of the text file in the text area
                numOfWords.setText("Words: ");
                numOfSentences.setText("Sentences: ");
                numOfSyllables.setText("Syllables: ");
                readingLevel.setText("Flesch-Kincaid Reading Level: ");
            }
            catch (IOException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                alert.setTitle("Exception");
                alert.setHeaderText("Something Went Wrong...");
                alert.showAndWait();
            }
            pathLabel.setText("Opened File: " + inputPath);

        });

        //action for the third recent file
        file3.setOnAction(e ->
        {
            inputFile = new File(recentFiles[2]);
            inputPath = inputFile.getPath();
            fileContents.clear(); //clear the text area
            try
            {
                fileContents.setText(GradeLevel.outputFileContents(inputFile)); //output the content of the text file in the text area
                numOfWords.setText("Words: ");
                numOfSentences.setText("Sentences: ");
                numOfSyllables.setText("Syllables: ");
                readingLevel.setText("Flesch-Kincaid Reading Level: ");
            }
            catch (IOException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                alert.setTitle("Exception");
                alert.setHeaderText("Something Went Wrong...");
                alert.showAndWait();
            }
            pathLabel.setText("Opened File: " + inputPath);

        });

        saveData.setOnAction(e ->
        {
            if (inputFile != null)
            {
                fileChooser.setTitle("Save Generated Data");
                File outputFile = fileChooser.showSaveDialog(window); //show the save file dialog
                outputPath = outputFile.getPath(); //the path to the output file
                if (!outputFile.getPath().endsWith(".txt")) //if the file doesn't already end with .txt, add it to the path
                {
                    outputPath = outputFile.getPath().concat(".txt");
                }

                saveLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, FontPosture.ITALIC, 14));
                saveLabel.setText("Saved Data to: " + outputPath);
                try
                {
                    GradeLevel.saveFile(outputPath, inputPath);
                }
                catch(IOException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                    alert.setTitle("Exception");
                    alert.setHeaderText("IOException");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You must have generated data to be able to save!", ButtonType.OK);
                alert.setTitle("No File Open");
                alert.setHeaderText("No Generated Data!");
                alert.showAndWait();
            }

        });

        exitProgram.setOnAction(e -> window.close());

        howToUse.setOnAction(e ->
        {
            fileContents.clear();
            fileContents.setText(howToUseString);
        });

        whatIsFC.setOnAction(e ->
        {
            fileContents.clear();
            fileContents.setText(whatIsFCString);
        });

        about.setOnAction(e ->
        {
            fileContents.clear();
            fileContents.setText(aboutString);
        });

        fileMenu.getItems().addAll(openFile, saveData, recentFilesMenu, new SeparatorMenuItem(), exitProgram);

        helpMenu.getItems().addAll(howToUse, whatIsFC, new SeparatorMenuItem(), about);

        menuBar.getMenus().addAll(fileMenu, helpMenu);
        mainLayout.setTop(menuBar);
    }

    private void createMainContent()
    {
        VBox verticalLayout = new VBox(10);
        verticalLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox generatedOutputLayout = new VBox(10);
        generatedOutputLayout.setPadding(new Insets(5, 5, 5, 5));
        generatedOutputLayout.getChildren().addAll(numOfWords, numOfSentences, numOfSyllables, readingLevel, calculate, clearAll);

        HBox horizontalLayout = new HBox(10);
        horizontalLayout.setPadding(new Insets(10, 10, 10, 10));
        horizontalLayout.getChildren().addAll(fileContents, generatedOutputLayout);

        verticalLayout.getChildren().addAll(pathLabel, saveLabel, horizontalLayout);

        numOfWords.setId("numWords");
        numOfWords.setFont(Font.font("Segoe UI", 16));

        numOfSentences.setId("numSentences");
        numOfSentences.setFont(Font.font("Segoe UI", 16));

        numOfSyllables.setId("numSyllables");
        numOfSyllables.setFont(Font.font("Segoe UI", 16));

        readingLevel.setId("readingLevel");
        readingLevel.setFont(Font.font("Segoe UI", 16));

        fileContents.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        fileContents.setWrapText(true);
        fileContents.setEditable(false);
        fileContents.setText("File Contents Will Be Here...");
        fileContents.setPrefSize(600, 600);

        pathLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, FontPosture.ITALIC, 14));
        pathLabel.setText("Opened File: <NO FILE OPEN>");

        calculate.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        clearAll.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        clearAll.setOnAction(e ->
        {
            fileContents.clear();
            pathLabel.setText("File Opened: <NO FILE OPEN>");
            inputFile = null;
            numOfWords.setText("Words: ");
            numOfSentences.setText("Sentences: ");
            numOfSyllables.setText("Syllables: ");
            readingLevel.setText("Flesch-Kincaid Reading Level: ");
            fileContents.setText("File Contents Will Be Here...");
            saveLabel.setText("");
        });

        calculate.setOnAction(e ->
        {
            if (inputFile != null)
            {
                try
                {
                    numOfWords.setText("Words: " + GradeLevel.getWords(inputFile));
                    numOfSentences.setText("Sentences: " + GradeLevel.getSentences(inputFile));
                    numOfSyllables.setText("Syllables: " + GradeLevel.getSyllables(inputFile));
                    readingLevel.setText("Flesch-Kincaid Reading Level: " + GradeLevel.readingLevel());

                }catch (IOException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.OK);
                    alert.setTitle("Exception");
                    alert.setHeaderText("Something Went Wrong...");
                    alert.showAndWait();
                }
            }else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No file is opened to analyze data from.", ButtonType.OK);
                alert.setTitle("No File");
                alert.showAndWait();
            }
        });

        mainLayout.setCenter(verticalLayout);
    }
}
