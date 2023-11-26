package mini_project;

import java.util.ArrayList;
import java.util.List;

interface Account {
    void deposit(double amt);
    void withdraw(double amt);
    double getBalance();
}

// Class SavingsAccount
class SavingsAccount implements Account {
    private double balance;
    private double interestRate;

    public SavingsAccount(double initialDeposit, double interestRate) {
        this.balance = initialDeposit;
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amt) {
        balance += amt;
        System.out.println("Deposited $" + amt + " into Savings Account. Current Balance: $" + balance);
    }

    @Override
    public void withdraw(double amt) {
        if (balance >= amt) {
            balance -= amt;
            System.out.println("Withdrawn $" + amt + " from Savings Account. Current Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void applyInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest);
        System.out.println("Interest applied. Current Balance: $" + balance);
    }
}

// Class CurrentAccount
class CurrentAccount implements Account {
    private double balance;
    private double overdraftLimit;

    public CurrentAccount(double initialDeposit, double overdraftLimit) {
        this.balance = initialDeposit;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amt) {
        balance += amt;
        System.out.println("Deposited $" + amt + " into Current Account. Current Balance: $" + balance);
    }

    @Override
    public void withdraw(double amt) {
        if (balance + overdraftLimit >= amt) {
            balance -= amt;
            System.out.println("Withdrawn $" + amt + " from Current Account. Current Balance: $" + balance);
        } else {
            System.out.println("Withdrawal failed. Exceeds overdraft limit.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        System.out.println("Overdraft limit set to $" + overdraftLimit);
    }
}

// Class Bank
class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added to the bank.");
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        System.out.println("Account removed from the bank.");
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    public void printAccountBalances() {
        System.out.println("Account Balances:");
        for (Account account : accounts) {
            System.out.println("Balance of account: $" + account.getBalance());
        }
    }
}

public class Main_Bank1 {
    public static void main(String[] args) {
        Bank bank = new Bank();

        SavingsAccount savingsAccount = new SavingsAccount(1000.0, 1.25);
        System.out.println("Savings Account:\nInitial Deposit: $1000.00\nInterest rate: 1.25%");
        
        CurrentAccount currentAccount = new CurrentAccount(5000.0, 1000.0);
        System.out.println("\nCurrent Account:\nInitial Deposit: $5000.00\nOverdraft Limit: $1000.00");

        bank.addAccount(savingsAccount);
        bank.addAccount(currentAccount);

        System.out.println("\nNow deposit $100 to Savings Account.");
        bank.deposit(savingsAccount, 1000.0);

        System.out.println("Now deposit $500 to Current Account.");
        bank.deposit(currentAccount, 5000.0);

        System.out.println("Withdraw $150 from Savings Account.\n");
        bank.withdraw(savingsAccount, 150.0);

        System.out.println("\nSavings A/c and Current A/c.:");
        bank.printAccountBalances();

        System.out.println("\nApplying interest on Savings A/c for 1 year:");
        savingsAccount.applyInterest();

        System.out.println("Savings A/c and Current A/c.:");
        bank.printAccountBalances();
    }
}
