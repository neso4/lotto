package lotto.service;

import static lotto.constant.NumberConstant.PERCENT;
import static lotto.constant.NumberConstant.SAME_COUNT_FIVE;
import static lotto.constant.NumberConstant.ZERO;

import java.util.List;
import lotto.constant.NumberConstant;
import lotto.domain.LottoTicket;
import lotto.domain.Result;

public class LottoService {


    private final RecordService recordService = new RecordService();
    private final YieldService yieldService = new YieldService();

    public Long calculateMoney(List<LottoTicket> lottoTickets, List<Integer> lottoWinNumbers,
        Integer bonusNumber) {
        for (int ticektNumber = ZERO.getNumber(); ticektNumber < lottoTickets.size(); ticektNumber++) {
            for (Integer lottoWinNumber : lottoWinNumbers) {
                nowLottoTicketContainWinNumber(lottoTickets, lottoWinNumber, ticektNumber);
            }
            nowLottoTicketHasBonusNumber(lottoTickets, bonusNumber, ticektNumber);
        }
        recordService.recordResult(lottoTickets);
        return calculateFinalMoney();
    }

    public double calculateYield(Long result, Long money) {
        return yieldService.calculateYield(result,money);
    }

    private long calculateFinalMoney() {
        long totalMoney = NumberConstant.ZERO.getNumber();
        for (Result value : Result.values()) {
            totalMoney += (long) value.getMoney() * value.getResultCount();
        }
        return totalMoney;
    }

    private void nowLottoTicketHasBonusNumber(List<LottoTicket> lottoTickets, Integer bonusNumber,
        int ticketNumber) {
        if (lottoTickets.get(ticketNumber).getSameCount() == SAME_COUNT_FIVE.getNumber() && lottoTickets.get(ticketNumber).getNumbers()
            .contains(bonusNumber)) {
            lottoTickets.get(ticketNumber).hasBonus();
        }
    }

    private void nowLottoTicketContainWinNumber(List<LottoTicket> lottoTickets,
        Integer lottoWinNumber, int ticketNumber) {
        if (lottoTickets.get(ticketNumber).getNumbers().contains(lottoWinNumber)) {
            lottoTickets.get(ticketNumber).addSameCount();
        }
    }

}
