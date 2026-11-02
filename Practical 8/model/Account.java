package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import java.util.Objects;

public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 0;

    public Account(String ownerName, long balance) {
        this.accountNumber = String.format("AC%04d", ++accountCounter);
        this.ownerName = ownerName;
        this.balance = Math.max(0, balance);
        this.active = true;
    }

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = Math.max(0, balance);
        this.active = true;
    }

    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be strictly positive! Provided: " + amount);
        }
        balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be strictly positive! Provided: " + amount);
        }
        if (amount > balance) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException("Insufficient balance in account " + accountNumber +
                    "! Balance: " + balance + ", Requested: " + amount, shortfall);
        }
        balance -= amount;
    }

    public void transfer(Account to, long amount) throws BankException {
        if (to == null) {
            throw new BankException("Transfer failed: Destination account cannot be null.");
        }

        System.out.println("[Transfer Initiated] Transferring Rs " + amount + " from " +
                this.accountNumber + " to " + to.getAccountNumber());

        try {
            this.withdraw(amount);
            to.deposit(amount);
            System.out.println("[Transfer Successful] Completed transfer of Rs " + amount);
        } catch (InsufficientFundsException | InvalidAmountException e) {
            System.out.println("[Transfer Error Caught in Account.transfer] " + e.getMessage());
            throw e;
        } finally {
            System.out.println("[Transfer Audit Log] Audit recorded for transaction between " +
                    this.accountNumber + " and " + to.getAccountNumber());
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | Balance: Rs " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
