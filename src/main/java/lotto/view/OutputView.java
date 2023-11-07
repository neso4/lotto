package lotto.view;

import static lotto.enums.OutputMessages.*;
import static lotto.enums.LottoRank.*;

import java.util.HashMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningDetails;

public class OutputView {

    public static void purchaseLotto(int lottoCount){
        System.out.printf(PURCHASE_LOTTO.getMessage(),lottoCount);
    }

    public static void showEachLotto(List<Lotto> lottos) {
        for(Lotto lotto : lottos){
            System.out.println(lotto.getNumbers());
        }
    }

    public static void showWinningDetails(WinningDetails winningDetails){
        HashMap<Integer,Integer> rank= winningDetails.getRank();

        System.out.println(WINNING_STATISTICS.getMessage());
        System.out.println(DIVIDED_LINE.getMessage());
        System.out.printf(MATCH_NUMBERS.getMessage(), FIFTH_RANK.getMatchNumbers(), FIFTH_RANK.getMoneyToString(), rank.get(FIFTH_RANK.getRank()));
        System.out.printf(MATCH_NUMBERS.getMessage(), FOURTH_RANK.getMatchNumbers(), FOURTH_RANK.getMoneyToString(), rank.get(FOURTH_RANK.getRank()));
        System.out.printf(MATCH_NUMBERS.getMessage(), THIRD_RANK.getMatchNumbers(), THIRD_RANK.getMoneyToString(), rank.get(THIRD_RANK.getRank()));
        System.out.printf(MATCH_FIVE_NUMBERS_BONUS_NUMBERS.getMessage(), SECOND_RANK.getMatchNumbers(), SECOND_RANK.getMoneyToString(), rank.get(SECOND_RANK.getRank()));
        System.out.printf(MATCH_NUMBERS.getMessage(), FIRST_RANK.getMatchNumbers(), FIRST_RANK.getMoneyToString(),rank.get(FIRST_RANK.getRank()));
    }

    public static void earningRate(float earningRate){
        System.out.printf(EARNING_RATE.getMessage(), earningRate);
    }
}
