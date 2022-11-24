package comp210.assn06;

public class Main {
    public static void main(String[] args) {

        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        for (int i=0; i<50; i++) {
            avl_bst = avl_bst.insert((int) (Math.random()*100));
        }
        
        System.out.println(avl_bst.height());

        for (int i=0; i<50; i++) {
            avl_bst = avl_bst.insert(i);
        }

        System.out.println(avl_bst.height());
    }
}
