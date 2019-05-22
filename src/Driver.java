import RebBlackTree.RB_Node;
import RebBlackTree.RB_Tree;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        RB_Tree tree = new RB_Tree();
        RB_Node root = new RB_Node(null, null, null, 30);
        tree.insert(root);

        print2DUtil(root, 4);
        int n = 0;

        while (n != -1) {
            System.out.print("Enter -1 to exit: ");
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();

            // insert
            RB_Node node = new RB_Node(null, null, null, n);
            tree.insert(node);

            // print
            print2DUtil(root, 4);
        }
    }

    private static void print2DUtil(RB_Node root, int space)
    {
        // Base case
        if (root == null) {
            return;
        }

        // Increase distance between levels
        space += 10;

        // Process right child first
        print2DUtil(root.getRightChild(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 10; i < space; i++) {
            System.out.print(" ");
        }

        System.out.println(root.getVal() + "|" + "red:" + !root.isBlack());

        // Process left child
        print2DUtil(root.getLeftChild(), space);
    }
}
