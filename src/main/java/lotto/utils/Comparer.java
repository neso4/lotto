package lotto.utils;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Winning;

public class Comparer {

    public static int compareLottoToWinning(Lotto lotto, Winning winning) {
        return Comparer.compareLottos(lotto, winning.getWinningLotto());
    }

    public static int compareLottos(Lotto lotto1, Lotto lotto2) {
        List<Integer> sameNumbers = new ArrayList<>(lotto1.getNumbers());
        sameNumbers.removeAll(lotto2.getNumbers());
        return sameNumbers.size();
    }

    public static boolean containsBonus(Lotto lotto, Winning winning) {
        return lotto.getNumbers().contains(winning.getBonusNumber());
    }

}
