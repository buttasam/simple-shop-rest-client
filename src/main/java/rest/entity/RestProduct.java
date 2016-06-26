package rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RestProduct {


    private String name;
    private Double price;
    private List<String> categories;

    public RestProduct() {
    }

    public RestProduct(String name, Double price, List<String> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return name;
    }
}
