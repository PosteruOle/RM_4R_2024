package Example_02;

public interface IBank {
    void transfer(int from, int to, int amount);
    int getTotalBalance();
    int count();
}
