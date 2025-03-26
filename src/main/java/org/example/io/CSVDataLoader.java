package org.example.io;

import org.example.model.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataLoader {

    public List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Omitir la línea de encabezados (header)
            String header = br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                // Separar tomando en cuenta posibles comas dentro de comillas
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Verificar que haya suficientes columnas
                if (tokens.length < 20) {
                    continue;
                }

                // Extraer datos según los índices que se necesitan
                String category = tokens[0].trim();        // CATEGORY
                String sku = tokens[6].trim();            // SKU
                double priceRetail = parseDouble(tokens[9].trim());    // PRICE_RETAIL
                double priceCurrent = parseDouble(tokens[10].trim());  // PRICE_CURRENT
                String productName = tokens[18].trim();   // PRODUCT_NAME

                // Crear objeto Product y agregarlo a la lista
                Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                products.add(product);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo CSV: " + e.getMessage());
        }

        return products;
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
