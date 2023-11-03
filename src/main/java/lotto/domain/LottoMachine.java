package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    private List<Integer> winningNumbers;
    private int bonusNumber;

    public LottoMachine(String numbers) {
        checkNumbers(numbers);
        winningNumbers = toListInteger(numbers);
    }

    private void checkNumbers(String value) {
        validateEmpty(value);
        validateNotNumber(value);
        validateBlack(value);
        List<Integer> numbers = toListInteger(value);
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private List<Integer> toListInteger(String value) {
        List<String> values = Arrays.asList(value.split(","));
        List<Integer> numbers = values.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        return numbers;
    }

    private void validateEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력 값을 넣어주세요.");
        }
    }

    private void validateNotNumber(String value) {
        String removeComma = value.replace(",","");
        try {
            Integer.parseInt(removeComma);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호를 구분하는 쉼표(,)와 숫자만 입력 가능합니다.");
        }
    }

    private void validateBlack(String value) {
        if (value.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 공백 없이 입력해주세요.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개 여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않아야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().filter(n -> (n >= 1) && (n <= 45)).count() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
