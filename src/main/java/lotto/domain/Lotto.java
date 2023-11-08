package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.constant.NumberConstant.*;
import static lotto.utils.Validator.validateLotto;

public class Lotto {
    private final List<Integer> numbers;


    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }


    public List<Integer> getNumbers() {
        return numbers;
    }


    public static Lotto create(List<Integer> numbers) {
        try {
            validateLotto(numbers);
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }


    public static List<Lotto> createRandomLottos(int ticketNum) {
        return IntStream.range(0, ticketNum)
                .mapToObj(i -> generateRandomLotto())
                .collect(Collectors.toList());
    }

    private static Lotto generateRandomLotto() {
        List<Integer> randomLotto = pickUniqueNumbersInRange(LOTTO_NUMBER_START, LOTTO_NUMBER_END, LOTTO_SIZE);

        try {
            randomLotto.sort(null);
        } catch (UnsupportedOperationException e) {
            randomLotto = new ArrayList<>(randomLotto);
            randomLotto.sort(null);
        }

        return create(randomLotto);
    }


    public static MatchResult match(Lotto randomLotto, Lotto winningLotto, int bonusNum) {
        List<Integer> randomNumbers = randomLotto.getNumbers();
        List<Integer> winningNumbers = winningLotto.getNumbers();

        int matchCount = (int) randomNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        if (isBonusMatch(matchCount, randomNumbers, bonusNum))
            return MatchResult.BONUS;

        return MatchResult.getFromCount(matchCount);
    }

    private static boolean isBonusMatch(int matchCount, List<Integer> randomNumbers, int bonusNum) {
        return matchCount == BONUS_MATCH_COUNT && randomNumbers.contains(bonusNum);
    }
}
