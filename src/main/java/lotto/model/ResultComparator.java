package lotto.model;

import static lotto.util.Constants.ERROR;
import static lotto.util.enums.LottoResult.*;

import java.util.Optional;
import lotto.util.exception.input.ConstructionErrorException;


public class ResultComparator {
    private static final int MATCH_5 = 5;

    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    private ResultComparator(){
        throw new ConstructionErrorException();
    }

    public ResultComparator(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Optional<String> result(Lotto lotto) {
        Integer match = resultCompare(lotto);
        if (hasMatchedBonus(lotto, match)) {
            return Optional.ofNullable(MATCH_5_BONUS.getDescription());
        }
        return fromMatchCount(match);
    }

    private Integer resultCompare(Lotto lotto) {
        return lotto.countMatchingNumbers(winningNumbers);
    }

    private boolean hasMatchedBonus(Lotto lotto, Integer matchCount) {
        return lotto.hasMatchedBonus(bonusNumber) && matchCount == MATCH_5;
    }
}
