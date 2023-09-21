package baseball;

public class Service {

    private static Service instance;
    private boolean remaining;

    private Service() {
        remaining = true;
    }

    public static Service getInstance() {
        if (instance == null) {
            return new Service();
        }
        return instance;
    }

    public StringBuilder calculateResult(int inputNum) {
        StringBuilder sb = new StringBuilder();

        return sb;
    }

    public boolean isRemaining() {
        return remaining;
    }
}
