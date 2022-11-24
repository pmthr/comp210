package comp210.assn06;


public interface SelfBalancingBST<T extends Comparable<T>> {
    boolean isEmpty();
    int height();
    int size();

    SelfBalancingBST<T> insert(T element);
    SelfBalancingBST<T> remove(T element);

    T findMin();
    T findMax();

    boolean contains(T element);
    T getValue();

    SelfBalancingBST<T> getLeft();
    SelfBalancingBST<T> getRight();
}
