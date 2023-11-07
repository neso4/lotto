package lotto.output;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningInformation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputProcessor {
    private static final String NUMBER_OF_BOUGHT_PROMPT = "%d개를 구매했습니다.";
    private static final String DEFAULT_SEPARATOR = ", ";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String WINNING_INFORMATION_PROMPT = "%d개 일치%s (%s원) - %d개";
    private static final String SECOND_PLACE_ADDITIONAL_MESSAGE = ", 보너스 볼 일치";
    private static final String WINNING_INFORMATION_HEADER_PROMPT = "당첨 통계\n---";
    private static final String AMOUNT_PATTERN = "#,###";
    private static final String PROFITABILITY_PATTERN = "#,##0.0";
    private static final String PURCHASE_MONEY_INPUT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_PROMPT = "보너스 번호를 입력해 주세요.";
    private static final String PROFITABILITY_PROMPT = "총 수익률은 %s%%입니다.";
    public static final String ERROR_MESSAGE_HEADER = "[ERROR] ";
    private final OutputSender outputSender;

    public OutputProcessor(OutputSender outputSender) {
        this.outputSender = outputSender;
    }

    public void outputPurchaseMoneyInputMessage() {
        outputSender.send(PURCHASE_MONEY_INPUT_PROMPT);
    }

    public void outputWinningNumberInputMessage() {
        outputSender.send(WINNING_NUMBER_INPUT_PROMPT);
    }

    public void outputBonusNumberInputMessage() {
        outputSender.send(BONUS_NUMBER_INPUT_PROMPT);
    }

    public void outputNumberOfLottos(int numberOfLottos) {
        outputSender.send(String.format(NUMBER_OF_BOUGHT_PROMPT, numberOfLottos));
    }

    public void outputLotto(Lotto lotto) {
        List<String> numbers = lotto.getLottoNumbers().stream()
                .sorted()
                .map(String::valueOf)
                .toList();
        outputSender.send(LEFT_BRACKET +
                String.join(DEFAULT_SEPARATOR, numbers) +
                RIGHT_BRACKET);
    }

    public void outputWinningInformation(WinningInformation winningInformation) {
        outputSender.send(WINNING_INFORMATION_HEADER_PROMPT);
        outputWinningDetails(winningInformation.getWinningCounts());
        outputProfitability(winningInformation.getProfitability());
    }

    public void outputProfitability(double profitability) {
        DecimalFormat amountFormat = new DecimalFormat(PROFITABILITY_PATTERN);
        String formattedProfitability = amountFormat.format(profitability);
        outputSender.send(String.format(PROFITABILITY_PROMPT, formattedProfitability));
    }

    public void outputError(String errorMessage) {
        outputSender.send(ERROR_MESSAGE_HEADER + errorMessage);
    }

    public void outputNewLine() {
        outputSender.send("");
    }

    private void outputWinningDetails(Map<Rank, Integer> winningCounts) {
        Arrays.stream(Rank.values())
                .sorted(Comparator.comparingInt(Rank::getPrize))
                .filter(rank -> rank != Rank.LOSER)
                .forEach(rank -> outputWinningDetail(rank, winningCounts));
    }

    private String formatIntegerToAmount(int amount) {
        DecimalFormat amountFormat = new DecimalFormat(AMOUNT_PATTERN);
        return amountFormat.format(amount);
    }

    private void outputWinningDetail(Rank rank, Map<Rank, Integer> winningCounts) {
        String additionalMessage = "";
        if (rank == Rank.SECOND) {
            additionalMessage = SECOND_PLACE_ADDITIONAL_MESSAGE;
        }
        String formattedPrompt = String.format(
                WINNING_INFORMATION_PROMPT,
                rank.getMatchingNumberCount(),
                additionalMessage,
                formatIntegerToAmount(rank.getPrize()),
                winningCounts.get(rank)
        );
        outputSender.send(formattedPrompt);
    }
}
