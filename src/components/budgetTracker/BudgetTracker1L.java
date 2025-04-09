package components.budgetTracker;

import java.util.Iterator;

import components.stack.Stack;
import components.stack.Stack2;

/**
 * {@code budgetTracker} represented as a {@link components.stack.Stack2
 * components.stack.Stack2} with implementations of primary methods.
 *
 * @convention <pre>
 * [$this.rep is not null]
 * [$this.accountName is not null]
 * </pre>
 * @correspondence <pre>
 * this = [value of $this.rep based on Stack]
 * </pre>
 */
public class BudgetTracker1L extends BudgetTrackerSecondary {
    /*
     * Private members
     */

    /**
     * The stack that represents the budget tracker.
     */
    private Stack<Transaction> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Stack2<>();
    }

    /**
     * The name of the account associated with this budget tracker.
     */
    private String accountName = "Default Account";

    /**
     * The balance of the budget tracker.
     */
    private float balance = 0.0f;

    /*
     * Constructors
     */

    /**
     * No-argument constructor.
     */
    public BudgetTracker1L() {
        this.createNewRep();
    }

    /**
     * Constructor with account name.
     *
     * @param accountName
     *            the name of the account associated with this budget tracker
     */
    public BudgetTracker1L(String accountName) {
        assert accountName != null : "Violation of: accountName is not null";
        this.accountName = accountName;
        this.createNewRep();
    }

    /**
     * Constructor with account name and balance.
     *
     * @param accountName
     *            the name of the account associated with this budget tracker
     * @param balance
     *            the initial balance of the budget tracker
     */
    public BudgetTracker1L(String accountName, float balance) {
        assert accountName != null : "Violation of: accountName is not null";
        this.accountName = accountName;
        this.balance = balance;
        this.createNewRep();
    }

    /*
     * Standard methods
     */

    @Override
    public final BudgetTracker newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
        this.accountName = "Default Account";
        this.balance = 0.0f;
    }

    @Override
    public final void transferFrom(BudgetTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof BudgetTracker1L : "Violation of: "
                + "source is of dynamic type BudgetTracker1L";
        BudgetTracker1L localSource = (BudgetTracker1L) source;
        this.rep = localSource.rep;
        this.accountName = localSource.accountName;
        this.balance = localSource.balance;
        localSource.createNewRep();
        localSource.accountName = "Default Account";
        localSource.balance = 0.0f;
    }

    /*
     * Kernel methods
     */

    @Override
    public final int length() {
        return this.rep.length();
    }

    @Override
    public final Transaction remove() {
        assert this.length() > 0 : "Violation of: |this| > 0";
        Transaction t = this.rep.pop();
        this.balance -= t.amount();
        return t;
    }

    @Override
    public final void add(float amount, String description) {
        Transaction t = new Transaction(amount, description);
        this.rep.push(t);
        this.balance += amount;
    }

    @Override
    public final void add(Transaction record) {
        this.rep.push(record);
        this.balance += record.amount();
    }

    @Override
    public final float balance() {
        return Math.round(this.balance * ROUNDING) / ROUNDING;
    }

    @Override
    public final void setAccountName(String name) {
        assert name != null : "Violation of: name is not null";
        this.accountName = name;
    }

    @Override
    public final String getAccountName() {
        return this.accountName;
    }

    @Override
    public final Iterator<Transaction> iterator() {
        return this.rep.iterator();
    }
}
