package Example_02;

import java.util.Arrays;

public class SynchronizedBank implements IBank{
    private final int[] accounts;
    SynchronizedBank(int numberOfAccounts, int startingBalance){
        this.accounts=new int[numberOfAccounts];
        Arrays.fill(this.accounts, startingBalance);
    }

    @Override
    public synchronized void transfer(int from, int to, int amount) {
        while(this.accounts[from]<amount){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.accounts[from]-=amount;
        this.accounts[to]+=amount;

        System.out.printf("Transfer from %d to %d: %d\n", from, to, amount);
        System.out.println("Total balance in the bank: " + this.getTotalBalance());

        this.notifyAll();
    }

    public void transferAlternativeWay(int from, int to, int amount) {

        synchronized (this){
            while(this.accounts[from]<amount){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            this.accounts[from] -= amount;
            this.accounts[to] += amount;

            this.notifyAll();
        }

        synchronized (System.out) {
            System.out.printf("Transfer from %d to %d: %d\n", from, to, amount);
            System.out.println("Total balance in the bank: " + this.getTotalBalance());
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
