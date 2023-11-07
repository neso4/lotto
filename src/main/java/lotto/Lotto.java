package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        Validator.validateNumbersSize(numbers);
        Validator.validateIsDuplicationExists(numbers);
    }

    public LottoDTO getLottoDTO() {
        return LottoDTO.from(numbers);
    }

    public int countMatchingNumber(WinningNumber winningNumber) {
        return (int) numbers.stream()
                .filter(winningNumber::contains)
                .count();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return bonusNumber.isBonusNumberContainedInLottoNumbers(numbers);
    }
}