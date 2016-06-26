package rest.logic;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import rest.entity.RestProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Proxy {


    private ObjectMapper mapper;
    private RestTemplate restTemplate;

    public Proxy() {
        mapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }


    public RestProduct restProductById(int id) throws IOException {
        String restProductString = restTemplate.getForObject("http://localhost:8080/rest/product/" + id, String.class);
        RestProduct restProduct = mapper.readValue(restProductString, RestProduct.class);

        return restProduct;
    }

    public ArrayList<RestProduct> allRestProducts() throws IOException {

        String restProductString = restTemplate.getForObject("http://localhost:8080/rest/products", String.class);
        RestProduct[] restProduct = mapper.readValue(restProductString, RestProduct[].class);

        ArrayList<RestProduct> list = new ArrayList<>(Arrays.asList(restProduct));

        return list;
    }

}