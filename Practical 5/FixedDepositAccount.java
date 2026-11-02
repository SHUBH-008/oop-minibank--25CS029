public class FixedDepositAccount extends Account {
    private int termMonths;
    private boolean matured;

    public FixedDepositAccount(String ownerName, long balance, int termMonths) {
        super(ownerName, balance);
        this.termMonths = termMonths;
        this.matured = false;
    }

    public FixedDepositAccount(String ownerName, long balance) {
        this(ownerName, balance, 12);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {

        return matured && (balance >= amount);
    }

    public void setMatured(boolean matured) {
        this.matured = matured;
    }

    public boolean isMatured() {
        return matured;
    }

    public int getTermMonths() {
        return termMonths;
    }

    @Override
    public String toString() {
        return super.toString() + " [FixedDeposit | " + termMonths + " months | Matured: " + matured + "]";
    }
}
