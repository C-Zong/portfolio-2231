package components.budgetTracker;

import components.standard.Standard;

/**
 * Budget tracker kernel component with primary methods. (Note: by package-wide
 * convention, all references are non-null.)
 *
 * @mathmodel type BudgetTrackerKernel is modeled by string of {@code Record}
 *            (Record: (amount, description))
 * @initially {@code
 * ():
 *  ensures
 *   this = <>
 * }
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface BudgetTrackerKernel
        extends Standard<BudgetTracker>, Iterable<BudgetTracker> {
    /**
     * A constant used to round floating-point values to two decimal places.
     */
    float ROUNDING = 100.0f;

    /**
     * Inner class representing a single budget record. It stores the amount of
     * money and its description.
     */
    @SuppressWarnings("unused")
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
     * Return the total number of records in the tracker.
     *
     * @return the current size of the tracker
     * @ensures length = |this|
     */
    int length();

    /**
     * Remove and return the latest record in the tracker.
     *
     * @return the latest record in the tracker
     * @update this
     * @requires |this| > 0
     * @ensures #this = this * <remove>
     */
    Record remove();

    /**
     * Add a record to the tracker.
     *
     * @param amount
     *            the amount of money of the record
     * @param description
     *            the description of the record
     * @update this
     * @ensures this = #this * <(amount, description)>
     */
    void add(float amount, String description);

    /**
     * Add a record to the tracker.
     *
     * @param record
     *            the record to be added
     * @update this
     * @ensures this = #this * <record>
     */
    void add(Record record);

    /**
     * Reports the current balance, rounded according to {@code ROUNDING}.
     *
     * @return the current balance
     * @ensures balance = [sum of all amounts in the tracker]
     */
    float balance();
}
