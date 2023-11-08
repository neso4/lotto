package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.view.ExceptionOutputView;

public class Lotto {
    private final List<Integer> numbers;
    public static final int PRICE_PER_LOTTO = 1000;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSixDigits(numbers);
        validateDuplicate(numbers);
        validateNumberInRange(numbers);
    }

    private void validateSixDigits(List<Integer> numbers) {
        if (numbers.size() != 6) {
            ExceptionOutputView.printLottoSixDigitError();
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = numbers.stream().collect(Collectors.toSet());
        if (uniqueNumbers.size() != numbers.size()) {
            ExceptionOutputView.printLottoDuplicateError();
            throw new IllegalArgumentException();
        }
    }

    private void validateNumberInRange(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > 45 || numbers.get(i) < 1) {
                ExceptionOutputView.printLottoRangeError();
                throw new IllegalArgumentException();
            }
        }
    }

    public Rank determineRank(Lotto targetLotto, int bonusNumber) {
        int matchingCount = countMatchingNumbers(targetLotto);
        Rank rankWithoutBonus = determineRankWithoutBonus(matchingCount);
        if (rankWithoutBonus == Rank.MATCH_5 && numbers.contains(bonusNumber)) {
            return Rank.MATCH_5_BONUS;
        }
        return rankWithoutBonus;
    }

    private int countMatchingNumbers(Lotto targetLotto) {
        int count = 0;
        for (int number : numbers) {
            if (targetLotto.contains(number)) {
                count++;
            }
        }
        return count;
    }

    private Rank determineRankWithoutBonus(int matchingCount) {
        if (matchingCount == 3) {
            return Rank.MATCH_3;
        }
        if (matchingCount == 4) {
            return Rank.MATCH_4;
        }
        if (matchingCount == 5) {
            return Rank.MATCH_5;
        }
        if (matchingCount == 6) {
            return Rank.MATCH_6;
        }
        return null;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        List<Integer> numbersForString = new ArrayList<>(numbers);
        Collections.sort(numbersForString);
        return numbersForString.toString();
    }
}

