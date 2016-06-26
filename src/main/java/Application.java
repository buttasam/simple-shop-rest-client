import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rest.logic.Proxy;

import java.io.IOException;
import java.util.logging.Logger;

public class Application extends javafx.application.Application {

    public static Application instance;

    private Proxy proxy;
    private Logger log = Logger.getLogger(Application.class.getName());

    private ProductPanel productPanel;
    private TextField tfName;

    public Application() {
        instance = this;
        proxy = new Proxy();
        productPanel = new ProductPanel(proxy);
        tfName = new TextField();
    }

    @Override
    public void start(Stage stage) {

        Button btnRefresh = new Button("Refresh");
        btnRefresh.setOnAction(e -> btnRefreshHandler());

        Button btnAddProduct = new Button("Add product");
        btnAddProduct.setOnAction(e -> btnAddProductHandler());


        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(btnRefresh, btnAddProduct);
        Parent root = new VBox(tfName, productPanel, buttonPanel);
        Scene scene = new Scene(root);

        stage.setTitle("Rest shop client");
        stage.setScene(scene);
        stage.show();
    }

    void alert(String er) {
        new Alert(Alert.AlertType.ERROR, er, ButtonType.OK).show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void btnRefreshHandler() {
        try {
            productPanel.refresh();
        } catch(IOException e1) {
            log.warning("IOExcetion");
        }
        log.info("Data were refreshed.");
    }

    private void btnAddProductHandler() {
        try {
            String productName = tfName.getText();
            proxy.saveRestProduct(productName);
            productPanel.refresh();

            log.info("New product called: " + productName + " was added.");
        } catch(Exception ex) {
            alert("Error");
        }
    }

}