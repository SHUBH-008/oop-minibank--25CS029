package service;

import model.*;
import static util.Validator.isValidAmount;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 06: Interfaces, Packaging & Lambdas ===\n");

        Account sa = new SavingsAccount("Riya Sharma", 50000, 2000);
        Account ca = new CurrentAccount("Apex Traders", 20000, 10000);
        Account fda = new FixedDepositAccount("Deepak Verma", 100000, 12);

        System.out.println("--- 1. Testing Interface Default Methods ---");
        System.out.printf("Savings Account yearly interest:  Rs %.2f%n", sa.yearlyInterest());
        System.out.printf("Savings Account monthly interest: Rs %.2f%n", sa.monthlyInterest());
        System.out.printf("Fixed Deposit yearly interest:   Rs %.2f%n", fda.yearlyInterest());

        System.out.println("\n--- 2. Testing Marker Interface (Premium) ---");
        if (fda instanceof Premium) {
            System.out.println(fda.getAccountNumber() + " is marked as a PREMIUM account tier.");
        }

        System.out.println("\n--- 3. Testing WithdrawRule via Anonymous Class ---");
        WithdrawRule anonymousRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {

                return amount <= 25000 && account.canWithdraw(amount);
            }
        };

        long testAmount1 = 30000;
        System.out.println("Testing withdrawal of Rs " + testAmount1 + " on " + sa.getAccountNumber());
        System.out.println("Anonymous Rule Allowed: " + anonymousRule.allow(sa, testAmount1));

        System.out.println("\n--- 4. Testing WithdrawRule via Lambda Expression ---");
        WithdrawRule lambdaRule = (account, amount) -> (amount <= 25000 && account.canWithdraw(amount));
        System.out.println("Lambda Rule Allowed: " + lambdaRule.allow(sa, testAmount1));

        long testAmount2 = 15000;
        System.out.println("Testing withdrawal of Rs " + testAmount2 + " on " + sa.getAccountNumber());
        System.out.println("Lambda Rule Allowed: " + lambdaRule.allow(sa, testAmount2));

        System.out.println("\n--- 5. Testing Static Import of Validator ---");
        System.out.println("isValidAmount(\"5000\"): " + isValidAmount("5000"));
        System.out.println("isValidAmount(\"-10\"):   " + isValidAmount("-10"));
    }
}
