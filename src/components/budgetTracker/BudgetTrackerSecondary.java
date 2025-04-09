package components.budgetTracker;

import components.simplewriter.SimpleWriter;
import components.stack.Stack;
import components.stack.Stack2;

/**
 * Layered implementations of secondary methods for {@code BudgetTracker}.
 */
public abstract class BudgetTrackerSecondary implements BudgetTracker {
    /*
     * Common methods (from Object)
     */
    @Override
    public final int hashCode() {
        // Return the hash code of the account name
        return this.getAccountName().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        // Check if the object is null
        if (obj == null) {
            return false;
        }
        // Check if the object is an instance of BudgetTrackerSecondary
        if (!(obj instanceof BudgetTrackerSecondary)) {
            return false;
        }
        // Compare the account names
        return this.getAccountName()
                .equals(((BudgetTrackerSecondary) obj).getAccountName());
    }

    @Override
    public final String toString() {
        // Return the account name and the number of records
        return this.getAccountName() + " has " + this.length() + " records.";
    }

    /*
     * Other non-kernel methods
     */
    @Override
    public final void printNumOfRecords(int num, SimpleWriter out) {
        assert num <= this.length() && out
                .isOpen() : "Violation of: num <= |this| and out.isOpen()";
        // Use a stack to store the last num records
        Stack<Transaction> stack = new Stack2<>();
        for (int i = 0; i < num; i++) {
            Transaction record = this.remove();
            out.println(i + ": " + String.format("%.2f", record.amount()) + "$ "
                    + record.description());
            stack.push(record);
        }

        // Add the records back
        while (stack.length() > 0) {
            this.add(stack.pop());
        }
    }

    @Override
    public final void addToIndex(int index, float amount, String description) {
        assert index <= this.length()
                && index >= 0 : "Violation of: index <= |this| and index >= 0";
        // Use a stack to store the records before the index
        Stack<Transaction> stack = new Stack2<>();
        for (int i = 0; i < index; i++) {
            stack.push(this.remove());
        }

        // Add the new record
        this.add(amount, description);

        // Add the records back
        while (stack.length() > 0) {
            this.add(stack.pop());
        }
    }

    @Override
    public final Transaction modifyIndex(int index, float amount,
            String description) {
        assert index < this.length()
                && index >= 0 : "Violation of: index < |this| and index >= 0";
        // Use a stack to store the records before the index
        Stack<Transaction> stack = new Stack2<>();
        for (int i = 0; i < index; i++) {
            stack.push(this.remove());
        }

        // Modify the record at the index
        Transaction record = this.remove();
        this.add(amount, description);

        // Add the records back
        while (stack.length() > 0) {
            this.add(stack.pop());
        }

        // Return the record before modifying
        return record;
    }

    @Override
    public final Transaction removeIndex(int index) {
        assert index < this.length()
                && index >= 0 : "Violation of: index < |this| and index >= 0";
        // Use a stack to store the records before the index
        Stack<Transaction> stack = new Stack2<>();
        for (int i = 0; i < index; i++) {
            stack.push(this.remove());
        }

        // Remove the record at the index
        Transaction record = this.remove();

        // Add the records back
        while (stack.length() > 0) {
            this.add(stack.pop());
        }

        // Return the removed record
        return record;
    }

    @Override
    public final void changeIndex(int curIndex, int index) {
        assert index < this.length()
                && index >= 0 : "Violation of: index < |this| and index >= 0";
        assert curIndex < this.length()
                && curIndex >= 0 : "Violation of: curIndex < |this| and curIndex >= 0";
        // If the indexes are the same, no changes are needed
        if (curIndex == index) {
            return;
        }

        // Use a stack to store the records before the curIndex
        Stack<Transaction> stack = new Stack2<>();
        for (int i = 0; i < curIndex; i++) {
            stack.push(this.remove());
        }

        // Store the record at the curIndex
        Transaction record = this.remove();

        // Find the target index
        if (curIndex < index) {
            for (int i = 0; i < index - curIndex - 1; i++) {
                stack.push(this.remove());
            }
        } else {
            for (int i = 0; i < curIndex - index; i++) {
                this.add(stack.pop());
            }
        }

        // Insert the record at the new index and restore the remaining records
        this.add(record);
        while (stack.length() > 0) {
            this.add(stack.pop());
        }
    }
}
