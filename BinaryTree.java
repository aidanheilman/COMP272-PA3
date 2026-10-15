import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A binary tree of integers (the tree from class, now storing int values).
 *
 * EXERCISE 1 — Complete the two methods marked TODO: add(int) and search(int).
 * Do NOT change the provided code, method names, or signatures.
 * You may add private helper methods.
 *
 * Name: Aidan Heilman
 * Student ID: aheilman 00001675857
 */
public class BinaryTree {

    public static class Node {
        private int element;
        private Node left;
        private Node right;

        public Node(int element) {
            this.element = element;
        }

        public int getElement() { return element; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right; }
    }

    // protected (not private) so that subclasses such as BST can use it.
    protected Node root;

    /** Creates an empty tree. */
    public BinaryTree() {
        this.root = null;
    }

    /** Creates a tree whose root is the given node (may be a hand-built tree). */
    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() { return root; }

    // Height is measured in edges: a leaf has height 0.
    // An empty tree has height -1.
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // ------------------------------------------------------------------
    // EXERCISE 1 — TODO
    // ------------------------------------------------------------------

    /**
     * Part A: Adds a new node containing {@code value} at the FIRST FREE POSITION
     * found by a LEVEL-ORDER (breadth-first) traversal of the tree.
     *
     * Rule: visit the nodes level by level, from top to bottom, and from left
     * to right within each level (use a queue). At each visited node:
     *   - if its left child is empty, attach the new node there and stop;
     *   - otherwise, if its right child is empty, attach the new node there and stop;
     *   - otherwise, keep traversing.
     * If the tree is empty, the new node becomes the root.
     * Duplicate values are allowed in a general binary tree.
     */
    public void add(int value) {
        // Create new node obj that holds value as element
        Node newNode = new Node(value);
        // If tree is empty, added val becomes root
        if (root == null) {
            root = newNode;
        }
        // Otherwise tree is not empty, search for open space using queue && insert
        // Store head of queue in variable 'current'
        // Check if space on left or right of current, if not enqueue children and repeat
        else {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            while (true) {
                Node current = queue.poll();
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    return;
                }
                if (current.getRight() == null) {
                    current.setRight(newNode);
                    return;
                }
                else {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                }
            }
        }
    }

    /**
     * Part B: Searches the tree for a node whose element equals {@code value}.
     * Use a PRE-ORDER traversal and return the FIRST matching node found
     * (this matters when the tree contains duplicates).
     *
     * @return the node containing value, or null if no such node exists
     *         (including when the tree is empty).
     */
    // Private algo traversal helper
    private Node preOrderSearch(Node node, int value) {
        // Check if the current node is null (empty tree or at child of leaf)
        if (node == null) {
            return null;
        }
        // If the node holds the value in question, return it 
        if (node.getElement() == value) {
            return node;
        }
        // If the parent node was not empty and did not hold the value
        // Recurse down the left child and return the result if not null
        Node found = preOrderSearch(node.getLeft(), value);
        if (found != null) {
            return found;
        }
        // If the left side was null, search the right recursively
        return preOrderSearch(node.getRight(), value);  
    }

    // Encapsulate init at root to search whole tree 
    // User only needs to enter value to be found
    public Node search(int value) {
        return preOrderSearch(root, value);
    }
        
    

    /*
     * Exercise 1, Part C — Complexity of search (n = number of nodes)
     *
     * Best case: O(1)
     *
     * Worst case: O(n)
     *
     * Input that produces the worst case: 
     * Searching for a node that is stored at the bottom right node of the tree.
     *
     * Justification: 
     * A preorder algorithm first checks the root, then the entirety of the left
     * subtree, then the right subtree, recursively. In a small example, if you have
     * a tree holding n = 3 nodes, where A is the root, B is the left child, and 
     * C is the right child, and you are searching for C the algorithm will check A,
     * then B, then C, at which point it will return C after completing 3 (3 = n) O(1)
     * operations. Best case would be to search for a value in an empty tree or a tree
     * where the root holds the value in question, in this example, A, because it will
     * return before recursively calling itself even once. 
     */

    // ------------------------------------------------------------------
    // PROVIDED — display the structure of any binary tree.
    // ------------------------------------------------------------------

    public void printTree() {
        if (root == null) {
            System.out.println("(empty tree)");
        }
        else {
            System.out.println(root.getElement());
            printChildren(root, "");
        }
    }

    private void printChildren(Node node, String prefix) {
        Node left = node.getLeft();
        Node right = node.getRight();

        if (left != null) {
            boolean last = right == null;
            System.out.println(prefix + (last ? "└── " : "├── ")
                    + "L: " + left.getElement());
            printChildren(left, prefix + (last ? "    " : "│   "));
        }
        if (right != null) {
            System.out.println(prefix + "└── R: " + right.getElement());
            printChildren(right, prefix + "    ");
        }
    }
}
