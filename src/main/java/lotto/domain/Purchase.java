package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.constant.LottoConstants;
import lotto.vo.TicketCount;
import lotto.vo.TotalAmount;

public class Purchase {
    private final TicketCount ticketCount;

    private Purchase(TicketCount ticketCount) {
        this.ticketCount = ticketCount;
    }

    public static Purchase from(TotalAmount totalAmount) {
        TicketCount ticketCount = totalAmount.calculateTicketCount();
        return new Purchase(ticketCount);
    }

    public LottoTickets generateLottoTickets() {
        List<Lotto> tickets = new ArrayList<>();
        ticketCount.forEach(() -> {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoConstants.MIN_NUMBER,
                    LottoConstants.MAX_NUMBER, LottoConstants.LOTTO_COUNT);
            tickets.add(new Lotto(numbers));
        });
        return LottoTickets.from(tickets);
    }

    public int getTicketCount() {
        return ticketCount.getCount();
    }
}
