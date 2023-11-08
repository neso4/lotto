package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int NUMBER_OF_LOTTO = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개가 필요합니다.");
        }

        for (Integer number : numbers) {
            if (Collections.frequency(numbers, number) > 1) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않아야 합니다.");
            }

            if (number < MIN_LOTTO_NUMBER || MAX_LOTTO_NUMBER < number) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자입니다.");
            }
        }
    }

    public static Lotto generateLottoNumbers() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
                MIN_LOTTO_NUMBER,
                MAX_LOTTO_NUMBER,
                NUMBER_OF_LOTTO
        );
        return new Lotto(randomNumbers);
    }

    public static List<Lotto> createLottos(int numberOfTickets) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lottos.add(generateLottoNumbers());
        }
        return lottos;
    }

    public Rank checkResult(Winning winning, Bonus bonus) {
        int matchCount = (int) numbers.stream()
                .filter(winning.getNumbers()::contains)
                .count();
        boolean matchBonus = numbers.contains(bonus.getBonusNumber());

        return Rank.getRank(matchCount, matchBonus);
    }

    public String toSortedString() {
        List<Integer> mutableNumbers = new ArrayList<>(numbers);
        Collections.sort(mutableNumbers);
        return mutableNumbers.toString();
    }
}
