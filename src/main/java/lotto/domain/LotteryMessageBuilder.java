package lotto.domain;

import lotto.domain.lottery.Lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.domain.Computer.rateOfProfit;
import static lotto.domain.Computer.rewards;

public class LotteryMessageBuilder {

    private final static String JOIN_SYMBOL = " - ";
    private final static String MATCH_MESSAGE = "개 일치 ";
    private final static String COUNT_UNIT = "개";
    private final static String PROFIT_MESSAGE_START = "총 수익률은 ";
    private final static String PROFIT_MESSAGE_END = "%입니다.";
    private final static String LOTTO_NUMBER_PREFIX = "[";
    private final static String LOTTO_NUMBER_SUFFIX = "]";
    private final static String LOTTO_NUMBER_DELIMITER = ", ";

    public String returnLottoList(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto :
                lottos) {
            sb.append(showLottoNumbers(lotto)).append("\n");
        }

        return sb.toString();
    }

    private String showLottoNumbers(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        String lottoNumbers = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER, LOTTO_NUMBER_PREFIX, LOTTO_NUMBER_SUFFIX));

        return lottoNumbers;
    }
    public String returnWinningLottoList(Map<Integer, Integer> winningStats) {
        StringBuilder sb = new StringBuilder();
        for (int result : winningStats.keySet()) {
            sb.append(returnEachResult(result, winningStats.get(result)));
        }
        sb.append(returnRateOfProfit());
        return sb.toString();
    }

    private String returnRateOfProfit() {
        StringBuilder sb = new StringBuilder();
        sb.append(PROFIT_MESSAGE_START).append(rateOfProfit).append(PROFIT_MESSAGE_END);
        return sb.toString();
    }

    private String returnEachResult(int result, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(result).append(MATCH_MESSAGE)
                .append(returnEachResultAmount(result))
                .append(JOIN_SYMBOL)
                .append(count)
                .append(COUNT_UNIT)
                .append("\n");

        return sb.toString();
    }

    private String returnEachResultAmount(int result) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(rewards[result - 1]);
    }

}
