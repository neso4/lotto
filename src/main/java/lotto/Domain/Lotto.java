package lotto.Domain;

import java.util.ArrayList;
import java.util.List;
import lotto.Constant.Constant;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(String inputNumbers) {
        List<Integer> numbers = validate(inputNumbers);
        this.numbers = numbers;
    }

    private List<Integer> validate(String inputNumbers) {
        List<Integer> validateNumbers = new ArrayList<>();
        lottoSizeValidate(inputNumbers);
        return validateNumbers;
    }

    //입력값이 6개인지 확인
    private void lottoSizeValidate(String inputNumbers) {
        String[] splitNumbers = inputNumbers.split(",");
        if (splitNumbers.length != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(
                    Constant.ERROR_PREFIX + Constant.USER_NUMBER_PREFIX + Constant.ERROR_NOT_COUNT_MESSAGE);
        }
    }
}
