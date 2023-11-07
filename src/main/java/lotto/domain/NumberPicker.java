package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberPicker {

    private List<Integer> winningNumbers = new ArrayList<>();


    // 중복되지 않는 6개의 숫자를 뽑는다.
    public List<Integer> stringToNumbers(String input) {
        ensureCommaDelimiter(input);
        String[] splitNumbers = input.split(",");
        checkDuplicated(splitNumbers);
        checkNumbersLength(splitNumbers);
        isNumber(splitNumbers);
        for (String number : splitNumbers) {
            winningNumbers.add(Integer.parseInt(number));
        }
        checkNumbersRange(winningNumbers);
        return winningNumbers;
    }

    public void ensureCommaDelimiter(String input) {
        if (input.contains(",") == false) {
            throw new IllegalArgumentException("[ERROR] 번호는 쉼표(,)를 기준으로 구분해주세요. (ex.1,2,3,4,5,6)");
        }
    }

    public void checkDuplicated(String[] numbers) {
        Set<String> selectedNumbers = new HashSet<>();
        for (String number : numbers) {
            selectedNumbers.add(number);
        }
        if (selectedNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않는 숫자 6개를 입력해주세요.");
        }
    }

    public void checkNumbersLength(String[] numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
    }

    public void isNumber(String[] numbers) {
        try {
            for (int i = 0; i < numbers.length; i++) {
                Integer.parseInt(numbers[i]);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자 이외의 값은 입력할 수 없습니다.");
        }

    }

    public void checkNumbersRange(List<Integer> numbers) {
        numbers.forEach((num) -> {
            checkLottoNumberRange(num);
        });
    }


    //  보너스 번호 1개를 뽑아 winningNumbers에 저장
    public int pickBonusNumber(String input) {
        checkIsBonusNumber(input);
        int bonusNumber = Integer.parseInt(input);
        winningNumbers.add(bonusNumber);
        checkIsDuplicate(winningNumbers);
        checkLottoNumberRange(bonusNumber);
        return bonusNumber;

    }

    public void checkIsDuplicate(List<Integer> winningNumbers) {
        Set<Integer> selectedNumbers = new HashSet<>(winningNumbers);
        if (selectedNumbers.size() == 6) {
            throw new IllegalArgumentException("[ERROR] 중복되는 값은 입력할 수 없습니다.");
        }
    }

    public void checkLottoNumberRange(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException("[ERROR] 1~45 사이의 값을 선택하세요.");
        }
    }

    public void checkIsBonusNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자 이외의 값은 입력할 수 없습니다.");
        }
    }




}
