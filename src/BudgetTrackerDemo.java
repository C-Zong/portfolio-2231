
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack2;

/**
 * This class serves as a proof-of-concept for the BudgetTracker. It uses a
 * stack (Stack2) to manage and store budget records.
 */
public class BudgetTrackerDemo {

    /*
     * A useful constant for rounding purposes.
     */

    /**
     * A constant used to round floating-point values to two decimal places.
     */
    private final float roundingFactor = 100.0f;

    /*
     * Private field
     */

    /**
     * Inner class representing a single budget record. It stores the amount of
     * money and its description.
     */
    class Record {
        /**
         * The amount of money.
         */
        private float amount;
        /**
         * The record description.
         */
        private String description;
    }

    /**
     * A stack to store all the budget records.
     */
    private Stack<Record> records;

    /**
     * The current number of records in the stack.
     */
    private int length;

    /**
     * Current balance.
     */
    private float balance;

    /**
     * Initializes the fields.
     */
    private void createNewRep() {
        this.records = new Stack2<>();
        this.length = 0;
        this.balance = 0;
    }

    /*
     * Constructor
     */

    /**
     * No argument constructor.
     */
    BudgetTrackerDemo() {
        this.createNewRep();
    }

    /*
     * Kernal methods
     */

    /**
     * Report the size of the stack.
     *
     * @return the size of the stack
     */
    private int length() {
        return this.length;
    }

    /**
     * Remove the first element in the stack.
     *
     * @return the first element in the stack
     */
    private Record remove() {
        assert this.length != 0 : "Violate: this.length != 0";
        Record p = this.records.pop();
        // Adjust the balance
        this.balance -= p.amount;
        // Decrease the number of records after removal
        this.length--;
        return p;
    }

    /**
     * Add a record to the stack.
     *
     * @param amount
     *            the amount of money
     * @param description
     *            the description of the record
     */
    private void add(float amount, String description) {
        Record record = new Record();
        record.amount = amount;
        record.description = description;
        // Adjust the balance
        this.balance += amount;
        // Increase the number of records after addition
        this.length++;
        this.records.push(record);
    }

    /**
     * Add a record to the stack.
     *
     * @param record
     *            the record to be added
     */
    private void add(Record record) {
        // Adjust the balance
        this.balance += record.amount;
        // Increase the number of records after addition
        this.length++;
        this.records.push(record);
    }

    /**
     * Repert the current balance, rounded to two decimal places.
     *
     * @return the current balance
     */
    private float balance() {
        return Math.round(this.balance * this.roundingFactor)
                / this.roundingFactor;
    }

    /*
     * Secondary methods
     */

    /**
     * Print the last {@code num} records with an index.
     *
     * @param num
     *            the number of records need to be printed
     * @param index
     *            the index of the record
     * @param out
     *            output stream
     */
    private void printNumOfRecords(int num, int index, SimpleWriter out) {
        assert num <= this.length() : "Violate: num <= this.length()";
        assert out.isOpen() : "Violate: out.isOpen()";
        // Recursively print the record util the num of records is printed
        if (num > 0) {
            Record p = this.remove();
            out.print(index + "\t" + p.amount + "\t" + p.description + "\n");
            this.printNumOfRecords(num - 1, index + 1, out);
            this.add(p);
        }
    }

    /**
     * Add the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @param amount
     *            the amount of money
     * @param description
     *            the description of the record
     */
    private void addToIndex(int index, float amount, String description) {
        assert index < this.length() : "Violate: index < this.length()";
        assert index >= 0 : "Violate: index >= 0";
        // Recursively find the index
        if (index != 0) {
            Record p = this.remove();
            this.addToIndex(index - 1, amount, description);
            this.add(p);
        } else {
            this.add(amount, description);
        }
    }

    /**
     * Modify the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @param amount
     *            the amount of money
     * @param description
     *            the description of the record
     * @return the record before modifying
     */
    private Record modifyIndex(int index, float amount, String description) {
        assert index < this.length() : "Violate: index < this.length()";
        assert index >= 0 : "Violate: index >= 0";
        Record modRec = null;
        // Recursively find the index
        if (index != 0) {
            Record p = this.remove();
            modRec = this.modifyIndex(index - 1, amount, description);
            this.add(p);
        } else {
            modRec = this.remove();
            this.add(amount, description);
        }
        return modRec;
    }

    /**
     * Remove the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @return the removed record
     */
    private Record removeIndex(int index) {
        assert index < this.length() : "Violate: index < this.length()";
        assert index >= 0 : "Violate: index >= 0";
        Record remRec = null;
        // Recursively find the index
        if (index != 0) {
            Record p = this.remove();
            remRec = this.removeIndex(index - 1);
            this.add(p);
        } else {
            remRec = this.remove();
        }
        return remRec;
    }

    /**
     * Change the record from {@code curIndex} to {@code index}.
     *
     * @param curIndex
     *            the index of the record before changing
     * @param index
     *            the index of the record after changing
     */
    private void changeIndex(int curIndex, int index) {
        assert curIndex < this.length() : "Violate: curIndex < this.length()";
        assert curIndex >= 0 : "Violate: curIndex >= 0";
        assert index < this.length() : "Violate: index < this.length()";
        assert index >= 0 : "Violate: index >= 0";
        // Remove the record at curIndex
        Record p = this.removeIndex(curIndex);
        // Add the removed record to the new index
        this.addToIndex(index, p.amount, p.description);
    }

    /**
     * Test the secondary methods and the kernel method balance in
     * BudgetTrakerProof.
     *
     * @param args
     *            system input
     */
    public static void main(String[] args) {
        /*
         * Useful constants
         */
        // Floating point number: minus five point five
        final float breakfastCost = -5.5f;
        // Floating point number: minus ten point one
        final float lunchCost = -10.1f;
        // Floating point number: minus fifteen point two
        final float dinnerCost = -15.2f;

        /*
         * Open output stream and initialize the budget tracker with four
         * records
         */
        SimpleWriter out = new SimpleWriter1L();
        BudgetTrackerDemo btp = new BudgetTrackerDemo();
        btp.addToIndex(0, btp.roundingFactor, "Initial balance 02/17");
        btp.addToIndex(0, breakfastCost, "Breakfast 02/17");
        btp.addToIndex(0, lunchCost, "Lunch 02/17");
        btp.addToIndex(0, dinnerCost, "Dinner 02/17");
        out.print("Initial records are:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Add a record to the middle of the records
         */
        btp.addToIndex(1, breakfastCost, "Snack 02/17");
        out.print("\nAfter adding:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Remove a record from the middle of the records
         */
        btp.removeIndex(2);
        out.print("\nAfter removing:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Modify a record at the middle of the records
         */
        btp.modifyIndex(1, lunchCost, "Lunch 02/17");
        out.print("\nAfter modifying:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Change a record index to a larger one
         */
        btp.changeIndex(0, 2);
        out.print("\nAfter changing:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Change a record index to a smaller one
         */
        btp.changeIndex(2, 0);
        out.print("\nAfter changing:\n");
        btp.printNumOfRecords(btp.length(), 0, out);
        out.print("Current balance: " + btp.balance() + "\n");

        /*
         * Close the output stream
         */
        out.close();
    }
}
