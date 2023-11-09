package lotto.core.iomanangers;

import static lotto.core.enums.WinningChartEnum.FIVE_AND_BONUS_MATCH;
import static lotto.core.enums.WinningChartEnum.FIVE_MATCH;
import static lotto.core.enums.WinningChartEnum.FOUR_MATCH;
import static lotto.core.enums.WinningChartEnum.SIX_MATCH;
import static lotto.core.enums.WinningChartEnum.THREE_MATCH;

import java.math.BigDecimal;
import java.util.List;
import lotto.core.lotto.LottoTicket;
import lotto.core.lotto.ScratchedLottoTicketList;

public class MessageManager {

    private record findCount(Integer threeMatchCount, Integer fourMatchCount, Integer fiveMatchCount,
                             Integer fiveAndBonusMatchCount, Integer sixMatchCount) {
    }

    public String OneLottoTicketMessage(LottoTicket lottoTicket) {
        List<Integer> lotto = lottoTicket.getNumbers();
        return (lotto.toString());
    }

    private static findCount getFindCount(ScratchedLottoTicketList scratchedLottoTicketList) {
        Integer threeMatchCount = scratchedLottoTicketList.getThreeMatchCount();
        Integer fourMatchCount = scratchedLottoTicketList.getFourMatchCount();
        Integer fiveMatchCount = scratchedLottoTicketList.getFiveMatchCount();
        Integer fiveAndBonusMatchCount = scratchedLottoTicketList.getFiveAndBonusMatchCount();
        Integer sixMatchCount = scratchedLottoTicketList.getSixMatchCount();
        return new findCount(threeMatchCount, fourMatchCount, fiveMatchCount, fiveAndBonusMatchCount,
                sixMatchCount);
    }

    public String rateOfReturnMessage(BigDecimal rateOfReturn) {
        return ("총 수익률은 " + rateOfReturn + "%입니다.");
    }

    public String winningNumberAskMessage() {
        return ("당첨 번호를 입력해 주세요.");
    }

    public String bonusNumberAskMessage() {
        return ("보너스 번호를 입력해 주세요.");
    }

    public String winningChartAnnounceMessage() {
        return ("당첨 통계" + System.lineSeparator() + "---");
    }

    public String purchaseAmountAskMessage() {
        return ("구입 금액을 입력해 주세요.");
    }

    public String QuantityAnnounceMessage(Integer NumberOfPurchase) {
        return (NumberOfPurchase + "개를 구매했습니다.");
    }

    public String winningChartMessage(ScratchedLottoTicketList scratchedLottoTicketList) {
        MessageManager.findCount findCount = getFindCount(scratchedLottoTicketList);
        return (THREE_MATCH.getDescription() + " - " + findCount.threeMatchCount() + "개") + System.lineSeparator()
                + (FOUR_MATCH.getDescription() + " - " + findCount.fourMatchCount() + "개") + System.lineSeparator()
                + (FIVE_MATCH.getDescription() + " - " + findCount.fiveMatchCount() + "개") + System.lineSeparator()
                + (FIVE_AND_BONUS_MATCH.getDescription() + " - " + findCount.fiveAndBonusMatchCount() + "개")
                + System.lineSeparator()
                + (SIX_MATCH.getDescription() + " - " + findCount.sixMatchCount() + "개");
    }

    public String winningChartAndRateOfReturnMessage(ScratchedLottoTicketList scratchedLottoTicketList,
                                                     BigDecimal rateOfReturn) {
        String winningChartMessage = this.winningChartMessage(scratchedLottoTicketList);
        String rateOfReturnMessage = this.rateOfReturnMessage(rateOfReturn);
        String announceMessage = this.winningChartAnnounceMessage();
        return announceMessage + System.lineSeparator() + winningChartMessage + System.lineSeparator()
                + rateOfReturnMessage;
    }
}
