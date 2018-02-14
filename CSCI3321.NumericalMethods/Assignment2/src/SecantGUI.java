import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


public class SecantGUI extends Application
{
    private Stage window;

    private BorderPane mainLayout = new BorderPane();
    private TableView table = new TableView();

    private Button calculate = new Button("Calculate");
    private TextField x0 = new TextField("-1");
    private TextField x1 = new TextField("2");
    private Label true_answer = new Label("True Root: " + String.valueOf(SecantMethod.TRUE_ANSWER));

    private String aboutText =
            "This application is an example of the Secant Method which is used to\n" +
                    "approximate the root(s) of an equation. In this application,\n" +
                    "the equation f(x) = tanh(x) - 0.5 = 0 is used as the example.\n" +
                    "\n" +
                    "Programmed by: Ethan Hann\n" +
                    "Created for CSCI 3321: Numerical Methods";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //setUserAgentStylesheet(STYLESHEET_MODENA);
        window = primaryStage;
        //AquaFx.style();
        window.setTitle("Secant Method");
        window.setResizable(false);
        window.getIcons().add(new Image(SecantGUI.class.getResourceAsStream("secantIcon.png")));

        createTop();
        createCenter();

        Scene scene = new Scene(mainLayout, 620, 700);
        scene.getStylesheets().add("SecantTheme.css");
        window.setScene(scene);
        window.show();
    }

    /**
     * Creates the top section of the BorderPane
     */
    private void createTop()
    {
        //Creating Top of BorderPane
        VBox top = new VBox(5);
            MenuBar menuBar = new MenuBar();
            Menu options = new Menu("Options");
            MenuItem aboutMenu = new MenuItem("About This Application");
                options.setOnAction(e -> {
                Stage about = new Stage();
                about.initModality(Modality.APPLICATION_MODAL);
                about.initOwner(window);
                about.setTitle("About");
                about.setResizable(false);
                about.setAlwaysOnTop(true);
                about.getIcons().add(new Image(SecantGUI.class.getResourceAsStream("aboutIcon.png")));
                VBox aboutBox = new VBox(20);
                aboutBox.setPadding(new Insets(10, 10, 10, 10));
                aboutBox.getChildren().add(new Text(aboutText));
                Scene aboutScene = new Scene(aboutBox, 400, 150);
                about.setScene(aboutScene);
                about.show();
            });
            options.getItems().add(aboutMenu);
            menuBar.getMenus().add(options);

            HBox equationArea = new HBox(5);
                Label equationLabel = new Label("Equation: ");
                Image equationImage = new Image("f(x).png");
                ImageView equationView = new ImageView(equationImage);
                equationArea.getChildren().addAll(equationLabel, equationView);
            equationArea.setPadding(new Insets(5, 5, 5, 5));

            HBox formulaArea = new HBox(5);
                Label formulaLabel = new Label("Formula: ");
                Image formulaImage = new Image("formula.png");
                ImageView formulaView = new ImageView(formulaImage);
                formulaArea.getChildren().addAll(formulaLabel, formulaView);
            formulaArea.setPadding(new Insets(5, 5, 5, 5));

            HBox inputArea = new HBox(10);
                Label x0Label = new Label("x_0: ");
                Label x1Label = new Label("x_1: ");
                inputArea.getChildren().addAll(x0Label, x0, x1Label, x1, true_answer);
            inputArea.setPadding(new Insets(5, 40, 5, 5));

            HBox buttonArea = new HBox(5);
            buttonArea.setPrefWidth(620);
            buttonArea.setPadding(new Insets(5, 5, 5, 5));
            buttonArea.getChildren().add(calculate);
            calculate.setMinWidth(buttonArea.getPrefWidth());
            calculate.setOnAction(e -> buttonAction());

        top.getChildren().addAll(menuBar, equationArea, formulaArea, inputArea, buttonArea);
        mainLayout.setTop(top);
    }

    /**
     * Handles the click event for the Calculate button
     */
    @SuppressWarnings("unchecked")
    private void buttonAction()
    {
        double x0Num = Double.parseDouble(x0.getText());
        double x1Num = Double.parseDouble(x1.getText());
        ArrayList<SecantData> data = SecantMethod.calculate(x0Num, x1Num);
        for (SecantData d : data)
        {
            System.out.println(d.getIterationNumber());
            System.out.println(d.getX());
            System.out.println(d.getFOfX());
        }

        ObservableList<SecantData> tableList = FXCollections.observableArrayList(data);
        table.setItems(tableList);
    }

    /**
     * Creates the table in the center of the BorderPane
     */
    @SuppressWarnings("unchecked")
    private void createCenter()
    {
        table.setEditable(false);
        TableColumn iterationCol = new TableColumn("Iteration (n)");
        iterationCol.setCellValueFactory(new PropertyValueFactory<Integer, SecantData>("iterationNumber"));

        TableColumn approxValueXCol = new TableColumn("Approximate 'x' Value");
        approxValueXCol.setCellValueFactory(new PropertyValueFactory<Double, SecantData>("x"));

        TableColumn fSubXnCol = new TableColumn("f(xn) Value");
        fSubXnCol.setCellValueFactory(new PropertyValueFactory<Double, SecantData>("fOfX"));

        table.getColumns().addAll(iterationCol, approxValueXCol, fSubXnCol);
        table.setColumnResizePolicy((param) -> true); //resizing column headers to all be the same size
        Platform.runLater(() -> customResize(table)); //" "

        mainLayout.setCenter(table);
    }

    /**
     * Method ensures that all columns have the same width at the start of the program.
     * @param view : the table to resize
     */
    private void customResize(TableView<?> view)
    {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> width.addAndGet((long) col.getWidth()));
        double tableWidth = view.getWidth();

        if (tableWidth > width.get())
        {
            view.getColumns().forEach(
                    col -> col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size())));
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
