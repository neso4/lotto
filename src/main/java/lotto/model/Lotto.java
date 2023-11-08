package lotto.model;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.util.Constants.*;

public class Lotto {
    public static final String ERROR_MESSAGE_DUPLICATE_NUMBERS = "[ERROR] 로또 번호가 중복되어서는 안됩니다.";
    public static final String ERROR_MESSAGE_NOT_SIX_NUMBERS = "[ERROR] 로또 번호는 6개로 이루어져야 합니다.";
    public static final String ERROR_MESSAGE_NUMBER_IN_RANGE = "[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSixNumbers(numbers);
        validateDuplicateNumbers(numbers);
        validateNumberInRange(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    public static List<Lotto> purchaseLottos(int purchaseCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            List<Integer> numbers =
                    pickUniqueNumbersInRange(LOTTERY_NUMBER_MIN, LOTTERY_NUMBER_MAX, WINNING_NUMBERS_SIZE);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    private static void validateNumberInRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < LOTTERY_NUMBER_MIN || number > LOTTERY_NUMBER_MAX) {
                throw new IllegalArgumentException(ERROR_MESSAGE_NUMBER_IN_RANGE);
            }
        }
    }

    private static void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATE_NUMBERS);
        }
    }

    private void validateSixNumbers(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_SIX_NUMBERS);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
