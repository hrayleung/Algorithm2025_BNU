import java.util.*;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// Definition for a binary tree node.


public class leetcode109 {

    // Convert a sorted singly linked list to a balanced BST.
    public TreeNode sortedListToBST(ListNode head) {
        // Base case: if the list is empty, return null.
        if (head == null) {
            return null;
        }

        // If the list has only one node, return that node as the root.
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // Use the two-pointer technique to find the middle of the linked list.
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // Disconnect the left half from the middle node.
        if (prev != null) {
            prev.next = null;
        }

        // The middle node is the root of the BST.
        TreeNode root = new TreeNode(slow.val);

        // Recursively build the left and right subtrees.
        if (prev != null) {
            root.left = sortedListToBST(head);
        } else {
            root.left = null;
        }

        root.right = sortedListToBST(slow.next);
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode head = new ListNode(sc.nextInt());
        ListNode current = head;
        for (int i = 2; i <= n; i++) {
            current.next = new ListNode(sc.nextInt());
            current = current.next;
        }
        current.next = null;
        TreeNode root = new leetcode109().sortedListToBST(head);

        printTree(root);
    }

    // Helper method to print the tree in level order.
    private static void printTree(TreeNode root) {


        if (root == null) {
            System.out.println("[]");
            return;
        }

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    result.add("null");
                } else {
                    result.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }

        // Remove trailing nulls
        while (result.size() > 0 && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        // Print the tree
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
