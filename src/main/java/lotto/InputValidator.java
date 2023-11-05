package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {
    private static final int START_RANGE = 1;
    private static final int END_RANGE = 45;
    private static final int SIZE = 6;

    //당첨 번호 예외 체크
    public void checkWinningNumbers(List<Integer> numbers) {
        if (numbersSize(numbers)) throw new IllegalArgumentException("[ERROR] 숫자 6개만 입력 가능합니다.");
        else if (numbersDuplicate(numbers)) throw new IllegalArgumentException("[ERROR] 중복되지 않는 숫자만 입력 가능합니다.");
        for (int number : numbers) {
            if (!verifyNumberInRange(number)) throw new IllegalArgumentException("[ERROR] 1~45 범위 내 숫자만 입력 가능합니다.");
        }
    }

    //입력값 예외 체크
    public void checkInputValue(String inputValue) {
        if (checkForSpaces(inputValue)) throw new IllegalArgumentException("[ERROR] 입력값에 띄어쓰기가 포함되어 있습니다.");
        else if (checkForEmpty(inputValue)) throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        else if (!checkForNumericOnly(inputValue)) throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }

    //보너스 번호 예외 체크
    public void checkBonusNumber(int bonusNumber) {
        if (!verifyNumberInRange(bonusNumber)) throw new IllegalArgumentException("[ERROR] 1~45 범위 내 숫자만 입력 가능합니다.");
    }

    //구입 금액 예외 체크
    public void checkBuyingAmount(int buyingAmount) {
        if(!checkDivisibleBy1000(buyingAmount)) throw new IllegalArgumentException("[ERROR] 1000으로 나누어 떨어지지 않습니다.");
    }

    private boolean numbersSize(List<Integer> numbers) {
        return numbers.size() != SIZE;
    }

    //당첨 숫자 중복 체크
    private boolean numbersDuplicate(List<Integer> numbers) {
        Set<Integer> numbersCheck = new HashSet<>(numbers);
        return numbersCheck.size() != numbers.size();
    }

    //1~45 범위 내 숫자 검사
    private boolean verifyNumberInRange(int number) {
        if (START_RANGE <= number && number <= END_RANGE) {
            return true;
        }
        return false;
    }

    private boolean checkForSpaces(String inputValue) {
        return inputValue.contains(" ");
    }

    private boolean checkForEmpty(String inputValue) {
        return inputValue.isEmpty();
    }

    private boolean checkForNumericOnly(String inputValue) {
        String[] parts = inputValue.split(",");
        for (String part : parts) {
            if (!part.matches("\\d+")) return false;
        }
        return true;
    }

    private boolean checkDivisibleBy1000(int buyingAmount){
        return buyingAmount % 1000 == 0;
    }
}
