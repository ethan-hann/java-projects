import javafx.application.Application;
import javafx.beans.property.StringProperty;
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

public class CCipherGUI extends Application {
    private static boolean fileSelected = false;
    private Stage window;
    private Scene scene;
    private File inputFile; //the input file if the user chooses this method
    private BorderPane mainLayout = new BorderPane(); //layout
    private FileChooser fileChooser = new FileChooser(); //for open file menu
    private Button calculate;
    private Button clearAllText;
    private Label pathLabel = new Label();
    private TextField textInput = new TextField();
    private TextArea convertedText = new TextArea();
    private TextField keyInput = new TextField();
    private CheckBox keyUnknown = new CheckBox("Unknown Key");
    private RadioMenuItem encodeOption = new RadioMenuItem("Encode");
    private RadioMenuItem decodeOption = new RadioMenuItem("Decode");

    private boolean encode = true;
    private boolean decode = false;
    private boolean isKeyUnknown = false;


    private String helpText = "This program will apply a Caesar Shift to the given text.\n" +
            "Usage:\n" +
            "1. Enter some text into the text box or select a text file to open from the File drop down menu.\n" +
            "2. From the Options drop down menu, select to either Encode (Encrypt) or Decode (Decrypt) the supplied text.\n" +
            "3. If a file is used, the file name will appear below the buttons; the input text box and this box will be greyed out.\n" +
            "4. Enter a key (a.k.a shift) into the second text box. This will be the value the supplied text will be shifted.\n" +
            "-> If the key is unknown, select the Unknown Key checkbox. This will test all possible combinations of the shift.\n" +
            "5. Press the Encode/Decode button.\n" +
            "-> If you supplied the text via text box, the converted text will be displayed here in this large text area.\n" +
            "-> If you supplied the text via a file, the converted text will be in a new file in the original directory.\n" +
            "-> The path to the new file will be displayed below the buttons.\n" +
            "-> In order to type into the text box again, you must first close the opened file.\n" +
            "\t1. Select Close File from the File drop down menu.\n" +
            "-> Valid punctuation does not matter i.e. ! . , ; : ? / \\ ' - ` ~\n" +
            "-> Any punctuation will be replaced by a space.\n" +
            "-> Numbers and Spaces will be conserved to the converted string.\n" +
            "****************************\n" +
            "*    (c) 2016 Ethan Hann   *\n" +
            "* Assignment for CSCI 1471 *\n" +
            "*    Lines of Code: 732    *\n" +
            "****************************\n";

    private String ccDesc = "A Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code or Caesar shift," +
            " is one of the simplest and most widely known encryption techniques." +
            " It is a substitution cipher in which each letter in the plaintext is replaced by some letter some fixed number" +
            " of positions down the alphabet.\n\n" +
            "A B C D E F G H I J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z\n" +
            "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26\n\n" +
            "For example,\n" +
            "Given the string 'Caesar' and a shift of 3:\n" +
            "C + 3 -> F\n" +
            "A + 3 -> D\n" +
            "E + 3 -> H\n" +
            "S + 3 -> V\n" +
            "A + 3 -> D\n" +
            "R + 3 -> U\n";
    private String path;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("CCipher");
        window.setResizable(true);
        window.getIcons().add(new Image(CCipherGUI.class.getResourceAsStream("ccipher.png")));

        createMenu();
        createCenter();

