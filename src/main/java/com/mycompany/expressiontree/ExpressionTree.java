package com.mycompany.expressiontree;
import java.util.Stack;

class Node {
    String value;
    Node left, right;

    public Node(String item) {
        value = item;
        left = right = null;
    }
}

public class ExpressionTree {
    
      public static void main(String[] args) {
        String postfix = "53+82-*";

        Node root = buildTree(postfix);

        printInfix(root);
        System.out.println("\nResult: " + evaluate(root));
    }

    public static Node buildTree(String postfix) {
        Stack<Node> s = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            Node node = new Node(String.valueOf(c));

            if (c >= '0' && c <= '9') {
                s.push(node);
            } else {
                node.right = s.pop();
                node.left = s.pop();
                s.push(node);
            }
        }

        return s.pop();
    }

    public static int evaluate(Node root) {
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.value);
        }

        int left = evaluate(root.left);
        int right = evaluate(root.right);

        switch (root.value) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            default:
               return 0;
        }
    }

    public static void printInfix(Node root) {
        if (root.left == null && root.right == null) {
            System.out.print(root.value);
        } else {
            System.out.print("(");
            printInfix(root.left);
            System.out.print(" " + root.value + " ");
            printInfix(root.right);
            System.out.print(")");
        }
    }
}