package Example_02;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TransferRunnable implements Runnable{
    private static final int MAX_TRANSFER_DELAY = 2;

    private IBank bank;
    private int from_account;
    private int maximal_amount;


    TransferRunnable(IBank bank, int from_account, int maximal_amount) {
        this.bank = bank;
        this.from_account = from_account;
        this.maximal_amount = maximal_amount;
    }


    @Override
    public void run() {
        // Random random=new Random();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        try {
            while (true) {
                // Transfer funds to a random account
                int to_acount = random.nextInt(this.bank.count());
                int amount = random.nextInt(this.maximal_amount);
                this.bank.transfer(this.from_account, to_acount, amount);
                Thread.sleep(random.nextLong(MAX_TRANSFER_DELAY));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
