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

    public int generateRandomNumber() {
        int randomNum = 0;

        while (String.valueOf(randomNum).contains("0")) {
            randomNum = (int) (Math.random() * 1000);
        }

        return randomNum;
    }



    public StringBuilder calculateResult(int inputNum) {
        int randomNum = generateRandomNumber();

        int ballCount = countBall(inputNum, randomNum);


        StringBuilder sb = new StringBuilder();
        return sb;
    }

    public int countBall(int inputNum, int randomNum) {

        int result = 0;

        for (int i = 0; i < 3; i++) {
            int by = (int) Math.pow(10, 2 - i);
            if ((int) inputNum / by == (int) randomNum / by) {
                result += 1;
            }
            inputNum %= by;
            randomNum %= by;
        }

        return result;
    }

    public boolean isRemaining() {
        return remaining;
    }
}
