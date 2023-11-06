package lotto.domain;

import static java.lang.String.format;
import static lotto.domain.enums.ConsoleMessage.LOTTO_NUMBER_MATCH;
import static lotto.domain.enums.ConsoleMessage.LOTTO_NUMBER_MATCH_WITH_BONUS_NUMBER;
import static lotto.domain.constants.SeparatorConstant.DECIMAL_FORMAT;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import lotto.domain.enums.WinningGrade;

public class RankingResult {

    private final Map<WinningGrade, Long> rankingResult;

    private RankingResult(final Map<WinningGrade, Long> rankingResult) {
        this.rankingResult = rankingResult;
    }

    public static RankingResult from(final Map<WinningGrade, Long> rankingResult) {
        return new RankingResult(rankingResult);
    }

    public List<String> receiveRankingResultInfo() {
        return rankingResult.keySet().stream()
                .map(this::receiveRankingMessage)
                .sorted()
                .toList();
    }

    private String receiveRankingMessage(final WinningGrade winningGrade) {
        if (winningGrade.incorrectFiveNumbersWithBonusNumber()) {
            return receiveRankingMessageFormat(LOTTO_NUMBER_MATCH.getMessage(), winningGrade);
        }
        return receiveRankingMessageFormat(LOTTO_NUMBER_MATCH_WITH_BONUS_NUMBER.getMessage(), winningGrade);
    }

    private String receiveRankingMessageFormat(final String message, final WinningGrade winningGrade) {
        return format(message,
                winningGrade.getMatchingCount(),
                receiveFormattedPrice(winningGrade),
                rankingResult.get(winningGrade));
    }

    private String receiveFormattedPrice(final WinningGrade winningGrade) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        return decimalFormat.format(winningGrade.getPrice());
    }

    public BigDecimal receiveProfitability(final Payment payment) {
        long result = rankingResult.keySet()
                .stream()
                .mapToLong(grade -> grade.getPrice() * rankingResult.get(grade))
                .sum();

        return payment.calculateProfitability(result);
    }
}
