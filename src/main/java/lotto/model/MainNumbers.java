package lotto.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.common.Constant.LOTTO_NUMBERS_SIZE;
import static lotto.common.ExceptionMessage.MAIN_NUMBERS_DUPLICATE_ERROR;
import static lotto.common.ExceptionMessage.MAIN_NUMBERS_SIZE_ERROR;

import java.util.HashSet;
import java.util.List;

public class MainNumbers {
    private final List<MainNumber> mainNumbers;

    private MainNumbers(List<MainNumber> mainNumbers) {
        validate(mainNumbers);
        this.mainNumbers = mainNumbers;
    }

    public static MainNumbers from(List<String> numbers) {
        return numbers.stream()
                .map(MainNumber::from)
                .collect(collectingAndThen(toList(), MainNumbers::new));
    }

    private void validate(List<MainNumber> mainNumbers) {
        validateSize(mainNumbers);
        validateUnique(mainNumbers);
    }

    private void validateSize(List<MainNumber> mainNumbers) {
        if (hasOverSize(mainNumbers)) {
            throw new IllegalArgumentException(MAIN_NUMBERS_SIZE_ERROR);
        }
    }

    private boolean hasOverSize(List<MainNumber> mainNumbers) {
        return mainNumbers.size() != LOTTO_NUMBERS_SIZE;
    }

    private void validateUnique(List<MainNumber> mainNumbers) {
        if (hasDuplicate(mainNumbers)) {
            throw new IllegalArgumentException(MAIN_NUMBERS_DUPLICATE_ERROR);
        }
    }

    private boolean hasDuplicate(List<MainNumber> mainNumbers) {
        return mainNumbers.size() != new HashSet<>(mainNumbers).size();
    }
}
