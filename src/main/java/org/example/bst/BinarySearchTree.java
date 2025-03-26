package org.example.bst;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Product;

public class BinarySearchTree<E extends Comparable<E>> {

    private BSTNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(E element) {
        root = insertRec(root, element);
    }

    private BSTNode<E> insertRec(BSTNode<E> node, E element) {
        if (node == null) {
            return new BSTNode<>(element);
        }
        if (element.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, element);
        } else if (element.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, element);
        }
        return node;
    }

    // Busca un producto en el árbol utilizando el SKU. Se asume que E es de tipo Product.
    public E search(String sku) {
        return searchRec(root, sku);
    }

    private E searchRec(BSTNode<E> node, String sku) {
        if (node == null) {
            return null;
        }
        Product prod = (Product) node.data;
        int cmp = sku.compareTo(prod.getSku());
        if (cmp == 0) {
            return node.data;
        } else if (cmp < 0) {
            return searchRec(node.left, sku);
        } else {
            return searchRec(node.right, sku);
        }
    }

    public List<E> inOrderTraversal() {
        List<E> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(BSTNode<E> node, List<E> list) {
        if (node != null) {
            inOrderRec(node.left, list);
            list.add(node.data);
            inOrderRec(node.right, list);
        }
    }
}
