package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

    public List<CheckoutData> getCheckoutData() {

        List<CheckoutData> data =
                new ArrayList<>();

        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "src/test/java/tests/resources/checkout-data.csv"
                            )
                    );

            String line;

            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {

                String[] values =
                        line.split(",");

                data.add(
                        new CheckoutData(
                                values[0],
                                values[1],
                                values[2]
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}