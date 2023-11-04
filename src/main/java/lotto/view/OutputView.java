package lotto.view;

import static lotto.constants.LottoIOMessage.BUYING_QUANTITY_FORMAT;
import static lotto.constants.LottoIOMessage.GET_REWARDED_LOTTO_COUNT;
import static lotto.constants.LottoIOMessage.SAME_BONUS_NUM;
import static lotto.constants.LottoIOMessage.SAME_NUMBER_COUNT_FORMAT;
import static lotto.constants.LottoIOMessage.TOTAL_RETURN_FORMAT;
import static lotto.constants.LottoIOMessage.WINNING_MONEY_FORMAT;
import static lotto.constants.LottoIOMessage.WINNING_RESULT_MSG;

import camp.nextstep.edu.missionutils.Console;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import lotto.constants.LottoRanks;

public class OutputView {
    public void printQuantityAndAllNumbers(int quantity, List<List<Integer>> manyLotto) {
        System.out.printf(BUYING_QUANTITY_FORMAT, quantity);
        printAllLottoNumber(manyLotto);
    }
    private void printAllLottoNumber(List<List<Integer>> manyLotto) {
        for (List<Integer> oneLotto : manyLotto) {
            printOneLottoNumber(oneLotto);
        }
    }
    private void printOneLottoNumber(List<Integer> oneLotto) {
        System.out.println(convertLottoNumToString(oneLotto));
    }
    public String convertLottoNumToString(List<Integer> oneLotto) {
        StringBuilder lottoNum = new StringBuilder("[" + oneLotto.get(0));
        for (int i = 1; i < oneLotto.size(); i++) {
            lottoNum.append(", ");
            lottoNum.append(oneLotto.get(i));
        }
        lottoNum.append("]");
        return lottoNum.toString();
    }

    public void printLottoResult(Map<LottoRanks, Integer> result, double returnRate) {
        System.out.println(WINNING_RESULT_MSG);
        printWinningLottoCounts(result);
        printTotalReturnRate(returnRate);
        Console.close();
    }
    public void printWinningLottoCounts(Map<LottoRanks, Integer> enumMap) {
        for (LottoRanks key : enumMap.keySet()) {
            if (key == LottoRanks.NONE)
                continue;
            System.out.printf(SAME_NUMBER_COUNT_FORMAT, key.getSameNumber());
            if (key.isSameBonus()) {
                System.out.print(SAME_BONUS_NUM);
            }
            System.out.printf(WINNING_MONEY_FORMAT, convertWinningMoneyToString(key.getWinnings()));
            System.out.printf(GET_REWARDED_LOTTO_COUNT, enumMap.get(key));
        }
    }
    public String convertWinningMoneyToString(int winningMoney) {
        DecimalFormat temp = new DecimalFormat("###,###");
        return temp.format(winningMoney);
    }

    public void printTotalReturnRate(double returnRate) {
        System.out.printf(TOTAL_RETURN_FORMAT, returnRate);
    }
}
