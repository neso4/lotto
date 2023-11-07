package lotto.view;

import lotto.OutputMessage;
import lotto.domain.Lotto;
import lotto.domain.Prize;

import java.util.EnumMap;
import java.util.List;

public class OutputView {
    public void getAmount() {
        System.out.println(OutputMessage.AMOUNT);
    }

    public void ticketResult(int count, List<Lotto> lottoTickets){
        System.out.printf(OutputMessage.TICKET_BUY_SUCCESS, count);
        printLottoNumber(lottoTickets);
    }
    private void printLottoNumber(List<Lotto> lottoTickets){
        for (Lotto lotto : lottoTickets) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void getWinningNumbers() {
        System.out.println(OutputMessage.WINNING_NUMBERS);
    }

    public void getBonusNumber() {
        System.out.println(OutputMessage.BONUS_NUMBER);
    }

    public void printPrizeResult(EnumMap<Prize, Integer> prizeResults, double earnRate) {
        System.out.println(OutputMessage.PRIZE_RESULT);
        System.out.println(OutputMessage.PRIZE_RESULT_DELIMITER);
        System.out.printf(OutputMessage.FIFTH_PRIZE, prizeResults.getOrDefault(Prize.FIFTH, 0));
        System.out.printf(OutputMessage.FOURTH_PRIZE, prizeResults.getOrDefault(Prize.FOURTH, 0));
        System.out.printf(OutputMessage.THIRD_PRIZE, prizeResults.getOrDefault(Prize.THIRD, 0));
        System.out.printf(OutputMessage.SECOND_PRIZE, prizeResults.getOrDefault(Prize.SECOND, 0));
        System.out.printf(OutputMessage.FIRST_PRIZE, prizeResults.getOrDefault(Prize.FIRST, 0));
        System.out.printf(OutputMessage.EARN_RATE, earnRate);
    }
}
