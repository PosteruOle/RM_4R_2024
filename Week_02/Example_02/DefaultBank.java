package Example_02;

import java.util.Arrays;

public class DefaultBank implements IBank{
    private int[] accounts;

    DefaultBank(int numberOfAccounts, int startingBalance){
        this.accounts=new int[numberOfAccounts];
        Arrays.fill(this.accounts, startingBalance);
    }


    @Override
    public void transfer(int from, int to, int amount) {
        if(accounts[from]<amount){
            return;
        }

        accounts[from]-=amount;
        accounts[to]+=amount;

        System.out.printf("Transfer from %d to %d: %d\n", from, to, amount);
        System.out.println("Total balance in the bank: " + this.getTotalBalance());
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
