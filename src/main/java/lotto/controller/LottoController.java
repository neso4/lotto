package lotto.controller;

import static lotto.constant.ConstantInteger.ZERO;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoTicketManager;
import lotto.domain.LottoTickets;
import lotto.domain.number.AutoNumberGenerator;
import lotto.domain.number.LottoNumberGenerator;

public class LottoController {

    private final LottoTicketManager lottoTicketManager;
    private final LottoNumberGenerator lottoNumberGenerator;
    private final LottoTickets lottoTickets;

    public LottoController(){
        lottoTicketManager = new LottoTicketManager();
        lottoNumberGenerator = new AutoNumberGenerator();
        lottoTickets = new LottoTickets();
    }

    public List<Lotto> buyTicket(String price) {
        Integer numberOfTickets = lottoTicketManager.countTickets(price);
        while (numberOfTickets-- > ZERO ){
            lottoTickets.setTicket(lottoNumberGenerator.getLottoNumbers());
        }
        return lottoTickets.getLottoTickets();
    }

    public void setLuckyNumber(Lotto luckyNumber){
        lottoTicketManager.getLuckyNumber(luckyNumber);
    }

    public void validateBonusNumber(Integer bonusNumber){
        lottoTicketManager.getBonusNumber(bonusNumber);
    }



}
