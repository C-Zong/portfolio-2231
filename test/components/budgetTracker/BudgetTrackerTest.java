package components.budgetTracker;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import components.budgetTracker.BudgetTrackerKernel.Transaction;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit test fixture for {@code BudgetTracker}.
 *
 * @author Chenyang Zong
 */
public abstract class BudgetTrackerTest {
    /*
     * Usful constants
     */

    /**
     * Delta for float comparison.
     */
    private final float delta = 0.01f;

    /**
     * Balance for testing.
     */
    private final float balance = 100.0f;

    /*
     * Amounts for testing.
     */

    /**
     * Amount1 for testing.
     */
    private final float amount1 = 10.0f;

    /**
     * Amount2 for testing.
     */
    private final float amount2 = 20.0f;

    /**
     * Amount3 for testing.
     */
    private final float amount3 = 30.0f;

    /**
     * Natural number 3.
     */
    private final int three = 3;

    /**
     * Invokes the {@code BudgetTracker} no-argument constructor for the
     * implementation under test and returns the result.
     *
     * @return the new budget tracker
     * @ensures constructorTest = ("Default Account", 0.0f, {})
     */
    protected abstract BudgetTracker noArgConstructor();

    /**
     * Invokes the {@code BudgetTracker} constructor with account name for the
     * implementation under test and returns the result.
     *
     * @param accountName
     *            the name of the account associated with this budget tracker
     * @return the new budget tracker
     * @ensures constructorTest = (accountName, 0.0f, {})
     */
    protected abstract BudgetTracker accountNameConstructor(String accountName);

    /**
     * Invokes the {@code BudgetTracker} constructor with account name and
     * balance for the implementation under test and returns the result.
     *
     * @param accountName
     *            the name of the account associated with this budget tracker
     * @param balance
     *            the initial balance of the budget tracker
     * @return the new budget tracker
     * @ensures constructorTest = (accountName, balance, {})
     */
    protected abstract BudgetTracker accountNameAndBalanceConstructor(
            String accountName, float balance);

    /*
     * Test constructors
     */

    /**
     * Test of no-argument constructor.
     */
    @Test
    public void noArgConstructorTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();

