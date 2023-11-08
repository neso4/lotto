package lotto.model;

import static lotto.model.Bonus.BONUS_EXCLUDE;
import static lotto.model.Bonus.BONUS_INCLUDE;
import static lotto.exception.ExceptionMessage.BONUS_NUMBER_ALREADY_USE;

import lotto.model.dto.PrizeResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoCompany {

    private static final int MINIMUM_MATCH_SIZE = 3;
    private static final int MAXIMUM_MATCH_SIZE = 6;

    private final GoalNumbers goalNumbers;
    private final LottoNumber bonusNumber;

    private LottoCompany(final GoalNumbers goalNumbers, final LottoNumber bonusNumber) {
        this.goalNumbers = goalNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoCompany of(final GoalNumbers goalNumbers, final LottoNumber bonusNumber) {
        validateIsBonusNumberDuplicate(goalNumbers, bonusNumber);
        return new LottoCompany(goalNumbers, bonusNumber);
    }

    private static void validateIsBonusNumberDuplicate(final GoalNumbers goalNumbers, final LottoNumber bonusNumber) {
        List<LottoNumber> goals = goalNumbers.getNumbers();

        if (goals.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_USE.getMessage());
        }
    }

    public List<PrizeResult> evaluateLottos(final List<Lotto> lottos) {
        List<PrizeResult> results = new ArrayList<>();
        results.addAll(evaluateLottosWithBonusOption(lottos, BONUS_INCLUDE.getValue()));
        results.addAll(evaluateLottosWithBonusOption(lottos, BONUS_EXCLUDE.getValue()));

        Collections.sort(results);

        return results;
    }

    private List<PrizeResult> evaluateLottosWithBonusOption(final List<Lotto> lottos, final boolean hasBonus) {
        List<PrizeResult> results = new ArrayList<>();
        for (int match = MINIMUM_MATCH_SIZE; match <= MAXIMUM_MATCH_SIZE; match++) {
            List<Lotto> matchLottos = collectLottosWithOption(lottos, match, hasBonus);
            Prize.findByMatchAndBonus(match, hasBonus)
                    .ifPresent(prize -> savePrizeResult(prize, matchLottos.size(), results));
        }
        return results;
    }

    private List<Lotto> collectLottosWithOption(final List<Lotto> lottos, final int matchSize, final boolean hasBonus) {
        return lottos.stream()
                .filter(lotto -> lotto.isContainsNumbersWithSize(goalNumbers.getNumbers(), matchSize))
                .filter(lotto -> lotto.isContainsNumber(bonusNumber) == hasBonus)
                .toList();
    }

    private static void savePrizeResult(final Prize prize, final int matchSize, final List<PrizeResult> results) {
        PrizeResult prizeResult = PrizeResult.of(prize.getCondition(), prize.getMoney(), matchSize);
        results.add(prizeResult);
    }
}
