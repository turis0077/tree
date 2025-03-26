package org.example.bst;

public class BSTNode<E> {
    public E data;
    public BSTNode<E> left;
    public BSTNode<E> right;

    public BSTNode(E data) {
        this.data = data;
        left = null;
        right = null;
    }
}
