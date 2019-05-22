package RebBlackTree;

/**
 * {@code TreeNode} represented as a Binary Tree Node such that each node has exactly one parent
 * and two children.
 * Binary Tree Node
 * @convention <pre>
 *
 * </pre>
 */
public class RB_Node {
    // Binary Tree
    private RB_Node parent;
    private RB_Node leftChild;
    private RB_Node rightChild;
    private boolean isBlack;

    private int val;

    public RB_Node(RB_Node parent, RB_Node leftChild, RB_Node rightChild, int val) {
        this.leftChild = rightChild;
        this.leftChild = leftChild;
        this.parent = parent;
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public RB_Node replaceRightChild(RB_Node rightChild) {
        RB_Node tmp = this.rightChild;
        this.rightChild = rightChild;
        return tmp;
    }

    public RB_Node replaceLeftChild(RB_Node leftChild) {
        RB_Node tmp = this.leftChild;
        this.leftChild = leftChild;
        return tmp;
    }

    public RB_Node replaceParent(RB_Node parent) {
        RB_Node tmp = this.parent;
        this.parent = parent;
        return tmp;
    }

    public void makeBlack() {
        this.isBlack = true;
    }

    public void makeRed() {
        this.isBlack = false;
    }

    public RB_Node getParent() {
        return this.parent;
    }

    public RB_Node getLeftChild() {
        return this.leftChild;
    }

    public RB_Node getRightChild() {
        return this.rightChild;
    }

    public boolean isBlack() {
        return this.isBlack;
    }

    public int compare(RB_Node x) {
        // return 0 if equal
        if (this.val == x.getVal()) {
            return 0;
        }
        // return 1 if this < x
        else if (this.val < x.getVal()) {
            return 1;
        }
        // return -1 if this > x
        else {
            return -1;
        }
    }
}

