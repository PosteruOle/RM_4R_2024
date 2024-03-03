package Example_02;

public class BankTest {
    public static int ACCOUNTS=5;
    public static int STARTING_BALANCE=1000;
    public static void main(String[] args) {
        // IBank bank=new DefaultBank(ACCOUNTS, STARTING_BALANCE);
        // IBank bank=new LockedBank(ACCOUNTS, STARTING_BALANCE);
        IBank bank=new SynchronizedBank(ACCOUNTS, STARTING_BALANCE);

        for(int i=0;i<ACCOUNTS;i++){
            Thread thread=new Thread(new TransferRunnable(bank, i, 10));
            thread.start();
        }
    }
}
