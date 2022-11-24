package comp210.assn04;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new NonEmptyBST<Integer>(3);
        
        bst = bst.insert(8);
        bst = bst.insert(1);
        bst = bst.insert(9);
        bst = bst.insert(4);
    }
}