        /*
         * Evaluation
         */
        assertEquals("Default Account", budgetTracker.getAccountName());
        assertEquals(0.0f, budgetTracker.balance(), this.delta);
    }

    /**
     * Test of constructor with account name.
     */
    @Test
    public void accountNameConstructorTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameConstructor("Test Account");

        /*
         * Evaluation
         */
        assertEquals("Test Account", budgetTracker.getAccountName());
        assertEquals(0.0f, budgetTracker.balance(), this.delta);
    }

    /**
     * Test of constructor with account name and initial balance.
     */
    @Test
    public void accountNameAndBalanceConstructorTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);

        /*
         * Evaluation
         */
        assertEquals("Test Account", budgetTracker.getAccountName());
        assertEquals(this.balance, budgetTracker.balance(), this.delta);
    }

    /*
     * Test standard methods
     */

    /**
     * Test of newInstance method.
     */
    @Test
    public void newInstanceTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);

        /*
         * Call the method
         */
        BudgetTracker budgetTrackerCopy = budgetTracker.newInstance();

        /*
         * Evaluation
         */
        assertEquals("Default Account", budgetTrackerCopy.getAccountName());
        assertEquals(0.0f, budgetTrackerCopy.balance(), this.delta);
    }

    /**
     * Test of clear method.
     */
    @Test
    public void clearTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);

        /*
         * Call the method
         */
        budgetTracker.clear();

        /*
         * Evaluation
         */
        assertEquals("Default Account", budgetTracker.getAccountName());
        assertEquals(0.0f, budgetTracker.balance(), this.delta);
    }

    /**
     * Test of transferFrom method.
     */
    @Test
    public void transferFromTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);
        BudgetTracker budgetTrackerCopy = this.noArgConstructor();

        /*
         * Call the method
         */
        budgetTrackerCopy.transferFrom(budgetTracker);

        /*
         * Evaluation
         */
        assertEquals("Test Account", budgetTrackerCopy.getAccountName());
        assertEquals(this.balance, budgetTrackerCopy.balance(), this.delta);
        assertEquals("Default Account", budgetTracker.getAccountName());
        assertEquals(0.0f, budgetTracker.balance(), this.delta);
    }

    /*
     * Test kernel methods
     */

    /**
     * Test of length method.
     */
    @Test
    public void lengthTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Evaluation
         */
        assertEquals(this.three, budgetTracker.length());
    }

    /**
     * Test of remove method.
     */
    @Test
    public void removeTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        BudgetTracker.Transaction transaction = budgetTracker.remove();

        /*
         * Evaluation
         */
        assertEquals(this.amount3, transaction.amount(), this.delta);
        assertEquals("Test Transaction 3", transaction.description());
        assertEquals(this.three - 1, budgetTracker.length());
        assertEquals(this.amount1 + this.amount2, budgetTracker.balance(),
                this.delta);
    }

    /**
     * Test of add method with amount and description.
     */
    @Test
    public void addWithAmountAndDescriptionTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();

        /*
         * Call the method
         */
        budgetTracker.add(this.amount1, "Test Transaction 1");

        /*
         * Evaluation
         */
        assertEquals(this.amount1, budgetTracker.balance(), this.delta);
        assertEquals(1, budgetTracker.length());
    }

    /**
     * Test of add method with record.
     */
    @Test
    public void addWithRecordTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        BudgetTracker.Transaction transaction = new BudgetTracker.Transaction(
                this.amount2, "Test Transaction 2");

        /*
         * Call the method
         */
        budgetTracker.add(transaction);

        /*
         * Evaluation
         */
        assertEquals(this.amount2, budgetTracker.balance(), this.delta);
        assertEquals(1, budgetTracker.length());
    }

    /**
     * Test of balance method.
     */
    @Test
    public void balanceTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Evaluation
         */
        assertEquals(this.amount1 + this.amount2 + this.amount3,
                budgetTracker.balance(), this.delta);
    }

    /**
     * Test of setAccountName method.
     */
    @Test
    public void setAccountNameTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();

        /*
         * Call the method
         */
        budgetTracker.setAccountName("New Account Name");

        /*
         * Evaluation
         */
        assertEquals("New Account Name", budgetTracker.getAccountName());
    }

    /**
     * Test of getAccountName method.
     */
    @Test
    public void getAccountNameTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);

        /*
         * Evaluation
         */
        assertEquals("Test Account", budgetTracker.getAccountName());
    }

    /**
     * Test of iterator method.
     */
    @Test
    public void iteratorTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");
        float balance = 0.0f;

        /*
         * Call the method
         */
        Iterator<Transaction> iterator = budgetTracker.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            balance += transaction.amount();
        }

        /*
         * Evaluation
         */
        assertEquals(budgetTracker.balance(), balance, this.delta);
    }

    /*
     * Test for common methods
     */

    /**
     * Test of hashCode method.
     */
    @Test
    public void hashCodeTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker1 = this
                .accountNameConstructor("Test Account");
        BudgetTracker budgetTracker2 = this
                .accountNameConstructor("Test Account");

        /*
         * Call the method
         */
        int hashCode1 = budgetTracker1.hashCode();
        int hashCode2 = budgetTracker2.hashCode();

        /*
         * Evaluation
         */
        assertEquals(hashCode1, hashCode2);
    }

    /**
     * Test of equals method.
     */
    @Test
    public void equalsTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker1 = this
                .accountNameConstructor("Test Account");
        BudgetTracker budgetTracker2 = this
                .accountNameConstructor("Test Account");

        /*
         * Call the method
         */
        boolean equals = budgetTracker1.equals(budgetTracker2);

        /*
         * Evaluation
         */
        assertEquals(true, equals);
    }

    /**
     * Test of toString method.
     */
    @Test
    public void toStringTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this
                .accountNameAndBalanceConstructor("Test Account", this.balance);

        /*
         * Call the method
         */
        String str = budgetTracker.toString();

        /*
         * Evaluation
         */
        assertEquals("Test Account has 0 records.", str);
    }

    /*
     * Test of secondary methods
     */

    /**
     * Test of printNumOfRecords method.
     */
    @Test
    public void printNumOfRecordsTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");
        SimpleWriter1L out = new SimpleWriter1L();
        out.println("------------------------------------");
        out.println("Test for printNumOfRecords method:");

        /*
         * Call the method
         */
        budgetTracker.printNumOfRecords(2, new SimpleWriter1L());

        /*
         * Evaluation
         */
        assertEquals(this.three, budgetTracker.length());
        out.println("------------------------------------");
        out.close();
    }

    /**
     * Test of addToIndex method.
     */
    @Test
    public void addToIndexTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        budgetTracker.addToIndex(this.three, this.amount1,
                "Test Transaction 4");

        /*
         * Evaluation
         */
        assertEquals(this.three + 1, budgetTracker.length());
        assertEquals(this.amount1 + this.amount2 + this.amount3 + this.amount1,
                budgetTracker.balance(), this.delta);
        budgetTracker.remove();
        budgetTracker.remove();
        budgetTracker.remove();
        assertEquals("Test Transaction 4",
                budgetTracker.remove().description());

    }

    /**
     * Test of modifyIndex method.
     */
    @Test
    public void modifyIndexTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        Transaction transaction = budgetTracker.modifyIndex(2, this.amount2,
                "Test Transaction 4");

        /*
         * Evaluation
         */
        assertEquals(this.three, budgetTracker.length());
        assertEquals(this.amount2 + this.amount2 + this.amount3,
                budgetTracker.balance(), this.delta);
        assertEquals("Test Transaction 1", transaction.description());
        budgetTracker.remove();
        budgetTracker.remove();
        assertEquals("Test Transaction 4",
                budgetTracker.remove().description());
    }

    /**
     * Test of removeIndex method.
     */
    @Test
    public void removeIndexTest() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        Transaction transaction = budgetTracker.removeIndex(2);

        /*
         * Evaluation
         */
        assertEquals(this.three - 1, budgetTracker.length());
        assertEquals(this.amount2 + this.amount3, budgetTracker.balance(),
                this.delta);
        assertEquals("Test Transaction 1", transaction.description());
    }

    /**
     * Test of changeIndex method moves the record at smaller index to a larger
     * index.
     */
    @Test
    public void changeIndexTest1() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        budgetTracker.changeIndex(0, 2);

        /*
         * Evaluation
         */
        assertEquals(this.three, budgetTracker.length());
        assertEquals(this.amount1 + this.amount2 + this.amount3,
                budgetTracker.balance(), this.delta);
        assertEquals("Test Transaction 2",
                budgetTracker.remove().description());
        assertEquals("Test Transaction 1",
                budgetTracker.remove().description());
        assertEquals("Test Transaction 3",
                budgetTracker.remove().description());
    }

    /**
     * Test of changeIndex method moves the record at larger index to a smaller
     * index.
     */
    @Test
    public void changeIndexTest2() {
        /*
         * Setup variables
         */
        BudgetTracker budgetTracker = this.noArgConstructor();
        budgetTracker.add(this.amount1, "Test Transaction 1");
        budgetTracker.add(this.amount2, "Test Transaction 2");
        budgetTracker.add(this.amount3, "Test Transaction 3");

        /*
         * Call the method
         */
        budgetTracker.changeIndex(2, 0);

        /*
         * Evaluation
         */
        assertEquals(this.three, budgetTracker.length());
        assertEquals(this.amount1 + this.amount2 + this.amount3,
                budgetTracker.balance(), this.delta);
        assertEquals("Test Transaction 1",
                budgetTracker.remove().description());
        assertEquals("Test Transaction 3",
                budgetTracker.remove().description());
        assertEquals("Test Transaction 2",
                budgetTracker.remove().description());
    }
}
