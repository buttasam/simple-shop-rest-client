import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rest.logic.Proxy;

public class Application extends javafx.application.Application {

    public static Application instance;

    private Proxy proxy;
    private ProductPanel productPanel;

    public Application() {
        instance = this;
        proxy = new Proxy();
        productPanel = new ProductPanel(proxy);
    }

    @Override
    public void start(Stage stage) {

        Button btnRefresh = new Button("Refresh");
        btnRefresh.setOnAction(e -> {
            try {
                productPanel.refresh();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(btnRefresh);
        Parent root = new VBox(productPanel, buttonPanel);
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

}