package components.budgetTracker;

/**
 * Customized JUnit test fixture for {@code BudgetTracker1L}.
 *
 * @author Chenyang Zong
 */
public class BudgetTracker1LTest extends BudgetTrackerTest {
    @Override
    protected final BudgetTracker noArgConstructor() {
        return new BudgetTracker1L();
    }

    @Override
    protected final BudgetTracker1L accountNameConstructor(String accountName) {
        return new BudgetTracker1L(accountName);
    }

    @Override
    protected final BudgetTracker1L accountNameAndBalanceConstructor(
            String accountName, float balance) {
        return new BudgetTracker1L(accountName, balance);
    }
}
