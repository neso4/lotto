package lotto.utils;

import java.util.List;
import lotto.domain.LottoRank;

public class LottoCaclulator {

    public static double calculateRateOfReturn(final List<Integer> winningCount, final int money) {
        double sum = 0;
        int i = 0;
        for (LottoRank match : LottoRank.getMembers()) {
            int repeat = winningCount.get(i);
            sum += (match.getMoney() * repeat);
            i++;
        }
        return sum / money;
    }
}
