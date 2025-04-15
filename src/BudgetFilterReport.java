
import java.util.Iterator;

import components.budgetTracker.BudgetTracker;
import components.budgetTracker.BudgetTrackerKernel.Transaction;

/**
 * Report the transactions in the {@code BudgetTracker} which contain the given
 * keyword in their description.
 *
 * @author Chenyang Zong
 */
public class BudgetFilterReport {
    /**
     * The {@code BudgetTracker} to be reported on.
     */
    private BudgetTracker budgetTracker;

    /**
     * Constructs a {@code BudgetFilterReport} with the given
     * {@code BudgetTracker}.
     *
     * @param budgetTracker
     *            the {@code BudgetTracker} to be reported on
     */
    public BudgetFilterReport(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    /**
     * Reports the transactions in the {@code BudgetTracker} which contain the
     * given keyword in their description.
     *
     * @param keyword
     *            the keyword to search for in the transaction descriptions
     * @requires this.budgetTracker != null this.budgetTracker.length() > 0
     * @ensures reportTransactionsWithKeyword = [the total amount of the
     *          transactions with the given keyword in their description]
     * @return the total amount of the transactions with the given keyword in
     *         their description
     */
    public float reportTransactionsWithKeyword(String keyword) {
        assert this.budgetTracker != null : "BudgetTracker is null";
        assert keyword != null : "Keyword is null";

        float totalAmount = 0;
        Iterator<Transaction> iterator = this.budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            if (transaction.description().contains(keyword)) {
                totalAmount += transaction.amount();
                System.out.println(transaction);
            }
        }
        return totalAmount;
    }
}
