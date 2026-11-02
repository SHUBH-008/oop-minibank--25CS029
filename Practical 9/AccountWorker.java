public class AccountWorker implements Runnable {
    private final Account account;
    private final int times;
    private final long amount;
    private final boolean useSynchronization;

    public AccountWorker(Account account, int times, long amount) {
        this(account, times, amount, true);
    }

    public AccountWorker(Account account, int times, long amount, boolean useSynchronization) {
        this.account = account;
        this.times = times;
        this.amount = amount;
        this.useSynchronization = useSynchronization;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            if (useSynchronization) {
                account.deposit(amount);
            } else {
                account.unsyncDeposit(amount);
            }
        }
    }
}
