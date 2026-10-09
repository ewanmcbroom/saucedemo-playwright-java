package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ProductDataReader {

    public List<ProductData> getProducts() {

        try {

            Type listType =
                    new TypeToken<List<ProductData>>() {}.getType();

            return new Gson().fromJson(
                    new FileReader(
                            "src/test/java/tests/resources/products.json"
                    ),
                    listType
            );

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }
}