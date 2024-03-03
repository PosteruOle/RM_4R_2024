package Example_02;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedBank implements IBank{
    private int[] accounts;
    Lock lock;
    Condition insiffitiantFunds;

    LockedBank(int numberOfAccounts, int startingBalance){
        this.accounts=new int[numberOfAccounts];
        Arrays.fill(this.accounts, startingBalance);
        this.lock=new ReentrantLock();
        this.insiffitiantFunds=this.lock.newCondition();
    }

    @Override
    public void transfer(int from, int to, int amount) {

        this.lock.lock();

        try {
            while (this.accounts[from] < amount) {
                try {
                    this.insiffitiantFunds.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            this.accounts[from] -= amount;
            this.accounts[to] += amount;

            System.out.printf("Transfer from %d to %d: %d\n", from, to, amount);
            System.out.println("Total balance in the bank: " + this.getTotalBalance());

            this.insiffitiantFunds.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public int getTotalBalance() {
        return Arrays.stream(this.accounts).sum();
    }

    @Override
    public int count() {
        return this.accounts.length;
    }
}
