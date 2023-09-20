package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    void inputUserNum() {

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

    void printResult(int number) {

    }

    void inputIfContinue() {

    }

    void printIfContinue(int number) {

    }

}
