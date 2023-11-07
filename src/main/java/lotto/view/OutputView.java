package lotto.view;

import lotto.domain.Rank;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.MessageList.*;

public class OutputView {
    public static void printPurchaseQuantityMessage(int purchaseQuantity) {
        System.out.println(purchaseQuantity + PURCHASE_QUANTITY_MESSAGE);
    }

    public static void printLottoNumbers(List<Integer> lottoNumbers) {
        System.out.println(lottoNumbers);
    }

    public static void printWinningStatistics(Map<Rank, Integer> prizeCount) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDE_LINE);

//        Iterator<Rank> iterator = prizeCount.keySet().iterator();
//
//        while (iterator.hasNext()) {
//            Rank key = iterator.next();
//            int value = (int) prizeCount.get(key);
//            System.out.println(key.resultMessage + value + NUMBER);
//        }
        System.out.println(Rank.FIFTH.resultMessage + prizeCount.get(Rank.FIFTH) + NUMBER);
        System.out.println(Rank.FOURTH.resultMessage + prizeCount.get(Rank.FOURTH) + NUMBER);
        System.out.println(Rank.THIRD.resultMessage + prizeCount.get(Rank.THIRD) + NUMBER);
        System.out.println(Rank.SECOND.resultMessage + prizeCount.get(Rank.SECOND) + NUMBER);
        System.out.println(Rank.FIRST.resultMessage + prizeCount.get(Rank.FIRST) + NUMBER);
    }

    public static void printTotalRateOfReturn(double rateOfReturn) {
        System.out.println(TOTAL_RATE_OF_RETURN_MESSAGE + String.format("%.1f", rateOfReturn) + FINISH_MESSAGE);
    }
}
