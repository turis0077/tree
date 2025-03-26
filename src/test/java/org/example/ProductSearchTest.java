package org.example;

import org.example.model.Product;
import org.example.bst.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductSearchTest {

    private BinarySearchTree<Product> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testInsert() {
        Product p1 = new Product("A001", 100.0, 90.0, "Producto A", "Categoría 1");
        Product p2 = new Product("A002", 200.0, 180.0, "Producto B", "Categoría 2");
        Product p3 = new Product("A003", 300.0, 270.0, "Producto C", "Categoría 3");

        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);

        List<Product> inOrderList = bst.inOrderTraversal();
        assertEquals(3, inOrderList.size(), "La cantidad de productos en el árbol debe ser 3");

        // Verificar que se inserten correctamente según el orden de SKU (alfabético)
        assertEquals("A001", inOrderList.get(0).getSku());
        assertEquals("A002", inOrderList.get(1).getSku());
        assertEquals("A003", inOrderList.get(2).getSku());
    }

    @Test
    void testSearchBySKU() {
        Product p1 = new Product("A001", 100.0, 90.0, "Producto A", "Categoría 1");
        Product p2 = new Product("A002", 200.0, 180.0, "Producto B", "Categoría 2");
        Product p3 = new Product("A003", 300.0, 270.0, "Producto C", "Categoría 3");

        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);

        // Búsqueda de un SKU existente
        Product found = bst.search("A002");
        assertNotNull(found, "El producto con SKU A002 debe encontrarse");
        assertEquals("A002", found.getSku(), "El SKU del producto encontrado debe ser A002");

        // Búsqueda de un SKU inexistente
        Product notFound = bst.search("A999");
        assertNull(notFound, "No debe encontrarse ningún producto con SKU A999");
    }
}
