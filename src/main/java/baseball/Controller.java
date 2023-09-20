package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            // 잘못된 입력은 IllegalArgumentException으로 날린다.
            throw new IllegalArgumentException(e);
        }
        Validator.validateThreeNum(input); // 문제가 있으면 예외가 날라가게 돼있다.
        int inputNum = Integer.parseInt(input);
        printResult(inputNum);
    }

    public void printResult(int number) {
        StringBuilder resultSB = service.calculateResult(number);
        System.out.println(new String(resultSB));
        inputIfContinue();
    }

    public void inputIfContinue() {

    }

    public void printIfContinue(int number) {

    }

}
