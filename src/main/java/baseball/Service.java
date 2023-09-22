package baseball;

public class Service {

    private static Service instance;
    private boolean remaining = false;

    private Service() {
        remaining = true;
    }

    public static Service getInstance() {
        if (instance == null) {
            return new Service();
        }
        return instance;
    }

    public String generateRandomNumber() {
        int randomNum = 0;

        while (String.valueOf(randomNum).contains("0")) {
            randomNum = (int) (Math.random() * 1000);
        }

        return String.valueOf(randomNum);
    }



    public StringBuilder calculateResult(int inputNum) {

        String randomNumber = generateRandomNumber();

        StringBuilder sb = new StringBuilder();

        return sb;
    }

    public boolean isRemaining() {
        return remaining;
    }
}
