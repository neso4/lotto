package lotto.application;

import camp.nextstep.edu.missionutils.Randoms;

import lotto.domain.Lotto;
import lotto.global.constant.enums.MatchResultType;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public abstract class LottoMachine {
    private static final int LOWER_LIMIT_NUMBER = 1;
    private static final int UPPER_LIMIT_NUMBER = 45;
    private static final int NUMBER_QUANTITY = 6;

    static Lotto issueAutomaticLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
                LOWER_LIMIT_NUMBER, UPPER_LIMIT_NUMBER, NUMBER_QUANTITY
        );
        List<Integer> lottoNumbers = new ArrayList<>(randomNumbers);
        lottoNumbers.sort(Integer::compareTo);
        return new Lotto(lottoNumbers);
    }

    static Lotto issueManualLotto(List<Integer> manualNumbers) {
        List<Integer> lottoNumbers = new ArrayList<>(manualNumbers);
        lottoNumbers.sort(Integer::compareTo);
        return new Lotto(lottoNumbers);
    }

    static MatchResultType match(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        if (isAllMatch(lotto, winningLotto)) {
            return MatchResultType.MATCH_SIX;
        }
        int matchedNumberQuantity = countMatchedNumber(lotto, winningLotto);
        boolean bonusIn = isBonusIn(lotto, bonusNumber);
        MatchResultType matchResultType = decideMatchType(matchedNumberQuantity);

        if (Objects.equals(matchResultType, MatchResultType.MATCH_FIVE) && bonusIn) {
            return MatchResultType.MATCH_FIVE_WITH_BONUS;
        }
        return matchResultType;
    }

    private static boolean isAllMatch(Lotto lotto, Lotto winningLotto) {
        return lotto.isSameWith(winningLotto);
    }

    private static boolean isBonusIn(Lotto lotto, int bonusNumber) {
        return lotto.isExist(bonusNumber);
    }

    private static int countMatchedNumber(Lotto lotto, Lotto winningLotto) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(number -> winningLotto.getLottoNumbers().contains(number))
                .count();
    }

    private static MatchResultType decideMatchType(int matchCount) {
        switch (matchCount) {
            case 3 -> {
                return MatchResultType.MATCH_THREE;
            }
            case 4 -> {
                return MatchResultType.MATCH_FOUR;
            }
            case 5 -> {
                return MatchResultType.MATCH_FIVE;
            }
        }
        return MatchResultType.BOOM;
    }

}
