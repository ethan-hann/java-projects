import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 640;

    //center screen items
    private Canvas canvas;
    private Group drawingRoot = new Group();
    private TextArea gameText = new TextArea();
    private BorderPane mainLayout = new BorderPane();
    private TextField userText = new TextField();

    //menu items
    private MenuBar topMenuBar = new MenuBar();

    private Menu fileMenu = new Menu("File");
    private MenuItem newGame = new MenuItem("New Game");
    private MenuItem exitGame = new MenuItem("Exit Game");
    private MenuItem aboutGame = new MenuItem("About Game");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GraphicsContext gc = createLayout();

        //all the drawing to the canvas is here
        draw(gc);
        //drawing ends here

        Scene s = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(s);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hangman");
        primaryStage.show();
    }

    private void draw(GraphicsContext gc)
    {
        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);
    }

    private GraphicsContext createLayout()
    {
        createMenu();
        gameText.setEditable(false);
        gameText.setWrapText(true);
        gameText.setText("Some game text will be here.");
        gameText.setPrefSize(570, WINDOW_HEIGHT - 96);
        gameText.setPadding(new Insets(10, 10, 10, 10));

        userText.setEditable(true);
        userText.setPadding(new Insets(10, 10, 10, 10));
        userText.setPrefSize(32, 16);
        userText.setMaxSize(64, 16);

        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10, 10, 10, 10));
        centerBox.getChildren().addAll(gameText, userText);

        canvas = new Canvas(200, WINDOW_HEIGHT);

        drawingRoot.getChildren().add(canvas);
        mainLayout.setCenter(centerBox);
        mainLayout.setRight(drawingRoot);

        return canvas.getGraphicsContext2D();
    }

    private void createMenu()
    {
        fileMenu.getItems().addAll(newGame, exitGame, new SeparatorMenuItem(), aboutGame);
        topMenuBar.getMenus().add(fileMenu);

        mainLayout.setTop(topMenuBar);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
