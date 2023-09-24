package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private static Service instance;
    private final ThreadLocal<Boolean> gamePlayingStatus = new ThreadLocal<>();
    private final ThreadLocal<Integer> randomNumberHolder = new ThreadLocal<>();

    private final static String BALL = "볼 ";
    private final static String STRIKE = "스트라이크 ";
    private final static String NOTHING = "낫싱";

    /**
     * 싱글톤 패턴 적용
     */
    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            return new Service();
        }
        return instance;
    }

    public int generateRandomNumber() {
        ArrayList<Integer> randomNum = new ArrayList<>();
        while (randomNum.size() < 3) {
            int randomDigit = Randoms.pickNumberInRange(1, 9);
            if (!randomNum.contains(randomDigit)) {
                randomNum.add(randomDigit);
            }
        }

        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            randomStr.append(randomNum.get(i));
        }

        int completeRandomNum = Integer.parseInt(new String(randomStr));

        randomNumberHolder.set(completeRandomNum);
        return completeRandomNum;
    }

    private int getRandomNumber() {

        int randomNum = 0;
        if (randomNumberHolder.get() != null) {
            randomNum = randomNumberHolder.get();
        } else {
            randomNum = generateRandomNumber();
        }
        return randomNum;
    }

    public StringBuilder calculateResult(int inputNum) {

        gamePlayingStatus.set(true);

        int randomNum = getRandomNumber();

        int ballCount = countBall(inputNum, randomNum);
        int strikeCount = countStrike(inputNum, randomNum);

        StringBuilder sb = new StringBuilder();

        if (ballCount != 0) {
            sb.append(ballCount);
            sb.append(BALL);
        }

        if (strikeCount != 0) {
            sb.append(strikeCount);
            sb.append(STRIKE);
            if (strikeCount == 3) {
                gamePlayingStatus.set(false);
                randomNumberHolder.remove(); // 매 게임이 끝날 때마다 랜덤함수는 종료시킨다.
            }
        }

        if (ballCount == 0 && strikeCount == 0) {
            sb.append(NOTHING);
        }
//        System.out.println("정답 : " + randomNum);

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
            if (randomNumList.contains(inputNumList.get(i))&& !(randomNumList.indexOf(inputNumList.get(i)) == i)) {
                result++;
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

    /**
     * 사용자가 게임을 종료하였으므로 gamePlayingStatus도 제거한다.
     */
    public void destroyGameStatus() {
        gamePlayingStatus.remove();
    }
}
