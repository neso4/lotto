package lotto.constant.Message;

import lotto.constant.Lotto;
import lotto.constant.Sign;

public enum WinningStatusMessage {
    WINNING_STATUS_MESSAGE("당첨 통계"),
    FIRST_PRIZE_STATUS_MESSAGE(Lotto.NUMBER_NEEDED_FOR_FIRST + "개 일치 (" + Lotto.FIRST_PRIZE_MONEY.getLottoNumber() + "원)" + Sign.SEPARATOR + "%d개"),
    SECOND_PRIZE_STATUS_MESSAGE(Lotto.NUMBER_NEEDED_FOR_SECOND + "개 일치 (" + Lotto.SECOND_PRIZE_MONEY.getLottoNumber() + "원)" + Sign.SEPARATOR + "%d개"),
    THIRD_PRIZE_STATUS_MESSAGE(Lotto.NUMBER_NEEDED_FOR_THIRD + "개 일치 (" + Lotto.THIRD_PRIZE_MONEY.getLottoNumber() + "원)" + Sign.SEPARATOR + "%d개"),
    FOURTH_PRIZE_STATUS_MESSAGE(Lotto.NUMBER_NEEDED_FOR_FOURTH + "개 일치 (" + Lotto.FOURTH_PRIZE_MONEY.getLottoNumber() + "원)" + Sign.SEPARATOR + "%d개"),
    FIFTH_PRIZE_STATUS_MESSAGE(lotto.constant.Lotto.NUMBER_NEEDED_FOR_FIFTH + "개 일치 (" + Lotto.FIFTH_PRIZE_MONEY.getLottoNumber() + "원)" + Sign.SEPARATOR + "%d개"),
    TOTAL_RETURN_MESSAGE("총 수익률은 %s" + Sign.PERCENT + "입니다.");

    private final String winningStatusMessage;

    WinningStatusMessage(String winningStatusMessage) {
        this.winningStatusMessage = winningStatusMessage;
    }

    public String getWinningStatusMessage() {
        return winningStatusMessage;
    }

}
