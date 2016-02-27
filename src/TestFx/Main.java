package TestFx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    public Stage window;
    private Scene scene1, scene2;
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
        window.setTitle("Database content");

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

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Invalid password/username combination");
            alert.show();
        }

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
