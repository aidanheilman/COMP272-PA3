public class BST extends BinaryTree {
    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        // If empty, set root
        if (root == null) {
            root = newNode;
            return;
        }
        // If val < current, move left
        // If val > current, move right
        // If val = current, return
        // If current.left is null && val < current, current.left = val
        // If current.right is null && val > current, current.right = val
        Node current = root;
        while (true) {
            if (current.getElement() == value) {
                return;
            }
            else if (value < current.getElement()) {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);    
                    return;
                }
                current = current.getLeft();
            }
            else if (value > current.getElement()) {
                if (current.getRight() == null) {
                    current.setRight(newNode);    
                    return;
                }
                current = current.getRight();
            }
        }
    }
    
    // Private recursive search function
    private Node binarySearch(Node node, int value){
        if (node == null) {
            return null;
        }
        if (node.getElement() == value) {
            return node;
        }
        // If the value was not in the current node,
        // the current node was not empty, 
        // and the value is less than the current node's stored value,
        // recurse left
        else if ( value < node.getElement()) {
            return binarySearch(node.getLeft(), value);
        }
        // Otherwise, it must be right of the current node,
        // Recurse right
        else {
            return binarySearch(node.getRight(), value);
        }

    }


    @Override
    // Initialize encapsulated search at root to search entire tree
    // for user provided value
    public Node search(int value) {
        return binarySearch(root, value);
    } 
}

/*
Let:

- $n$ be the number of nodes in the BST;
- $h$ be the height of the BST.

Answer the following questions in a comment in `BST.java`, directly after your `search` method. Draw trees with text in the comment.

1. Determine the running time of `BST.search` in terms of $h$. Then express it in terms of $n$:
   - when the tree is balanced: O(h)
   - in the worst case: O(n)

2. Give an insertion order of the values $1, 2, \dots, 7$ that produces the worst case, and draw the resulting tree.
add(1), add(2), add(3)... add(7)
(1)
  \
  (2)
    \
    (3)
      \
      (4)
        \
        (5)
          \
          (6)
            \
            (7)   

3. Compare your answer with Exercise 1, Part C. Why is it worth overriding `search` even though the inherited version returns correct answers?
Assuming the tree is balanced, the worst case drops to O(h). If we were to not override search, the worst case (even for a balanced BST) would remain O(n).

4. Explain what happens in the following code, and why. Which `add` method is executed? Draw the resulting tree.
BinaryTree t = new BST();
t.add(50);
t.add(70);
t.add(30);

A BST object is declared as t, because the object is a BST, it uses the overridden BST add function, not the generic BinaryTree one. 
Because it uses the BST's add function, the resulting tree will have nodes holding 50 as the root, 70 as the right child, and 30 as the left child.
    (50)
   /   \
(30)   (70)

*/
