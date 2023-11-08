package lotto.view;

import static lotto.util.ExceptionMessage.ERROR_PREFIX;
import static lotto.util.Introduction.AMOUNT_OF_LOTTO_PURCHASE;
import static lotto.util.Introduction.BONUS_PRIZE_LIST;
import static lotto.util.Introduction.DELIMITER;
import static lotto.util.Introduction.LOTTO_PRINT_END;
import static lotto.util.Introduction.LOTTO_PRINT_START;
import static lotto.util.Introduction.NOT_BONUS_PRIZE_LIST;
import static lotto.util.Introduction.RATE_OF_RETURN;
import static lotto.util.Introduction.SEPARATOR;
import static lotto.util.Introduction.WINNING_STATISTICS;
import static lotto.util.PrizeDetails.FIFTH_PRIZE;
import static lotto.util.PrizeDetails.FIRST_PRIZE;
import static lotto.util.PrizeDetails.FOURTH_PRIZE;
import static lotto.util.PrizeDetails.SECOND_PRIZE;
import static lotto.util.PrizeDetails.THIRD_PRIZE;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.data.Lotto;
import lotto.model.data.WinningPortfolio;
import lotto.util.PrizeDetails;
import lotto.util.PrizeMoney;

/**
 * {@code OutputView}의 구현부다.
 */
public class OutputViewImpl implements OutputView {

    /**
     * 안내 문구를 출력한다.
     *
     * @param introduction 안내 문구
     */
    @Override
    public void printIntroduction(String introduction) {
        System.out.println(introduction);
    }

    /**
     * 예외 메시지를 출력한다.
     *
     * @param exceptionMessage 예외 메시지
     */
    @Override
    public void printException(String exceptionMessage) {
        System.out.println(ERROR_PREFIX.getMessage() + exceptionMessage);
    }

    /**
     * 로또 번호를 출력한다. delimeter와 접두, 접미 문자를 붙여 출력한다.
     *
     * @param purchasedLottos 출력할 로또들
     */
    @Override
    public void printLottos(List<Lotto> purchasedLottos) {
        System.out.println(String.format(AMOUNT_OF_LOTTO_PURCHASE.getIntroduction(), purchasedLottos.size()));
        for (Lotto purchasedLotto : purchasedLottos) {
            List<Integer> numbers = purchasedLotto.getNumbers(); // Assuming this method exists

            String formattedLottoNumbers = numbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(DELIMITER.getIntroduction() + " ",
                            LOTTO_PRINT_START.getIntroduction(),
                            LOTTO_PRINT_END.getIntroduction()));

            System.out.println(formattedLottoNumbers);
        }
    }

    /**
     * 당첨 내역을 출력한다. 5등부터 1등까지의 정보와 당첨 횟수를 출력한다.
     *
     * @param winningPortfolio 당첨 내역
     */
    @Override
    public void printWinningStatistics(WinningPortfolio winningPortfolio) {
        printIntroduction(WINNING_STATISTICS.getIntroduction());
        printIntroduction(SEPARATOR.getIntroduction());
        printNotBonusPrize(FIFTH_PRIZE.getPrize(), winningPortfolio.getCountOfPrize(FIFTH_PRIZE.getPrize()));
        printNotBonusPrize(FOURTH_PRIZE.getPrize(), winningPortfolio.getCountOfPrize(FOURTH_PRIZE.getPrize()));
        printNotBonusPrize(THIRD_PRIZE.getPrize(), winningPortfolio.getCountOfPrize(THIRD_PRIZE.getPrize()));
        printBonusPrize(SECOND_PRIZE.getPrize(), winningPortfolio.getCountOfPrize(SECOND_PRIZE.getPrize()));
        printNotBonusPrize(FIRST_PRIZE.getPrize(), winningPortfolio.getCountOfPrize(FIRST_PRIZE.getPrize()));
        printRateOfReturn(winningPortfolio.getRateOfReturn().rateOfReturn());
    }

    private void printNotBonusPrize(Integer prize, Integer count) {
        Integer countOfMatchedLottoNumbers = PrizeDetails.getCountOfMatchedLottoNumbers(prize);

        System.out.println(String.format(NOT_BONUS_PRIZE_LIST.getIntroduction(), countOfMatchedLottoNumbers,
                PrizeMoney.getFormattedPrizeMoney(prize), count));
    }


    private void printBonusPrize(Integer prize, Integer count) {
        Integer countOfMatchedLottoNumbers = PrizeDetails.getCountOfMatchedLottoNumbers(prize);

        System.out.println(String.format(BONUS_PRIZE_LIST.getIntroduction(), countOfMatchedLottoNumbers,
                PrizeMoney.getFormattedPrizeMoney(prize), count));
    }

    /**
     * 수익률을 출력한다. %를 붙이고 그룹화하여 출력 형식에 맞추고 소수점 첫째 자리까지 출력한다.
     *
     * @param rateOfReturn 수익률
     */
    @Override
    public void printRateOfReturn(Double rateOfReturn) {
        String formattedRateOfReturn = formatRateOfReturn(rateOfReturn);

        System.out.println(String.format(RATE_OF_RETURN.getIntroduction(), formattedRateOfReturn));
    }

    private String formatRateOfReturn(Double rateOfReturn) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setGroupingUsed(true);
        percentFormat.setMaximumFractionDigits(1);

        return percentFormat.format(rateOfReturn / 100.0);
    }
}
