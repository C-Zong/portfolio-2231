import java.util.Iterator;

import components.budgetTracker.BudgetTracker;
import components.budgetTracker.BudgetTrackerKernel.Transaction;

/**
 * Report the transaction index of the maximum or minimum transaction amount in
 * the {@code BudegetTracker}.
 *
 * @author Chenyang Zong
 */
public class BudgetMaxMinReport {
    /**
     * The {@code BudgetTracker} to be reported on.
     */
    private BudgetTracker budgetTracker;

    /**
     * Constructs a {@code BudgetMaxMinReport} with the given
     * {@code BudgetTracker}.
     *
     * @param budgetTracker
     *            the {@code BudgetTracker} to be reported on
     */
    public BudgetMaxMinReport(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    /**
     * Returns the transaction index of the maximum income in the
     * {@code BudgetTracker}.
     *
     * @requires this.budgetTracker != null this.budgetTracker.length() > 0
     * @ensures getMaxIncomeIndex = [the transaction index of the maximum
     *          income]
     * @return the transaction index of the maximum income in the
     *         {@code BudgetTracker}
     */
    public int getMaxIncomeIndex() {
        assert this.budgetTracker != null : "BudgetTracker is null";
        assert this.budgetTracker.length() > 0 : "BudgetTracker is empty";
        float maxIncome = 0;
        int index = -1;
        int maxIndex = 0;
        Iterator<Transaction> iterator = this.budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            index++;
            if (transaction.amount() > maxIncome) {
                maxIncome = transaction.amount();
                maxIndex = index;
            }
        }
        return maxIndex;
    }

    /**
     * Returns the transaction index of the minimum income in the
     * {@code BudgetTracker}.
     *
     * @requires this.budgetTracker != null this.budgetTracker.length() > 0
     * @ensures getMinIncomeIndex = [the transaction index of the minimum
     *          income]
     * @return the transaction index of the minimum income in the
     *         {@code BudgetTracker}
     */
    public int getMinIncomeIndex() {
        assert this.budgetTracker != null : "BudgetTracker is null";
        assert this.budgetTracker.length() > 0 : "BudgetTracker is empty";
        float minIncome = Float.MAX_VALUE;
        int index = -1;
        int minIndex = 0;
        Iterator<Transaction> iterator = this.budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            index++;
            if (transaction.amount() < minIncome && transaction.amount() > 0) {
                minIncome = transaction.amount();
                minIndex = index;
            }
        }
        return minIndex;
    }

    /**
     * Returns the transaction index of the maximum expense in the
     * {@code BudgetTracker}.
     *
     * @requires this.budgetTracker != null this.budgetTracker.length() > 0
     * @ensures getMaxExpenseIndex = [the transaction index of the maximum
     *          expense]
     * @return the transaction index of the maximum expense in the
     *         {@code BudgetTracker}
     */
    public int getMaxExpenseIndex() {
        assert this.budgetTracker != null : "BudgetTracker is null";
        assert this.budgetTracker.length() > 0 : "BudgetTracker is empty";
        float maxExpense = 0;
        int index = -1;
        int maxIndex = 0;
        Iterator<Transaction> iterator = this.budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            index++;
            if (transaction.amount() < maxExpense) {
                maxExpense = transaction.amount();
                maxIndex = index;
            }
        }
        return maxIndex;
    }

    /**
     * Returns the transaction index of the minimum expense in the
     * {@code BudgetTracker}.
     *
     * @requires this.budgetTracker != null this.budgetTracker.length() > 0
     * @ensures getMinExpenseIndex = [the transaction index of the minimum
     *          expense]
     * @return the transaction index of the minimum expense in the
     *         {@code BudgetTracker}
     */
    public int getMinExpenseIndex() {
        assert this.budgetTracker != null : "BudgetTracker is null";
        assert this.budgetTracker.length() > 0 : "BudgetTracker is empty";
        float minExpense = Float.NEGATIVE_INFINITY;
        int index = -1;
        int minIndex = 0;
        Iterator<Transaction> iterator = this.budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            index++;
            if (transaction.amount() > minExpense && transaction.amount() < 0) {
                minExpense = transaction.amount();
                minIndex = index;
            }
        }
        return minIndex;
    }
}
