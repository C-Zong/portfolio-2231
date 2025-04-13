package components.budgetTracker;

import components.budgetTracker.BudgetTrackerKernel.Transaction;
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
        extends Standard<BudgetTracker>, Iterable<Transaction> {
    /**
     * A constant used to round floating-point values to two decimal places.
     */
    float ROUNDING = 100.0f;

    /**
     * A record representing a transaction with an amount and a description.
     *
     * @param amount
     *            the amount of money of the record
     * @param description
     *            the description of the record
     */
    record Transaction(float amount, String description) {
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
    Transaction remove();

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
    void add(Transaction record);

    /**
     * Reports the current balance, rounded according to {@code ROUNDING}.
     *
     * @return the current balance
     * @ensures balance = [sum of all amounts in the tracker]
     */
    float balance();

    /**
     * Sets the name of the account.
     *
     * @param name
     *            the name of the account
     * @update this
     * @requires name != null
     * @ensures this.accountName = name
     */
    void setAccountName(String name);

    /**
     * Returns the name of the account.
     *
     * @return the name of the account
     * @ensures getAccountName = this.accountName
     */
    String getAccountName();
}
