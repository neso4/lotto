package lotto.domain;

import lotto.Lotto;
import lotto.domain.collections.LotteryResultCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LuckyTicket {

    private final Lotto lotto;
    private final int bonusNumber;

    private LuckyTicket(
        final Lotto lotto,
        final int bonusNumber
    ) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static LuckyTicket of(
        final Lotto lotto,
        final int bonusNumber
    ) {
        return new LuckyTicket(lotto, bonusNumber);
    }

    public LotteryResultCollection matchWith(final User user) {
        List<Lotto> userTickets = user.getTickets();
        List<Integer> luckyNumbers = lotto.getNumbers();
        List<LotteryResult> results = new ArrayList<>();

        for (Lotto userTicket : userTickets) {
            List<Integer> numbers = userTicket.getNumbers();
            int numberCount = matchCountWithStream(luckyNumbers, numbers);
            int bonusCount = Collections.frequency(luckyNumbers, bonusNumber);
            results.add(makeLotteryResult(numberCount, bonusCount));
        }

        return LotteryResultCollection.of(results);
    }

    private LotteryResult makeLotteryResult(int matchCount, int bonusCount) {
        return LotteryResult.getWinnerMoneyOf(matchCount, bonusCount);
    }

    private int matchCountWithStream(
        final List<Integer> luckyNumbers,
        final List<Integer> userNumbers
    ) {
        Long count = userNumbers.stream()
            .filter(luckyNumbers::contains)
            .count();
        return count.intValue();
    }

}
