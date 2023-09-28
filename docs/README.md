# java-baseball

기능 요구사항

1. 컴퓨터는 3자의 랜덤숫자를 생성해야 한다.
2. 사용자로부터 세자리의 숫자를 입력받아야 한다.
    1. 잘못된 값을 입력하면 `IllegalArgumentException` 을 발생시킨 후 애플리케이션은 종료되어야 한다.
    2. 사용자가 3개의 숫자를 모두 맞히면 게임이 종료된다.
3. 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
    1. 게임을 새로 시작하려면 1, 종료하려면 2를 입력해야한다.

도메인

- 서비스
    - 랜덤 숫자 발전기
- 컨트롤러
    - 숫자 입력기 User Interface

객체 다이어그램

- Controller
    - `void initiatingGame()`
    - `void inputUserNum()`
        - → Validator.getIOValidatedInput()
        - → Validator.validateThreeNum(String inputNum)
        - → printResult(int number) call
    - `void printResult(int number)`
        - → Service.calculateResult(int inputNum) call
        - → inputIfContinue()
    - `void inputIfContinue()`
        - → Validator.getIOValidatedInput() 
        - → Validator.validateContinue(String inputFlag)
        - → printIfContinue(int number)
        - → isRemaining()
    - `void printIfContinue(int number)`
- Service
    - `StringBuilder generateRandomNumber()`
    - `int countBall(int inputNum, int randomNum)`
    - `int countStrike(int inputNum, int randomNum)`
    - `String calculateResult(int inputNum)`
        - → generateRandomNumber() call
        - → GamePlayingStatus.setStatus() call
        - → countBall(int inputNum, int randomNum) call
        - → countStrike(int inputNum, int randomNum) call
    - `boolean getGameStatus()`
        - → GamePlayingStatus.getStatus()
    - void destroyGameStatus()
        - → GamePlayingStatus.destroyStatus()
- Validator
    - `void validateThreeNum(String inputNum)`
    - `void validateContinue(String inputFlag)`
    - `String getIOValidatedInput()`
    - 공통으로 사용하는 내부 메서드 : `validateIntType(String inputNum),` `validateIfNull(String input)`
- RandomNumberHolder
  - void restoreRandomNumber(int randomNumber)
  - boolean ifHoldRandomNumber()
  - int getRandomNumber()
- GamePlayingStatus
  - void setStatus(boolean ifPlaying)
  - boolean getStatus()
  - void destroyStatus()