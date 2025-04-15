import components.budgetTracker.BudgetTracker;
import components.budgetTracker.BudgetTracker1L;
import components.budgetTracker.BudgetTrackerKernel.Transaction;

/**
 * Test for cases of {@code BudgetTracker}.
 *
 * @author Chenyang Zong
 */
public final class BudgetTrackerCasesTest {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BudgetTrackerCasesTest() {
    }

    /**
     * Main method to run the test cases for {@code BudgetTracker}.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test case 1: BudgetMaxMinReport
        System.out.println("Budget Max Min Report:");

        /**
         * Income1 for testing.
         */
        final float income1 = 10.0f;

        /**
         * Income2 for testing.
         */
        final float income2 = 20.0f;

        /**
         * Income3 for testing.
         */
        final float income3 = 30.0f;

        /**
         * Expense1 for testing.
         */
        final float expense1 = -10.0f;

        /**
         * Expense2 for testing.
         */
        final float expense2 = -20.0f;

        /**
         * Expense3 for testing.
         */
        final float expense3 = -30.0f;

        /*
         * Set up the budget tracker and add transactions.
         */
        BudgetTracker budgetTracker = new BudgetTracker1L();
        BudgetMaxMinReport budgetMaxMinReport = new BudgetMaxMinReport(
                budgetTracker);
        budgetTracker.add(income1, "Income1");
        budgetTracker.add(income2, "Income2");
        budgetTracker.add(income3, "Income3");
        budgetTracker.add(expense1, "Expense1");
        budgetTracker.add(expense2, "Expense2");
        budgetTracker.add(expense3, "Expense3");

        /*
         * Test methods of BudgetMaxMinReport.
         */
        int maxIncomeIndex = budgetMaxMinReport.getMaxIncomeIndex();
        int minIncomeIndex = budgetMaxMinReport.getMinIncomeIndex();
        Transaction removed = budgetTracker.removeIndex(maxIncomeIndex);
        System.out.println("Max income: " + removed.description());
        budgetTracker.addToIndex(maxIncomeIndex, removed.amount(),
                removed.description());
        removed = budgetTracker.removeIndex(minIncomeIndex);
        System.out.println("Min income: " + removed.description());
        budgetTracker.addToIndex(minIncomeIndex, removed.amount(),
                removed.description());
        int maxExpenseIndex = budgetMaxMinReport.getMaxExpenseIndex();
        int minExpenseIndex = budgetMaxMinReport.getMinExpenseIndex();
        removed = budgetTracker.removeIndex(maxExpenseIndex);
        System.out.println("Max expense: " + removed.description());
        budgetTracker.addToIndex(maxExpenseIndex, removed.amount(),
                removed.description());
        removed = budgetTracker.removeIndex(minExpenseIndex);
        System.out.println("Min expense: " + removed.description());
        budgetTracker.addToIndex(minExpenseIndex, removed.amount(),
                removed.description());

        // Test case 2: BudgetFilterReport
        System.out.println("\nBudget Filter Report:");
        BudgetFilterReport budgetFilterReport = new BudgetFilterReport(
                budgetTracker);
        float totalIncome = budgetFilterReport
                .reportTransactionsWithKeyword("Income");
        System.out.println("Total income: " + totalIncome);
        float totalExpense = budgetFilterReport
                .reportTransactionsWithKeyword("Expense");
        System.out.println("Total expense: " + totalExpense);
    }
}