        scene = new Scene(mainLayout, 900, 600);
        scene.getStylesheets().add("com/ethanhann/Cipher/CipherTheme.css");
        textInput.requestFocus();
        window.setScene(scene);
        window.show();
    }

    /**
     * This method will create the menu bar and handle all events associated with it.
     */
    private void createMenu()
    {
        //File Menu
        Menu fileMenu = new Menu("File");

        MenuItem open = new MenuItem("Open...");
        open.setOnAction(e -> {
            fileChooser.setTitle("Open Text File");

            //only show text files in the open file chooser
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));

            inputFile = fileChooser.showOpenDialog(window); //show the file chooser dialog

            path = inputFile.getPath(); //the full path to the file
            String fileDirectory = inputFile.getParentFile().getAbsolutePath(); //the directory in which the file resides
            String fileNameOnly = path.replace(fileDirectory, "").replace("\\", ""); //just the name of the file (i.e. relative path)

            pathLabel.setText("File Opened: " + fileNameOnly);
            pathLabel.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 12));
            fileSelected = true;
            textInput.setDisable(true);
            textInput.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 12));
            textInput.clear();
            textInput.setPromptText("File is open; input is disabled");

            convertedText.setDisable(true);
            convertedText.setEditable(false);
            convertedText.clear();
            convertedText.setPromptText("File is open; output will be to a new file");
        });

        MenuItem close = new MenuItem("Close File");
        close.setOnAction(e -> {
            if (fileSelected) {
                fileSelected = false;
                path = "";
                pathLabel.setText(path);
                textInput.setDisable(false);
                convertedText.setDisable(false);
                convertedText.setEditable(false);
                textInput.setFont(Font.font("Courier New", 12));

                if (encodeOption.isSelected())
                {
                    textInput.setPromptText("Enter a string to encode...");
                    convertedText.setPromptText("Encoded text will be here...");
                }
                else if (decodeOption.isSelected())
                {
                    textInput.setPromptText("Enter a string to decode...");
                    convertedText.setPromptText("Decoded text will be here...");
                }
            }
        });

        MenuItem exitProgram = new MenuItem("Exit");
        exitProgram.setOnAction(e -> window.close()); //exit the program

        fileMenu.getItems().addAll(open, close, new SeparatorMenuItem(), exitProgram); //add the file menu items

        //Options Menu
        Menu optionsMenu = new Menu("Options");
        ToggleGroup encodingToggle = new ToggleGroup();

        encodeOption.setToggleGroup(encodingToggle);
        decodeOption.setToggleGroup(encodingToggle);
        encodeOption.setSelected(true);
        optionsMenu.getItems().addAll(encodeOption, decodeOption); //add option items to options menu

        //listen for changes to the toggle group; if Encoding is selected, encode = true ...
        encodingToggle.selectedToggleProperty().addListener(e -> {
            if (!fileSelected) //if a file is not selected
            {
                if (encodeOption.isSelected()) //if the encoding options is selected
                {
                    keyUnknown.setSelected(false); //de-select the unknown key checkbox
                    keyInput.setDisable(false); //re-enable the key input
                    encode = true;
                    decode = false;
                    calculate.setText("Encode");
                    convertedText.setPromptText("Encoded text will be here...");
                    textInput.setPromptText("Enter a string to encode...");
                    keyUnknown.setDisable(true); //disable the unknown key checkbox
                }
                else if (decodeOption.isSelected()) //if the decoding option is selected
                {
                    encode = false;
                    decode = true;
                    calculate.setText("Decode");
                    convertedText.setPromptText("Decoded text will be here...");
                    textInput.setPromptText("Enter a string to decode...");
                    keyUnknown.setDisable(false); //re-enable the unknown key checkbox
                }
            }
            else //if a file is selected
            {
                if (encodeOption.isSelected()) //if the encoding options is selected
                {
                    keyUnknown.setSelected(false); //de-select the unknown key checkbox
                    keyInput.setDisable(false); //re-enable the key input
                    encode = true;
                    decode = false;
                    calculate.setText("Encode");
                    keyUnknown.setDisable(true); //disable the unknown key checkbox
                }
                else if (decodeOption.isSelected()) //if the decoding option is selected
                {
                    encode = false;
                    decode = true;
                    calculate.setText("Decode");
                    keyUnknown.setDisable(false); //re-enable the unknown key checkbox
                }
            }
        });

        //Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem howToUse = new MenuItem("How To Use");
        MenuItem whatIsCC = new MenuItem("What is a Caesar Cipher?");
        helpMenu.getItems().addAll(howToUse, whatIsCC);

        howToUse.setOnAction(e -> {
            convertedText.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 12.5));
            convertedText.setText(helpText);
        });

        whatIsCC.setOnAction(e -> {
            convertedText.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 12.5));
            convertedText.setText(ccDesc);
        });

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu); //add the file menu to the main menu bar
        mainLayout.setTop(menuBar); //set the menu bar as the top portion of the border-pane layout
    }

    /**
     * Method to create the main content of the GUI. All text fields, text areas, and buttons.
     * It also handles all events associated with any items inside the center of the GUI.
     */
    private void createCenter()
    {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(5, 5, 400, 5));

        int maxTextInputLength = 251;

        //listen for changes to the length of the text; restricts the length to 250 characters
        textInput.textProperty().addListener( (observable, oldValue, newValue) -> {
            if (newValue.length() >= maxTextInputLength)
            {
                ((StringProperty)observable).setValue(oldValue);
            }
        });

        if (fileSelected) {
            textInput.setDisable(true); //disable the textInput if the user selected a file to encode/decode
        } else {
            textInput.setFont(Font.font("Courier New", 12));
            textInput.setPromptText("Enter a string to encode..."); //user input for string to encode or decode

            textInput.setEditable(true);
            textInput.setDisable(false);
        }

        int maxKeyInputLength = 6; //the maximum length of the key input text field
        keyInput.setFont(Font.font("Courier New", 12));
        keyInput.setMaxWidth(130);

        //listen for changes to the length of the text; restricts the length to 5 characters
        keyInput.textProperty().addListener( (observable, oldValue, newValue) -> {
            if (newValue.length() >= maxKeyInputLength)
            {
                ((StringProperty)observable).setValue(oldValue);
            }
        });
        keyInput.setPromptText("Key of cipher..."); //user input for key of cipher

        calculate = new Button("Encode"); //encode or decode button; encode by default
        calculate.setFont(Font.font("Courier New", 12));

        clearAllText = new Button("Clear All"); //clear all text fields button
        clearAllText.setFont(Font.font("Courier New", 12));

        keyUnknown.setFont(Font.font("Courier New", 12));

        keyUnknown.setSelected(false);
        keyUnknown.setDisable(true);
        keyUnknown.setOnAction(e -> {
            if (keyUnknown.isSelected())
            {
                isKeyUnknown = true;
                keyInput.setDisable(true);
            }
            else
            {
                isKeyUnknown = false;
                keyInput.setDisable(false);
            }
        });

        //Calculate the Decoded or Encoded text
        calculate.setOnAction(e -> {
            try {
                if (encode) {
                    if (fileSelected) {
                        File newFile = CCipher.encode(inputFile, Integer.parseInt(keyInput.getText()));
                        pathLabel.setText("Encoded file saved to: " + newFile.getAbsolutePath());
                    } else {
                        convertedText.clear();
                        convertedText.setText(CCipher.encode(textInput.getText(), Integer.parseInt(keyInput.getText())));
                    }
                }

                if (decode) {
                    if (!isKeyUnknown)
                    {
                        if (fileSelected) {
                            File newFile = CCipher.decode(inputFile, Integer.parseInt(keyInput.getText()));
                            pathLabel.setText("Decoded file saved to: " + newFile.getAbsolutePath());
                        } else {
                            convertedText.clear();
                            convertedText.setText(CCipher.decode(textInput.getText(), Integer.parseInt(keyInput.getText())));
                        }
                    }
                    else
                    {
                        if (fileSelected)
                        {
                            File newFile = CCipher.decode(inputFile);
                            pathLabel.setText("Decoded file saved to: " + newFile.getAbsolutePath());
                        }
                        else
                        {
                            convertedText.clear();
                            convertedText.setText(CCipher.decode(textInput.getText()));
                        }
                    }
                }

            } catch (NumberFormatException | IOException z) {
                z.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Empty Input!" + z.getLocalizedMessage(), ButtonType.OK);
                alert.setTitle("An Exception Has Occurred");
                alert.showAndWait();
            }
        });

        clearAllText.setOnAction(e -> {
            textInput.clear();
            keyInput.clear();
            convertedText.clear();
            convertedText.setDisable(false);
            keyInput.setDisable(false);
            textInput.setDisable(false);
            keyUnknown.setDisable(true);
            keyUnknown.setSelected(false);
            encodeOption.setSelected(true);
            decodeOption.setSelected(false);
            fileSelected = false;
            pathLabel.setText("");
            convertedText.setPromptText("Encoded text will be here...");
            textInput.setPromptText("Enter a string to encode...");
            keyInput.setPromptText("Key of cipher...");
        });

        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        VBox textArea = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        convertedText.setFont(Font.font("Courier New", 12));
        convertedText.setWrapText(true);
        convertedText.setPrefHeight(600);

        convertedText.setEditable(false);
        convertedText.setPromptText("Encoded text will be here...");
        vBox.getChildren().add(convertedText);

        hBox.getChildren().addAll(calculate, clearAllText, keyUnknown);
        vBox.getChildren().addAll(textInput, keyInput, hBox, pathLabel);

        mainLayout.setCenter(vBox);
        mainLayout.setBottom(textArea);
    }
}