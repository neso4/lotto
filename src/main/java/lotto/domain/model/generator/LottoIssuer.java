package lotto.domain.model.generator;

import lotto.domain.model.lotto.PaidAmount;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lotto.LottoTickets;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoIssuer(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public LottoTickets issue(final PaidAmount paidAmount) {
        Integer numberOfTickets = getLottoTicketCount(paidAmount);

        List<Lotto> issuedLottos = new ArrayList<>();
        for (int issueCount = 0; issueCount < numberOfTickets; issueCount++) {
            issuedLottos.add(Lotto.from(lottoNumberGenerator.create()));
        }

        return new LottoTickets(issuedLottos);
    }

    private Integer getLottoTicketCount(final PaidAmount paidAmount) {
        return paidAmount.toNumberOfLottoTickets();
    }
}
