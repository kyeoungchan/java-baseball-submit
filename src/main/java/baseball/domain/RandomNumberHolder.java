package baseball.domain;

public class RandomNumberHolder {
    private static RandomNumberHolder instance;
    private static final ThreadLocal<Integer> holder = new ThreadLocal<>();

    /**
     * 싱글톤 패턴
     */
    private RandomNumberHolder() {}

    public static RandomNumberHolder getInstance() {
        if (instance == null) {
            instance = new RandomNumberHolder();
        }
        return instance;
    }

    public void restoreRandomNumber(int randomNumber) {
        holder.set(randomNumber);
    }

    public boolean ifHoldRandomNumber() {
        return holder.get() != null;
    }

    public int getRandomNumber() {
        return holder.get();
    }

    public void destroyRandomNumber() {
        holder.remove();
    }
}
