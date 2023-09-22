package baseball;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private static Service instance;
    //    private boolean remaining = true;
    private final ThreadLocal<Boolean> gamePlayingStatus = new ThreadLocal<>();
    private final ThreadLocal<Integer> randomNumberHolder = new ThreadLocal<>();

    private final static String BALL = "볼 ";
    private final static String STRIKE = "스트라이크 ";
    private final static String NOTHING = "낫싱";


    private Service() {
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
        randomNumberHolder.set(randomNum);

        return randomNum;
    }

    public StringBuilder calculateResult(int inputNum) {

        gamePlayingStatus.set(true);

        int randomNum = randomNumberHolder.get() != null ? randomNumberHolder.get() : generateRandomNumber();

        int ballCount = countBall(inputNum, randomNum);
        int strikeCount = countStrike(inputNum, randomNum);

        StringBuilder sb = new StringBuilder();

        if (ballCount == 0 && strikeCount == 0) {
            sb.append(NOTHING);
        } else {
            if (ballCount != 0) {
                sb.append(ballCount);
                sb.append(BALL);
            }

            if (strikeCount != 0) {
                sb.append(strikeCount);
                sb.append(STRIKE);
                if (strikeCount == 3) {
                    gamePlayingStatus.set(false);
                }
            }
        }
        System.out.println("정답 : " + randomNum);

        return sb;
    }

    public int countBall(int inputNum, int randomNum) {
        int result = 0;

        List<Integer> inputNumList = new ArrayList<>();
        List<Integer> randomNumList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int by = (int) Math.pow(10, (2 - i));
            inputNumList.add(inputNum / by);
            randomNumList.add(randomNum / by);
            inputNum %= by;
            randomNum %= by;
        }

        for (int i = 0; i < 3; i++) {
            if (randomNumList.contains(inputNumList.get(i))) {
                if (!(randomNumList.indexOf(inputNumList.get(i)) == i)) {
                    result++;
                }
            }
        }

        return result;
    }

    public int countStrike(int inputNum, int randomNum) {
        int result = 0;

        for (int i = 0; i < 3; i++) {
            int by = (int) Math.pow(10, 2 - i);
            if (inputNum / by == (int) randomNum / by) {
                result += 1;
            }
            inputNum %= by;
            randomNum %= by;
        }

        return result;
    }

    public boolean getGameStatus() {
        return gamePlayingStatus.get();
    }
}
