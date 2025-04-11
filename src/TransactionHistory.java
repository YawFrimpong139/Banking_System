

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TransactionHistory {
    private TransactionNode head;
    private int size;

    private static class TransactionNode {
        Transaction transaction;
        TransactionNode next;

        public TransactionNode(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }
    }

    public TransactionHistory() {
        this.head = null;
        this.size = 0;
    }

    // Add transaction to the front (most recent first)
    public void addTransaction(Transaction transaction) {
        TransactionNode newNode = new TransactionNode(transaction);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public List<Transaction> getLastNTransaction(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of transactions 'n' must be positive.");
        }
        List<Transaction> result = new LinkedList<>(); // Use LinkedList to maintain order
        TransactionNode current = head;
        int count = 0;

        while (current != null && count < n) {
            result.add(current.transaction);
            current = current.next;
            count++;
        }
        return result;
    }

    public int size() {
        return size;
    }
}


