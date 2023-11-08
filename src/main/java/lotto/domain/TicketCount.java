package lotto.domain;

import lotto.constants.LottoConstants;
import lotto.dto.TicketCountDto;

public class TicketCount {
    private int ticketCount;

    private TicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public static TicketCount create(PurchaseAmount purchaseAmount) {
        return new TicketCount(calTicketCount(purchaseAmount));
    }

    private static int calTicketCount(PurchaseAmount purchaseAmount) {
        return purchaseAmount.getPurchaseAmount() / LottoConstants.THOUSAND.getNumber();
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public TicketCountDto toDto() {
        return new TicketCountDto(ticketCount);
    }
}
