package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class TestDataReader {

    public List<UserData> getUsers() {

        try {

            Type listType =
                    new TypeToken<List<UserData>>() {}.getType();

            return new Gson().fromJson(
                    new FileReader(
                            "src/test/java/tests/resources/users.json"
                    ),
                    listType
            );

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }
}