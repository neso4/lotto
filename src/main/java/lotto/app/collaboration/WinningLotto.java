package lotto.app.collaboration;

import static java.util.stream.Collectors.groupingBy;
import static lotto.app.collaboration.enums.WinningLottoMessage.EXCEPTION_DUPLICATED;

import java.util.List;
import lotto.app.collaboration.dto.PlayerLotto;
import lotto.app.collaboration.enums.Prize;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(final List<Integer> winningNumbers, final int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> winningNumbers, int bonusNumber) {
        occurExceptionIfDuplicatedBoth(winningNumbers, bonusNumber);
    }

    private static void occurExceptionIfDuplicatedBoth(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(EXCEPTION_DUPLICATED.get());
        }
    }

    public PrizeLottos matchNumbers(final List<PlayerLotto> buyLottos) {
        return new PrizeLottos(buyLottos.stream()
                .collect(groupingBy(lotto ->
                        Prize.matchPrize(matchNumbers(lotto), matchBonusNumber(lotto)))));
    }

    private int matchNumbers(PlayerLotto lotto) {
        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean matchBonusNumber(PlayerLotto lotto) {
        return lotto.stream().anyMatch(number -> bonusNumber == number);
    }

}
