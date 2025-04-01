package components.budgetTracker;

import components.simplewriter.SimpleWriter;

/**
 * {@code BudgetTrackerKernel} enhanced with secondary methods.
 */
public interface BudgetTracker extends BudgetTrackerKernel {

    /**
     * Print the last {@code num} records with an index.
     *
     * @param num
     *            the number of records need to be printed
     * @param out
     *            output stream
     * @updates out
     * @requires num <= |this| and out.isOpen()
     * @ensures out = #out * printNumOfRecords
     */
    void printNumOfRecords(int num, SimpleWriter out);

    /**
     * Add the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @param amount
     *            the amount of money of the record
     * @param description
     *            the description of the record
     * @updates this
     * @requires index <= |this| and index >= 0
     * @ensures <pre> this = front * <(amount, description)> * back and
     * #this = front * back and |back| = index </pre>
     */
    void addToIndex(int index, float amount, String description);

    /**
     * Modify the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @param amount
     *            the amount of money of the record
     * @param description
     *            the description of the record
     * @return the record before modifying
     * @updates this
     * @requires index < |this| and index >= 0
     * @ensures <pre> this = front * <(amount, description)> * back and
     * #this = front * <(#amount, #description)> * back and |back| = index and
     * modifyIndex = (#amount, #description) </pre>
     */
    Transaction modifyIndex(int index, float amount, String description);

    /**
     * Remove the record at the specified {@code index}.
     *
     * @param index
     *            the index of the record
     * @return the removed record
     * @updates this
     * @requires index < |this| and index >= 0
     * @ensures <pre> #this = front * <(record)> * back and
     * this = front * back and |back| = index </pre>
     */
    Transaction removeIndex(int index);

    /**
     * Change the record from {@code curIndex} to {@code index}.
     *
     * @param curIndex
     *            the index of the record before changing
     * @param index
     *            the index of the record after changing
     * @updates this
     * @requires index < |this| and index >= 0 and curIndex < |this| and
     *           curIndex >= 0
     * @ensures <pre>
     * if curIndex < index:
     * #this = front * middle * <(#record)> * back and |back| = curIndex and
     * this = front * <(record)> * middle * back and |middle * back| = index
     * else:
     * #this = front * <(#record)> * middle * back and |middle * back| = curIndex
     * this = front * middle * <(record)> * back and |back| = index and
     * </pre>
     */
    void changeIndex(int curIndex, int index);
}
