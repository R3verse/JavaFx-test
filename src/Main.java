import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    public Stage window;
    private Scene scene2;
    private TableView<Product> table;
    private TextField nameInput, priceInput, quantityInput;
    private PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Product store - Max Jensen");

       setScene1();


        window.show();
    }

    public void setScene1()
    {
        window.setTitle("Login");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username:");
        nameLabel.setId("bold-label");
        GridPane.setConstraints(nameLabel, 0, 0);

        nameInput = new TextField("");
        nameInput.setPromptText("Username..");
        GridPane.setConstraints(nameInput, 1, 0);


        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password..");

        passwordField.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key == KeyCode.ENTER)
            {
                validateUser();
            }
        });

        GridPane.setConstraints(passwordField, 1, 1);

        //Login
        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
            validateUser();
        });
        GridPane.setConstraints(loginButton, 1, 2);

        //Sign up
        Button signUpButton = new Button("Sign Up");
        signUpButton.getStyleClass().add("button-blue");
        GridPane.setConstraints(signUpButton, 1, 3);


        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passwordField, loginButton, signUpButton);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();


    }



    public Scene setScene2()
    {

        window.setTitle("Product Store - Max Jensen");
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameInput = new TextField();
        nameInput.setPromptText("Name..");
        nameInput.setMinWidth(100);

        priceInput = new TextField();
        priceInput.setPromptText("Price..");
        priceInput.setMinWidth(100);

        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity..");
        quantityInput.setMinWidth(100);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        Button backButton = new Button("Go Back");
        backButton.setOnAction(e -> setScene1());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton, backButton);


        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, hbox);

       scene2 = new Scene(vbox);
        window.setScene(scene2);
        return scene2;
    }

    public void validateUser()
    {

        if (nameInput.getText().equals("Maxjensendk") && passwordField.getText().equals("1P@SSW0RD!") ) {

            setScene2();

            //TODO: Testing scene3
           setScene3();


        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Invalid password/username combination");
            alert.show();
        }

    }

    private void setScene3() {

        Stage window3 = new Stage();


        PortfolioController portfolioController = new PortfolioController();
        Portfolio portfolio = new Portfolio();
        portfolioController.setBalance(2500023);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("FRIMAN", 80),
                        new PieChart.Data("Bear Olja x10 VON", 25),
                        new PieChart.Data("TOBII", 35),
                        new PieChart.Data("FING B", 30));

        PieChart chart = new PieChart(pieChartData);

        chart.setMinSize(30.0, 30.0);

        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }

        chart.setTitle("Max\'s Portfolio");

        window3.setTitle("Portfolio");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);


        Label labelBalance = new Label("Balance: " + portfolioController.getBalance() + " kr,-");
        labelBalance.setStyle("-fx-border-color: black");


        GridPane.setConstraints(labelBalance, 1, 0);

        Button showGraphs = new Button("Show graph");
        showGraphs.setOnAction(e -> {
            runGraphScene();
        });
        GridPane.setConstraints(showGraphs, 2, 0);

        grid.getChildren().addAll(chart, caption, labelBalance, showGraphs);

        Scene scene3 = new Scene(grid, 800, 900);


        window3.setScene(scene3);
        window3.show();
    }

    public void runGraphScene()
    {
        Stage stage = new Stage();
        // adding stockmarket
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("In percent(%)");
        xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("FRIMAN");

        series1.getData().add(new XYChart.Data("Jan", 10));
        series1.getData().add(new XYChart.Data("Feb", 20));
        series1.getData().add(new XYChart.Data("Mar", 30));
        series1.getData().add(new XYChart.Data("Apr", 40));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("TOBII");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Bear Olja x10 VON");
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("FING B");
        series4.getData().add(new XYChart.Data("Jan", 44));
        series4.getData().add(new XYChart.Data("Feb", 53));
        series4.getData().add(new XYChart.Data("Mar", 47));
        series4.getData().add(new XYChart.Data("Apr", 57));


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1, series2, series3, series4);

        stage.setScene(scene);
        stage.show();
    }


    // adds to table
    public void addButtonClicked()
    {
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    public void deleteButtonClicked()
    {
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    public ObservableList<Product> getProduct()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Milk", 15.00, 20));
        products.add(new Product("Eggs", 30.00, 20));
        products.add(new Product("Tuna", 10.00, 50));


        return products;
    }

}
