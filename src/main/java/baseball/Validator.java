package baseball;

public class Validator {

    public static void validateThreeNum(String inputNum) {
        validateIfNull(inputNum);

        int inputInt = validateIntType(inputNum);

        if (inputInt <= 110 || inputInt > 999) {
            // 1~9 사이의 세 자리수이므로 최소 111부터 시작이다.
            throw new IllegalArgumentException("세자리 수로 입력해주세요(0은 포함하면 안 됩니다).");
        }

        if (inputNum.contains("0")) {
            throw new IllegalArgumentException("0이 포함되면 안 됩니다.");
        }
    }

    public static void validateContinue(String inputFlag) {
        validateIfNull(inputFlag);

        int inputInt = validateIntType(inputFlag);

        if (inputInt < 1 || inputInt > 2) {
            throw new IllegalArgumentException("1 또는 2로 입력해야 합니다.");
        }
    }

    /**
     * 입력한 값이 숫자인지 검증하는 메서드
     */
    private static int validateIntType(String inputNum) {
        int inputInt = 0;

        try {
            inputInt = Integer.parseInt(inputNum);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력한 값이 숫자여야합니다.", e);
        }
        return inputInt;
    }

    /**
     * 입력한 값이 null 이 아닌지 검증
     */
    private static void validateIfNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력한 값이 없습니다.");
        }
    }
}
