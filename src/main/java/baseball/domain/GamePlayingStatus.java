package baseball.domain;

public class GamePlayingStatus {
    private static GamePlayingStatus instance;
    private static final ThreadLocal<Boolean> statusHolder = new ThreadLocal<>();

    /**
     * 싱글톤 패턴
     */
    private GamePlayingStatus() {}

    public static GamePlayingStatus getInstance() {
        if (instance == null) {
            instance = new GamePlayingStatus();
        }
        return instance;
    }

    public void setStatus(boolean ifPlaying) {
        statusHolder.set(ifPlaying);
    }

    public boolean getStatus() {
        return statusHolder.get();
    }

    public void destroyStatus() {
        statusHolder.remove();
    }
}
