package baseball.domain;

public class StatusHolder {

    private static StatusHolder instance;
    private static final ThreadLocal<Boolean> gamePlayingStatus = new ThreadLocal<>();
    private static final ThreadLocal<Integer> randomNumberHolder = new ThreadLocal<>();

    /**
     * 싱글톤 패턴 적용
     */
    private StatusHolder() {
    }

    public static StatusHolder getInstance() {
        if (instance == null) {
            instance = new StatusHolder();
        }
        return instance;
    }

    public void setGamePlayingStatus(boolean ifPlaying) {
        gamePlayingStatus.set(ifPlaying);
    }

    public boolean getGameStatus() {
        return gamePlayingStatus.get();
    }

    public void destroyGameStatus() {
        gamePlayingStatus.remove();
    }

    public void restoreRandomNumber(int randomNumber) {
        randomNumberHolder.set(randomNumber);
    }

    public boolean ifHoldRandomNumber() {
        return randomNumberHolder.get() != null;
    }

    public int getRandomNumber() {
        return randomNumberHolder.get();
    }

    public void destroyRandomNumber() {
        randomNumberHolder.remove();
    }

}
