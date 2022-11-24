package comp210.assn04;

public interface BST<T extends Comparable<T>> {
    BST<T> insert(T element);
    BST<T> remove(T element);

    void printPreOrderTraversal();
    void printPostOrderTraversal();
    void printBreadthFirstTraversal();
    
    int getHeight();
    BST<T> getLeft();
    BST<T> getRight();
    T getElement();
    boolean isEmpty();
}
