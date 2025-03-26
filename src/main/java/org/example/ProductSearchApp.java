package org.example;

import org.example.model.Product;
import org.example.bst.BinarySearchTree;
import org.example.io.CSVDataLoader;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductSearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Crear el árbol binario de búsqueda
        BinarySearchTree<Product> productTree = new BinarySearchTree<>();

        // 2. Cargar los productos desde el archivo CSV
        CSVDataLoader loader = new CSVDataLoader();
        // Ajusta esta ruta si tu archivo CSV está en otro lugar
        String filePath = "src/main/resources/home appliance skus lowes.csv";
        List<Product> products = loader.loadProducts(filePath);

        // 3. Insertar cada producto en el árbol
        for (Product p : products) {
            productTree.insert(p);
        }

        // Mostrar cuántos productos se cargaron
        System.out.println(products.size() + " productos cargados en el árbol.");

        // 4. Buscar un producto por SKU
        System.out.print("Ingrese el SKU a buscar: ");
        String sku = scanner.nextLine();

        Product foundProduct = productTree.search(sku);
        if (foundProduct != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println("SKU: " + foundProduct.getSku());
            System.out.println("Precio Actual: " + foundProduct.getPriceCurrent());
            System.out.println("Precio Retail: " + foundProduct.getPriceRetail());
            System.out.println("Nombre del Producto: " + foundProduct.getProductName());
            System.out.println("Categoría: " + foundProduct.getCategory());
        } else {
            System.out.println("\nProducto con SKU " + sku + " no encontrado.");
        }

        // 5. Preguntar si el usuario desea ver el listado completo
        System.out.print("\n¿Desea ver el listado de productos? (S/N): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            // a) Listado in-order (ordenado por SKU, gracias a compareTo en Product)
            List<Product> inOrderProducts = productTree.inOrderTraversal();
            System.out.println("\nListado de productos (ordenado por SKU):");
            for (Product p : inOrderProducts) {
                System.out.println(p);
            }

            // b) Listar productos por precio actual ascendente
            List<Product> priceAsc = new ArrayList<>(inOrderProducts);
            priceAsc.sort(Comparator.comparing(Product::getPriceCurrent));
            System.out.println("\nListado de productos (orden ascendente por Price_Current):");
            for (Product p : priceAsc) {
                System.out.println(p);
            }

            // c) Listar productos por precio actual descendente
            List<Product> priceDesc = new ArrayList<>(inOrderProducts);
            priceDesc.sort(Comparator.comparing(Product::getPriceCurrent).reversed());
            System.out.println("\nListado de productos (orden descendente por Price_Current):");
            for (Product p : priceDesc) {
                System.out.println(p);
            }
        }

        // Fin del programa
        System.out.println("\nFin del programa.");
    }
}
