import java.util.Scanner;

class Node {
    int value;
    Node left;
    Node right;
}

class BST {
    public Node createNewNode(int val) {
        Node a = new Node();
        a.value = val;
        a.left = null;
        a.right = null;
        return a;
    }

    public Node insert(Node node, int val) {
        if (node == null) {
            return createNewNode(val);
        }
        if (val < node.value) {
            node.left = insert(node.left, val);
        } else if (val > node.value) {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public void printLeaves(Node node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                System.out.print(node.value + " ");
            }
            printLeaves(node.left);
            printLeaves(node.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        BST tree = new BST();
        Node root = null;
        int val;
        while ((val = scan.nextInt()) != 0) {
            root = tree.insert(root, val);
        }

        tree.printLeaves(root);

        scan.close();
    }
}
