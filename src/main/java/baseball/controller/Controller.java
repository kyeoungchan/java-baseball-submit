package baseball.controller;

import baseball.service.Service;
import baseball.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

import static baseball.controller.ControllerConstants.*;
import static baseball.validator.Validator.getIOValidatedInput;

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

    public void initiatingGame() {
        System.out.println(INITIATING_GAME_ANNOUNCEMENT);
        inputUserNum();
    }

    public void inputUserNum() {
        String input = getIOValidatedInput(); // IOException 검증 메서드
        Validator.validateThreeNum(input); // 문제가 있으면 예외가 날라가게 돼있다.
        printResult(Integer.parseInt(input));
    }

    public void printResult(int number) {
        StringBuilder resultSB = service.calculateResult(number);

        System.out.println(new String(resultSB));

        if (service.getGameStatus()) {
            inputUserNum();
        } else {
            System.out.println(ENDING_GAME_ANNOUNCEMENT);
            System.out.println(ASK_IF_CONTINUE);
            inputIfContinue();
        }
    }

    public void inputIfContinue() {
        String input = getIOValidatedInput(); // IOException 검증 메서드
        Validator.validateContinue(input); // 문제가 있으면 예외가 날라가게 돼있다.
        printIfContinue(Integer.parseInt(input));
    }

    public void printIfContinue(int number) {

        if (number == CONTINUE_ANSWER) {
            initiatingGame();
        } else if (number == EXIT_ANSWER) {
            service.destroyGameStatus();
        }
    }
}
