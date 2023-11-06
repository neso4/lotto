package lotto.domain;

import lotto.ErrorMessage;

public class User {
    private static final int TICKET_PRICE = 1000;

    private int amount;
    private LottoTicket lottoTicket;

    public User(int amount) {
        this.amount = amount;
        this.lottoTicket = buyTicket(amount);
    }

    private LottoTicket buyTicket(int amount){
        int count = calculateTicketCount(amount);
        return new LottoTicket(count);
    }

    private int calculateTicketCount(int amount){
        if (amount%TICKET_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TICKET_AMOUNT.getMessage());
        }
        return amount/TICKET_PRICE;
    }
}
