import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import rest.entity.RestProduct;
import rest.logic.Proxy;

import java.io.IOException;
import java.util.List;

public class ProductPanel extends StackPane {

    ObservableList<RestProduct> restProducts;
    private final ListView<RestProduct> listView;

    private Proxy proxy;

    public ProductPanel(Proxy proxy) {
        this.proxy = proxy;
        listView = new ListView<>(restProducts = FXCollections.observableArrayList());
        getChildren().add(listView);
        try {
            refresh();
        } catch(Exception ex) {
            Application.instance.alert(ex.toString());
        }
    }

    public void refresh() throws IOException {
        List<RestProduct> os = proxy.allRestProducts();
        restProducts.clear();
        restProducts.addAll(os);
    }

}