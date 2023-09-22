package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public static final String INITIATING_BASEBALL_GAME = "숫자 야구 게임을 시작합니다.";
    public static final String ENDING_BASEBALL_GAME = "게임 종료";
    private static Controller instance;
    private final Service service;

    private Controller() {
        service = Service.getInstance();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void inputUserNum() {

        System.out.println(INITIATING_BASEBALL_GAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            // 잘못된 입력은 IllegalArgumentException으로 날린다.
            throw new IllegalArgumentException(e);
        }
        Validator.validateThreeNum(input); // 문제가 있으면 예외가 날라가게 돼있다.
        printResult(Integer.parseInt(input));
    }

    public void printResult(int number) {
        StringBuilder resultSB = service.calculateResult(number);

        System.out.println(new String(resultSB));

        if (service.isRemaining()) {
            inputUserNum();
        } else {
            inputIfContinue();
        }
    }

    public void inputIfContinue() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            // 잘못된 입력은 IllegalArgumentException 으로 날린다.
            throw new IllegalArgumentException("입출력 에러입니다.", e);
        }
        Validator.validateContinue(input); // 문제가 있으면 예외가 날라가게 돼있다.
        int inputFlag = Integer.parseInt(input);
        printIfContinue(inputFlag);
    }

    public void printIfContinue(int number) {

        if (number == 1) {
            inputUserNum();
        }
    }


}
