import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PalindromeGUI extends Application
{
    //GUI COMPONENTS
    private Stage window;
    private Scene scene;
    private Button checkButton;
    private TextField userInput;
    private Label descLabel;
    private Label titleLabel;
    private Label isPalindromeLabel;
    //~GUI COMPONENTS

    public static void main(String[] args)
    {
        launch(args); //launches main application
    }

    //GUI METHOD
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Palindrome Checker - Ethan Hann");

        isPalindromeLabel = new Label();

        userInput = new TextField();
        userInput.setPromptText("Enter anything here...");

        checkButton = new Button("Check");
        checkButton.setOnAction(e -> {
            if (userInput.getText().equals("")) //show an error if the text field is empty
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You must enter something into the text box \n " +
                        "in order to check if it is a palindrome.", ButtonType.OK);
                alert.setTitle("Invalid Text");
                alert.showAndWait();
            }
            else //continue and check the text field string
            {
                boolean isPali = Palindrome.isPalindrome(userInput.getText()); //call isPalindrome to check if the string is a palindrome
                if (isPali)
                {
                    isPalindromeLabel.setText("");
                    isPalindromeLabel.setText(Palindrome.originalWord + " is a palindrome -> " + Palindrome.reversedWord);
                }
                else
                {
                    isPalindromeLabel.setText("");
                    isPalindromeLabel.setText(Palindrome.originalWord + " is NOT a palindrome -> " + Palindrome.reversedWord);
                }
            }
        });

        titleLabel = new Label("Palindrome Checker v.1");
        titleLabel.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 14));
        descLabel = new Label("You can enter anything into the box to check if it is a palindrome.\n" +
                "Numbers, Letters, Symbols, etc... The box will ignore all spaces and punctuation (! . ? , ; :)");
        descLabel.setFont(Font.font("Book Antiqua", FontWeight.NORMAL, 12));

        //Layout of GUI
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(titleLabel, descLabel, userInput, checkButton, isPalindromeLabel);

        scene = new Scene(layout, 500, 200);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
