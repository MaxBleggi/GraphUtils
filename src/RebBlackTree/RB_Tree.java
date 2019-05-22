package RebBlackTree;


/**
 rep invariant: an RB Tree must meet all following criteria
 - Every node is either red or black
 - root is black
 - every leaf node is nil
 - if a node is red, both its children are black
 - for each node, all simple paths from the node to descendant
 leaves contain the same number of black nodes
 */
public class RB_Tree {
    private RB_Node root;

    public RB_Tree() {
        this.root = null;
    }

    public RB_Node getRoot() {
        return root;
    }

    public void insert(RB_Node z) {
        RB_Node parent = locateParent(z);
        if (parent != null)
            System.out.print("parent: " + parent.getVal());

        z.replaceParent(parent);

        if (parent == null) {
            this.root = z;
        }
        else if (z.compare(parent) == 1) {
            parent.replaceLeftChild(z);
        }
        else {
            parent.replaceRightChild(z);
        }

        z.replaceRightChild(null);
        z.replaceLeftChild(null);
        z.makeRed();
        fixup(z);
    }

    private void fixup(RB_Node z) {
        RB_Node tmp = fixupCase1(z);
        z = tmp != null ? tmp : z;

        tmp = fixupCase2(z);
        z = tmp != null ? tmp : z;

        fixupCase3(z);
        this.root.makeBlack();
    }

    private RB_Node fixupCase1(RB_Node z) {
        while (z.compare(this.root) != 0 && !z.getParent().isBlack()) {
            RB_Node y = sibling(z.getParent());

            if (y == null || y.isBlack()) {
                return null;
            }

            z.getParent().makeBlack();
            y.makeRed();
            z = z.getParent().getParent();
            z.makeRed();
        }

        return z;
    }

    private RB_Node fixupCase2(RB_Node z) {
        if (z.compare(root) == 0 || z.getParent().isBlack()) {
            return null;
        }

        RB_Node x = z.getParent();
        RB_Node w = x.getParent();

        if (z.compare(x.getRightChild()) == 0 && (w == null || x.compare(w.getLeftChild()) == 0)) {
            z = x;
            leftRotate(x);
            return  z;
        }
        else if (z.compare(x.getLeftChild()) == 0 && (w == null || x.compare(w.getRightChild()) == 0)) {
            z = x;
            rightRotate(x);
            return z;
        }

        return null;
    }

    private void fixupCase3(RB_Node z) {
        if (z.compare(root) == 0 || z.getParent().isBlack()) {
            return;
        }

        RB_Node x = z.getParent();
        RB_Node w = x.getParent();

        if (z.compare(x.getLeftChild()) == 0 && (w == null || z.compare(w.getLeftChild()) == 0)) {
            rightRotate(w);
            x.makeBlack();
            w.makeRed();
        }
        else if (z.compare(x.getRightChild()) == 0 && (w == null || z.compare(w.getRightChild()) == 0)) {
            leftRotate(w);
            x.makeBlack();
            w.makeRed();
        }
    }

    private void rightRotate(RB_Node w) {
        RB_Node x = w.getLeftChild();
        RB_Node z = x.getLeftChild();
        RB_Node y = w.getRightChild();

        // rotate x up and its children
        w.replaceLeftChild(x.getRightChild());
        x.replaceParent(w.getParent());
        x.replaceRightChild(w);
        w.replaceParent(x);
    }

    private void leftRotate(RB_Node z) {
        RB_Node x = z.getParent();
        RB_Node w = x.getParent();

        // rotate x up and its children
        x.replaceRightChild(z.getLeftChild());
        z.replaceLeftChild(x);
        z.replaceParent(w);
        w.replaceParent(z);
    }

    private RB_Node sibling(RB_Node x) {
        if (x.getParent() != null) {
            RB_Node p = x.getParent();

            if (p.getLeftChild().compare(x) == 0) {
                return p.getRightChild();
            }

            return p.getLeftChild();
        }

        return null;
    }

    /**
     * locate parent of @code z node
     * @param z
     * @return
     */
    private RB_Node locateParent(RB_Node z) {

        if (this.root == null) {
            return null;
        }

        // start at top of the tree
        RB_Node parent = this.root;
        RB_Node tmp = this.root;

        // go until leaf node is found
        while (tmp.getLeftChild() != null || tmp.getRightChild() != null) {

            if (z.compare(tmp) == 1) {
                tmp = tmp.getLeftChild();
            }
            else {
                tmp = tmp.getRightChild();
            }

            parent = tmp;
        }

        return parent;
    }
}
